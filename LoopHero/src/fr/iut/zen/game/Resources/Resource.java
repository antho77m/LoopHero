package fr.iut.zen.game.Resources;

import java.io.Serializable;
import java.util.*;

public class Resource implements Serializable {
    private TreeMap<String,Integer> numberOfResource = new TreeMap<>();

    public Integer getNumberResource(String nameOfResource){
        return numberOfResource.getOrDefault(nameOfResource,0);
    }

    public void addResource(String nameOfResource){
        numberOfResource.put(nameOfResource,numberOfResource.getOrDefault(nameOfResource,0)+1);
    }

    public ArrayList<String> getlistString() {
        ArrayList<String> list= new ArrayList<>();
        for (Map.Entry<String,Integer> keyValue:numberOfResource.entrySet()) {
            list.add(keyValue.getKey()+" : "+getNumberResource(keyValue.getKey())) ;
        }
        return list;
    }
}
