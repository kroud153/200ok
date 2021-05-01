package spring.club.entity;


import lombok.*;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "roleSet")
public class ClubMember extends BaseEntity {
	
    @Id
    private String email;

    private String password;

    private String name;
    
    private String picture;
    
    private boolean fromSocial;//소셜로그인 인지 아닌지

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<ClubMemberRole> roleSet = new HashSet<>();

    public void addMemberRole(ClubMemberRole clubMemberRole){
        roleSet.add(clubMemberRole);
    }
    
    public ClubMember update(String name, String picture) {
    	this.name = name;
    	this.picture = picture;
    	
    	
    return this;
    }

}
