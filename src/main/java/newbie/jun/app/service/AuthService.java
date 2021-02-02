package newbie.jun.app.service;

import newbie.jun.app.controller.dto.MemberDto;
import newbie.jun.app.model.Member;

import java.util.Optional;

public interface AuthService {
    Member SignUp(Member member);
    Optional<Member> SignIn(String email, String password);
}
