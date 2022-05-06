package spring.core;

import spring.core.member.Grade;
import spring.core.member.Member;
import spring.core.member.MemberService;
import spring.core.member.MemberServiceImpl;
import spring.order.Order;
import spring.order.OrderService;
import spring.order.OrderServiceImpl;

public class OrderApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member); //회원가입 (리포지토리에 저장됨)
        Order order = orderService.createOrder(memberId, "itemA", 10000);
        System.out.println("order = " + order);


    }
}
