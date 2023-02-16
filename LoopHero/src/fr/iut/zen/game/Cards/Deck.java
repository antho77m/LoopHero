package fr.iut.zen.game.Cards;

import fr.iut.zen.game.Cells.*;
import fr.iut.zen.game.Entity.Scarecrow;
import fr.iut.zen.game.Entity.Skeleton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Deck implements Serializable {

    private ArrayList<Card> disponibleCard = new ArrayList<Card>() { //initialise les cartes disponibles
        {
            for (int i=0;i<12;i++){
                add(new Card(new Rock()));
            }
            for (int i=0;i<15;i++){
                add(new Card(new Meadow()));
            }
            for (int i=0;i<4;i++){
                add(new Card(new Grove(CellOrientation.BOTTOM_LEFT)));
            }
            for (int i=0;i<3;i++){
                add(new Card(new Cemetery(CellOrientation.BOTTOM_LEFT)));
            }
            for (int i=0;i<3;i++){
                add(new Card(new Ruins(CellOrientation.BOTTOM_LEFT)));
            }
            for (int i=0;i<6;i++){
                add(new Card(new Village(CellOrientation.BOTTOM_LEFT)));
            }
            for (int i=0;i<6;i++){
                add(new Card(new SpiderCocoon()));
            }
            for (int i=0;i<3;i++){
                add(new Card(new BattleField()));
            }
            for (int i=0;i<3;i++){
                add(new Card(new VampireMansion()));
            }
            for (int i=0;i<3;i++){
                add(new Oblivion());
            }
            for (int i=0;i<3;i++){
                add(new Card(new WheatField(CellOrientation.BOTTOM_LEFT)));
            }


            Collections.shuffle(this);

        }
    };


    public Card giveCard(){ // donne une carte dispo
        if (disponibleCard.size()!=0){
            return disponibleCard.remove(disponibleCard.size()-1);
        }
        return null;
    }
    public Card giveCard(Card card){ // donne une carte dispo selon une carte donnée
        if (disponibleCard.size()!=0){
            disponibleCard.remove(card);
            return card;
        }
        return null;
    }

    public void givebackCard(Card card){ // quand une carte est supprimé du jeu , la fonction remet cette carte dans les cartes dispo
        disponibleCard.add(card);
        Collections.shuffle(disponibleCard);
    }


}
