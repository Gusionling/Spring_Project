package com.hk.review.contrant;

import java.util.List;

public class Constrants {

    public static final String USER_NICKNAME_PREFIX = "USER_";
    public static String USER_ROLE = "ROLE_USER";

    public static List<String> NO_NEED_AUTH_URLS = List.of(
            "/v3/api-docs.html/**",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/api/v1/auth/login"
    );


}
