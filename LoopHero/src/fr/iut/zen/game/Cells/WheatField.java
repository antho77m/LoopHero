package fr.iut.zen.game.Cells;

import fr.iut.zen.game.Cards.Deck;
import fr.iut.zen.game.Entity.Hero;
import fr.iut.zen.game.Entity.Scarecrow;
import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.Property.Drawable;
import fr.iut.zen.game.Property.Dropable;
import fr.iut.zen.game.Tools;

import java.awt.*;
import java.nio.file.Path;
import java.util.ArrayList;

public class WheatField extends Paths{
    private int dayCount = 0;
    private Color color = Color.GRAY;

    public WheatField(CellOrientation orientation) {
        super(orientation);
    }

    @Override
    public void newDay(Cell[][] cells, Hero hero, GridPosition position) {
        dayCount++;
        if (dayCount %4==0) {
            addNewMonster(new Scarecrow(hero.getRound()));
        }
    }

    @Override
    public ArrayList<Dropable> HeroIsOnPath(Hero hero, Deck deck) {
        hero.addLife(5* hero.getRound());
        return super.HeroIsOnPath(hero, deck);
    }

    @Override
    public boolean isPosable(ArrayList<Cell> cells) {
        return super.isPosable(cells) && adjacentToVillage(cells);
    }

    private boolean adjacentToVillage(ArrayList<Cell> cells) {

        for (Cell cell : cells) {
            if (cell.isPath()) {
                Paths path = (Paths) (cell);

                if (new Village(path.getOrientation()).equals(path)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean buildable() {
        return false;
    }

    @Override
    public void draw(Graphics2D graphics, float column, float line, float squareSize) {
        String pictureName = "pictures/Textures/WheatFields.png";
        Path wheatfielsPath = Path.of(pictureName);
        Drawable.drawImage(graphics, column, line, wheatfielsPath, squareSize);
        drawMonster(graphics, column, line, squareSize);
    }

    @Override
    public void drawInterface(Graphics2D graphics, float xOrigin, float Yorigin, float lenght, float height) {
        String pictureName = "pictures/Cards/WheatFieldsCard.png";
        Path path = Path.of(pictureName);
        Drawable.drawImage(graphics, (int)xOrigin, (int)Yorigin, path, (int)lenght, (int)height);
    }
}
