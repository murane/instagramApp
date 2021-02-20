package newbie.jun.app.service;

import newbie.jun.app.controller.dto.MemberDto;
import newbie.jun.app.model.Member;
import newbie.jun.app.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.*;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {
    @InjectMocks
    private AuthService authService;
    @Mock
    private MemberRepository memberRepository;
    @Test
    public void 회원_가입테스트(){
        //given
        when()
        MemberDto.SignUpReq signUpReq = MemberDto.SignUpReq.builder()
                .name("junsu")
                .email("murane@naver.com")
                .nickname("test")
                .password("1234")
                .build();
        //when
        Member RegisteredMember = authService.SignUp(signUpReq);
        //then
        Assertions.assertThat(RegisteredMember.getEmail()).isEqualTo("murane@naver.com");
    }
}
