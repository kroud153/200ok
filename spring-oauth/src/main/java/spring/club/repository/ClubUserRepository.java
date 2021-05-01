//package spring.club.repository;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import spring.club.entity.ClubUser;
//
//import java.util.Optional;
//
//public interface ClubUserRepository extends JpaRepository<ClubUser, Long> {
//	
//   //@Query("select m from ClubUser m where m.email =:email")
//   Optional<ClubUser> findByEmail(@Param("email") String email);
//}