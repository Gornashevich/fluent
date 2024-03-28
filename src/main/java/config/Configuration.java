package config;


import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "file:.env.properties"
})
public interface Configuration extends Config {

    @Key("BASE_APP_URL")
    String baseAppUrl();

    @Key("USER_EMAIL")
    String userEmail();

    @Key("USER_PASSWORD")
    String userPassword();

    @Key("LOGIN_URL")
    String loginUrl();
    @Key(("LOGIN_MOS_KEY"))
    String loginMosKey();
}
