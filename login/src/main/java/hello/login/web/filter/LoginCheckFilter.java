package hello.login.web.filter;

import hello.login.web.SessionConst;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j
public class LoginCheckFilter implements Filter {

    private static final String[] whiteList = {"/", "/members/add", "/login", "/logout", "/css/*"};
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpServletRequest.getRequestURI();

        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        try {
            log.info("인증 체크 필터 시작 = {}", requestURI);

            //로그인이 필요한 경로라면.
            if (isLoginCheckPath(requestURI)) {
                log.info("인증 체크 로직 실행 {}", requestURI);
                //요청정보에서 세션정보를 가져옴.
                HttpSession session = httpServletRequest.getSession(false);
                //세션정보가 없거나 SessionConst.LOGIN_MEMBER 에 대한 세션이 없다면 수행
                if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
                    log.info("미인증 사용자 요청 {}", requestURI);
                    //로그인이 안된 사용자의 요청이므로 로그인 화면으로 redirect
                    httpServletResponse.sendRedirect("/login?redirectURL=" + requestURI);
                    return;
                }
            }

            //로그인이 필요없는 경로라면.
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {
            throw e; //예외 로깅 가능하지만 여기서 처리할 경우 정상로직처럼 동작하기 때문에, WAS(톰캣)까지 예외를 보내주어야 함.
        } finally {
            log.info("인증 체크 필터 종료");
        }
    }

    /**
     * 화이트 리스트의 경우 인증 체크 X
     */
    private boolean isLoginCheckPath(String requestURI) {
        return !PatternMatchUtils.simpleMatch(whiteList, requestURI);
    }

}
