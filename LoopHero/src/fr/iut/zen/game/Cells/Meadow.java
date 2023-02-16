package fr.iut.zen.game.Cells;

import fr.iut.zen.game.Property.Drawable;
import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.Entity.Hero;
import fr.iut.zen.game.Resources.Ration;
import fr.iut.zen.game.Resources.ResourceElement;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.nio.file.Path;

public class Meadow extends LandScape {

	private final Color color = Color.CYAN;

	@Override
	public void draw(Graphics2D graphics, float column, float line, float squareSize) {
		String pictureName = "pictures/Textures/Meadow.png";
		Path meadowPath = Path.of(pictureName);
		Drawable.drawImage(graphics, column, line, meadowPath, squareSize);
	}

	@Override
	public void drawInterface(Graphics2D graphics, float xOrigin, float Yorigin, float lenght, float height) {

		//String pictureName = "pictures/Cards/meadow.jpg";
		//Path slimePath = Path.of(pictureName);
		//Drawable.drawImage(graphics, xOrigin, Yorigin, slimePath,height);
		String pictureName = "pictures/Cards/MeadowCard.png";
		Path path = Path.of(pictureName);
		Drawable.drawImage(graphics, (int)xOrigin, (int)Yorigin, path, (int)lenght, (int)height);
	}

	@Override
	public void newDay(Cell[][] cells, Hero hero, GridPosition position) {
		hero.addLife(2);
	}

	@Override
	public boolean buildable() {
		return false;
	}

	@Override
	public ResourceElement set(Cell[][] cells, Hero hero, GridPosition position) {
		return new Ration();
	}
}
