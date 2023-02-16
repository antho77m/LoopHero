package fr.iut.zen.game.Cells;

import fr.iut.zen.game.Cards.Deck;
import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.Property.Drawable;
import fr.iut.zen.game.Property.Dropable;
import fr.iut.zen.game.Entity.Hero;

import java.awt.*;
import java.nio.file.Path;
import java.util.ArrayList;

public class Village extends Paths{

    private final Color color = Color.YELLOW;

    public Village(CellOrientation orientation) {
        super(orientation);
    }

    @Override
    public ArrayList<Dropable> HeroIsOnPath(Hero hero, Deck deck) {
        hero.addLife(15+5*hero.getRound());
        return super.HeroIsOnPath(hero, deck);
    }

    @Override
    public void draw(Graphics2D graphics, float column, float line, float squareSize) {
        String pictureName = "pictures/Textures/Village.png";
        Path villagePath = Path.of(pictureName);
        Drawable.drawImage(graphics, column, line, villagePath, squareSize);
        super.drawMonster(graphics, column, line, squareSize);
    }

    @Override
    public void drawInterface(Graphics2D graphics, float xOrigin, float Yorigin, float lenght, float height) {
        String pictureName = "pictures/Cards/VillageCard.png";
        Path path = Path.of(pictureName);
        Drawable.drawImage(graphics, (int)xOrigin, (int)Yorigin, path, (int)lenght, (int)height);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Village)) {
            return false;
        }
        Village village = (Village) o;

        return super.equals(village) && village.getOrientation() == this.getOrientation() && village.color == this.color;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (color != null ? color.hashCode() : 0);
        return result;
    }
}
