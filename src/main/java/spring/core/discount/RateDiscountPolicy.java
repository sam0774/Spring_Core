package spring.core.discount;

import spring.core.member.Grade;
import spring.core.member.Member;

public class RateDiscountPolicy implements DiscountPolicy {
    private int discountPercent = 10;

    @Override //얼마나 할인해줄지에 대한 금액 반환
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) //VIP이면
            return price * discountPercent / 100; //10프로 할인
        else
            return 0;
    }
}
