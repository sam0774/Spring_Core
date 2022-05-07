package spring.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.core.member.Grade;
import spring.core.member.Member;
import spring.core.member.MemberService;
import spring.core.member.MemberServiceImpl;

/*
 * 순수 자바코드로 test
 * */
public class MemberApp {
    public static void main(String[] args) {
        //AppConfig appConfig = new AppConfig();
        //MemberService memberService = appConfig.memberService();


        //AppConfig클래스에서 @Bean이 붙은 각 메소드의 반환값(객체)을 스프링 컨테이너에 등록하고 관리해줌
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        /* 스프링 컨테이너에서 빈을 꺼낼때는(반환할때는) 빈의 이름으로 꺼내야 하는데,
           기본적으로 빈의 이름은 @Bean이 붙어있는 해당 메소드의 이름으로 등록되어있음.

          <getBean 메소드>
           1. 첫번째 인자(key): 빈 이름(==메소드 이름)
           2. 두번째 인자(value): 메소드의 반환 타입        */
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());
    }
}

/*
* <Spring Container>
* ApplicationContext를 스프링 컨테이너라고 한다.
* 스프링 컨테이너는 @Configuration이 붙은 AppConfig를 설정 정보로 사용한다.
  여기서 @Bean이라고 붙은 각 메소드의 반환값(객체)을 스프링 컨테이너에 등록한다.
  이렇게 스프링 컨테이너에 등록된 객체를 스프링 빈 이라고 한다.
*@Bean이 붙은 메소드 이름을 스프링 빈의 이름으로 사용한다.

* */
