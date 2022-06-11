package qa.guru.owner.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "file:/tmp/secureAPI.properties",
        "classpath:API.properties"
})
public interface ApiConfig extends Config {
    @Key("baseurl")
    String baseurl();

    @Key("token")
    String token();
}
