package fr.iut.zen.game.Cells;

import fr.iut.zen.game.Cards.Deck;
import fr.iut.zen.game.Property.Drawable;
import fr.iut.zen.game.Property.Dropable;
import fr.iut.zen.game.Entity.Hero;
import fr.iut.zen.game.Entity.Skeleton;
import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.Resources.PreservedPebbles;

import java.awt.*;
import java.nio.file.Path;
import java.util.ArrayList;

public class Cemetery extends Paths{
    private Color color = Color.PINK;
    private int dayCount = 0;

    public Cemetery(CellOrientation orientation) {
        super(orientation);
    }

    @Override
    public void newDay(Cell[][] cells, Hero hero, GridPosition position) {
        dayCount++;
        if (dayCount%3 == 0) {
            addNewMonster(new Skeleton(hero.getRound()));

        }
    }

    @Override
    public boolean buildable() {
        return false;
    }

    @Override
    public void draw(Graphics2D graphics, float column, float line, float squareSize) {
        String pictureName = "pictures/Textures/Cemetery.png";
        Path cemeteryPath = Path.of(pictureName);
        Drawable.drawImage(graphics, column, line, cemeteryPath, squareSize);
        drawMonster(graphics, column, line, squareSize);
    }

    @Override
    public void drawInterface(Graphics2D graphics, float xOrigin, float Yorigin, float lenght, float height) {
        String pictureName = "pictures/Cards/CemeteryCard.png";
        Path path = Path.of(pictureName);
        Drawable.drawImage(graphics, (int)xOrigin, (int)Yorigin, path, (int)lenght, (int)height);
    }

    @Override
    public ArrayList<Dropable> HeroIsOnPath(Hero hero, Deck deck) {
        ArrayList<Dropable> dropable = super.HeroIsOnPath(hero, deck);
        dropable.add(new PreservedPebbles());
        return dropable;
    }
}
