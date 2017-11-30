package root.db.type;

public enum Dictionary {

    CONTENT_NAME("Название товара"),
    FABRICATOR_NAME("Название фирмы производителя");

    private String description;

    Dictionary(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}