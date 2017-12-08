package root.db.type;

public enum DictionaryType implements NodeType {

    //добавилось значение? Добавь в data.sql
    CONTENT_NAME("Название товара"),
    FABRICATOR_NAME("Название фирмы производителя");

    private final String description;

    DictionaryType(String description) {
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }

}