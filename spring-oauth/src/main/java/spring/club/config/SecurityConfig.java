
package spring.club.config;

import lombok.extern.log4j.Log4j2;
import spring.club.handler.ClubLoginSuccessHandler;
import spring.club.security.service.ClubOAuth2UserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//@EnableGlobalMethodSecurity 이 태그는 @PreAuthorize랑 짝꿍임 같이 쎠야함

@Configuration
@Log4j2
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ClubOAuth2UserDetailsService userDetailsService;

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        http.authorizeRequests()
//                .antMatchers("/sample/all").permitAll()
//                .antMatchers("/sample/member").hasRole("USER");

        http.formLogin();
        http.csrf().disable();
        http.logout().logoutUrl("/login");
        
        http.oauth2Login().successHandler(successHandler());
//        http.rememberMe().tokenValiditySeconds(60*60*7).userDetailsService(userDetailsService);  //7일 동안 쿠키 유지 (구글 로그인은 쿠키 생성 안됨)
//
//        http.addFilterBefore(apiCheckFilter(), UsernamePasswordAuthenticationFilter.class);
//        http.addFilterBefore(apiLoginFilter(), UsernamePasswordAuthenticationFilter.class);
    }

//    @Bean
//    public ApiLoginFilter apiLoginFilter() throws Exception{
//
//        ApiLoginFilter apiLoginFilter =  new ApiLoginFilter("/api/login", jwtUtil());
//        apiLoginFilter.setAuthenticationManager(authenticationManager());
//
//        apiLoginFilter
//                .setAuthenticationFailureHandler(new ApiLoginFailHandler());
//
//        return apiLoginFilter;
//    }

//    @Bean
//    public JWTUtil jwtUtil() {
//        return new JWTUtil();
//    }
//
//    @Bean
//    public ApiCheckFilter apiCheckFilter() {
//
//        return new ApiCheckFilter("/notes/**/*", jwtUtil());
//    }
//
//
// 로그인 성공이후의 처리를 담당하기위한 핸들러
    @Bean
    public ClubLoginSuccessHandler successHandler() {
        return new ClubLoginSuccessHandler(passwordEncoder());
    }


//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//        auth.inMemoryAuthentication().withUser("user1") //사용자 계정은 user1
//                .password("$2a$10$qbTVRGiC8RePIsMz4z/QP.LjBmLOMGXBCkmW2comzfNaoeidd5/aa") //1111 패스워드 인코딩
//                .roles("USER");
//
// }


}
