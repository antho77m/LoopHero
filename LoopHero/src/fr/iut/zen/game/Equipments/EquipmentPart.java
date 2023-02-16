package fr.iut.zen.game.Equipments;

import fr.iut.zen.game.Property.Drawable;
import fr.iut.zen.game.Property.Selectable;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.nio.file.Path;

public enum EquipmentPart implements Drawable, Selectable, Serializable {

    Helmet,
    Armor,
    Glove,
    Boots,
    Belt,
    Shield,
    Weapons,
    Ring;

    @Override
    public void draw(Graphics2D graphics, float column, float line, float squareSize) {
        switch (this) {
            case Helmet -> {
                String pictureName = "pictures/Equip/Helmet.png";
                Path meadowPath = Path.of(pictureName);
                Drawable.drawImage(graphics, column, line, meadowPath, squareSize);
            }
            case Belt -> {
                String pictureName = "pictures/Equip/Belt.png";
                Path meadowPath = Path.of(pictureName);
                Drawable.drawImage(graphics, column, line, meadowPath, squareSize);
            }
            case Armor -> {
                String pictureName = "pictures/Equip/Armor.png";
                Path meadowPath = Path.of(pictureName);
                Drawable.drawImage(graphics, column, line, meadowPath, squareSize);
            }
            case Glove -> {
                String pictureName = "pictures/Equip/Glove.png";
                Path meadowPath = Path.of(pictureName);
                Drawable.drawImage(graphics, column, line, meadowPath, squareSize);
            }
            case Boots -> {
                String pictureName = "pictures/Equip/Boots.png";
                Path meadowPath = Path.of(pictureName);
                Drawable.drawImage(graphics, column, line, meadowPath, squareSize);
            }
            case Shield -> {
                String pictureName = "pictures/Equip/Shield.png";
                Path meadowPath = Path.of(pictureName);
                Drawable.drawImage(graphics, column, line, meadowPath, squareSize);
            }
            case Weapons -> {
                String pictureName = "pictures/Equip/Weapon.png";
                Path meadowPath = Path.of(pictureName);
                Drawable.drawImage(graphics, column, line, meadowPath, squareSize);
            }
            case Ring -> {
                String pictureName = "pictures/Equip/Ring.png";
                Path meadowPath = Path.of(pictureName);
                Drawable.drawImage(graphics, column, line, meadowPath, squareSize);
            }
        }
    }

    @Override
    public Boolean checkPosition(Point2D.Float location, float column, float line, float squareSize) {
        return location.x>column && location.x<column+squareSize && location.y>line && location.y<line+squareSize;
    }
}
