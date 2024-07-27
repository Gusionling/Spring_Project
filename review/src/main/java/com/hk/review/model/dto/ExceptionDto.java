package com.hk.review.model.dto;

import com.hk.review.exception.enums.ErrorCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(name = "ExceptionDto", description = "API 예외 발생 시 응답 DTO")
public class ExceptionDto {
    @Schema(name = "code", description = "에러 코드")
    private final Integer code;

    @Schema(name = "message", description = "에러 메시지")
    private final String message;

    public ExceptionDto(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public static ExceptionDto of(ErrorCode errorCode) {
        return new ExceptionDto(errorCode);
    }
}
