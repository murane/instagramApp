package newbie.jun.app.repository;

import newbie.jun.app.model.Follow;
import newbie.jun.app.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Column;
import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<Follow,Long> {
    Optional<Follow> findByFollowerAndFollowee(Member Follower, Member Followee);
}
