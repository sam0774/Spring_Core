package spring.core.discount;

import spring.core.member.Member;

public interface DiscountPolicy {

    int discount(Member member, int price); //return 할인 대상 금액

}
