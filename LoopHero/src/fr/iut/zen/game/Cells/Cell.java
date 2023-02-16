package fr.iut.zen.game.Cells;

import fr.iut.zen.game.Cards.Deck;
import fr.iut.zen.game.Property.Drawable;
import fr.iut.zen.game.Property.Dropable;
import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.Entity.Hero;
import fr.iut.zen.game.Resources.ResourceElement;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public interface Cell extends Drawable, Serializable {

	public boolean isPath();

	// indique si la cellules est posable par rapport au carte alentour, la premiere position de cells
	// est la tuile sur la quelle elle veut etre posé
	public boolean isPosable(ArrayList<Cell> cells);

	public ResourceElement set(Cell[][] cells, Hero hero, GridPosition position);

	//action when is it a new day
	public void newDay(Cell[][] cells, Hero hero, GridPosition position);

	default public Cell newRound(Hero hero, Cell[][] matrix,GridPosition position) {
		return null;
	}

	public void drawInterface(Graphics2D graphics, float xOrigin, float Yorigin, float lenght,float height);
	// dessine la carte contenant la cellule




	public boolean buildable();

	//bout de code exécuter au moment d'un changement de tuiles
	public void replace(Cell cell);

	//action when Hero is on the Path
	public ArrayList<Dropable> HeroIsOnPath(Hero hero, Deck deck);

	default public boolean isRock(){
		return false;
	}
}

