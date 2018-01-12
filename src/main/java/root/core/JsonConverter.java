package root.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import root.db.dto.ProductDTO;
import root.db.type.NodeType;
import root.ui.builder.DialogBuilder;

import java.io.*;
import java.util.Comparator;
import java.util.List;

@Component
public class JsonConverter {

    private static final Logger log = LogManager.getLogger(JsonConverter.class);
    private static final ObjectMapper converter = new ObjectMapper();

    @Autowired
    private DialogBuilder dialogBuilder;

    public void convertToJsonAndSaveToFile(List<ProductDTO> toJson, String fullFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fullFileName))) {
            toJson.sort(
                    Comparator.comparing(ProductDTO::getDescription)
                            .thenComparing(ProductDTO::getPrice)
                            .thenComparing(ProductDTO::getWeight)
            );
            writer.write(converter.writeValueAsString(toJson));
            writer.flush();
        } catch (IOException ex) {
            dialogBuilder.showError("Ошибка парсинга в json", "Сообщите разработчику. Не забудьте логи");
            log.error("Ошибка парсинга в json", ex);
        }
    }

    public List<ProductDTO> loadFromFile(String fullFileName, NodeType type) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fullFileName))) {
            StringBuilder json = new StringBuilder();
            String currString;
            while ((currString = reader.readLine()) != null) {
                json.append(currString);
            }
            List<ProductDTO> dtos = converter.readValue(json.toString(), converter.getTypeFactory().constructCollectionType(List.class, ProductDTO.class));
            dtos.forEach(dto -> dto.setNodeType(type));
            return dtos;
        } catch (IOException ex) {
            dialogBuilder.showError("Ошибка парсинга из json", "Сообщите разработчику. Не забудьте логи");
            log.error("Ошибка парсинга из json", ex);
            return null;
        }
    }

}