package fr.iut.zen.game.Cards;

import fr.iut.zen.game.Cells.BorderPath;
import fr.iut.zen.game.Cells.Cell;
import fr.iut.zen.game.Cells.LandScape;
import fr.iut.zen.game.Cells.Paths;
import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.Property.Drawable;
import fr.iut.zen.game.Tools;

import java.awt.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Oblivion extends Card{
    public Oblivion() {
        super(null);
    }

    @Override
    public boolean isPosable(ArrayList<Cell> cells) {
        return true;
    }

    @Override
    public Cell drop(Cell[][] cells, GridPosition position) {

        if (cells[position.line()][position.column()].isPath()){
            Paths path = (Paths) cells[position.line()][position.column()];
            return new Paths(path.getOrientation());
        }
        ArrayList<Cell> adjacentcells = Tools.getAdjacentCell(cells, position);
        if (isLandscape(adjacentcells)){
            return new LandScape();
        }
        else {
            return new BorderPath();
        }

    }

    private boolean isLandscape(List<Cell> cells){
        for (Cell cell:cells){
            if (cell.isPath()){
                return false;
            }
        }
        return true;
    }

    @Override
    public void draw(Graphics2D graphics, float column, float line, float squareSize) {
        String pictureName = "pictures/Cards/OblivionCard.png";
        Path oblivionPath = Path.of(pictureName);
        Drawable.drawImage(graphics, column, line, oblivionPath, squareSize*1.5f, squareSize*2.5f);

    }
}
