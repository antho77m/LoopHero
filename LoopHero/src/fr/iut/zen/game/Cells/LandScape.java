package fr.iut.zen.game.Cells;

import fr.iut.zen.game.Cards.Deck;
import fr.iut.zen.game.Property.Dropable;
import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.Entity.Hero;
import fr.iut.zen.game.Resources.ResourceElement;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class LandScape implements Cell {

    //private final Color color = Color.GRAY;
    private final Color color = Color.BLACK;


    @Override
    public boolean isPath() {
        return false;
    }

    @Override
    public boolean isPosable(ArrayList<Cell> cells) {
        for (Cell cell:cells) {
            if (cell.isPath()){
                return false;
            }
        }
        return cells.get(0).buildable();
    }

    @Override
    public ResourceElement set(Cell[][] cells, Hero hero, GridPosition position) {
        return null;
    }

    @Override
    public void newDay(Cell[][] cells, Hero hero, GridPosition position) {

    }

    @Override
    public ArrayList<Dropable> HeroIsOnPath(Hero hero, Deck deck) {
        return null;
    }

    @Override
    public void draw(Graphics2D graphics, float column, float line, float squareSize) {
        graphics.setColor(color);
        graphics.fill(new Rectangle2D.Float(column, line, squareSize, squareSize));
    }

    @Override
    public void drawInterface(Graphics2D graphics, float xOrigin, float Yorigin, float lenght, float height) {

    }

    @Override
    public boolean buildable() {
        return true;
    }

    @Override
    public void replace(Cell cell) {

    }



}
