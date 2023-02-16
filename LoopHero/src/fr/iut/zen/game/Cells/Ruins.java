package fr.iut.zen.game.Cells;

import fr.iut.zen.game.Cards.Deck;
import fr.iut.zen.game.Property.Drawable;
import fr.iut.zen.game.Property.Dropable;
import fr.iut.zen.game.Entity.Hero;
import fr.iut.zen.game.Entity.ScorchWorm;
import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.Resources.PreservedPebbles;
import fr.iut.zen.game.Resources.Ration;
import fr.iut.zen.game.Resources.ScrapMetal;
import fr.iut.zen.game.Resources.StableBranches;

import java.awt.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;

public class Ruins extends Paths{
    private int dayCount = 0;
    private Color color = Color.BLUE;
    public Ruins(CellOrientation orientation) {
        super(orientation);
    }

    @Override
    public void newDay(Cell[][] cells, Hero hero, GridPosition position) {
        dayCount++;
        if (dayCount%2 == 0) {
            addNewMonster(new ScorchWorm(hero.getRound()));
        }
    }

    @Override
    public ArrayList<Dropable> HeroIsOnPath(Hero hero, Deck deck) {
        ArrayList<Dropable> ChanceDropable = super.HeroIsOnPath(hero, deck);
        ChanceDropable.add(new PreservedPebbles());
        ChanceDropable.add(new StableBranches());
        ChanceDropable.add(new ScrapMetal());
        ChanceDropable.add(new Ration());
        Random random = new Random();
        int randomNumber = random.nextInt(ChanceDropable.size());

        ArrayList<Dropable> dropable = new ArrayList<>();
        dropable.add(ChanceDropable.get(randomNumber));


        return dropable;

    }

    @Override
    public boolean buildable() {
        return false;
    }

    @Override
    public void drawInterface(Graphics2D graphics, float xOrigin, float Yorigin, float lenght, float height) {
        String pictureName = "pictures/Cards/RuinsCard.png";
        Path path = Path.of(pictureName);
        Drawable.drawImage(graphics, (int)xOrigin, (int)Yorigin, path, (int)lenght, (int)height);
    }

    @Override
    public void draw(Graphics2D graphics, float column, float line, float squareSize) {
        graphics.setColor(color);
        graphics.fillRect((int)column, (int)line, (int)squareSize, (int)squareSize);
        drawMonster(graphics, column, line, squareSize);
    }
}
