package newbie.jun.app.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private AuthService authService;
    private JwtUtil jwtUtil;
    private CookieUtil cookieUtil;
    private RedisUtil redisUtil;

    @PostMapping("/signin")
    public Response signin(@RequestBody MemberDto.SignInReq signInReq,
                           HttpServletRequest req, HttpServletResponse res){
        try{
            String token=authService.SignIn(signInReq);
            return new Response("success", "로그인 성공", token);
        }catch (Exception e){
            return new Response("fail","로그인 실패",e.getMessage());
        }
    }
    @PostMapping("/signup")
    public Response signup(@RequestBody MemberDto.SignUpReq signUpReq){
        try{
            authService.SignUp(signUpReq);
            return new Response("success","회원가입 성공",null);
        }catch (Exception e){
            return new Response("fail","회원가입 실패",e.getMessage());
        }
    }
}
