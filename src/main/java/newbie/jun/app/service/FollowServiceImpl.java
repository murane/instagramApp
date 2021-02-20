package newbie.jun.app.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import newbie.jun.app.model.Follow;
import newbie.jun.app.model.Member;
import newbie.jun.app.repository.FollowRepository;
import newbie.jun.app.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Slf4j
@Service
@AllArgsConstructor
public class FollowServiceImpl implements FollowService{

    private FollowRepository followRepository;
    private MemberRepository memberRepository;
    @Override
    @Transactional
    public void Follow(String FollowerEmail, Long followeeId) {
        Optional<Member> Mem = memberRepository.findByEmail(FollowerEmail);
        Member follower =Mem.orElseThrow(() -> new RuntimeException("존재하지 않는 팔로워입니다."));
        Member followee = memberRepository.findById(followeeId).orElseThrow(() -> new RuntimeException("존재하지 않는 팔로이입니다."));
        if(IsFollowing(follower, followee)){
            return ;
        }
        Follow follow = new Follow(follower, followee);
        followRepository.save(follow);
    }

    @Override
    @Transactional
    public void UnFollow(String FollowerEmail, Long followeeId) {
        Optional<Member> Mem = memberRepository.findByEmail(FollowerEmail);
        Member follower =Mem.orElseThrow(() -> new RuntimeException("존재하지 않는 팔로워입니다."));
        Member followee = memberRepository.findById(followeeId).orElseThrow(() -> new RuntimeException("존재하지 않는 팔로이입니다."));
        if(!IsFollowing(follower, followee)){
            return;
        }
        Optional<Follow> getFollow =  followRepository.findByFollowerAndFollowee(follower,followee);
        Follow follow = getFollow.orElseThrow(()->new RuntimeException("팔로우가 존재하지 않습니다."));
        log.info(follow.getId()+", "+follow.getFollowee()+", "+follow.getFollower());
        followRepository.delete(follow);
    }

    //TODO:21.02.18.stream으로 for문 최적화할것
    @Override
    public Boolean IsFollowing(Member follower, Member followee) {
        try{
            Optional<Follow> follow =  followRepository.findByFollowerAndFollowee(follower,followee);
            follow.orElseThrow(()->new RuntimeException("존재하지 않는 팔로우 관계입니다."));
            return true;
        }catch (Exception e){
            log.info(e.getMessage());
            return false;
        }
    }

    @Override
    public Stream<Member> GetFollowers(String FolloweeEmail) {
        Stream<Member> followersStream=Stream.empty();
        try {
            Member member =memberRepository.findByEmail(FolloweeEmail)
                    .orElseThrow(() -> new RuntimeException("존재하지 않는 사용자입니다."));
            log.info(member.getName());
            Set<Follow> followings=  member.getFollowings();
            Set<Follow> test=member.getFollowers();
            log.info(">>"+test.size());
            log.info(">>"+followings.size());
            for(Follow each : followings){
                log.info("-> "+each.getFollowee().getName());
            }
            followersStream=followings.stream().map(Follow::getFollowee);
            log.info("try문 안 :" + followersStream.toString());
        }catch (Exception e){
            log.info(e.getMessage());
        }
        log.info("try문 밖 :" + followersStream.toString());
        return followersStream;
    }
}
