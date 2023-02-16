package fr.iut.zen.game.Resources;

import fr.iut.zen.game.Property.Dropable;

import java.io.Serializable;

public interface ResourceElement extends Dropable, Serializable {

    @Override
    default boolean isCard(){
        return false;
    }

    public String getName();
}
