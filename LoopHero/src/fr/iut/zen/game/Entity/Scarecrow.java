package fr.iut.zen.game.Entity;

import fr.iut.zen.game.Property.Drawable;
import fr.iut.zen.game.Resources.CraftFragment;
import fr.iut.zen.game.Resources.Ration;
import fr.iut.zen.game.Resources.ResourceElement;
import fr.iut.zen.game.Resources.StableBranches;

import java.awt.*;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;

public class Scarecrow extends AbstractMonster{

    private Color color = Color.BLACK;
    public Scarecrow(int round) {
        super(100, 18, 8.25F,0, 0, 0, 0, 0, round);
    }

    @Override
    public String name() {
        return "épouventail";
    }

    @Override
    public void draw(Graphics2D graphics, float line, float column, float squareSize) {
        String pictureName = "pictures/monsters/Scarecrow.png";
        Path scarecrowPath = Path.of(pictureName);
        Drawable.drawImage(graphics, line, column, scarecrowPath,squareSize);
    }

    @Override
    public ResourceElement itemDrop() {
        Random random = new Random();
        int randomNumber = random.nextInt(3);
        return List.of(new StableBranches(), new Ration(), new CraftFragment()).get(randomNumber);
    }
}
