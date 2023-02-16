package fr.iut.zen.game.Cells;

import fr.iut.zen.game.Entity.Hero;
import fr.iut.zen.game.Entity.RatWolf;
import fr.iut.zen.game.Entity.Spider;
import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.Property.Drawable;
import fr.iut.zen.game.Tools;

import java.awt.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;

public class SpiderCocoon extends BorderPath{

    private Color color = Color.WHITE;



    @Override
    public void newDay(Cell[][] cells, Hero hero, GridPosition position) {
        ArrayList<GridPosition> adjacentCell = new ArrayList<>();
        adjacentCell.add(new GridPosition(position.line(), position.column() + 1));
        adjacentCell.add(new GridPosition(position.line(), position.column() - 1));
        adjacentCell.add(new GridPosition(position.line() + 1, position.column()));
        adjacentCell.add(new GridPosition(position.line() - 1, position.column()));

        Random random = new Random();


        while (true) {
            int number = random.nextInt(4); // case grove actuelle exclu
            if (addSpiderToAdjacentCell(cells, adjacentCell.get(number), hero.getRound())) {
                break;
            }

        }

    }

    private boolean addSpiderToAdjacentCell(Cell[][] cells, GridPosition position,int round) {
        if (Tools.posInMatrix(cells, position) && cells[position.line()][position.column()].isPath()) {
            Paths path = (Paths) (cells[position.line()][position.column()]);
            path.addNewMonster(new Spider(round));
            return true;
        }
        return false;
    }

    @Override
    public boolean buildable() {
        return false;
    }

    @Override
    public void drawInterface(Graphics2D graphics, float xOrigin, float Yorigin, float lenght, float height) {
        String pictureName = "pictures/Cards/SpiderCocoonCard.png";
        Path path = Path.of(pictureName);
        Drawable.drawImage(graphics, (int)xOrigin, (int)Yorigin, path, (int)lenght, (int)height);
    }

    @Override
    public void draw(Graphics2D graphics, float column, float line, float squareSize) {
        String pictureName = "pictures/Textures/SpiderCocoon.png";
        Path spidercocoonPath = Path.of(pictureName);
        Drawable.drawImage(graphics, column, line, spidercocoonPath,squareSize);
    }
}
