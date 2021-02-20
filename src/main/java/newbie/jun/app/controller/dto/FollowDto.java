package newbie.jun.app.controller.dto;

import lombok.*;

import javax.validation.Valid;

public class FollowDto {
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class FollowReq {
        private Long followeeId;
    }
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class UnfollowReq {
        private Long followeeId;
    }
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class followerRes {
        private Long followeeId;
    }
}
