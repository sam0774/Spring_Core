package spring.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spring.core.AppConfig;

public class MemberServiceTest {

    MemberService memberService;
    @BeforeEach
    public void test() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();

    }

    @Test
    void join() {
        //given
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when
        memberService.join(member);
        Member findMember = memberService.findMember(member.getId());

        //then
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
/*
* 검증시 org.assertj.core를 사용함
* */
