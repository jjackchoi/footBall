package footBall.intercepter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
@Component
public class CacheControlInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // 요청을 처리하기 전에 실행되는 메서드
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
        return true;
    }

//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//                           ModelAndView modelAndView) throws Exception {
//        // 요청을 처리한 후, View를 렌더링하기 전에 실행되는 메서드
//        // 여기서 HTTP 응답 헤더를 설정하여 캐싱을 제어할 수 있음
//        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
//        response.setHeader("Pragma", "no-cache");
//        response.setHeader("Expires", "0");
//    }
}
