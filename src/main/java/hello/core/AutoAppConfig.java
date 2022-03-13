package hello.core;

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
}
