package root.ui.builder;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import root.db.type.ImgResource;

public final class ImgResourceBuilder {

    private ImgResourceBuilder() {
    }

    public static ImageView getSqView(ImgResource res, int size) {
        ImageView imgView = new ImageView(res.path());

        setSqSize(imgView, size);
        setNiceToSee(imgView);

        return imgView;
    }

    public static void initSqView(ImageView imgView, ImgResource res, int size) {
        imgView.setImage(new Image(res.path()));

        setSqSize(imgView, size);
        setNiceToSee(imgView);
    }

    private static void setNiceToSee(ImageView imgView) {
        imgView.setPreserveRatio(true);
        imgView.setSmooth(true);
        imgView.setCache(true);
    }

    private static void setSqSize(ImageView imgView, int size) {
        imgView.setFitWidth(size);
        imgView.setFitHeight(size);
    }

}