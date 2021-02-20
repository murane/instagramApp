package newbie.jun.app.service;

import newbie.jun.app.controller.dto.MemberDto;
import newbie.jun.app.model.Member;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface AuthService {
    Member SignUp(MemberDto.SignUpReq signUpReq);
    Optional<Member> SignIn(String email, String password);
}
