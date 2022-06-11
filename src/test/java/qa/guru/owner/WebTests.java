package qa.guru.owner;

import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static qa.guru.owner.config.WebConfig.*;

public class WebTests {
    @Test
    @DisplayName("Считывание с данными для локального запуска на chrome")
    public void WebLocalTest() {
        System.setProperty("LocalOrRemote", "Local");
        WEB config = ConfigFactory
                .create(WEB.class, System.getProperties());

        assertThat(config.browser()).isEqualTo("Chrome");
        assertThat(config.version()).isEqualTo("99");
        assertThat(config.host()).isEqualTo("https://localhost:4444/wd/hub");
    }
    @Test
    @DisplayName("Считывание с данными для удаленного запуска на selenoid")
    public void WebRemoteTest() {
        System.setProperty("LocalOrRemote", "Remote");
        WEB config = ConfigFactory
                .create(WEB.class, System.getProperties());

        assertThat(config.browser()).isEqualTo("Chrome");
        assertThat(config.version()).isEqualTo("100");
        assertThat(config.host()).isEqualTo("https://user1:1234@selenoid.autotests.cloud/wd/hub/");
    }
}
