package newbie.jun.app.model;


import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

public class MemberTest {
    @Test(expected=IllegalArgumentException.class)
    public void 멤버_빌더_패스워드_누락(){
        Member.builder()
                .email("murane@naver.com")
                .password("")
                .name("테스트")
                .nickname("테스트")
                .build();
    }
    @Test(expected=IllegalArgumentException.class)
    public void 멤버_빌더_이메일_누락(){
        Member.builder()
                .email("")
                .password("12341234")
                .name("테스트")
                .nickname("테스트")
                .build();
    }
    @Test(expected=IllegalArgumentException.class)
    public void 멤버_빌더_인자_누락(){
        Member.builder()
                .build();
    }
}
