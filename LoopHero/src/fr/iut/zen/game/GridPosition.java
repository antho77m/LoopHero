package fr.iut.zen.game;

import java.io.Serializable;

/**
 * 
 */
public record GridPosition (int line, int column) implements Serializable {
    public boolean isAdjacent(GridPosition gridPosition) {
        return Math.abs(line - gridPosition.line) <= 1 && Math.abs(column - gridPosition.column) <= 1;
    }


}
