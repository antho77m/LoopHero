package fr.iut.zen.game.Equipments;

import fr.iut.zen.game.Tools;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

//contient l'ensemble des statistiques qu'un équipement peut avoir
public record Statistics(float maxLife , List<Float> damage, float defense, float counter, float evade, float regen, float vampirism) implements Serializable {
    public Statistics(float maxLife, List<Float> damage, float defense, float counter, float evade, float regen, float vampirism) {
        this.maxLife =  Tools.roundNumber(maxLife);
        this.defense = Tools.roundNumber(defense);
        this.counter = Tools.roundNumber(counter);
        this.evade = Tools.roundNumber(evade);
        this.regen = Tools.roundNumber(regen);
        this.vampirism = Tools.roundNumber(vampirism);
        if (damage.size()>2 || Objects.requireNonNull(damage.get(0))>Objects.requireNonNull(damage.get(1))){
            throw new IllegalArgumentException();
        }
        this.damage = Objects.requireNonNull(damage);
    }



    @Override
    public String toString() {
        return "vie maximal : +" + maxLife +
                "\n dommage : +" + damage +
                "\n defense : +" + defense +
                "\n contre attaque : +" + counter +
                "\n esquive : +" + evade +
                "\n regeneration : +" + regen +
                ",\n vampirisme : +" + vampirism;
    }
    public Statistics sum(Statistics statistics){
        return new Statistics(this.maxLife + statistics.maxLife,
                List.of(this.damage.get(0) + statistics.damage.get(0),this.damage.get(1) + statistics.damage.get(1)),
                this.defense + statistics.defense,
                this.counter + statistics.counter,
                this.evade + statistics.evade,
                this.regen + statistics.regen,
                this.vampirism + statistics.vampirism);
    }
    public  Statistics multiply(float f){
        return new Statistics(this.maxLife * f,
                List.of(this.damage.get(0) * f,this.damage.get(1) * f),
                this.defense * f,
                this.counter * f,
                this.evade * f,
                this.regen * f,
                this.vampirism * f);
    }

    public Statistics divide(float f){
        if (f==0){
            return this;
        }
        return  new Statistics(this.maxLife / f,
                List.of(this.damage.get(0) / f,this.damage.get(1) / f),
                this.defense / f,
                this.counter / f,
                this.evade / f,
                this.regen / f,
                this.vampirism / f);
    }

}
