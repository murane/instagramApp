package newbie.jun.app.controller;

import newbie.jun.app.common.CookieUtil;
import newbie.jun.app.common.JwtUtil;
import newbie.jun.app.common.RedisUtil;
import newbie.jun.app.controller.dto.MemberDto;
import newbie.jun.app.controller.dto.Response;
import newbie.jun.app.model.Member;
import newbie.jun.app.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private CookieUtil cookieUtil;
    @Autowired
    private RedisUtil redisUtil;
    @PostMapping("/signin")
    public Response signin(@RequestBody MemberDto.SignInReq signInReq,
                           HttpServletRequest req, HttpServletResponse res){
        try{
            String email=signInReq.getEmail();
            String password=signInReq.getPassword();
            Member member=authService.SignIn(email,password).orElseThrow(
                    ()->new RuntimeException("로그인 실패")
            );
            String token=jwtUtil.generateToken(member);
            String refreshJwt=jwtUtil.generateRefreshToken(member);
            Cookie acccessToken=cookieUtil.createCookie(JwtUtil.ACCESS_TOKEN_NAME,token);
            Cookie refreshToken=cookieUtil.createCookie(JwtUtil.REFRESH_TOKEN_NAME,refreshJwt);
            redisUtil.setDataExpire(refreshJwt, member.getName(), JwtUtil.REFRESH_TOKEN_VALIDATION_SECOND);
            res.addCookie(acccessToken);
            res.addCookie(refreshToken);
            return new Response("success", "로그인 성공", token);
        }catch (Exception e){
            return new Response("error","로그인 실패",e.getMessage());
        }

    }
    @PostMapping("/signup")
    public Response signup(@RequestBody MemberDto.SignUpReq signUpReq){
        try{
            Member member=Member.builder()
                    .email(signUpReq.getEmail())
                    .name(signUpReq.getName())
                    .nickname(signUpReq.getNickname())
                    .password(signUpReq.getPassword())
                    .build();
            authService.SignUp(member);
            return new Response("success","회원가입 성공",null);
        }catch (Exception e){
            return new Response("fail","회원가입 실패",e.getMessage());
        }
    }
}
