package qa.guru.owner;

import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qa.guru.owner.config.ApiConfig;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ApiTests {
    @Test
    @DisplayName("Тест на передачу параметров через файл на файловой системе")
    public void testClasspathRemote() throws IOException {
        String content = "baseurl=https://mail.ru/\ntoken=666666KKK";
        Path propsPath = Paths.get("/tmp/secureAPI.properties");
        //Для падения теста закомментируй строку ниже
        Files.write(propsPath, content.getBytes(StandardCharsets.UTF_8));

        ApiConfig config = ConfigFactory
                .create(ApiConfig.class, System.getProperties());
        assertThat(config.baseurl()).isEqualTo("https://mail.ru/");
        assertThat(config.token()).isEqualTo("666666KKK");

        Files.delete(propsPath);
    }

    @Test
    @DisplayName("Тест на передачу параметров через системные property")
    public void testClasspathLocal() {

        ApiConfig config = ConfigFactory.create(ApiConfig.class, System.getProperties());

        assertThat(config.baseurl()).isEqualTo("https://www.google.com/");
        assertThat(config.token()).isEqualTo("ad44df297e4f49c487f8c6aa01fc90f5");
    }
}
