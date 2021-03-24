package ok.springtest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//주소만 바꾸어 줌
@Controller
public class HelloController {
@GetMapping("hello")
 public String helloMvc(Model model) {
 model.addAttribute("name", "hello!!");
 return "hello";
 }	
	
//뷰 거쳐서 전달 ?name = V
 @GetMapping("hello-mvc")
 public String helloMvc(@RequestParam("name") String name, Model model) {
 model.addAttribute("name", name);
 return "hello-template";
 }
 
 //뷰 없이 그대로 전달 ?name = V
 @GetMapping("hello-string")
 @ResponseBody
 public String helloString(@RequestParam("name") String name) {
	 return "hello "+name;
 }
 
 //이게 디폴트 전달 방식임
 @GetMapping("hello-api")
 @ResponseBody
 public Hello helloApi(@RequestParam("name") String name) {
	 Hello hello = new Hello();
	 hello.setName(name);
	 return hello;
 }
 
 static class Hello {
	 private String name;
//property ? 프로퍼티 접근 방식 / getter setter 방식 / 자바빈 방식
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	 
 }
}
