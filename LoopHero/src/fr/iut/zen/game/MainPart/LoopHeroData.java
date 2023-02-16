package fr.iut.zen.game.MainPart;

import fr.iut.zen.game.Cards.Card;
import fr.iut.zen.game.Cards.Deck;
import fr.iut.zen.game.Cards.Oblivion;
import fr.iut.zen.game.Cells.*;
import fr.iut.zen.game.Property.Dropable;
import fr.iut.zen.game.Entity.Hero;
import fr.iut.zen.game.Equipments.EquipmentPart;
import fr.iut.zen.game.GameManagementFile;
import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.Resources.Resource;
import fr.iut.zen.game.Resources.ResourceElement;
import fr.iut.zen.game.Tools;

import java.io.Serializable;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class LoopHeroData implements Serializable {

    private static final long serialVersionUID = 1L;
    Deck listCard = new Deck();
    private final Cell[][] matrix;
    private int roundNumber = 0;
    private ArrayList<Card> hand;  // endroit ou sera stocké les cartes du joueur
    private GridPosition selectedCell;
    private Integer selectedEquipmentInBag;
    private EquipmentPart selectedEquipmentEquip;
    private Integer selectedCard;
    private Resource resource= new Resource();


    private final List<GridPosition> pathList; ;
    private Hero hero;

    /*
     * je commence en 1 : Base (2,5) { (2,4) , (3,4) , (3,3) , (4,3) , (5,3) , (5,2)
     * , (6,2) , (7,2) , (8,2) , (9,2) , (9,3) , (10,3) , (11,3) , (11,4) , (12,4) ,
     * (12,5) , (12,6) , (11,6) , (10,6) , (10,7) , (9,7) , (8,7) , (8,8) , (7,8) ,
     * (6,8) , (6,9) , (5,9) , (4,9) , (3,9) , (3,8) , (3,7) , (2,7) , (2,6) }
     */
    public LoopHeroData(int nbLines, int nbColumns) {

        hand = new ArrayList<>();
        hand.add(listCard.giveCard(new Card(new Meadow())));
        hand.add(listCard.giveCard(new Card(new Grove(CellOrientation.BOTTOM_LEFT))));
        hand.add(listCard.giveCard(new Card(new Rock())));

        if (nbLines < 1 || nbColumns < 1) {
            throw new IllegalArgumentException("at least one line and column");
        }
        matrix = new Cell[nbLines][nbColumns];
        pathList= GameManagementFile.loadPaths(Path.of("paths.txt"),nbColumns(),nbLines());
        hero = new Hero(pathList, 250);
    }

    public Cell[][] matrix() {
        return matrix;
    }

    /**
     * The number of lines in the matrix contained in this GameData.
     *
     * @return the number of lines in the matrix.
     */
    public int nbLines() {
        return matrix.length;
    }

    /**
     * The number of columns in the matrix contained in this GameData.
     *
     * @return the number of columns in the matrix.
     */
    public int nbColumns() {
        return matrix[0].length;
    }

    private void checkBoundsOrThrow(int line, int column) {
        Objects.checkIndex(line, matrix.length);
        Objects.checkIndex(column, matrix[0].length);
    }

    /**
     * The coordinates of the cell selected, if a cell is selected.
     *
     * @return the coordinates of the selected cell; null otherwise.
     */
    public GridPosition getSelectedCell() {
        return selectedCell;
    }

    public Integer getSelectedCard() {
        return selectedCard;
    }

    public GridPosition getPosHero() {
        return hero.Position();
    }

    /**
     * Tests if a cell is selected.
     *
     * @return true if and only if at least one cell is selected; false otherwise.
     */
    public boolean hasASelectedCell() {
        return selectedCell != null;
    }

    public boolean hasASelectedCard() {
        return selectedCard != null;
    }

    public boolean hasASelectedEquipment() {
        return selectedEquipmentInBag != null;
    }

    public boolean hasASelectedEquipmentEquip() {
        return selectedEquipmentEquip != null;
    }

    public EquipmentPart getSelectedEquipmentEquip() {
        return selectedEquipmentEquip;
    }

    public void setSelectedEquipmentEquip(EquipmentPart selectedEquipmentEquip) {
        this.selectedEquipmentEquip = selectedEquipmentEquip;
    }

    public Integer getSelectedEquipmentInBag() {
        return selectedEquipmentInBag;
    }

    /**
     * Selects the cell identified by the specified coordinates.
     *
     * @param line   the first coordinate of the cell.
     * @param column the second coordinate of the cell.
     * @throws IndexOutOfBoundsException
     * @throws IllegalStateException     if a first cell is already selected.
     */
    public void selectCell(int line, int column) {
        if (selectedCell != null) {
            throw new IllegalStateException("First cell already selected");
        }
        selectedCell = new GridPosition(line, column);
    }

    public void selectCard(Integer integer) {
        selectedCard = integer;
    }

    public void selectEquipment(Integer selectedEquipment) {
        this.selectedEquipmentInBag = selectedEquipment;
    }


    public ArrayList<Card> getHand() {
        return hand;
    }


    //sépare les différent dropable afin de les rangé dans les bon conteneur
    private void sortDropable(ArrayList<Dropable> dropables, Deck deck) {
        if (dropables != null) {//si combat contre un monstre alors ajout des cartes
            for (Dropable dropable : dropables) {
                if (dropable.isCard()) {
                    hand.add((Card) dropable);
                }
                else {
                    ResourceElement element = (ResourceElement)dropable;
                    resource.addResource(element.getName());
                }
            }
            while (hand.size() > 13) {    // enlevement du surplus
                deck.givebackCard(hand.remove(0));
            }
        }

    }

    public void moveHero() {

        if (hero.isInCombat()){
            hero.removeTimeInCombat();
            return;
        }

        hero.moveToNextPosition();

        ArrayList<Dropable> dropables = matrix[hero.Position().line()][hero.Position().column()].HeroIsOnPath(hero,listCard);
        sortDropable(dropables,listCard);

        if (hero.Position() == pathList.get(0)) {
            roundNumber += 1; // rajoute 1 au compteur du hero quand le hero revient a la premiere position (le
            // CampFire)
            if (roundNumber >= 8) {
                System.out.println("vous avez gagnez !");
                System.exit(0);
            }

            //explore the matrix
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    matrix[i][j].newRound(hero,matrix,new GridPosition(i, j));
                }
            }

        }
    }

    //parcours la liste chaque nouveaux jour afin que chaque tuile fasse son action newDay
    public void newDay() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j].newDay(matrix, hero, new GridPosition(i, j));
            }
        }
    }

    /**
     * Unselects the cell (whether they is a selected cell or not).
     */
    public void unselect() {
        selectedCell = null;
        selectedCard = null;
        selectedEquipmentInBag =null;
        selectedEquipmentEquip=null;
    }


    /**
     * Initialize a  grid
     */

    public void fillMatrix() {

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = new LandScape();
            }
        }

        boolean first = true;

        for (int i = 0; i < pathList.size(); i++) {
            GridPosition gridPosition = pathList.get(i);
            if (first) {
                first = false;
                matrix[gridPosition.line()][gridPosition.column()] = new CampFire(CellOrientation.getOrientation(pathList,i)); // ajout du campfire
            } else {
                matrix[gridPosition.line()][gridPosition.column()] = new Paths(CellOrientation.getOrientation(pathList,i)); // ajout des path
            }

            addBorderPath(new GridPosition(gridPosition.line() - 1, gridPosition.column()));
            addBorderPath(new GridPosition(gridPosition.line() + 1, gridPosition.column()));
            addBorderPath(new GridPosition(gridPosition.line(), gridPosition.column() + 1));
            addBorderPath(new GridPosition(gridPosition.line(), gridPosition.column() - 1));
        }

    }




    private void addBorderPath(GridPosition gridPosition) {

        if (Tools.posInMatrix(matrix, gridPosition)) {

            if (!matrix[gridPosition.line()][gridPosition.column()].isPath()) { // verification que se n'est pas un Path
                matrix[gridPosition.line()][gridPosition.column()] = new BorderPath(); // ajout des BorderPath
            }
        }
    }

    public Hero getHero() {
        return hero;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public ArrayList<String> ressourceInfo(){
        return resource.getlistString();
    }

    private void dropCardOnBoard(){
        GridPosition testpos = selectedCell; // position ou a cliqué le joueur
        Card card = hand.get(selectedCard);    // carte de l'utilisateur

        ArrayList<Cell> cellAdjacent = new ArrayList<>();    //liste des cellules dont on a besoin pour savoir si
        // l'utilisateur a bien cliqué sur une tuile correct avec sa carte

        cellAdjacent.add(matrix[testpos.line()][testpos.column()]); // ajout de la position a la quelle la carte veut etre mise
        cellAdjacent.addAll(Tools.getAdjacentCell(matrix, testpos));    // ajout des position adjacente

        if (card.isPosable(cellAdjacent)) {    // si la cellule est bien du meme type que l'emplacement alors ...
            Cell newCell = card.drop(matrix, testpos);

            matrix[testpos.line()][testpos.column()].replace(newCell);
            matrix[testpos.line()][testpos.column()] = newCell;    // pose de la carte
            ResourceElement element = matrix[testpos.line()][testpos.column()].set(matrix, hero, testpos);   // recupération de la ressource
            if (element!=null){
                resource.addResource(element.getName());    // ajoute l'element a sa ressource correspondante
            }
            hand.remove(card);    //suppression de la carte dans la main
        }
        unselect();
    }

    public void equip(){
        if (selectedEquipmentInBag !=null){
            hero.equip(selectedEquipmentInBag);
            selectedEquipmentInBag =null;
        }
    }

    /**
     * Updates the data contained in the GameData.
     */
    public void updateData() {

        if (hasASelectedCell() && hasASelectedCard()) {
            dropCardOnBoard();
        }


    }
}
