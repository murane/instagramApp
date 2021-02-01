package newbie.jun.app.controller;

import newbie.jun.app.model.Member;
import newbie.jun.app.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<Member> signin(Member member){
        return ResponseEntity.ok(member);
    }
    @PostMapping("/signup")
    public void signup(){

    }
}
