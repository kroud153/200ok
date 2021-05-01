//package spring.security;
//
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import spring.club.entity.ClubUser;
//import spring.club.entity.ClubUserRole;
//import spring.club.repository.ClubUserRepository;
//
//import java.util.HashSet;
//import java.util.Optional;
//import java.util.stream.IntStream;
//
//@SpringBootTest
//public class ClubUserTests {
//
//    @Autowired
//    private ClubUserRepository repository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Test
//    public void insertDummies() {
//
//        //1 - 80까지는 USER만 지정
//        //81- 90까지는 USER,MANAGER
//        //91- 100까지는 USER,MANAGER,ADMIN
//
//        IntStream.rangeClosed(1,100).forEach(i -> {
//            ClubUser clubUser = ClubUser.builder()
//            		.id(null)
//                    .email("user"+i+"@zerock.org")
//                    .name("사용자"+i)
//                    .picture(null)
//                    .role(ClubUserRole.USER)
//                    .build();
//
//            //default role
////            clubUser.addUserRole(ClubUserRole.GUEST);
//
////            if(i > 80){
////            	clubUser.addUserRole(ClubUserRole.User);
////            }
////
////            if(i > 90){
////                clubMember.addMemberRole(ClubMemberRole.ADMIN);
////            }
//
//            repository.save(clubUser);
//
//        });
//
//    }
//
//    @Test
//    public void testRead() {
//
//        Optional<ClubUser> result = repository.findByEmail("user95@zerock.org");
//
//        ClubUser clubUser = result.get();
//
//        System.out.println(clubUser);
//
//    }
//
//
//}
