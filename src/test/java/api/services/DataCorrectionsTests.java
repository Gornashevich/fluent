package api.services;

import api.ApiBaseTest;
import api.services.datacorrections.DataCorrectionsService;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.Map;

public class DataCorrectionsTests extends ApiBaseTest {

    private DataCorrectionsService dataCorrectionsService;

    @BeforeClass
    private void initServices() {
        dataCorrectionsService = new DataCorrectionsService(Specifications.getSpecWithToken());
    }

    @Test
    public void getDataWithDefaultQueryParams() {
        var mosAccountId = "31";
        var mosProjectId = "adc6dd4d08";
        Map<String, ?> queryParams = Collections.emptyMap();
        var dataCorrectionsGetDataResponse = dataCorrectionsService
                .getData(mosAccountId, mosProjectId, queryParams);

        var respondents = dataCorrectionsGetDataResponse
                .getSuccessfulBody()
                .getData()
                .getRespondents();
        Assertions.assertThat(respondents).as("respondents from response").isNotEmpty();
    }
}
