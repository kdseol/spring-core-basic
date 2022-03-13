package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        //탐색 위치와 기본 스캔 대상
        //basePackages = "hello.core.member", //이 패키지를 포함해서 하위 패키지를 모두 탐색한다.
        //basePackageClasses = AutoAppConfig.class, //지정한 클래스의 패키지를 탐색 위치로 지정한다.
        //만약 지정치 않을 시 ,@ComponentSacn 이 붙은 설정 정보 클래스의 패키지가 시작 위치가 된다.

        //예제를 안전하게 유지하기 위해서 기존의 @Configuration을 제외하는 필터를 추가했다.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
    // 빈 이름이 중복되어있을 경우 수동 빈 등록이 우선권을 가진다(수동 빈이 자동 빈을 오버라이딩 해버린다.)
    // 스프링부트가 다른 여러가지 예외 상황으로 인해 빈 이름이 중복일 경우 실행이 되지 않도록 기본설정되었다.
    // 만약 사용하려먼 properties에서 'spring.main.allow-bean-definition-overriding=true' 를 명시해 주어야 가능하다.
    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

}
