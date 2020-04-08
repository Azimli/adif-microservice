package auditing.security;


public final class LoginJwtTokenResponse {

    private String accessToken;

    public LoginJwtTokenResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}