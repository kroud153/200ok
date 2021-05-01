//package spring.club.security.service;
//
//import java.util.Collections;
//
//import javax.servlet.http.HttpSession;
//
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
//import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
//import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.stereotype.Service;
//
//import lombok.RequiredArgsConstructor;
//import spring.club.entity.ClubUser;
//import spring.club.repository.ClubUserRepository;
//import spring.club.security.dto.OAuthAttributes;
//import spring.club.security.dto.SessionUser;
//
////2. 두번째 방법
//
//
//@RequiredArgsConstructor
//@Service
//public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
//	
//     private final ClubUserRepository userRepository;
//	 private final HttpSession httpSession;
//	 @Override
//	   public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//		   
//		   
//		   OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
//		   OAuth2User oAuth2User = delegate.loadUser(userRequest);
//		   
//		   String registrationId = userRequest.getClientRegistration().getRegistrationId();
//		   String userNameAttributeName = userRequest.getClientRegistration()
//		           .getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
//		
//		   OAuthAttributes attributes = OAuthAttributes.
//		           of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
//		
//		   ClubUser clubuser = saveOrUpdate(attributes);
//		   httpSession.setAttribute("user", new SessionUser(clubuser));
//		
//		   return new DefaultOAuth2User(
//		           Collections.singleton(new SimpleGrantedAuthority(clubuser.getRoleKey())),
//		           attributes.getAttributes(),
//		           attributes.getNameAttributeKey());
//		   
//	 }
//	    private ClubUser saveOrUpdate(OAuthAttributes attributes) {
//	        ClubUser user = userRepository.findByEmail(attributes.getEmail())
//	                .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
//	                .orElse(attributes.toEntity());
//	        return userRepository.save(user);
//	    }
//	    
//}
//	 