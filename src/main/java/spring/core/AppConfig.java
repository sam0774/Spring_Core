package spring.core;

import spring.core.discount.FixDiscountPolicy;
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
public class AppConfig {
    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }
}
