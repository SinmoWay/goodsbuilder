package root.db.type;

public enum ImgResource {

    ADD("add"),
    EDIT("edit"),
    REMOVE("remove"),
    REFRESH("refresh"),

    FACE_BAD("bad"),
    FACE_NORMAL("normal"),
    FACE_GOOD("good"),

    MENU_CLOSE("menu_to_close"),
    MENU_OPEN("menu_to_open"),

    TUX("tux"),
    RUB("rub"),
    SETTINGS("settings"),
    EYE("eye"),

    UNLOAD("unload"),
    UPLOAD("upload");

    private static final String PREFIX_PATH = "img/";
    private static final String SUFFIX_PATH = ".png";
    private final String path;

    ImgResource(String path) {
        this.path = path;
    }

    public String path() {
        return PREFIX_PATH + path + SUFFIX_PATH;
    }

}