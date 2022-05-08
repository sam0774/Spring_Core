package spring.core.beanfind;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.core.AppConfig;
import spring.core.member.MemberService;
import spring.core.member.MemberServiceImpl;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextBasicFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);

    }
    @Test
    @DisplayName("이름없이 타입으로만 조회")
    void findBeanByType() {
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);

    }
    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2() {
        /* getBean메소드의 인자로 클래스 타입을 넣을 때, 구체타입으로 적어줘도 됨(하지만 유연성 떨어짐)
           하지만 이렇게 되면 구현에 의존하게 됨(Bad) 역할에 의존하자!(추상화 == 인터페이스) */
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    //테스트는 항상 실패테스트도 만들어줘야 한다.
    @Test
    @DisplayName("빈 이름으로 조회X")
    void findBeanByNameX() {
        //JUnit import해줘야됨(현재는 static import로 올려둠)
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("xxxxx", MemberService.class));

        /*
        * 실패 테스트는 실패가 되어야 성공하는 테스트인데, assertThrows메소드를 사용해주면
          테스트가 실패했을 시 에러가 뜨지 않고 성공한다.
        * assertThrows메소드
        * 첫 번째 인자: 실패했을 시 발생할 Exception 클래스 타입
        * 두 번째 인자: 실행할 실패 로직
        * */
    }
}
