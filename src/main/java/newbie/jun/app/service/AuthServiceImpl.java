package newbie.jun.app.service;

import com.sun.xml.bind.v2.TODO;
import lombok.extern.slf4j.Slf4j;
import newbie.jun.app.controller.dto.MemberDto;
import newbie.jun.app.model.Member;
import newbie.jun.app.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService{
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

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
    public Optional<Member> SignIn(String email, String password){
        Optional<Member> member = memberRepository.findByEmail(email);
        Member loginMember = member.orElseThrow(() ->new RuntimeException("존재하지 않는 이메일입니다."));
        if(!passwordEncoder.matches(password,loginMember.getPassword())) {
            throw new RuntimeException("비밀번호가 틀렸습니다.");
        }
        return member;
    }
}
