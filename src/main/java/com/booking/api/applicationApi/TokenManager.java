package com.booking.api.applicationApi;

import com.booking.api.RestResource;
import com.booking.requests.pojo.lombok.Auth;

public class TokenManager {
    private static String token;
    public static String getToken() {
        if (token == null) {
            Auth auth = Auth.builder()
                    .username("admin")
                    .password("password123")
                    .build();

            token = RestResource.authorizeAccount(auth)
                    .then()
                    .extract()
                    .path("token");
        }
        return token;
    }
}
