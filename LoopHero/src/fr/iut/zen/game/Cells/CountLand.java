package fr.iut.zen.game.Cells;

import fr.iut.zen.game.Cards.Deck;
import fr.iut.zen.game.Property.Drawable;
import fr.iut.zen.game.Property.Dropable;
import fr.iut.zen.game.Entity.Hero;

import java.awt.*;
import java.nio.file.Path;
import java.util.ArrayList;

public class CountLand extends Paths {

    private Color color = Color.GREEN;

    public CountLand(CellOrientation orientation) {
        super(orientation);
    }

    @Override
    public ArrayList<Dropable> HeroIsOnPath(Hero hero, Deck deck) {

        hero.addLife(15+20*hero.getRound());
        return super.HeroIsOnPath(hero, deck);
    }

    @Override
    public void draw(Graphics2D graphics, float column, float line, float squareSize) {
        String pictureName = "pictures/Textures/CoutnLand.png";
        Path countlandPath = Path.of(pictureName);
        Drawable.drawImage(graphics, line, column, countlandPath,squareSize);
    }

    @Override
    public boolean buildable() {
        return true;
    }
}
