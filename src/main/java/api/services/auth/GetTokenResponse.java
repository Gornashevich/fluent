package api.services.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetTokenResponse {
    @JsonProperty("expiresIn")
    private Integer expiresIn;

    @JsonProperty("isError")
    private Boolean isError;

    @JsonProperty("errorType")
    private String errorType;

    @JsonProperty("scope")
    private String scope;

    @JsonProperty("raw")
    private String raw;

    @JsonProperty("httpErrorReason")
    private String httpErrorReason;


    @JsonProperty("accessToken")
    private String accessToken;

    @JsonProperty("tokenType")
    private String tokenType;

}
