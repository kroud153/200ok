package spring.club.security.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import spring.club.entity.ClubMember;
import spring.club.entity.ClubMemberRole;
import spring.club.repository.ClubMemberRepository;
import spring.club.security.dto.ClubAccountDTO;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClubAccountService {
	private final ClubMemberRepository accountRepository;

    @Transactional
    public String createUser(ClubAccountDTO form) {
    	ClubMember clubMember = form.toEntity();
    	//default role
    	clubMember.addMemberRole(ClubMemberRole.USER);
      
        accountRepository.save(clubMember);
        return clubMember.getEmail();
    }

}
