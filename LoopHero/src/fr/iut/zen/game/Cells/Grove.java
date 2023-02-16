package fr.iut.zen.game.Cells;

import fr.iut.zen.game.*;
import fr.iut.zen.game.Cards.Deck;
import fr.iut.zen.game.Entity.Hero;
import fr.iut.zen.game.Entity.RatWolf;
import fr.iut.zen.game.Property.Drawable;
import fr.iut.zen.game.Property.Dropable;
import fr.iut.zen.game.Resources.StableBranches;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;

public class Grove extends Paths {

	private Color color = Color.PINK;
	private int dayCount = 0;

	public Grove(CellOrientation orientation) {
		super(orientation);
	}

	@Override
	public void draw(Graphics2D graphics, float column, float line, float squareSize) {
		String pictureName = "pictures/Textures/Grove (1).png";
		Path grovePath = Path.of(pictureName);
		Drawable.drawImage(graphics, column, line, grovePath, squareSize);
		super.drawMonster(graphics, column, line, squareSize);
	}

	@Override
	public void drawInterface(Graphics2D graphics, float xOrigin, float Yorigin, float lenght, float height) {
		
		//String pictureName = "pictures/Cards/Grove.png";
		//Path slimePath = Path.of(pictureName);
		//Drawable.drawImage(graphics, xOrigin, Yorigin, slimePath,height);
		String pictureName = "pictures/Cards/GroveCard.png";
		Path path = Path.of(pictureName);
		Drawable.drawImage(graphics, (int)xOrigin, (int)Yorigin, path, (int)lenght, (int)height);

	}

	@Override
	public boolean buildable() {
		return false;
	}

	private boolean addRatWolfOnAnotherCell(Cell[][] cells, GridPosition position,int round) {
		if (Tools.posInMatrix(cells, position) && cells[position.line()][position.column()].isPath()) {
			Paths path = (Paths) (cells[position.line()][position.column()]);
			path.addNewMonster(new RatWolf(round));
			return true;
		}
		return false;
	}

	@Override
	public void newDay(Cell[][] cells, Hero hero, GridPosition position) {
		dayCount++;
		if (dayCount % 2 == 0) {
			Random random = new Random();
			int number = random.nextInt(100);

			if (number < 30) { // met le ratWolf dans une autre case


				ArrayList<GridPosition> adjacentCell = new ArrayList<>();
				adjacentCell.add(new GridPosition(position.line(), position.column() + 1));
				adjacentCell.add(new GridPosition(position.line(), position.column() - 1));
				adjacentCell.add(new GridPosition(position.line() + 1, position.column()));
				adjacentCell.add(new GridPosition(position.line() - 1, position.column()));

				while (true) {
					number = random.nextInt(4); // case grove actuelle exclu
					if (addRatWolfOnAnotherCell(cells, adjacentCell.get(number), hero.getRound())) {
						break;
					}

				}
			} else {
				super.addNewMonster(new RatWolf(hero.getRound()));
			}
		}
	}

	@Override
	public ArrayList<Dropable> HeroIsOnPath(Hero hero, Deck deck) {
		ArrayList<Dropable> list = super.HeroIsOnPath(hero, deck);
		list.add(new StableBranches());
		return list;
	}
}
