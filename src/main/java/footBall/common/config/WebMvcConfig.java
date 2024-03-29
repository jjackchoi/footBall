package footBall.common.config;

import footBall.common.intercepter.CacheControlInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private CacheControlInterceptor cacheControlInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // CacheControlInterceptor를 등록하여 모든 요청에 적용
        registry.addInterceptor(new CacheControlInterceptor())
                .addPathPatterns("/myPage/profile");
    }
}
