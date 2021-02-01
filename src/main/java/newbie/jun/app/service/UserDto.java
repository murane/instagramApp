package newbie.jun.app.service;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserDto {
    @Getter
    @NoArgsConstructor(access = AccessLevel.PUBLIC)
    public static class SignInReq {
        public String email;
        public String password;
        @Builder
        public SignInReq(String email,String password){
            this.email=email;
            this.password=password;
        }
    }
    @Getter
    public static class SignInRes {

    }
    @Getter
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
