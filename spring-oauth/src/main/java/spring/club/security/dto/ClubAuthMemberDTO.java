package spring.club.security.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import spring.club.entity.ClubMember;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;

import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;

@Log4j2
@Getter
@Setter
@ToString
public class ClubAuthMemberDTO extends User implements OAuth2User {

    private String email;

    private String password;

    private String name;

    private boolean fromSocial;

    private Map<String, Object> attr;

    public ClubAuthMemberDTO(String username, String password, boolean fromSocial,
                             Collection<? extends GrantedAuthority> authorities, Map<String, Object> attr) {
        this(username,password, fromSocial, authorities);
        this.attr = attr;
    }

    public ClubAuthMemberDTO(String username, String password, boolean fromSocial, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.email = username;
        this.password = password;
        this.fromSocial = fromSocial;

    }
    public ClubMember toEntity(){
        return ClubMember.builder()
                .name(name)
                .password(new BCryptPasswordEncoder().encode(password))
                .email(email)
                .fromSocial(fromSocial)
                .build();
    }
//Oauth2User는 Map 타입으로 모든 인증 결과를 attributes 라는 이름으로 가지고 있으므로
//ClubAuthMember 역시 attr 이란 변수를 만들어 주고 getAttributes 메서드를 오버라이딩 함
//ClubOAuth2UserDetailsService 도 변경해줘야 함.
    @Override
    public Map<String, Object> getAttributes() {
        return this.attr;
    }

}
