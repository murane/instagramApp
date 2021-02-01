package newbie.jun.app.common;

import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class CookieUtil {
    public Cookie createCookie(String cookieName, String value){
        Cookie token = new Cookie(cookieName,value);
        token.setHttpOnly(true);
        token.setMaxAge((int)JwtUtil.TOKEN_VALIDATION_SECOND);
        token.setPath("/");
        return token;
    }
    public Optional<Cookie> getCookie(HttpServletRequest req, String cookieName){
        return Arrays.stream(req.getCookies())
                .filter(x -> x!=null && x.getName().equals(cookieName))
                .findAny();
    }
}
