package fr.iut.zen.game.Cards;

import fr.iut.zen.game.Cells.Cell;
import fr.iut.zen.game.Property.Drawable;
import fr.iut.zen.game.Property.Dropable;
import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.Property.Selectable;

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.ArrayList;

public class Card implements Dropable, Drawable, Selectable, Serializable {

    private final Cell cellContains;

    @Override
    public boolean isCard() {
        return true;
    }

    public Card(Cell cellContains) {
        this.cellContains = cellContains;
    }

    public boolean isPosable(ArrayList<Cell> cells){
        return cellContains.isPosable(cells);
    }

    public Cell drop(Cell[][] cells, GridPosition position){
        return cellContains;
    }

    @Override
    public void draw(Graphics2D graphics, float column, float line, float squareSize) {

        cellContains.drawInterface(graphics,column,line, (float) (squareSize*1.5),squareSize*2.5f);
    }

    @Override
    public Boolean checkPosition(Point2D.Float location, float column, float line, float squareSize){
        return location.x>column && location.x<column+squareSize*(1.5) && location.y>line && location.y<line+squareSize*(2.5);
    }

}
