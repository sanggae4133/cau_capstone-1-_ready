package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import hello.hellospring.service.Memberservice;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    private final Memberservice memberservice;

    @Autowired
    public MemberController(Memberservice memberservice) {
        this.memberservice = memberservice;
    }
    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){ //form을 통해 입력받은 name이 form객체에 저장됨
        Member member=new Member();
        member.setName(form.getName());

        memberservice.join(member);

        return "redirect:/"; //홈 화면으로 돌아감
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberservice.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";

    }

}
