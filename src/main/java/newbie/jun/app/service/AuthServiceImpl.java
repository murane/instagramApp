package newbie.jun.app.service;

import lombok.extern.slf4j.Slf4j;
import newbie.jun.app.model.Member;
import newbie.jun.app.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
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
    public Member SignUp(Member member) {
        if(EmailDuplicationCheck(member)){
            throw new RuntimeException();
        }
        if(NicknameDuplicationCheck(member)){
            throw new RuntimeException();
        }
        String password=member.getPassword();
        member.setPassword(passwordEncoder.encode(password));
        return memberRepository.save(member);
    }
    private boolean EmailDuplicationCheck(Member member){
        Optional<Member> result = memberRepository.findByEmail(member.getEmail());
        return result.isPresent();
    }
    private boolean NicknameDuplicationCheck(Member member){
        Optional<Member> result = memberRepository.findByNickname(member.getNickname());
        return result.isPresent();
    }
    @Override
    public Optional<Member> SignIn(String email, String password){
        Optional<Member> member = memberRepository.findByEmail(email);
        if (member.isEmpty()){
            throw new RuntimeException("존재하지 않는 이메일입니다");
        }
        else {
            Member loginMember = member.get();
            if(!passwordEncoder.matches(password,loginMember.getPassword())) {
                log.info(loginMember.getPassword());
                log.info(password);
                throw new RuntimeException("비밀번호가 틀렸습니다.");
            }
        }
        return member;
    }
}
