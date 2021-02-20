package newbie.jun.app.common.security;

import newbie.jun.app.common.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.persistence.Access;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

public class SecurityAuthenticationFilter extends OncePerRequestFilter {

    JwtUtil jwtUtil;
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Enumeration<String> res = request.getHeaderNames();
        String token = (String)res.nextElement();
        jwtUtil.getUseremail(token);
        UserDetails authentication = customUserDetailsService.loadUserByUsername(token);
        UsernamePasswordAuthenticationToken auth= new UsernamePasswordAuthenticationToken(authentication.getUsername(),null,null);
        SecurityContextHolder.getContext().setAuthentication(auth);
        filterChain.doFilter(request,response);
    }
}
