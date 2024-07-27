package com.hk.review.security.handler.singout;

import com.hk.review.exception.enums.ErrorCode;
import com.hk.review.model.dto.ExceptionDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONValue;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CustomLogoutResultHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if (authentication == null){
            setFailureResponse(response);
            return;
        }
        setSuccessResponse(response);
    }

    private void setSuccessResponse(HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);

        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("data", null);
        result.put("error", null);

        response.getWriter().write(JSONValue.toJSONString(result));

    }

    private void setFailureResponse(HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);

        Map<String, Object> result = new HashMap<>();
        result.put("success", false);
        result.put("data", null);
        result.put("error", ExceptionDto.of(ErrorCode.NOT_FOUND_LOGIN_USER));

        response.getWriter().write(JSONValue.toJSONString(result));
    }
}
