package service;

import domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.MemoryMemberRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MemberserviceTest {
    Memberservice memberservice;
    MemoryMemberRepository memberRepository=new MemoryMemberRepository();
    @BeforeEach
    public void beforeEach(){
        memberRepository=new MemoryMemberRepository();
        memberservice=new Memberservice(memberRepository);//같은 memberRepository사용가능
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given
        Member member=new Member();
        member.setName("spring");

        //when
        Long saveId=memberservice.join(member);
        //then
        Member findMember=memberservice.findOne(saveId).get();
        Assertions.assertThat(findMember.getName()).isEqualTo(member.getName());
    }

    @Test
    public void 중복_회원_처리(){
        //given
        Member member1=new Member();
        member1.setName("spring");

        Member member2=new Member();
        member2.setName("spring");

        //when
        memberservice.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberservice.join(member2));
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
  /*      try {
            memberservice.join(member2);
            fail("예외가 발생해야 합니다.");
        }catch (IllegalStateException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.zxc");
        }
    */
        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}