package com.spring.boot.security.web;

import com.spring.boot.security.config.CustomAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.Optional;

/**
 * <p>Description: 安全 Controller</p>
 *
 * @author Rock Jiang
 * @version 1.0
 * @date 2018/4/4 0004
 */
@RestController
public class SecurityController extends BaseController {

    @Autowired
    private CustomAuthenticationProvider authenticationProvider;

    @RequestMapping("/customLogin")
    public String login(HttpServletRequest request) {
        logger.debug("SecurityController login");

        Enumeration<String> stringEnumeration = request.getParameterNames();
        while (stringEnumeration.hasMoreElements()) {
            logger.debug("ParameterName: {}", stringEnumeration.nextElement());
        }

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        logger.debug("username: {} password: {}", username, password);

        //使用用户输入的username, password组装一个Authentication
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
        //登录认证，认证成功返回认证后的Authentication
        Authentication retAuthentication = authenticationProvider.authenticate(authentication);

        //登录失败返回
        if (!Optional.ofNullable(retAuthentication).isPresent()) {
            return "login failed";
        }

        //认证通过之后，将用户认证信息放入SecurityContext中
        SecurityContextHolder.getContext().setAuthentication(retAuthentication);

        //获取SessionId，即Redis中保存的SessionId
        HttpSession httpSession = request.getSession();
        String sessionId = httpSession.getId();
        logger.debug("sessionId: {}", sessionId);

        // If possible, developers should not interact directly with a SessionRepository or a Session.
        // Instead, developers should prefer interacting with SessionRepository and Session indirectly through the HttpSession and WebSocket integration.
        httpSession.setAttribute("rock444555", "rock78979878979");

        //前后端分离时，登录成功后由前端负责跳转到主页
        return "login success";
    }

    @RequestMapping("/customLogout")
    public String customLogout(HttpServletRequest request) {
        logger.debug("登出");

        HttpSession httpSession = request.getSession();
        //Invalidates this session then unbinds any objects bound to it
        httpSession.invalidate();

        SecurityContextHolder.getContext().setAuthentication(null);

        SecurityContextHolder.clearContext();

        //前后端分离时，登出成功由前端负责跳转到登录页或主页
        return "logout success";
    }

    @RequestMapping("/home")
    public String home() {
        logger.debug("主页");

        return "home success";
    }
}
