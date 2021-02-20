package newbie.jun.app.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import newbie.jun.app.common.JwtUtil;
import newbie.jun.app.controller.dto.FollowDto;
import newbie.jun.app.controller.dto.Response;
import newbie.jun.app.model.Member;
import newbie.jun.app.service.FollowService;
import newbie.jun.app.service.FollowServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@Slf4j
public class FollowController {

    private JwtUtil jwtUtil;
    private FollowService followService;

    @PostMapping("/follow")
    public Response follow(@RequestBody FollowDto.FollowReq followReq,HttpServletRequest req){
        String token = req.getHeader("Authorization");
        String email =  jwtUtil.getUseremail(token);
        try {
            followService.Follow(email,followReq.getFolloweeId());
            return new Response("success", "팔로우됨", null);
        }catch (Exception e){
            return new Response("fail","팔로우 실패",null);
        }
    }
    @PostMapping("/unfollow")
    public Response unfollow(@RequestBody FollowDto.UnfollowReq unfollowReq, HttpServletRequest req){
        String token = req.getHeader("Authorization");
        String email =  jwtUtil.getUseremail(token);
        try{
            followService.UnFollow(email, unfollowReq.getFolloweeId());
        }catch (Exception e){
            return new Response("fail","언팔로우 실패",null);
        }
        return new Response("success","언팔로우 성공",null);
    }
    @PostMapping("/followings")
    public Response getFollowerList(HttpServletRequest req){
        String token = req.getHeader("Authorization");
        String email =  jwtUtil.getUseremail(token);
        try {
            log.info("컨트롤러 try문 안");
            Stream<Member> followersStream= followService.GetFollowers(email);
            List<Member> res = followersStream.collect(Collectors.toList());
            return new Response("success","팔로잉 리스트 요청 성공",res);
        }catch (Exception e){
            return new Response("success","팔로잉 리스트 요청 실패",null);
        }
    }
}
