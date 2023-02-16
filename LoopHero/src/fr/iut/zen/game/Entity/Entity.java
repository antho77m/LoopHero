package fr.iut.zen.game.Entity;

import java.awt.*;
import java.io.Serializable;
import java.util.List;

public interface Entity extends Serializable {
    public String name();

    public void removeLife(float impact);

    public void addLife(float impact) ;

    public float getLife();

    public float getMaxLife();

    public void setMaxLife(float maxLife);

    public void setLife(float life);

    public float getDefense();

    public void setDefense(int defense);

    public float getCounter();

    public void setCounter(float counter);

    public float getEvade();

    public void setEvade(float evade);

    public float getRegen();

    public void setRegen(float regen);

    public float getVampirism();

    public void setVampirism(float vampirism);

    public float getDamage();

    public void setDamage(List<Float> plageDamage);

    public List<Float> getRangeDamage();

    public boolean isDied();

    public void draw(Graphics2D graphics, float line, float column, float squareSize);

}
