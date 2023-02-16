package fr.iut.zen.game.Entity;

import fr.iut.zen.game.Property.Drawable;
import fr.iut.zen.game.Resources.CraftFragment;
import fr.iut.zen.game.Resources.ResourceElement;
import fr.iut.zen.game.Resources.StableBranches;

import java.awt.*;
import java.nio.file.Path;
import java.util.Random;

public class Chest extends AbstractMonster{
    private Color color = Color.ORANGE;

    public Chest(int round) {
        super(100, 11, 0.6F, 0, 0, 0, 0, 0, round);
    }

    @Override
    public String name() {
        return "coffre";
    }

    @Override
    public void draw(Graphics2D graphics, float line, float column, float squareSize) {
        String pictureName = "pictures/monsters/Chest.png";
        Path chestPath = Path.of(pictureName);
        Drawable.drawImage(graphics, line, column, chestPath,squareSize);
    }

    @Override
    public ResourceElement itemDrop() {
        Random random = new Random();
        if (random.nextInt(100) < 50) {
            return new StableBranches();
        }
        else {
            return new CraftFragment();
        }
    }
}
