package hello.login.web.argumentresolver;

import hello.login.domain.member.Member;
import hello.login.web.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Slf4j
public class LoginMemberArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        log.info("supportsParameter 실행");

        // 해당 파라미터에 Login.class 어노테이션이 있는지 여부
        boolean hasLoginAnnotation = parameter.hasParameterAnnotation(Login.class);
        // 해당 파라미터 타입이 Member.class 인지 여부
        boolean hasMemberType = Member.class.isAssignableFrom(parameter.getParameterType());

        // 파라미터에 @Login 이 붙어있으며, 파라미터 타입이 Member일 경우 true > resolveArgument 메서드 실행
        return hasLoginAnnotation && hasMemberType;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        log.info("resolveArgument 실행");

        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        HttpSession session = request.getSession(false);

        if (session == null) {
            return null;
        }

        //세션에 loginMember 가 있으면 반환, 없으면 null 반환
        return session.getAttribute(SessionConst.LOGIN_MEMBER);
    }
}
