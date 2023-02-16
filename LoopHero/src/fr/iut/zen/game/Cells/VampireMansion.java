package fr.iut.zen.game.Cells;

import fr.iut.zen.game.Property.Drawable;

import java.awt.*;
import java.nio.file.Path;

public class VampireMansion extends BorderPath{
    private Color color = Color.green;

    @Override
    public boolean buildable() {
        return false;
    }

    @Override
    public void draw(Graphics2D graphics, float column, float line, float squareSize) {
        String pictureName = "pictures/Textures/VampireMansion.png";
        Path vampiremansionPath = Path.of(pictureName);
        Drawable.drawImage(graphics, column, line, vampiremansionPath, squareSize);
    }

    @Override
    public void drawInterface(Graphics2D graphics, float xOrigin, float Yorigin, float lenght, float height) {
        String pictureName = "pictures/Cards/VampireMansionCard.png";
        Path path = Path.of(pictureName);
        Drawable.drawImage(graphics, (int)xOrigin, (int)Yorigin, path, (int)lenght, (int)height);
    }
}
