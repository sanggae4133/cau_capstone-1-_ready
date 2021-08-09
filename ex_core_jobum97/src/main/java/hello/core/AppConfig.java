package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    //@Bean memberService -> new MemoryMemberRepository()
    //@Bean orderService -> new MemoryMemberRepository()


    //"call AppConfig.memberService"
    //"call AppConfig.orderService"
    //"call AppConfig.memberRepository"

    @Bean
    public MemberService memberService() { //역활
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository()); //구현
    }

    @Bean
    public MemberRepository memberRepository() { //역활
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository(); //구현
    }

    @Bean
    public DiscountPolicy discountPolicy() { //역활
        //return new FixDiscountPolicy(); //구현
        return new RateDiscountPolicy(); //구현체 갈아끼우기
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

}
