package fr.iut.zen.game.Entity;

import fr.iut.zen.game.Property.Drawable;
import fr.iut.zen.game.Resources.LivingFabric;
import fr.iut.zen.game.Resources.ResourceElement;

import java.awt.*;
import java.nio.file.Path;

public class Spider extends AbstractMonster{

    Color color = Color.lightGray;

    public Spider(int round) {
        super(45, 8, 3.1F, 0, 0, 10, 0, 0, round);
    }

    @Override
    public String name() {
        return "arraignée";
    }

    @Override
    public void draw(Graphics2D graphics, float line, float column, float squareSize) {
        String pictureName = "pictures/monsters/Spider.png";
        Path spiderPath = Path.of(pictureName);
        Drawable.drawImage(graphics, line, column, spiderPath,squareSize);
    }

    @Override
    public ResourceElement itemDrop() {
        return new LivingFabric();
    }
}
