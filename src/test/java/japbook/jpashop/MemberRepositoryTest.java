package japbook.jpashop;

import japbook.jpashop.domain.Member;
import japbook.jpashop.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)//junit에게 스프링 관련으로 테스트 한다고 알려줌
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test//처음에 오류가 난다. 엔티티 매니저를 통한 모든 데이터 변경은 트랜잭션 안에서 이뤄져야하기 떄문
    @Transactional //테스트에 있으면 db 롤백함
    @Rollback(value = false)
    public void testMember() throws Exception
    {
        /*
        //given
        Member member = new Member();
        member.setUsername("memberA");

        //when
       / Long saveId = memberRepository.save(member);
        Member findMember = memberRepository.find(saveId);

        //then
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        Assertions.assertThat(findMember).isEqualTo(member);//같은 영속성 안에서 식별자 같으면 같은 엔티티로 인식


         */

    }
}