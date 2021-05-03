package spring.club.controller;



import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import spring.club.security.dto.ClubAccountDTO;
import spring.club.security.dto.ClubAuthMemberDTO;
import spring.club.security.dto.SessionUser;
import spring.club.security.service.ClubAccountService;
import spring.club.security.service.ClubOAuth2UserDetailsService;

import javax.servlet.http.HttpSession;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//final 생성자를 생성해줌 - final 오류 방지
//final 없으면 초기화가 안되서 이상한 세션값이 넘어올 수 있음
@RequiredArgsConstructor
@Controller
@Log4j2
//@RequestMapping("/")
public class SampleController {
	
	private final ClubAccountService accountService;
	
	
    //private final HttpSession httpSession;
	@PreAuthorize("permitAll()")
	@GetMapping("/")
    public String index(Model model){
        log.info("home..........");
        
//        SessionUser user = (SessionUser) httpSession.getAttribute("user");
//        log.info(user);
//        if(user != null) {
//            model.addAttribute("userName", user.getName());
//            //model.addAttribute("userImg", user.getPicture());
//        }
        return "index";
    }
	@PreAuthorize("permitAll()")
	@GetMapping("/login/login")
    public String login(){
		log.info("login..........");
        return "login";
    }
	
//	@PreAuthorize("permitAll()")
//	@GetMapping("/signup")
//    public String signupex(){
//		log.info("singup ex..........");
//        return "signup";
//    }
//	
	@PreAuthorize("permitAll()")
	@GetMapping("/loginUser")
    public String signup(Model model){
        log.info("get singup..........");
        model.addAttribute("userForm",new ClubAccountDTO());

        return "signup";
    }
	@PreAuthorize("permitAll()")
    @PostMapping("/loginUser")
    public String signupUser(@Validated ClubAccountDTO form, BindingResult result){
    	log.info("post singup..........");
    	if(result.hasErrors()){
            return "signup";
        }
        accountService.createUser(form);

        return "index";
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/all")
    public void exAll(){
        log.info("exAll..........");
    }

//    @GetMapping("/member")
//    public void exMember(@AuthenticationPrincipal ClubAuthMemberDTO clubAuthMember){
//        log.info("exMember..........");
//        log.info("--------------------------------");
//        log.info(clubAuthMember);
//    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public void exAdmin(){
        log.info("exAdmin..........");
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/member")
    public void exMember(Model model, @AuthenticationPrincipal ClubAuthMemberDTO clubAuthMember){

        log.info("exMember..........");

        log.info("-------------------------------");
        log.info(clubAuthMember);
        
        if(clubAuthMember.getAttr() != null) {
        	model.addAttribute("userName", clubAuthMember.getName());
        	model.addAttribute("userImg", clubAuthMember.getAttr().get("picture"));
        }
        else if(clubAuthMember != null) {
        	model.addAttribute("userName", clubAuthMember.getName());
        	model.addAttribute("userImg","/image/user.png");
        }

    }
//user95@zerock.org인 사람만 접근 가능
    @PreAuthorize("#clubAuthMember != null && #clubAuthMember.username eq \"user95@zerock.org\"")
    @GetMapping("/exOnly")
    public String exMemberOnly(@AuthenticationPrincipal ClubAuthMemberDTO clubAuthMember){

        log.info("exMemberOnly.............");
        log.info(clubAuthMember);

        return "/sample/admin";
    }

}
