package github.javaguide.springsecurityjwtguide;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author Chr.yl
 */
@SpringBootApplication
public class SpringSecurityJwtGuideApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityJwtGuideApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的Application启动类
//        return builder.sources(SpringSecurityJwtGuideApplication.class);
        return builder.sources(this.getClass());
    }
}
