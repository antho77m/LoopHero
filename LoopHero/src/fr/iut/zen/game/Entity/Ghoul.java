package fr.iut.zen.game.Entity;

import fr.iut.zen.game.Property.Drawable;
import fr.iut.zen.game.Resources.PitifulRemains;
import fr.iut.zen.game.Resources.ResourceElement;

import java.awt.*;
import java.nio.file.Path;

public class Ghoul extends AbstractMonster {

    Color color = Color.BLACK;

    public Ghoul(int round) {
        super(50, 30, 5, 0, 0, 0, 0, 0, round);
    }

    @Override
    public String name() {
        return "ghoul";
    }

    @Override
    public void draw(Graphics2D graphics, float line, float column, float squareSize) {
        String pictureName = "pictures/monsters/Ghoul.png";
        Path ghoulPath = Path.of(pictureName);
        Drawable.drawImage(graphics, line, column, ghoulPath,squareSize);
    }

    @Override
    public ResourceElement itemDrop() {
        return new PitifulRemains();
    }
}