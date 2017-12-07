package root.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import root.ui.builder.TreeBuilder;

import java.io.IOException;

@Configuration
public class CommonBeanConfiguration {

    @Bean
    public TreeBuilder getTreeBuilder() throws IOException {
        return new TreeBuilder();
    }

}