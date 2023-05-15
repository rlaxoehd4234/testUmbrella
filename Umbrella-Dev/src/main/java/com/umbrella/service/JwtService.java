package com.umbrella.service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public interface JwtService {

    String createAccessToken(String email);
    String createRefreshToken(String email);

    void updateRefreshToken(String email, String refreshToken);

    void destroyRefreshToken(String email);

    void sendAccessAndRefreshToken(HttpServletResponse response, String accessToken, String refreshToken) throws IOException;

    void setRefreshTokenInCookie(HttpServletResponse response, String refreshToken);

    void sendAccessToken(HttpServletResponse response, String accessToken);

    Optional<String> extractAccessToken(HttpServletRequest request) throws IOException, ServletException;

    Optional<String> extractRefreshToken(HttpServletRequest request) throws IOException, ServletException;

    Optional<String> extractEmail(String accessToken);

    Optional<String> extractSubject(String accessToken);

    void setAccessTokenHeader(HttpServletResponse response, String accessToken);
    void setRefreshTokenHeader(HttpServletResponse response, String refreshToken);

    int isTokenValid(String token);
}
