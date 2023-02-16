package fr.iut.zen.game.Entity;

import fr.iut.zen.game.Property.Drawable;
import fr.iut.zen.game.Resources.CraftFragment;
import fr.iut.zen.game.Resources.ResourceElement;
import fr.iut.zen.game.Resources.ShapelessMass;

import java.awt.Graphics2D;
import java.nio.file.Path;
import java.util.Random;

public class Slime extends AbstractMonster {

	public Slime(int round) {
		super(35,13, 3.3f,0,0,0,0,0,round);

	}

	@Override
	public ResourceElement itemDrop() {
		Random random = new Random();
		if (random.nextInt(100)<50){
			return new CraftFragment();
		}
		return new ShapelessMass();

	}

	@Override
	public void draw(Graphics2D graphics, float line, float column, float squareSize) {
		String pictureName = "pictures/monsters/slime.png";
		Path slimePath = Path.of(pictureName);
		Drawable.drawImage(graphics, line, column, slimePath,squareSize);
	}

	@Override
	public String name() {
		return "slime";
	}
}
