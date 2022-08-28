package chartBoard.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {

    //메서드이름 정확히 표기하기(exist -> exists)
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Optional<Member> findByUsername(String username);

    Optional<Member> findByEmail(String email);
}
