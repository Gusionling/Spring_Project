package com.hk.review.api.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {

    @NotBlank
    @Schema(description = "providerId", example = "203912941")
    private Long providerId;

    @NotBlank
    @Schema(description = "이메일", example = "example.google.com")
    private String email;

    @NotBlank
    @Schema(description = "비밀번호", example = "1234")
    private String password;

    @NotBlank
    @Schema(description = "플랫폼", example = "general")
    private String platform;


}
