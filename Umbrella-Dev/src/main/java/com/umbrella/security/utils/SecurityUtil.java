package com.umbrella.security.utils;

import com.umbrella.security.userDetails.UserContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtil {
    public String getLoginUserEmail() {
        UserContext userContext = getPrincipalInAuthentication();
        return userContext.getUsername();
    }

    public String getLoginUserNickname() {
        UserContext userContext = getPrincipalInAuthentication();
        return userContext.getNickName();
    }

    public Long getLoginUserId() {
        UserContext userContext = getPrincipalInAuthentication();
        return userContext.getId();
    }

    private UserContext getPrincipalInAuthentication() {
        return (UserContext) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
