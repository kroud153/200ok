//package spring.club.security.dto;
//
//import java.util.Map;
//
//import lombok.Builder;
//import lombok.Getter;
//import spring.club.entity.ClubUser;
//import spring.club.entity.ClubUserRole;
//
//@Getter
//public class OAuthAttributes {
//	
//private Map<String, Object> attributes;
//private String nameAttributeKey, name, email, picture;
//@Builder
//public OAuthAttributes(Map<String, Object> attributes,
//                       String nameAttributeKey,
//                       String name, String email, String picture) {
//    this.attributes = attributes;
//    this.nameAttributeKey = nameAttributeKey;
//    this.name = name;
//    this.email = email;
//    this.picture = picture;
//}
//public static OAuthAttributes of(String registrationId,
//                                 String userNameAttributeName,
//                                 Map<String, Object> attributes) {
//    return ofGoogle(userNameAttributeName, attributes);
//}
//public static OAuthAttributes ofGoogle(String userNameAttributeName,
//                                       Map<String, Object> attributes) {
//    return OAuthAttributes.builder()
//            .name((String) attributes.get("name"))
//            .email((String) attributes.get("email"))
//            .picture((String) attributes.get("picture"))
//            .attributes(attributes)
//            .nameAttributeKey(userNameAttributeName)
//            .build();
//}
//public ClubUser toEntity() {
//    return ClubUser.builder()
//            .name(name)
//            .email(email)
//            .picture(picture)
//            .role(ClubUserRole.GUEST)
//            .build();
//}
//}