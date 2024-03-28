package api.services;

import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;

public class RestResponse<SUCCESS_BODY> {
    private final Response response;
    private TypeRef<SUCCESS_BODY> success_bodyTypeRef;

    public RestResponse(Response response, TypeRef<SUCCESS_BODY> success_bodyTypeRef) {
        this.response = response;
        this.success_bodyTypeRef = success_bodyTypeRef;
    }

    public Response getRawResponse() {
        return response;
    }

    public SUCCESS_BODY getSuccessfulBody() {
        return response.as(success_bodyTypeRef);
    }
}