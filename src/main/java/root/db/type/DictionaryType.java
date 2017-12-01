package root.db.type;

public enum DictionaryType {

    CONTENT_NAME("Название товара"),
    FABRICATOR_NAME("Название фирмы производителя");

    private final String description;

    DictionaryType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}