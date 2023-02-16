package fr.iut.zen.game.Entity;

import fr.iut.zen.game.Tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public abstract class AbstractEntity implements Entity {
    private float maxLife;
    private float life;
    private float defense;
    private float counter;
    private float evade;
    private float regen;
    private float vampirism;
    private List<Float> plageDamage;




    AbstractEntity(float maxLife,List<Float> plageDamage ,float defense, float counter, float evade, float regen, float vampirism) {
        this.maxLife = maxLife;
        life = maxLife;
        this.defense = defense;
        this.counter = counter;
        this.evade = evade;
        this.regen = regen;
        this.vampirism = vampirism;
        if (plageDamage.size()>2 || plageDamage.get(0)>plageDamage.get(1)){
            throw new IllegalArgumentException();
        }
        this.plageDamage = plageDamage;
    }

    @Override
    public void removeLife(float impact) {
        if (impact < 0)
            impact = 0;
        life -= impact;
        if (life < 0) {
            life = 0;
        }

        roundLife();
    }

    @Override
    public void addLife(float impact) {
        life += Math.abs(impact);
        if (life > maxLife) {
            life = maxLife;
        }

        roundLife();
    }

    @Override
    public float getLife() {
        roundLife();
        return life;
    }

    @Override
    public float getMaxLife() {
        return maxLife;
    }

    @Override
    public void setMaxLife(float maxLife) {
        this.maxLife = maxLife;
    }

    @Override
    public void setLife(float life) {
        this.life = life;
        roundLife();
    }

    @Override
    public float getDefense() {
        return defense;
    }

    @Override
    public void setDefense(int defense) {
        this.defense = defense;
    }

    @Override
    public float getCounter() {
        return counter;
    }

    @Override
    public void setCounter(float counter) {
        this.counter = counter;
    }

    @Override
    public float getEvade() {
        return evade;
    }

    @Override
    public void setEvade(float evade) {
        this.evade = evade;
    }

    @Override
    public float getRegen() {
        return regen;
    }

    @Override
    public void setRegen(float regen) {
        this.regen = regen;
    }

    @Override
    public float getVampirism() {
        return vampirism;
    }

    @Override
    public void setVampirism(float vampirism) {
        this.vampirism = vampirism;
    }

    @Override
    public float getDamage() {
        Random random =new Random();
        if (plageDamage.get(0).equals(plageDamage.get(1))){
            return plageDamage.get(0);
        }
        else{
            return random.nextFloat(plageDamage.get(0),plageDamage.get(1));
        }
    }

    @Override
    public List<Float> getRangeDamage() {
        return List.of(Tools.roundNumber(plageDamage.get(0)),Tools.roundNumber(plageDamage.get(1)));
    }


    @Override
    public void setDamage(List<Float> plageDamage) {
        if (plageDamage.size()>2 || plageDamage.get(0)>plageDamage.get(1)){
            throw new IllegalArgumentException();
        }
        this.plageDamage = plageDamage;
    }

    @Override
    public boolean isDied() {
        return life==0;
    }

    private void roundLife(){
        life*=10;
        life = Math.round(life);
        life/=10;
    }





    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractEntity that = (AbstractEntity) o;
        return Float.compare(that.maxLife, maxLife) == 0 && Float.compare(that.life, life) == 0
                && Float.compare(that.defense, defense) == 0 && Float.compare(that.counter, counter) == 0
                && Float.compare(that.evade, evade) == 0 && Float.compare(that.regen, regen) == 0
                && Float.compare(that.vampirism, vampirism) == 0 && plageDamage.equals(that.plageDamage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maxLife, life, defense, counter, evade, regen, vampirism, plageDamage);
    }

    @Override
    public String toString() {

        return  " vie : " + Tools.roundNumber(life) + "/" + Tools.roundNumber(maxLife) +
                "\n defense : " + Tools.roundNumber(defense) +
                "\n contre attaque : " + Tools.roundNumber(counter)  +
                "\n esquive : " + Tools.roundNumber(evade)  +
                "\n regeneration/s : " + Tools.roundNumber(regen) +
                "\n vampirisme : " + Tools.roundNumber(vampirism) +
                "\n Dommage : " + List.of(Tools.roundNumber(plageDamage.get(0)),Tools.roundNumber(plageDamage.get(1)));
    }
}
