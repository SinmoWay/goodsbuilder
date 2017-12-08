package root.db.type;

public enum ProductType implements NodeType {

    CARTON("Подарки в картоне", "carton", false),
    TEXTILE("Подарки в текстиле", "textile", false),
    TIN("Жестянные подарки", "tin", false),
    VIP("ВИП подарки", "vip", false),

    FLOUR("Мука", "flour", true),
    SALT("Соль", "salt", true),
    SUGAR("Сахар", "sugar", true),
    CERALS("Крупы", "cereals", true);

    private final String description;
    private final String fileName;
    private final boolean defective;

    ProductType(String description, String fileName, boolean defective) {
        this.description = description;
        this.fileName = fileName;
        this.defective = defective;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public String getFileName() {
        return fileName + ".json";
    }

    public boolean isDefective() {
        return defective;
    }

    @Override
    public String toString() {
        return description;
    }

}