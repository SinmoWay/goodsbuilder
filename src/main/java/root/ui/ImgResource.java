package root.ui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import root.db.type.ImgResources;

public final class ImgResource {

    private ImgResource() {
    }

    public static ImageView getSqView(ImgResources res, int size) {
        ImageView imgView = new ImageView(res.path());

        setSqSize(imgView, size);
        setNiceToSee(imgView);

        return imgView;
    }

    public static void initSqView(ImageView imgView, ImgResources res, int size) {
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