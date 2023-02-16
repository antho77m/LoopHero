package fr.iut.zen.game.Equipments;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public record Equipment(RaretyColor color, EquipmentPart equipmentPart, Statistics stats) implements Serializable {

    private static Random random = new Random(); // evite d'en construire plusieurs fois

    public Equipment(RaretyColor color, EquipmentPart equipmentPart, Statistics stats) {
        this.color = Objects.requireNonNull(color);
        this.equipmentPart = Objects.requireNonNull(equipmentPart);
        this.stats = Objects.requireNonNull(stats);
    }

    private static Equipment createPartArmor(RaretyColor color,int roundNumber){
        int rand = random.nextInt(5);
        EquipmentPart equipmentPart;
        if (rand==0){
            equipmentPart = EquipmentPart.Helmet;
        }
        else if (rand==1){
            equipmentPart = EquipmentPart.Armor;
        }
        else if (rand==2){
            equipmentPart = EquipmentPart.Glove;
        }
        else if (rand==3){
            equipmentPart = EquipmentPart.Boots;
        }
        else {
            equipmentPart = EquipmentPart.Belt;
        }
        int life = random.nextInt(80*roundNumber,100*roundNumber);

        return new Equipment(color,equipmentPart,ModifyStatsRarety(new Statistics(life,List.of(0.f,0.f),0,0,0,0,0),color,roundNumber));
    }

    private static RaretyColor takeRandomColor(int roundNumber){

        int rand = random.nextInt(100);

        if (rand<15 && roundNumber>=3){
            return RaretyColor.Orange;
        }
        else if (rand<35){
            return RaretyColor.Yellow;
        }
        else if (rand<65){
            return RaretyColor.Blue;
        }
        else {
            return RaretyColor.Grey;
        }
    }

    public static Equipment createRandomEquipment(int roundNumber){

        RaretyColor color = takeRandomColor(roundNumber);

        int rand = random.nextInt(7);
        //choix de la partie de l'équipement

        if (rand<=5){ // armor
            return createPartArmor(color,roundNumber);
        }
        else if (rand==6){ // shield
            return new Equipment(color,EquipmentPart.Shield,ModifyStatsRarety(new Statistics(0,List.of(0.f,0.f),4*roundNumber,0,0,0,0),color,roundNumber) );
        }
        else if (rand==7){ // weapon
            return new Equipment(color,EquipmentPart.Weapons,ModifyStatsRarety(new Statistics(0,List.of(4.f*roundNumber,6.f*roundNumber),0,0,0,0,0),color,roundNumber));
        }
        else { // ring
            return createRing(color,roundNumber);

        }
    }

    // change les stats de sorte a se que cela corresponde a la rareté de l'équipement
    private static Statistics ModifyStatsRarety(Statistics statistics, RaretyColor color, int roundNumber){

        if (color==RaretyColor.Grey){
            return statistics;
        }
        else if (color==RaretyColor.Blue){
            Statistics statAdd = createRing(RaretyColor.Grey,roundNumber).stats.divide(3);
            return statistics.multiply(0.9f).sum(statAdd);

        }
        else if (color==RaretyColor.Yellow){
            Statistics statAdd = createRing(RaretyColor.Grey,roundNumber).stats.divide(2);
            Statistics statAdd2 = createRing(RaretyColor.Grey,roundNumber).stats.divide(2);
            int valueMultiply =random.nextInt(80,100)/100;
            return statistics.multiply(valueMultiply).sum(statAdd).sum(statAdd2);

        }
        else {
            Statistics statAdd = createRing(RaretyColor.Grey,roundNumber).stats.divide(2);
            Statistics statAdd2 = createRing(RaretyColor.Grey,roundNumber).stats.divide(2);
            Statistics statAdd3 = createRing(RaretyColor.Grey,roundNumber-2).stats;
            int valueMultiply =random.nextInt(80,100)/100;
            return statistics.multiply(valueMultiply).sum(statAdd).sum(statAdd2).sum(statAdd3);
        }
    }

    private static Equipment createRing(RaretyColor color, int roundNumber) {
        Random random = new Random();

        int rand = random.nextInt(5);
        Statistics statistics = switch (rand) {
            case 0 -> new Statistics(0, List.of(0.f, 0.f), 0, 8 + (roundNumber - 1) * 4, 0, 0, 0);
            case 1 -> new Statistics(0, List.of(0.f, 0.f), 0, 0, 0, 0, (float) (8 + (roundNumber - 1) * 1.5));
            case 2 -> new Statistics(0, List.of(0.f, 0.f), 0, 0, 0, (float) (roundNumber * 0.6), 0);
            case 3 -> new Statistics(0, List.of(0.f, 0.f), 0, 0, 8 + (roundNumber - 1) * 2, 0, 0);
            default -> new Statistics(0, List.of(0.f, 0.f), (float) (roundNumber * 1.5), 0, 0, 0, 0);
        };
        return new Equipment(color,EquipmentPart.Ring,ModifyStatsRarety(statistics,color,roundNumber));

    }

    @Override
    public String toString() {
        return equipmentPart +
                "\nrareté : " + color +
                "\n" + stats.toString();
    }
}
