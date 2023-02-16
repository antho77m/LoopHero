package fr.iut.zen.game;

import fr.iut.zen.game.Entity.Entity;

import java.io.Serializable;

public record BattleDetails(Entity entity,String details) implements Serializable {


}
