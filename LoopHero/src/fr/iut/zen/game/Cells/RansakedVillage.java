package fr.iut.zen.game.Cells;

import fr.iut.zen.game.Entity.Ghoul;
import fr.iut.zen.game.Entity.Hero;
import fr.iut.zen.game.GridPosition;

import java.awt.*;
import java.nio.file.Path;

public class RansakedVillage extends Paths {
    private Color color = Color.ORANGE;
    private int roundCounter = 0;

    public RansakedVillage(CellOrientation orientation) {
        super(orientation);
    }

    @Override
    public Cell newRound(Hero hero, Cell[][] cells, GridPosition position) {
        roundCounter++;
        if (roundCounter == 3) {
            return new RansakedVillage(getOrientation());
        }

        addNewMonster(new Ghoul(hero.getRound()));
        return null;
    }

    @Override
    public boolean buildable() {
        return true;
    }
}
