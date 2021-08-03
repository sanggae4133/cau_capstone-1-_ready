package hello.hellospring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import service.Memberservice;

@Controller
public class MemberController {
    private final Memberservice memberservice;

    @Autowired
    public MemberController(Memberservice memberservice) {
        this.memberservice = memberservice;
    }


}