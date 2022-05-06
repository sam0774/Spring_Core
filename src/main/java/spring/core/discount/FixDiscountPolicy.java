package spring.core.discount;

import spring.core.member.Grade;
import spring.core.member.Member;

public class FixDiscountPolicy implements DiscountPolicy{
    private int discountFixAmount = 1000; //1000원 할인

    @Override //얼마나 할인해줄지에 대한 금액 반환
    public int discount(Member member, int price) {

        //enum은 상수이므로 == 연산자 사용가능
        if (member.getGrade() == Grade.VIP) //멤버가 vip이면
            return discountFixAmount; //고정금액 반환
        else
            return 0;

    }

}
