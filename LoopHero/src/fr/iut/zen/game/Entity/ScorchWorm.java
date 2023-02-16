package fr.iut.zen.game.Entity;

import fr.iut.zen.game.Property.Drawable;
import fr.iut.zen.game.Resources.LivingFabric;
import fr.iut.zen.game.Resources.ResourceElement;

import java.awt.*;
import java.nio.file.Path;

public class ScorchWorm extends AbstractMonster {
    private Color color = Color.RED;
    public ScorchWorm(int round) {
        super(85, 10, 2.5F, 0, 0, 10, 0, 0, round);
    }

    @Override
    public String name() {
        return "vers scorché";
    }

    @Override
    public void draw(Graphics2D graphics, float line, float column, float squareSize) {
        String pictureName = "pictures/monsters/ScorchWorm.png";
        Path scorchwormPath = Path.of(pictureName);
        Drawable.drawImage(graphics, line, column, scorchwormPath,squareSize);
    }

    @Override
    public ResourceElement itemDrop() {
        return new LivingFabric();
    }
}
