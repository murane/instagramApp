package newbie.jun.app.service;

import newbie.jun.app.model.Member;

import java.util.Optional;

public interface AuthService {
    Member SignUp(String email, String password, String name, String nickname);
    Optional<Member> SignIn(String email, String password);
}
