package newbie.jun.app.common.security;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import newbie.jun.app.model.Member;
import newbie.jun.app.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private MemberRepository memberRepository;
    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> member=memberRepository.findByEmail(username);
        Member mem = member.orElseThrow(() -> new RuntimeException("존재하지 않음"));
        return new CustomUserDetails(mem);
    }
}
