package fr.iut.zen.game.Cells;

import fr.iut.zen.game.Property.Drawable;
import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.Entity.Hero;
import fr.iut.zen.game.Resources.PreservedPebbles;
import fr.iut.zen.game.Resources.ResourceElement;
import fr.iut.zen.game.Tools;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.nio.file.Path;

public class Rock extends LandScape{
    private final Color color= Color.BLACK;
    private int lifeAddToHero=0;

    @Override
    public ResourceElement set(Cell[][] cells, Hero hero, GridPosition position) {
        float lifeAdd = hero.maxLife()/100;
        float resultLifeAdd=lifeAdd;

        if (Tools.posInMatrix(cells,new GridPosition(position.line()+1,position.column()))
                && cells[position.line()+1][position.column()].isRock())
        {
            resultLifeAdd+=lifeAdd;
        }
        if (Tools.posInMatrix(cells,new GridPosition(position.line(),position.column()+1))&&
                cells[position.line()][position.column()+1].isRock())
        {
            resultLifeAdd+=lifeAdd;
        }
        if (Tools.posInMatrix(cells,new GridPosition(position.line()-1,position.column()))&&
                cells[position.line()-1][position.column()].isRock()){
            resultLifeAdd+=lifeAdd;
        }
        if (Tools.posInMatrix(cells,new GridPosition(position.line()-1,position.column()))&&
                cells[position.line()][position.column()-1].isRock()){
            resultLifeAdd+=lifeAdd;
        }
        lifeAddToHero= (int) resultLifeAdd;

        hero.lifeMaxImpact(lifeAddToHero);
        return new PreservedPebbles();

    }

    @Override
    public boolean buildable() {
        return false;
    }

    @Override
    public void draw(Graphics2D graphics, float column, float line, float squareSize) {
    	String pictureName = "pictures/Textures/Rock.png";
		Path rockPath = Path.of(pictureName);
		Drawable.drawImage(graphics, column, line, rockPath, squareSize);
    }

    @Override
    public void drawInterface(Graphics2D graphics, float xOrigin, float Yorigin, float lenght, float height) {
        String pictureName = "pictures/Cards/RockCard.png";
        Path path = Path.of(pictureName);
        Drawable.drawImage(graphics, (int)xOrigin, (int)Yorigin, path, (int)lenght, (int)height);
    }

    @Override
    public boolean isRock() {
        return true;
    }
}
