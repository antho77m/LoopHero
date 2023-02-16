package fr.iut.zen.game.Property;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public interface Drawable {

    public void draw (Graphics2D graphics, float column, float line, float squareSize);

    static void drawImage(Graphics2D graphics, float line, float column, Path path, float squareSize) {
        drawImage(graphics, line, column, path, squareSize, squareSize);
    }

    static void drawImage(Graphics2D graphics, float line, float column, Path path, float length, float height) {
        try (InputStream in = Files.newInputStream(path)) {
            BufferedImage img = ImageIO.read(in);
            AffineTransformOp scaling = new AffineTransformOp(AffineTransform
                    .getScaleInstance(length / (double) img.getWidth(), height / (double) img.getHeight()),
                    AffineTransformOp.TYPE_BILINEAR);

            graphics.drawImage(img, scaling, (int) line, (int) column);
        } catch (IOException e) {
            throw new RuntimeException("problème d'affichage : " + path.getFileName());
        }
    }



}
