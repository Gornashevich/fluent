package api.services.datacorrections.responses.getdata;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Jacksonized
@Builder
@Data
public class DataCorrectionsGetDataResponse {
    private Pagination pagination;
    private Data data;

    @Jacksonized
    @Builder
    @lombok.Data
    public static class Data{
        private List<Respondent> respondents;
    }

    @Jacksonized
    @Builder
    @lombok.Data
    public static class NameAndAnswers {
        private String name;
        private List<String> answers;
    }

    @Jacksonized
    @Builder
    @lombok.Data
    public static class Pagination{
        private Integer total;
        private String last;
        private String first;
    }

    @Jacksonized
    @Builder
    @lombok.Data
    public static class QuestionResponse {
        private String cleanedBy;
        private List<NameAndAnswers> responsesCleaned;
        private String cleanedUpdateDateTime;
        private String presentationKey;
        private List<NameAndAnswers> responses;
        private String questionName;
    }

    @Jacksonized
    @Builder
    @lombok.Data
    public static class Respondent {
        private List<QuestionResponse> questionResponses;
        private String respondentId;
        private String status;
    }


}
