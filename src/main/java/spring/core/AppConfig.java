package spring.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.core.discount.DiscountPolicy;
import spring.core.discount.FixDiscountPolicy;
import spring.core.discount.RateDiscountPolicy;
import spring.core.member.MemberRepository;
import spring.core.member.MemberService;
import spring.core.member.MemberServiceImpl;
import spring.core.member.MemoryMemberRepository;
import spring.core.order.OrderService;
import spring.core.order.OrderServiceImpl;

/*
*애플리케이션의 환경 구성을 이 클래스에서 전부 함
*이렇게 코드를 짜면 AppConfig를 통해 구현객체를 만들 때,
 코드 변경 시, 구현클래스 내에서는 다른것을 신경쓰지 않아도 됨.(AppConfig에서 생성자 주입으로 대신 신경 써주기 때문)
*코드 변경 시, AppConfig만 수정하면 된다. 의존성 주입을 AppConfing에 모아놓음
* AppConfig는 구체 클래스를 선택한다.
*/


/* @Configuration을 클래스에 붙이고 @Bean을 메소드에 붙이면,
   해당 메소드의 반환값이 스프링 컨테이너에 스프링 빈으로 등록이 된다.*/
@Configuration //설정정보
public class AppConfig { //AppConfig는 IoC컨테이너(DI컨테이너)역할을 한다.
    //역할과 구현 한눈에 들어옴
    @Bean//스프링 컨테이너에 스프링 Bean으로 등록, 빈 이름은 항상 다르게 등록해야 됨!!
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    /* AppConfig refactoring
     * 1. 구성정보에서 역할과 구현을 명확하게 분리함
     * 2. 중복 제거(유지보수 용이) */

    @Bean
    public MemberRepository memberRepository() { //함수 추출(method extract)
        return new MemoryMemberRepository();
    }
    @Bean
    public DiscountPolicy discountPolicy() { //함수 추출(method extract)
        return new RateDiscountPolicy();
    }
}

/*
* 제어의 역전(IoC, Inversion of Control)이란?
* 프로그램의 제어 흐름을 직접 제어하는 것이 아니라, 외부에서 관리해주는 것을 뜻함(프레임워크가 IoC를 담당함)
* ex) JUnit의 @Test, @AfterEach 등
*
* 프레임워크: 내가 작성한 코드가 직접 제어의 흐름을 담당하지 않고, 누군가 제어해줌(대신 실행해줌)
* 라이브러리: 내가 작성한 코드가 직접 제어의 흐름을 담당함
* */

/*
* <정적 클래스 의존관계>
* - import 코드만 보고 의존관계를 유추함
* - diagrams 기능을 통해 객체 의존관계들을 시각적으로 확인할 수 있음
*
* <동적 인스턴스 의존관계>
* - 애플리케이션 실행 시점에 실제 생성된 인스턴스의 참조가 연결된 의존 관계
* - 애플리케이션 실행시점(런타임)에 외부에서 실제 구현 객체를 생성하고, 클라이언트에 전달해서
*   클라이언트와 서버의 실제 의존관계가 연결되는 것을 의존 관계 주입(Dependency Injection)이라고 한다
*
* 의존관계 주입을 사용하면?
* - 클라이언트 코드를 변경하지 않고, 클라이언트가 호출하는 대상의 타입 인스턴스를 변경할 수 있다.
* - 정적인 클래스 의존관계를 변경하지 않으면서, 동적인 객체 인스턴스 의존관계를 쉽게 변경할 수 있다.
*/

/* 실제 스프링에서의 작동 순서
* 1. 스프링 빈 생성
  2. 의존관계 주입(DI)
 */
