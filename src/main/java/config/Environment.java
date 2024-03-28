package config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
@Getter
@RequiredArgsConstructor
public enum Environment {
    DEV("https://dev-survey-designer-api.marketonce.com", "aliaksandr.viartseika@marketonce.com", "mo_Survey2023"),
    QA("https://qa-survey-designer.marketonce.com", "testuser1@marketonce.com", "QATest123"),
    STAGE("https://stage-survey-designer-api.marketonce.com", "aliaksandr.viartseika@marketonce.com", "mo_Survey2023");

    private final String baseUrl;
    private final String userName;
    private final String password;
}
