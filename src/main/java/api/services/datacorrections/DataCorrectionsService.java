package api.services.datacorrections;

import api.services.Endpoints;
import api.services.RestResponse;
import api.services.Service;
import api.services.datacorrections.responses.getdata.DataCorrectionsGetDataResponse;
import io.qameta.allure.Step;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static org.apache.http.HttpStatus.SC_OK;


public class DataCorrectionsService extends Service {
    public DataCorrectionsService(RequestSpecification requestSpecification) {
        super(requestSpecification);
    }

    @Step("corrections: get data")
    public RestResponse<DataCorrectionsGetDataResponse> getData(String mosAccountId,
                                                                String mosProjectId,
                                                                Map<String, ?> queryParams,
                                                                int statusCode) {

        //TODO: analyze required headers and refactor getting requestSpec to use them in generic way
        RequestSpecification requestSpec = requestSpec()
                .header("Mos-Account-Id", mosAccountId)
                .header("Mos-Project-Id", mosProjectId)
                .queryParams(queryParams);
        Response response = get(requestSpec, statusCode, Endpoints.API_V1_CORRECTIONS_DATA);
        return new RestResponse<>(response, new TypeRef<>() {
        });
    }

    public RestResponse<DataCorrectionsGetDataResponse> getData(String mosAccountId,
                                                                String mosProjectId,
                                                                Map<String, ?> queryParams) {
        return getData(mosAccountId, mosProjectId, queryParams, SC_OK);
    }
}
