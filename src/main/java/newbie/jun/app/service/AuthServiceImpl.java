package newbie.jun.app.service;

import com.sun.xml.bind.v2.TODO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import newbie.jun.app.common.JwtUtil;
import newbie.jun.app.common.RedisUtil;
import newbie.jun.app.common.security.CustomUserDetails;
import newbie.jun.app.common.security.CustomUserDetailsService;
import newbie.jun.app.controller.dto.MemberDto;
import newbie.jun.app.model.Member;
import newbie.jun.app.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

//TODO:: signIn , signUp 서비스 클래스 분리
@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private MemberRepository memberRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private CustomUserDetailsService customUserDetailsService;
    private JwtUtil jwtUtil;
    private RedisUtil redisUtil;

    @Override
    public Member SignUp(MemberDto.SignUpReq signUpReq) {
        Member member = Member.builder()
                .name(signUpReq.getName())
                .nickname(signUpReq.getNickname())
                .password(passwordEncoder.encode(signUpReq.getPassword()))
                .email(signUpReq.getEmail())
                .build();
        EmailDuplicationCheck(member);
        NicknameDuplicationCheck(member);
        return memberRepository.save(member);
    }
    //TODO:2021.02.16. RuntimeException -> 정확한 예외로 구체화 시켜주기
    private void EmailDuplicationCheck(Member member){
        Optional<Member> result = memberRepository.findByEmail(member.getEmail());
        if(result.isPresent()){
            throw new RuntimeException("이메일중복");
        }
    }
    private void NicknameDuplicationCheck(Member member){
        Optional<Member> result = memberRepository.findByNickname(member.getNickname());
        if(result.isPresent()){
            throw new RuntimeException("닉네임중복");
        }
    }

    @Override
    public String SignIn(MemberDto.SignInReq signInReq) throws Exception{
        auth(signInReq.getEmail(), signInReq.getPassword());
        final CustomUserDetails customUserDetails =
                customUserDetailsService.loadUserByUsername(signInReq.getEmail());
        final String accessToken = jwtUtil.generateToken(customUserDetails.getMember());
        final String refreshToken = jwtUtil.generateRefreshToken(customUserDetails.getMember());
        redisUtil.setDataExpire(refreshToken, signInReq.getEmail(), JwtUtil.REFRESH_TOKEN_VALIDATION_SECOND);
        return accessToken;
    }
    private void auth(String email,String password) throws Exception{
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,password));
        }catch (Exception e){
            throw new Exception("로그인 실패");
        }
    }
}
