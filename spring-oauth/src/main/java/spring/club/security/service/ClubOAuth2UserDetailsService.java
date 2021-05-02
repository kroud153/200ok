package spring.club.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import spring.club.entity.ClubMember;
import spring.club.entity.ClubMemberRole;
import spring.club.repository.ClubMemberRepository;
import spring.club.security.dto.ClubAuthMemberDTO;
import spring.club.security.dto.SessionUser;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

@Log4j2
@Service
@RequiredArgsConstructor
public class ClubOAuth2UserDetailsService extends DefaultOAuth2UserService {

//1. 첫 방법 - 프로필 넣는 법을 모르겠음
    private final ClubMemberRepository repository;//사용자 정보를 관리하는 DB 저장소
    private final PasswordEncoder passwordEncoder;



    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        log.info("--------------------------------------");
        log.info("userRequest:" + userRequest);
        
        //return super.loadUser(userRequest); test 용

        String clientName = userRequest.getClientRegistration().getClientName();

        log.info("clientName: " + clientName);
        log.info(userRequest.getAdditionalParameters());

        OAuth2User oAuth2User =  super.loadUser(userRequest);

        log.info("==============================");
        oAuth2User.getAttributes().forEach((k,v) -> {
            log.info(k +":" + v);
        });
        

        String email = null;
        String name = null;

        if(clientName.equals("Google")){
            email = oAuth2User.getAttribute("email");
            name = oAuth2User.getAttribute("name");
        }

        log.info("EMAIL: " + email);
        log.info("NAME: " + name);

 //       ClubMember member = saveSocialMember(email); //조금 뒤에 사용

  //      return oAuth2User;
 //   이메일 주소를 DTO에 저장해서 화면에 언제든지 뛰워줄 수 있게 하기 위해
        ClubMember member = saveSocialMember(email,name);

        ClubAuthMemberDTO clubAuthMember = new ClubAuthMemberDTO(
                member.getEmail(),
                member.getPassword(),
                true,   //fromSocial
                member.getRoleSet().stream().map(
                        role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                        .collect(Collectors.toList()),
                oAuth2User.getAttributes()
        );
        clubAuthMember.setName(member.getName());
        


        return clubAuthMember;
        


    }
//
//
    private ClubMember saveSocialMember(String email, String name){

        //기존에 동일한 이메일로 가입한 회원이 있는 경우에는 그대로 조회만
        Optional<ClubMember> result = repository.findByEmail(email, true);

        if(result.isPresent()){
            return result.get();
        }

        //없다면 회원 추가 패스워드는 1111 이름은 그냥 이메일 주소로
        ClubMember clubMember = ClubMember.builder().email(email)
                .name(name)
                .password( passwordEncoder.encode("1111") )
                .fromSocial(true)
                .build();

        clubMember.addMemberRole(ClubMemberRole.USER);


        repository.save(clubMember);

        return clubMember;
    }
    


}
