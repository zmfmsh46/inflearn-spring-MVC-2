package hello.login.web.session;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Slf4j
@RestController
public class SessionInfoController {

    @GetMapping("/session-info")
    public String sessionInfo(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "세션이 없습니다.";
        }

        //세션 데이터 출력
        session.getAttributeNames().asIterator()
                .forEachRemaining(name -> log.info("session name = {}, value = {}", name, session.getAttribute(name)));

        //세션 아이디
        log.info("sessionId = {}", session.getId());
        //세션 유효 시간
        log.info("MaxInactiveInterval = {}", session.getMaxInactiveInterval());
        //세션 생성일자
        log.info("CreationTime = {}", new Date(session.getCreationTime()));
        //마지막에 접근한 시간
        log.info("LastAccessedTime = {}", new Date(session.getLastAccessedTime()));
        //새로 생성된 세션인지 유무
        log.info("isNew = {}", session.isNew());

        return "세션 출력";
    }
}
