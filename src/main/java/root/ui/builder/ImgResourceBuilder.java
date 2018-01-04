package root.ui.builder;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.springframework.stereotype.Component;
import root.db.type.ImgResource;

@Component
public final class ImgResourceBuilder {

    public ImageView getSqView(ImgResource res, int size) {
        ImageView imgView = new ImageView(res.path());

        setSqSize(imgView, size);
        setNiceToSee(imgView);

        return imgView;
    }

    public void initView(ImageView imgView, ImgResource res) {
        imgView.setImage(new Image(res.path()));
        setNiceToSee(imgView);
    }

    public void initView(ImageView imgView, ImgResource res, int size) {
        setSqSize(imgView, size);
        initView(imgView, res);
    }

    public void initView(ImageView imgView, String uri) {
        imgView.setImage(new Image(uri));
        setNiceToSee(imgView);
    }

    private void setNiceToSee(ImageView imgView) {
        imgView.setPreserveRatio(true);
        imgView.setSmooth(true);
        imgView.setCache(true);
    }

    private void setSqSize(ImageView imgView, int size) {
        imgView.setFitWidth(size);
        imgView.setFitHeight(size);
    }

}