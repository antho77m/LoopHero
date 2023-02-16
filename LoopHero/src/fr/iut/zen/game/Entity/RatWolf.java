package fr.iut.zen.game.Entity;

import fr.iut.zen.game.Property.Drawable;
import fr.iut.zen.game.Resources.LivingFabric;
import fr.iut.zen.game.Resources.ResourceElement;

import java.awt.Color;
import java.awt.Graphics2D;
import java.nio.file.Path;

public class RatWolf extends AbstractMonster {
	private Color color = Color.MAGENTA;

	public RatWolf(int round) {
		super(40,16,3.6f,0,0,0.1f,0.5f,0,round);
	}


	@Override
	public void draw(Graphics2D graphics, float line, float column, float squareSize) {
		String pictureName = "pictures/monsters/Ratwolf.png";
		Path ratwolfPath = Path.of(pictureName);
		Drawable.drawImage(graphics, line, column, ratwolfPath,squareSize);

	}





	@Override
	public ResourceElement itemDrop() {
		return new LivingFabric();
	}

	@Override
	public String name() {
		return "rat wolf";
	}
}
