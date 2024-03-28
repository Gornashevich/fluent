package api.services.auth;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@Data
public class GetTokenRequest {
    private String username;
    private String password;
}
