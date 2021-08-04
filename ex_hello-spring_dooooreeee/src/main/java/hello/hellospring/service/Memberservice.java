package hello.hellospring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hello.hellospring.repository.MemberRepository;

import java.util.List;
import domain.Member;

import java.util.Optional;


public class Memberservice {
    /*
    private final MemoryMemberRepository memberRepository=new MemoryMemberRepository();
    매번 다른 객체를 생성하지만 같은 store을 사용해야한다. store가 static이라 지금은 공유하지만
    안썻을 경우 공유하기위해선 아래처럼 바꾸어 주어야한다.test클래스와 그냥클래스는 같은 store가능
    */
    private final MemberRepository memberRepository;


    public Memberservice(MemberRepository memberRepository) { //생성자 주입
        this.memberRepository=memberRepository;
    }


    /**
     * 회원가입
     */
    public Long join(Member member){
        //같은 이름이 있는 중복 회원X
        validateDuplicateMember(member); //중복회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
           .ifPresent(m->{ //result가 Optional로 감싸져있기 때문에 가능한 여러메소들중 하나
               throw new IllegalStateException("이미 존재하는 회원입니다.");
           });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findeById(memberId);
    }
}
