package fr.iut.zen.game.Entity;

import fr.iut.zen.game.Property.Drawable;
import fr.iut.zen.game.Resources.ResourceElement;

public interface Monster extends Drawable, Entity {

    public ResourceElement itemDrop();

    public int getItemChance();

    public boolean isDied();
}
