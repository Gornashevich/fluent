package api.services;

import api.services.auth.AuthService;
import api.services.auth.GetTokenRequest;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.List;

import static config.ConfigManager.config;

public abstract class Specifications {

    public static RequestSpecification getDefaultBaseSpec(String baseUrl) {
        return new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .setContentType(ContentType.JSON)
                .addFilters(List.of(
                        new AllureRestAssured(),
                        new RequestLoggingFilter(),
                        new ResponseLoggingFilter()
                ))
                .build();
    }

    public static RequestSpecification getDefaultBaseSpec() {
        return getDefaultBaseSpec(config.baseAppUrl());
    }

    public static RequestSpecification getSpecWithToken(String baseAppUrl,
                                                        String loginBaseUrl,
                                                        String username,
                                                        String password,
                                                        String loginMosKey) {
        RequestSpecification baseSpecForAuth = getDefaultBaseSpec(loginBaseUrl);
        GetTokenRequest getTokenRequest = GetTokenRequest.builder()
                .username(username)
                .password(password)
                .build();
        String accessToken = new AuthService(baseSpecForAuth)
                .getToken(getTokenRequest, loginMosKey)
                .getSuccessfulBody()
                .getAccessToken();

        return getDefaultBaseSpec(baseAppUrl)
                .auth()
                .preemptive()
                .oauth2(accessToken);

    }

    public static RequestSpecification getSpecWithToken(String username, String password) {
        return getSpecWithToken(
                config.baseAppUrl(),
                config.loginUrl(),
                username,
                password,
                config.loginMosKey());
    }

    public static RequestSpecification getSpecWithToken() {
        return getSpecWithToken(config.userEmail(), config.userPassword());
    }

}

