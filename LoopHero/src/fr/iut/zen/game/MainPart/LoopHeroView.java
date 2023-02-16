package fr.iut.zen.game.MainPart;

import fr.iut.zen.game.*;
import fr.iut.zen.game.Cards.Card;
import fr.iut.zen.game.Cells.Cell;
import fr.iut.zen.game.Entity.Entity;
import fr.iut.zen.game.Entity.Hero;
import fr.iut.zen.game.Equipments.Equipment;
import fr.iut.zen.game.Equipments.EquipmentPart;
import fr.iut.zen.game.Equipments.Statistics;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public record LoopHeroView(int xOrigin, int yOrigin, int length, int width, int squareSize) implements GameView {

    public static LoopHeroView initGameGraphics(int xOrigin, int yOrigin, int length, LoopHeroData data) {
        int squareSize = (int) (length * 0.9 / data.nbLines());
        return new LoopHeroView(xOrigin - 150, yOrigin, length, data.nbColumns() * squareSize, squareSize);
    }

    public boolean checkXY(Point2D.Float location) {
        return location.x >= xOrigin && location.x < width + xOrigin && location.y >= yOrigin
                && location.y < length + yOrigin;
    }

    public Integer checkCard(Point2D.Float location, ArrayList<Card> cards) {
        float space = (float) (squareSize * 1.5 + 15);
        int i = 0;
        for (Card card : cards) {
            if (card.checkPosition(location, xOrigin + i * space, yOrigin + length + 80, squareSize)) {
                return i;
            }
            i++;
        }
        return null;
    }

    public Integer checkBagEquipmentPart(Point2D.Float location, ArrayList<EquipmentPart> bag) {

        int yOriginBis = yOrigin + 100;
        int xOriginBis = xOrigin + width + 300;
        float itemSize = (float) (squareSize / 1.2);

        float space = (float) (1.1);
        int j = 0;
        for (int i = 0; i < bag.size(); i++, j++) {
            if (i % 4 == 0) {
                yOriginBis += itemSize + space;
                j = 0;
            }
            if (bag.get(i).checkPosition(location, xOriginBis + j * (itemSize + space), yOriginBis, itemSize)) {
                return i;
            }
        }
        return null;
    }

    private int indexFromReaCoord(float coord, int origin) {
        return (int) ((coord - origin) / squareSize);
    }

    /**
     * Transforms a real y-coordinate into the index of the corresponding line.
     *
     * @param y a float y-coordinate
     * @return the index of the corresponding line.
     */
    public int lineFromY(float y) {
        return indexFromReaCoord(y, yOrigin);
    }

    /**
     * Transforms a real x-coordinate into the index of the corresponding column.
     *
     * @param x a float x-coordinate
     * @return the index of the corresponding column.
     */
    public int columnFromX(float x) {
        return indexFromReaCoord(x, xOrigin);
    }

    private float realCoordFromIndex(int index, int origin) {
        return origin + index * squareSize;
    }

    private float xFromColumn(int column) {
        return realCoordFromIndex(column, xOrigin);
    }

    private float yFromLine(int line) {
        return realCoordFromIndex(line, yOrigin);
    }

    ////////////////////////////// drawing methods //////////////////////////////

    private void drawHand(Graphics2D graphics, ArrayList<Card> cards) {

        float space = (float) (squareSize * 1.5 + 15);
        graphics.clearRect(xOrigin, yOrigin + length + 80, (int) ((squareSize * 1.5 + space) * ((7.2))), (int) (squareSize * 3)); //nettoie l'endroit ou sont dessiner les cartes

        int i = 0;
        for (Card card : cards) {
            card.draw(graphics, xOrigin + i * space, yOrigin + length + 80, squareSize);
            i++;
        }
    }

    private void drawBag(Graphics2D graphics, ArrayList<EquipmentPart> bag) {


        float itemSize = (float) (squareSize / 1.2);
        int yOriginBis = yOrigin + 100;
        int xOriginBis = xOrigin + width + 300;

        graphics.clearRect((int) (xOriginBis), yOriginBis, width / 4, length / 4);    //nettoie l'endroit ou sont dessiner le bag

        graphics.setColor(Color.BLACK);
        graphics.drawString("Bag :", xOriginBis, yOriginBis + 20);

        float space = (float) (1.1);
        int j = 0;
        for (int i = 0; i < bag.size(); i++, j++) {
            if (i % 4 == 0) {
                yOriginBis += itemSize + space;
                j = 0;
            }
            bag.get(i).draw(graphics, (xOriginBis + (j * space) * itemSize), (float) (yOriginBis), itemSize);
        }
    }

    private void drawEquipmentsEquip(Graphics2D graphics, ArrayList<EquipmentPart> equipmentParts) {

        float itemSize = (float) (squareSize / 1.2);
        int yOriginBis = yOrigin;
        int xOriginBis = xOrigin + width + 300;


        graphics.clearRect((int) (xOriginBis), yOriginBis, width / 4, length / 8);    //nettoie l'endroit ou sont dessiner le bag


        graphics.setColor(Color.BLACK);
        graphics.drawString("Equipements :", xOriginBis, yOriginBis - 20);
        float space = (float) (1.1);
        int j = 0;
        for (int i = 0; i < equipmentParts.size(); i++, j++) {
            if (i % 4 == 0 && i != 0) {
                yOriginBis += itemSize + space;
                j = 0;
            }
            if (equipmentParts.get(i) != null) {
                equipmentParts.get(i).draw(graphics, (xOriginBis + (j * space) * itemSize), (float) (yOriginBis), itemSize);
            }


        }

    }

    public EquipmentPart checkEquipmentsEquip(Point2D.Float location, ArrayList<EquipmentPart> equipmentParts) {

        float itemSize = (float) (squareSize / 1.2);
        int yOriginBis = yOrigin;
        int xOriginBis = xOrigin + width + 300;

        float space = (float) (1.1);
        int j = 0;
        for (int i = 0; i < equipmentParts.size(); i++, j++) {
            if (i % 4 == 0 && i != 0) {
                yOriginBis += itemSize + space;
                j = 0;
            }
            if (equipmentParts.get(i).checkPosition(location, (xOriginBis + (j * space) * itemSize), (float) (yOriginBis), itemSize)) {
                return equipmentParts.get(i);
            }
        }
        return null;


    }
    private  void drawSelectedEquipmentEquip(Graphics2D graphics, ArrayList<EquipmentPart> EquipmentPart, EquipmentPart selectedEquipment, Statistics statistics){


        float itemSize = (float) (squareSize / 1.2);
        int yOriginBis = yOrigin;
        int xOriginBis = xOrigin + width + 300;

        float space = (float) (1.1);
        int j = 0;
        for (int i = 0; i < EquipmentPart.size(); i++, j++) {
            if (i % 4 == 0 && i != 0) {
                yOriginBis += itemSize + space;
                j = 0;
            }
            if (EquipmentPart.get(i).equals(selectedEquipment)) {
                graphics.setColor(Color.DARK_GRAY);
                graphics.drawRect((int) (xOriginBis + (j * space) * itemSize), (int) (yOriginBis), (int) itemSize, (int) itemSize);
            }
        }

        //affichage des statistiques de l'équipement sélectionner
        graphics.setColor(Color.BLACK);
        graphics.clearRect(xOrigin + width + 300, (int) (yOrigin) + (length / 2), 250, 200);
        graphics.drawRect(xOrigin + width + 300, (int) (yOrigin) + (length / 2), 250, 200);
        String stat = selectedEquipment + "\n" ;
        if (statistics!=null){
            stat = stat + statistics.toString();
        }
        String stats[] = stat.split("\n");

        for (int i = 0; i < stats.length; i++) {
            graphics.drawString(stats[i], xOrigin + width + 300, (int) (yOrigin) + (length / 2) + 20 + i * 20);
        }

    }

    private void drawSelectedEquipment(Graphics2D graphics, ArrayList<EquipmentPart> bag, int pos, Equipment equipment) {

        //changement de couleur de la case sélectionner
        graphics.setColor(Color.darkGray);

        float itemSize = (float) (squareSize / 1.2);
        int yOriginBis = yOrigin + 100;
        int xOriginBis = xOrigin + width + 300;

        float space = (float) (1.1);
        int j = 0;
        for (int i = 0; i < bag.size(); i++, j++) {
            if (i % 4 == 0) {
                yOriginBis += itemSize + space;
                j = 0;
            }
            if (i == pos) {
                graphics.drawRect((int) (xOriginBis + (j * space) * itemSize), (int) (yOriginBis), (int) itemSize, (int) itemSize);
            }
        }

        //affichage des statistiques de l'équipement sélectionner
        graphics.setColor(Color.BLACK);
        graphics.clearRect(xOrigin + width + 300, (int) (yOrigin) + (length / 2), 250, 200);
        graphics.drawRect(xOrigin + width + 300, (int) (yOrigin) + (length / 2), 250, 200);
        String stats[] = equipment.toString().split("\n");

        for (int i = 0; i < stats.length; i++) {
            graphics.drawString(stats[i], xOrigin + width + 300, (int) (yOrigin) + (length / 2) + 20 + i * 20);
        }


    }

    private void drawSelectedCard(Graphics2D graphics, int pos) {
        graphics.setColor(Color.darkGray);
        float space = (float) (squareSize * 1.5 + 15);
        graphics.drawRect((int) (xOrigin + pos * space), yOrigin + length + 80, (int) (squareSize * 1.5), (int) (squareSize * 2.5));
    }


    private void drawSelectedCell(Graphics2D graphics, int line, int column) {
        float x = xFromColumn(column);
        float y = yFromLine(line);
        graphics.setColor(Color.BLACK);
        graphics.fill(new Rectangle2D.Float(x, y, squareSize, squareSize));
        graphics.setColor(Color.PINK);
        graphics.fill(new Rectangle2D.Float(x + 2, y + 2, squareSize - 4, squareSize - 4));
    }

    private void drawHero(Graphics2D graphics, GridPosition position, Hero hero) {
        hero.draw(graphics, xFromColumn(position.column()), yFromLine(position.line()), squareSize);
    }

    private void drawImage(Graphics2D graphics, int line, int column, Path path) {
        try (InputStream in = Files.newInputStream(path)) {
            BufferedImage img = ImageIO.read(in);
            AffineTransformOp scaling = new AffineTransformOp(AffineTransform
                    .getScaleInstance(squareSize / (double) img.getWidth(), squareSize / (double) img.getHeight()),
                    AffineTransformOp.TYPE_BILINEAR);
            graphics.drawImage(img, scaling, xOrigin + column * squareSize, yOrigin + line * squareSize);
        } catch (IOException e) {
            throw new RuntimeException("problème d'affichage : " + path.getFileName());
        }
    }

    private void drawGrid(Graphics2D graphics, Cell[][] matrix, int nbLines, int nbColumns) {

        graphics.setColor(Color.LIGHT_GRAY);
        graphics.fill(new Rectangle2D.Float(xOrigin, yOrigin, width, length));


        for (int i = 0; i <= nbLines; i++) {
            int line = yOrigin + i * squareSize;
            for (int j = 0; j <= nbColumns; j++) {
                int column = xOrigin + j * squareSize;

                if (i != nbLines && j != nbColumns) {
                    //remplis les cellules
                    matrix[i][j].draw(graphics, column, line, squareSize);
                }
                graphics.setColor(Color.BLACK);

            }
        }

    }

    private void drawStats(Graphics2D graphics, Hero hero) {

        graphics.clearRect(xOrigin + width + 50, 40, 200, 150);
        graphics.setColor(Color.BLACK);
        String title = "Héro :";
        if (hero.isInCombat()) {
            title = title + " ( en combat ) ";
        }

        graphics.drawString(title, xOrigin + width + 50, 50);

        String stats[] = hero.toString().split("\n");


        for (int i = 0; i < stats.length; i++) {
            graphics.drawString(stats[i], xOrigin + width + 50, 70 + i * 20);
        }
    }

    private void drawRoundNumber(Graphics2D graphics, int roundNumber) {

        graphics.setColor(Color.BLACK);
        graphics.clearRect(xOrigin, length+50, 200, 50);
        String lifeInfo = "nombre de tour : " + roundNumber;
        graphics.drawString(lifeInfo, xOrigin, length+70);


    }

    private void drawResource(Graphics2D graphics, ArrayList<String> resourceInfo) {
        graphics.clearRect(xOrigin + width + 300, length(), 200, 200);
        graphics.setColor(Color.BLACK);


        graphics.drawString("Ressources :", xOrigin + width + 300, length());
        for (int i = 0; i < resourceInfo.size(); i++) {
            graphics.drawString(resourceInfo.get(i), xOrigin + width + 300, length() + 25 + 15 * i);
        }
    }
    private void drawBar(Graphics2D graphics, int width, double timeFraction) {
        graphics.setColor(Color.LIGHT_GRAY);
        graphics.fill(new Rectangle2D.Double(xOrigin, yOrigin - 20, width, 10));
        graphics.setColor(Color.GREEN);
        graphics.fill(new Rectangle2D.Double(xOrigin, yOrigin - 20, width * timeFraction, 10));
        graphics.setColor(Color.BLACK);
        graphics.draw(new Rectangle2D.Double(xOrigin, yOrigin - 20, width, 10));
    }

    private void drawMonsterStats(Graphics2D graphics, Entity monster) {
        graphics.clearRect(xOrigin + width+50, length()/3, 200, 200);
        graphics.setColor(Color.BLACK);
        String stats[] = monster.toString().split("\n");


        graphics.drawString(monster.name(), xOrigin + width + 50, length()/3 + 20);
        for (int i = 0; i < stats.length; i++) {

            if (i == 0) {
                graphics.drawString("vie maximal : " + monster.getMaxLife(), xOrigin + width + 50, length()/3 + 40 + i * 20);
            } else {
                graphics.drawString(stats[i], xOrigin + width + 50, length()/3 + (i+2) * 20);
            }

        }

    }

    private  void drawBattleDetails(Graphics2D graphics, BattleDetails battleDetails){
        drawMonsterStats(graphics,battleDetails.entity());
        drawDetails(graphics,battleDetails.details());
    }

    private void drawDetails(Graphics2D graphics, String details) {


        String detail[] = details.toString().split("\n");

        graphics.clearRect(xOrigin + width+50, (int) (length()/1.5)-20, 200, (detail.length) * 20);

        graphics.setColor(Color.BLACK);


        graphics.drawString("Détails du combat :", xOrigin + width + 50, (int) (length()/1.5 ));
        for (int i = 0; i < detail.length; i++) {
                graphics.drawString( detail[i], xOrigin + width + 50, (int) (length()/1.5 + (i+2) * 15));

        }

    }


    ////////////////////////////////////////////////////////////////////////////

    /**
     * Draws the game board from its data, using an existing Graphics2D object.
     *
     * @param graphics a Graphics2D object provided by the default method
     *                 {@code draw(ApplicationContext, GameData)}
     * @param data     the GameData containing the game data.
     */
    @Override
    public void draw(Graphics2D graphics, LoopHeroData data, TimeData timeData) {

        drawBar(graphics, data.nbColumns() * squareSize, timeData.timeFraction());


        graphics.setFont(Font.getFont(Font.MONOSPACED));

        //dessin des différentes infos du jeu
        drawStats(graphics, data.getHero());
        drawRoundNumber(graphics, data.getRoundNumber());
        drawResource(graphics, data.ressourceInfo());

        drawEquipmentsEquip(graphics, data.getHero().getEquipEquipmentParts());
        if (data.hasASelectedEquipmentEquip()){
            drawSelectedEquipmentEquip(graphics, data.getHero().getEquipEquipmentParts(), data.getSelectedEquipmentEquip(),data.getHero().getEquipmentStats(data.getSelectedEquipmentEquip()));
        }

        drawBag(graphics, data.getHero().getBagEquipmentParts());


        if (data.hasASelectedEquipment()) {
            drawSelectedEquipment(graphics, data.getHero().getBagEquipmentParts(), data.getSelectedEquipmentInBag(), data.getHero().getEquipment(data.getSelectedEquipmentInBag()));
        }

        drawHand(graphics, data.getHand());
        if (data.hasASelectedCard()) {
            drawSelectedCard(graphics, data.getSelectedCard());
        }

        if(data.getHero().isInCombat()){
            drawBattleDetails(graphics,data.getHero().getBattleDetails());
        }else if (data.getHero().getBattleDetails()!=null){
            graphics.clearRect(xOrigin + width+50, length()/3, 200, 200);

            int numberLine = data.getHero().getBattleDetails().details().split("\n").length;
            graphics.clearRect(xOrigin + width+50, (int) (length()/1.5)-20, 200, numberLine * 20);

        }
        // dessin de la grille
        drawGrid(graphics, data.matrix(), data.nbLines(), data.nbColumns());

        // dessin de la cellule selectionnée
        GridPosition position = data.getSelectedCell();
        if (position != null) {
            drawSelectedCell(graphics, position.line(), position.column());
        }

        drawHero(graphics, data.getPosHero(), data.getHero());

    }


    /**
     * Draws only the cell specified by the given coordinates in the game board from
     * its data, using an existing Graphics2D object.
     *
     * @param graphics a Graphics2D object provided by the default method
     *                 {@code draw(ApplicationContext, GameData)}
     * @param data     the GameData containing the game data.
     * @param x        the float x-coordinate of the cell.
     * @param y        the float y-coordinate of the cell.
     */
    @Override
    public void drawOnlyOneCell(Graphics2D graphics, LoopHeroData data, int x, int y) {
        graphics.setColor(Color.BLACK);
        graphics.fill(new Rectangle2D.Float(x, y, 10, 10));
    }
}
