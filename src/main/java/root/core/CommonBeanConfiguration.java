package root.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import root.ui.builder.AlertBuilder;
import root.ui.builder.ImgResourceBuilder;
import root.ui.builder.TreeBuilder;

import java.io.IOException;

@Configuration
public class CommonBeanConfiguration {

    @Bean
    public AlertBuilder getAlertBuilder() {
        return new AlertBuilder();
    }

    @Bean
    public ImgResourceBuilder getImgResourceBuilder() {
        return new ImgResourceBuilder();
    }

    @Bean
    public TreeBuilder getTreeBuilder() throws IOException {
        return new TreeBuilder();
    }

    @Bean
    public JsonConverter getJsonConverter() {
        return new JsonConverter();
    }

}