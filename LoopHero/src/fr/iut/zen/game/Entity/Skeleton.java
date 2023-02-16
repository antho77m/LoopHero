package fr.iut.zen.game.Entity;

import fr.iut.zen.game.Property.Drawable;
import fr.iut.zen.game.Resources.PitifulRemains;
import fr.iut.zen.game.Resources.ResourceElement;

import java.awt.*;
import java.nio.file.Path;

public class Skeleton extends AbstractMonster{
    private Color color = Color.WHITE;
    public Skeleton(int round) {
        super(85, 12, 9, 0, 0, 5, 0, 0, round);
    }

    @Override
    public String name() {
        return "squelette";
    }

    @Override
    public void draw(Graphics2D graphics, float line, float column, float squareSize) {
        String pictureName = "pictures/monsters/skeleton.png";
        Path skeletonPath = Path.of(pictureName);
        Drawable.drawImage(graphics, line, column, skeletonPath,squareSize);
    }

    @Override
    public ResourceElement itemDrop() {
        return new PitifulRemains();
    }


}
