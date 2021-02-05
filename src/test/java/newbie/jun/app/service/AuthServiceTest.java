package newbie.jun.app.service;

import newbie.jun.app.model.Member;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@Transactional
public class AuthServiceTest {
    private AuthServiceImpl authService;
    @Test
    void 회원_가입테스트(){
        //given
        Member member = Member.builder()
                .email("murane@naver.com")
                .nickname("바보")
                .name("정준수")
                .password("12345")
                .build();
        //when
        //authService.SignUp(user);
        //then
        //Assertions.assertThat()
    }
}
