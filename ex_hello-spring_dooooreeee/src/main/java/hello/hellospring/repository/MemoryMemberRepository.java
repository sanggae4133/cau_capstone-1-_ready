package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;



public class MemoryMemberRepository implements MemberRepository{
    private static Map<Long,Member> store=new HashMap<>(); //원래는 동시성 문제때문에 concurrent hash맵을 써야함
    private static long sequence=0L; //이것도 동시성 문제로 다른 것으로 처리하는 게 맞음

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(),member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //null이 반환될 수 있기때문에 optional.ofNullable로 처리한다.
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member->member.getName().equals(name))
                .findAny();
        //루프를 돌면서 name이랑 같으면 필터링되고 리턴
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
    public void clearStore(){
        store.clear();
    }
}
