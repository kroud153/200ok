package spring.club.security.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.club.entity.ClubMember;
import spring.club.entity.ClubMemberRole;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
public class ClubAccountDTO {

    private String username;
    private String password;
    private String email;
    private String picture;

    @Builder
    public void ClubAccountDTO(String username, String password, String email, String picture) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.picture = picture;
    }

    public ClubMember toEntity(){
         ClubMember clubMember = ClubMember.builder()
                .name(username)
                .password(new BCryptPasswordEncoder().encode(password))
                .fromSocial(false)
                .email(email)
                .build();
         
         return clubMember;
         
    }
}