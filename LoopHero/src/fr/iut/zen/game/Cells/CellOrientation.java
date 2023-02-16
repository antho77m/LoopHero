package fr.iut.zen.game.Cells;

import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.Tools;

import java.util.List;

public enum CellOrientation {

    HORIZONTAL,
    VERTICAL,
    TOP_LEFT,
    TOP_RIGHT,
    BOTTOM_LEFT,
    BOTTOM_RIGHT;

    public static CellOrientation getOrientation(List<GridPosition> pathsPosition, int pos) {

        GridPosition position = pathsPosition.get(pos);
        GridPosition position1;
        if (pos == pathsPosition.size() - 1) {
            position1 = pathsPosition.get(0);
        } else {
            position1 = pathsPosition.get(pos + 1);
        }

        GridPosition position2;
        if (pos == 0) {
            position2 = pathsPosition.get(pathsPosition.size() - 1);
        } else {
            position2 = pathsPosition.get(pos - 1);
        }

        if (position.line() < position1.line() && position.column() < position2.column()
                || position.line() < position2.line() && position.column() < position1.column() ){
            return CellOrientation.TOP_RIGHT;
        }
        if (position.line() > position1.line() && position.column() > position2.column()
                || position.line() > position2.line() && position.column() > position1.column() ){
            return CellOrientation.BOTTOM_LEFT;
        }
        if (position.line() < position1.line() && position.column() > position2.column()
                || position.line() < position2.line() && position.column() > position1.column() ){
            return CellOrientation.TOP_LEFT;
        }
        if (position.line() > position1.line() && position.column() < position2.column()
                || position.line() > position2.line() && position.column() < position1.column() ){
            return CellOrientation.BOTTOM_RIGHT;
        }
        if (position.line() == position1.line() && position.column() < position2.column()
                || position.line() == position2.line() && position.column() < position1.column() ){
            return CellOrientation.HORIZONTAL;
        }
        return CellOrientation.VERTICAL;

    }

}
