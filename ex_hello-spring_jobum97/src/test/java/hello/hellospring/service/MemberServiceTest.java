package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

//    MemberService memberService = new MemberService();
//    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    //여기서 new 로 새로 선언하면 기존의 것과 다른 새로운 객체임 (예시에선 static List 라 문제 없지만)
    //내용물이 달라지는 등의 문제 발생 가능성 있음
    //같은 인스턴스를 쓰게 하기 위한 변경
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach //각 Test 돌아가기전에 동작
   public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }


    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("hello");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

/*        try{
            memberService.join(member2);
            fail();
        }catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/


        //then
    }

    @Test
    void 회원찾기() {
    }

    @Test
    void 사람찾기() {
    }
}