package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


// 정리
// @PostConstruct, @PreDestroy 애노테이션을 사용하자
// 코드를 고칠 수 없는 외부 라이브러리를 초기화, 종료해야 하면 @Bean 의 initMethod , destroyMethod 를 사용하자.
public class BeanLifeCycleTest {
    /**
     * 스프링 빈의 이벤트 라이프 사이클
     * 스프링컨테이너생성 -> 스프링빈생성 -> 의존관계주입 -> 초기화콜백 -> 사용 -> 소멸전콜백 -> 스프링 종료
     * 초기화 콜백: 빈이 생성되고, 빈의 의존관계 주입이 완료된 후 호출
     * 소멸전 콜백: 빈이 소멸되기 직전에 호출
     */
    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig {
        @Bean(initMethod = "connect", destroyMethod = "disconnect")
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
