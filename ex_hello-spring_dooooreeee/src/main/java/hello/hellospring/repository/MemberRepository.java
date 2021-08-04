package hello.hellospring.repository;
import java.util.List;
import domain.Member;

import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);  //회원을 저장하면 저장된 회원 반환
    Optional<Member> findeById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
