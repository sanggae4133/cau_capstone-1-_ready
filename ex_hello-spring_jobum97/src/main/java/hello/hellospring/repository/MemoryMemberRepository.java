package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository {

    private  static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId((++sequence));
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
        //NULL이 반환될 가능성이 있으면 Optional.ofNullable 로 감싼다
    }

    @Override
    public Optional<Member> findByName(String name) {
        //반복문 람다
        return store.values().stream() //store에서
                .filter(member -> member.getName().equals(name)) //name과 같은 것만 나오게 filtering
                .findAny(); //찾으면 반환
        //끝까지 없으면 위의 Optional에 따라 NULL 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
