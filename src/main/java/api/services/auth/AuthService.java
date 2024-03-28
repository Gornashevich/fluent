package api.services.auth;

import api.services.Endpoints;
import api.services.RestResponse;
import api.services.Service;
import io.qameta.allure.Step;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static org.apache.http.HttpStatus.SC_OK;

public class AuthService extends Service {
    public AuthService(RequestSpecification requestSpecification) {
        super(requestSpecification);
    }

    @Step("login: get token")
    public RestResponse<GetTokenResponse> getToken(GetTokenRequest tokenRequest, String mosLoginKey) {
        RequestSpecification requestSpec = requestSpec()
                .header("Mos-Login-Key", mosLoginKey)
                .body(tokenRequest);
        Response response = post(requestSpec, SC_OK, Endpoints.LOGIN);
        return new RestResponse<>(response, new TypeRef<>() {
        });
    }
}
