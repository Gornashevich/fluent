package api.services;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class Service {
    private final RequestSpecification requestSpecification;

    public Service(RequestSpecification requestSpecification) {
        this.requestSpecification = given(requestSpecification);
    }

    protected RequestSpecification requestSpec() {
        return given(requestSpecification);
    }

    protected Response executeRequest(RequestSpecification requestSpec, Method method, int statusCode, String path, Object... pathArgs) {
        Response response = requestSpec.request(method, path, pathArgs);
        return checkStatusCode(response, statusCode);
    }
    protected Response get(RequestSpecification requestSpec, int statusCode, String path, Object... pathArgs) {
        return executeRequest(requestSpec, Method.GET, statusCode, path, pathArgs);
    }

    protected Response put(RequestSpecification requestSpec, int statusCode, String path, Object... pathArgs) {
        return executeRequest(requestSpec, Method.PUT, statusCode, path, pathArgs);
    }

    protected Response post(RequestSpecification requestSpec, int statusCode, String path, Object... pathArgs) {
        return executeRequest(requestSpec, Method.POST, statusCode, path, pathArgs);
    }

    protected Response delete(RequestSpecification requestSpec, int statusCode, String path, Object... pathArgs) {
        return executeRequest(requestSpec, Method.DELETE, statusCode, path, pathArgs);
    }

    protected Response patch(RequestSpecification requestSpec, int statusCode, String path, Object... pathArgs) {
        return executeRequest(requestSpec, Method.PATCH, statusCode, path, pathArgs);
    }


    protected Response checkStatusCode(Response response, int statusCode) {
        return response.then()
                .statusCode(statusCode)
                .extract()
                .response();
    }
}
