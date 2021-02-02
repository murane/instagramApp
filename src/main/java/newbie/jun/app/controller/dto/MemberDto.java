package newbie.jun.app.controller.dto;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

public class MemberDto {
    @Getter
    @NoArgsConstructor(access = AccessLevel.PUBLIC)
    public static class SignInReq {
        @Valid
        public String email;
        @Valid
        public String password;
        @Builder
        public SignInReq(String email,String password){
            this.email=email;
            this.password=password;
        }
    }
    @Getter
    public static class SignInRes {
        private final String accessToken;
        private final String refreshToken;
        @Builder
        public SignInRes(String accessToken, String refreshToken){
            this.accessToken=accessToken;
            this.refreshToken=refreshToken;
        }
    }
    @Getter
    @NoArgsConstructor(access = AccessLevel.PUBLIC)
    public static class SignUpReq{
        public String email;
        public String name;
        public String nickname;
        public String password;
        @Builder
        public SignUpReq(String email,String name,String nickname,String password){
            this.email=email;
            this.name=name;
            this.nickname=nickname;
            this.password=password;
        }
    }
    @Getter
    public static class SignUpRes{

    }

}
