package fr.iut.zen.game.Equipments;

import java.io.Serializable;
import java.util.ArrayList;

public class Bag implements Serializable {
    ArrayList<Equipment> bag = new ArrayList<>();

    public void addEquipment(Equipment equipment){
        if (bag.size()==12){
            bag.remove(0);
        }
        bag.add(equipment);
    }

    public Equipment equip (int index){
        Equipment equipment = bag.get(index);
        bag.remove(index);
        return equipment;
    }

    public Equipment getEquipment (int index){
        Equipment equipment = bag.get(index);
        return equipment;
    }

    public ArrayList<EquipmentPart> getEquipmentParts(){
        ArrayList<EquipmentPart> equipmentParts = new ArrayList<>();
        for (Equipment equipment : bag){
            equipmentParts.add(equipment.equipmentPart());
        }
        return equipmentParts;
    }

    @Override
    public String toString() {
        return "Bag{" +
                "bag=" + bag +
                '}';
    }
}
