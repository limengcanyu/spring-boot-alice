package com.spring.boot.security.web;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <p>Description: 时间信息相关 Controller</p>
 *
 * @author Rock Jiang
 * @version 1.0
 * @date 2018/4/4 0004
 */
@RestController
@RequestMapping("/time")
public class TimeController extends BaseController {

    /**
     * 查询当前时间
     *
     * @param principal
     * @return
     */
    @Secured("ROLE_USER")
    @RequestMapping("/selectTime")
    public String selectTime(@AuthenticationPrincipal(errorOnInvalidType = true) String principal, HttpServletRequest request){
        logger.debug("查询当前时间 当前登录用户 username: {}", principal);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        logger.debug("查询当前时间 authentication.getPrincipal: {}", authentication.getPrincipal());

        String sessionId = request.getRequestedSessionId();
        logger.debug("查询当前时间 RequestedSessionId: {}", sessionId);

        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:SS"));
    }

    @RequestMapping("/selectDate")
    public String selectDate(){
        logger.debug("查询当前日期");

        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

}
