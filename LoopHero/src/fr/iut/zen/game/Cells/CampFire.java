package fr.iut.zen.game.Cells;

import fr.iut.zen.game.Cards.Deck;
import fr.iut.zen.game.Property.Drawable;
import fr.iut.zen.game.Property.Dropable;
import fr.iut.zen.game.Entity.Hero;

import java.awt.*;
import java.nio.file.Path;
import java.util.ArrayList;

public class CampFire extends Paths  {

	private final Color color = Color.RED;
	private CellOrientation orientation;

	public CampFire(CellOrientation orientation) {
		super(orientation);
	}

	@Override
	public ArrayList<Dropable> HeroIsOnPath(Hero hero, Deck deck) {
		hero.addLife(hero.maxLife() / 20);
		return null;
	}

	@Override
	public void draw(Graphics2D graphics, float column, float line, float squareSize) {
		String pictureName = "pictures/Textures/Campfire.png";
		Path path = Path.of(pictureName);
		Drawable.drawImage(graphics, column, line, path,squareSize);
	}

	@Override
	public void drawInterface(Graphics2D graphics, float xOrigin, float Yorigin, float lenght, float height) {
		
	}

	@Override
	public boolean buildable() {
		return false;
	}
}
