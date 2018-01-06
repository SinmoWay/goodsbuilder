package root.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import root.db.dto.ProductDTO;
import root.ui.builder.AlertBuilder;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Component
public class JsonConverter {

    private static final ObjectMapper converter = new ObjectMapper();

    @Autowired
    private AlertBuilder alertBuilder;

    public void convertToJsonAndSaveToFile(List<ProductDTO> toJson, String fullFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fullFileName))) {
            writer.write(converter.writeValueAsString(toJson));
            writer.flush();
        } catch (IOException ex) {
            alertBuilder.showError("Ошибка парсинга в json", ex.toString());
        }
    }

}