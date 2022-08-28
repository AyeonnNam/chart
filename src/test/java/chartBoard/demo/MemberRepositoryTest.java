package chartBoard.demo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
public class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;

    @Autowired EntityManager em;

    private void clear(){
        em.flush();
        em.clear();
    }

    /* em.flush, em.clear 둘다 해줬는데 test2,3에서
    org.hibernate.PropertyValueException: not-null property references a null or transient value
    오류가 떠서 수정, 근데 아직도 이유 모름
    * */
    @AfterEach
    private void after(){
       //em.flush();
        em.clear();
    }



    /*
    * 회원 저장
    회원 저장시 이멜이 없으면 오류
    회원 저장시 이름이 없으면 오류
    회원 저장시 중복된 아이디가 있으면 오류
    회원 수정
    회원 삭제
    existsByUsername이 잘 작동하는지
    username으로 회원 찾기 기능이 잘 작동하는지
*/
    @Test
    public void 회원저장성공() throws Exception{

        Member member =  Member.builder().email("boalove294@naver.com").password("12345678").username("askMan").build();

        Member saveMember = memberRepository.save(member);

        Member findMember = memberRepository.findById(saveMember.getId()).orElseThrow(()->new RuntimeException("회원이 없슴"));

        Assertions.assertThat(saveMember).isSameAs(findMember);
    }

    @Test
    public void 이메일이_없으면_오류() throws Exception {
        //given
        Member member =  Member.builder().password("12345678").username("askMan").build();


        assertThrows(Exception.class, () -> memberRepository.save(member));
    }

    @Test
    public void 이름이_없으면_오류() throws Exception {


        Member member = Member.builder().email("boalove294@naver.com").password("123456767").build();

        assertThrows(Exception.class, () -> memberRepository.save(member));
    }


    @Test
    public void 이메일이_중복이면_오류() throws Exception {

       Member member1 =
                Member.builder().email("boalove294@naver.com").password("12345678").username("askMan").build();

       Member member2 =
                Member.builder().email("boalove294@naver.com").password("akdlskda").username("dksks").build();


        memberRepository.save(member1);

        // clear();

        assertThrows(Exception.class, ()-> memberRepository.save(member2),"no member");


    }

    @Test
    public void 회원정보수정() throws Exception {

        Member member1 =
                Member.builder().email("boalove294@naver.com").password("12345678").username("askMan").build();


        Member saveMember = memberRepository.save(member1);

        saveMember.updateUsername("꾸준함");
        saveMember.updateEmail("adklskdl@naver.com");
        saveMember.updatePassword("398489ejd");

        Member findMember = memberRepository.findById(saveMember.getId()).orElseThrow(() -> new RuntimeException("그런거 없음"));

        Assertions.assertThat(findMember.getUsername()).isEqualTo("꾸준함");
        Assertions.assertThat(findMember.getEmail()).isEqualTo("adklskdl@naver.com");
        Assertions.assertThat(findMember.getPassword()).isEqualTo("398489ejd");
    }

    @Test
    public void 회원삭제() throws Exception {

    }

}
