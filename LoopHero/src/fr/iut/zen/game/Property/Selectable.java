package fr.iut.zen.game.Property;

import java.awt.geom.Point2D;

public interface Selectable {

    public Boolean checkPosition(Point2D.Float location, float column, float line, float squareSize);
}
