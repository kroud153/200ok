package spring.club.security.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import spring.club.entity.ClubMember;
import spring.club.entity.ClubMemberRole;
import spring.club.repository.ClubMemberRepository;
import spring.club.security.dto.ClubAuthMemberDTO;

@Service
@RequiredArgsConstructor
public class ClubAccountService {
	private final ClubMemberRepository accountRepository;

   // @Transactional
    public String createUser(ClubAuthMemberDTO form) {
    	ClubMember clubMember = form.toEntity();
    	//default role
    	clubMember.addMemberRole(ClubMemberRole.USER);
      
        accountRepository.save(clubMember);
        return clubMember.getEmail();
    }
}
