package com.hk.review.security.info;

import com.hk.review.exception.enums.ErrorCode;
import com.hk.review.model.dto.ExceptionDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.minidev.json.JSONValue;

import java.io.IOException;
import java.util.Map;

public class AbstractAuthenticationFailure {

    protected void setErrorResponse( HttpServletResponse response, ErrorCode errorCode) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(errorCode.getHttpStatus().value());

        Map<String, Object> result = Map.of(
                "success", false,
                "data", null,
                "error", ExceptionDto.of(errorCode
                ));

        response.getWriter().write(JSONValue.toJSONString(result));
    }
}
