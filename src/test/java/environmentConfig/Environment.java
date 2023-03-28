package environmentConfig;

import org.aeonbits.owner.Config;

@Config.Sources({"file:src/test/java/environmentConfig/${environment}.properties"})
public interface Environment extends Config {

    @DefaultValue("window")
    String osName();

    @Key("url")
    String url();

    @Key("username")
    String userName();

    @Key("password")
    String password();

    @Key("email")
    String email();
}
