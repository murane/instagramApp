package newbie.jun.app.repository;

import newbie.jun.app.model.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.Optional;

@DataJpaTest
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;
    @Test
    public void 유저_저장하기(){
        //given
        Member testMember = Member.builder()
                .email("murane@naver.com")
                .nickname("바보")
                .name("정준수")
                .password("12345")
                .build();
        memberRepository.save(testMember);
        //when
        Optional<Member> target= memberRepository.findByEmail("murane@naver.comab");
        //then
        Assertions.assertThat(target).isNotNull();
    }
}
