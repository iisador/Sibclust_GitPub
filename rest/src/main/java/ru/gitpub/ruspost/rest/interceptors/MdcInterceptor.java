package ru.gitpub.ruspost.rest.interceptors;

import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * Для отображения имени пользователя в логах.
 *
 * @see MDC
 */
public class MdcInterceptor implements AsyncHandlerInterceptor {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Principal user = request.getUserPrincipal();
        if (user != null) {
            MDC.put("user", user.getName());
        }

        // todo: SessionError: После решения проблемы удалить блок catch
        try {
            MDC.put("sessId", request.getSession().getId());
        } catch (Throwable e) {
            MDC.put("sessId", "NO_ID");
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) {
        MDC.remove("user");
        MDC.remove("sessId");
    }
}
