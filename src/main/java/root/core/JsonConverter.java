package root.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import root.db.dto.ProductDTO;
import root.db.type.NodeType;
import root.ui.builder.AlertBuilder;

import java.io.*;
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
            alertBuilder.showError("Ошибка парсинга из json", ex.toString());
            return null;
        }
    }

}