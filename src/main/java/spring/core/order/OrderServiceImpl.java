package spring.core.order;

import spring.core.discount.DiscountPolicy;
import spring.core.member.Member;
import spring.core.member.MemberRepository;
import spring.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    //주문서비스에서 리포지토리를 통해 회원을 찾아야하므로, MemberRepository 객체 생성
    private final MemberRepository memberRepository;
    //주문서비스에서 할인정책을 정해주므로 DiscountPolicy 객체 생성
    private DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);  //리포지토리에서 멤버 찾음

        /* DiscountPolicy가 알아서 할인 정책을 정해서 반환해줌.
         * 이 클래스에선 할인정책의 내부를 신경쓸 필요가 없음. 그저 반환값만 받아서
           처리하면 됨 => DiscountPolicy로 인해, 단일체계의 원칙이 잘 지켜짐. */
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}

/*
* 1. 주문 생성 요청이 오면 회원 조회를 한다.
* 2. 할인정책에 회원을 넘겨서 반환값을 받음(할인정책 적용)
* 3. 최종적으로 할인된 가격이 적용된 주문을 신청함(Order객체 반환)
* */
