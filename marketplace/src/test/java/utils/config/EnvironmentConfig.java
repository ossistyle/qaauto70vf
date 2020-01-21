package utils.config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:environment.properties")
public interface EnvironmentConfig extends Config{

    @Config.Key("url")
    String url();

    @Config.Key("env")
    String env();

    @Config.Key("portal")
    String portal();

}
