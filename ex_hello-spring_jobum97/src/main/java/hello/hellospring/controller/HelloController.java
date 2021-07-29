package hello.hellospring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute( "data","hello!!" );
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        //매개변수 받을 때 @RequestParam 사용
        model.addAttribute( "name",name );
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody //http 의 통신 프로토콜의 body 부분에 직접 넣어주겠다는 의미
    //view 거치지 않고 직접 데이터 전달
    public String helloString(@RequestParam("name") String name){
        //매개변수 받을 때 @RequestParam 사용
        return "hello "+name;
    }

    @GetMapping("hello-api")
    @ResponseBody //http 의 통신 프로토콜의 body 부분에 직접 넣어주겠다는 의미
    public Hello helloApi(@RequestParam("name") String name){
        //매개변수 받을 때 @RequestParam 사용
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
