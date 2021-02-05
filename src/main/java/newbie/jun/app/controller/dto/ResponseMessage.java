package newbie.jun.app.controller.dto;

public class ResponseMessage {
    public static final String LOGIN_SUCCESS = "로그인 성공";
    public static final String LOGIN_FAIL = "로그인 실패";
    public static final String REGISTER_FAIL_DUPLICATE_EMAIL="회원가입 실패 이메일 중복";
    public static final String REGISTER_FAIL_DUPLICATE_NICKNAME="회원가입 실패 닉네임 중복";
    public static final String INTERNAL_SERVER_ERROR = "서버 내부 에러";
    public static final String DB_ERROR = "데이터베이스 에러";
}
