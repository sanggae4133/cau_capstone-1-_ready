package service;

import repository.MemoryMemberRepository;
import java.util.List;
import domain.Member;

import java.util.Optional;

public class Memberservice {
    
    private final MemoryMemberRepository memberRepository=new MemoryMemberRepository();

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
