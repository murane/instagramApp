package newbie.jun.app.service;

import newbie.jun.app.model.Member;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public interface FollowService {
    public void Follow(String FollowerEmail, Long followeeId);
    public void UnFollow(String FollowerEmail, Long followeeId);
    public Boolean IsFollowing(Member follower, Member followee);
    public Stream<Member> GetFollowers(String FolloweeEmail);
}
