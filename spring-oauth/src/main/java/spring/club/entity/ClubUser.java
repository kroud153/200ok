//package spring.club.entity;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//
//import javax.persistence.*;
//
//@Builder
//@Getter
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//public class ClubUser extends BaseEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    @Column(nullable = false)
//    private String name;
//    @Column(nullable = false)
//    private String email;
//    @Column
//    private String picture;
//    
//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
//    private ClubUserRole role;  // Role: 직접 만드는 클래스
//   
//    
//    
////    public ClubUser(String name, String email, String picture, Role role) {
////        this.name = name;
////        this.email = email;
////        this.picture = picture;
////        this.role = role;
////    }
//
//    public ClubUser update(String name, String picture) {
//        this.name = name;
//        this.picture = picture;
//        return this;
//    }
//    
//
//    
//    public String getRoleKey() {
//        return this.role.getKey();
//    }
//}