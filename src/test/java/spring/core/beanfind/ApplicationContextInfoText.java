package spring.core.beanfind;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.core.AppConfig;

public class ApplicationContextInfoText {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean() {//@Bean붙인것들 + AppConfig
        String[] beanDefinitionNames = ac.getBeanDefinitionNames(); //모든 빈들의 이름을 반환
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName); //해당 스프링 빈 객체 반환(Object형)
            System.out.println("name = " + beanDefinitionName + " object = " + bean);
        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기") //@Bean을 붙인것들만 뽑아내기
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames(); //모든 빈들의 이름을 반환
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);//빈의 메타데이터



            //Role ROLE_APPLICATION: 프로그래머가 직접 등록한 애플리케이션 빈
            //ROLD INFRASTRUCTURE:  스프링 내부에서 사용하는 빈
            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + " object = " + bean);

            }
        }
    }
}
//참고로 AppConfig객체도 스프링 컨테이너에 스프링 빈으로 등록이 된다.