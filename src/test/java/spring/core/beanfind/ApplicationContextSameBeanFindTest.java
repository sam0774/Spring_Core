package spring.core.beanfind;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.core.member.MemberRepository;
import spring.core.member.MemoryMemberRepository;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextSameBeanFindTest {

    //SameBeanConfig클래스를 스프링 컨테이너에 SameBeanConfig.class에 정의 되어있는 @Bean들을 스프링 빈으로 등록함
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    //실패 테스트 케이스 만든거임
    @Test
    @DisplayName("타입으로 조회시 같은 타입 둘 이상 있으면, 중복 오류가 발생한다")
    void findBeanByTypeDuplicate() {

        assertThrows(NoUniqueBeanDefinitionException.class,
                ()-> ac.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("타입으로 조회시 같은 타입 둘 이상 있으면, 빈 이름을 지정하면 된다")
    void findBeanByName() {
        MemberRepository memberRepository1 = ac.getBean("memberRepository1", MemberRepository.class);
        assertThat(memberRepository1).isInstanceOf(MemoryMemberRepository.class);
    }


    @Test
    @DisplayName("특정 타입을 모두 조회하기")
    void findAllBeanByType() {
        /*getBeansOfType 메소드 사용 시, 해당 타입의 모든 빈들을 Map형태로 저장해서 반환
         * key: 빈 이름(메소드 이름)
         * value: 인스턴스
         */
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value = " + beansOfType.get(key));
        }
        System.out.println("beansOfType = " + beansOfType);
        assertThat(beansOfType.size()).isEqualTo(2);
    }

    //테스트용 AppConfig를 static class로 만들어줌
    @Configuration
    static class SameBeanConfig {

        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }
    }
}
