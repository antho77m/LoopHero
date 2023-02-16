package fr.iut.zen.game.Cells;

import fr.iut.zen.game.*;
import fr.iut.zen.game.Cards.Card;
import fr.iut.zen.game.Cards.Deck;
import fr.iut.zen.game.Entity.*;
import fr.iut.zen.game.Equipments.Equipment;
import fr.iut.zen.game.Property.Drawable;
import fr.iut.zen.game.Property.Dropable;
import fr.iut.zen.game.Resources.ResourceElement;

import java.awt.Graphics2D;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;

public class Paths implements Cell {

	private Monster enemy = null;
	private CellOrientation orientation;

	public Paths(CellOrientation orientation) {
		this.orientation = orientation;
	}

	public void addNewMonster(Monster abstractMonster) {
		if (enemy == null) {
			enemy = abstractMonster;
		}
	}

	public Monster getMonster() {
		return enemy;
	}



	@Override
	public boolean isPath() {
		return true;
	}

	@Override
	public boolean isPosable(ArrayList<Cell> cells) {
		return cells.get(0).isPath() && cells.get(0).buildable();
	}

	@Override
	public ResourceElement set(Cell[][] cells, Hero hero, GridPosition position) {
		return null;
	}

	@Override
	public void newDay(Cell[][] cells, Hero hero, GridPosition position) {

		Random random = new Random();

		if (random.nextInt(100) < 5) {
			addNewMonster(new Slime(hero.getRound()));
		}
	}

	@Override
	public void drawInterface(Graphics2D graphics, float xOrigin, float Yorigin, float lenght, float height) {

	}

	public CellOrientation getOrientation() {
        return orientation;
    }

    @Override
	public boolean buildable() {
		return true;
	}

	@Override
	public void replace(Cell cell) {
		if(cell.isPath()){
			Paths path = (Paths) cell; // avant cela on regarde que la tuile voulant etre posée est bien du meme type
			// de cellule,
			// donc le cast est sur
			path.orientation = orientation;

			if (!this.equals(path)) {
				path.addNewMonster(enemy);
			}

		}

	}

	private void fight(Entity attacker, Entity defender,StringBuilder text,Hero hero) {

		if(defender.isDied() || attacker.isDied()){
			return ;
		}
		if (attacker.equals(hero)){
			hero.addTimeInCombat();
		}

		Random rdm = new Random();
		if (rdm.nextInt(100) > defender.getEvade()) { // Test si le défendeur n'esquive pas
			if (rdm.nextInt(100) > defender.getCounter()) { // Test si le défendeur ne contre attaque pas
				float true_damage = attacker.getDamage() - defender.getDefense();

				true_damage*=10;
				true_damage = Math.round(true_damage);
				true_damage/=10;

				defender.removeLife(true_damage);
				text.append("Le ").append(defender.name()).append(" a perdu ").append(true_damage).append("pv\n");
				text.append("Il lui reste :").append(defender.getLife()).append("pv\n");
				if (rdm.nextInt(100) < attacker.getVampirism()) { // Test si l'attaquant vampirisme
					attacker.addLife(true_damage);
					text.append("Le ").append(attacker.name()).append(" a gagner ").append(true_damage * attacker.getVampirism()).append("\n");
					text.append("Il a encore : ").append(attacker.getLife()).append("\n");

				}
			} else { // Le défendeur contre attaque

				text.append("Le ").append(defender.name()).append(" contre attaque\n");
				fight(defender, attacker,text,hero);
			}
		}else{
			text.append("Le ").append(defender.name()).append(" a esquivé\n");
		}

	}

	private ArrayList<Dropable> heroFightingMonster(Hero hero, Deck deck) {

		Entity attacker = hero;
		Entity defender = (Entity) enemy;


		StringBuilder text = new StringBuilder();
		while (!(hero.isDied()) && !(enemy.isDied())) {

			if (hero.equals(attacker)) {
				attacker.addLife(attacker.getRegen());
				defender.addLife(defender.getRegen());
				fight(attacker,defender,text,hero);
				attacker = (Entity) enemy;
				defender = hero;
			} else {
				fight(attacker,defender,text,hero);
				attacker = hero;
				defender = (Entity) enemy;
			}
			text.append("\n");


		}
		hero.setBattleDetails(new BattleDetails(enemy,text.toString()));


		ArrayList<Dropable> dropable = new ArrayList<>();
		Random rand = new Random();
		int item = rand.nextInt(); // chance d'avoir un item (ressource)

		if (item < enemy.getItemChance()) {
			dropable.add(enemy.itemDrop()); // ajout de l'item obtenu
			hero.addEquipmentInBag(Equipment.createRandomEquipment(hero.getRound()));
		} else {
			int cardEarn = 2;
			for (int i = 0; i < cardEarn; i++) {
				Card card = deck.giveCard();
				if (card != null) {
					dropable.add(card);
				}
			}
		}
		enemy = null;
		return dropable;
	}

	@Override
	public ArrayList<Dropable> HeroIsOnPath(Hero hero, Deck deck) {
		if (enemy != null) {
			return heroFightingMonster(hero,deck );
		}

		return new ArrayList<>();
	}

	public void drawMonster(Graphics2D graphics, float column, float line, float squareSize) {
		if (enemy != null) {
			enemy.draw(graphics, column, line, squareSize);
		}
	}

	@Override
    public void draw(Graphics2D graphics, float column, float line, float squareSize) {

		String pictureName = "pictures/Textures/Basic (1).png";


		switch (orientation) {
			case HORIZONTAL -> {pictureName = "pictures/Textures/Basic (3).png";}
			case VERTICAL -> {pictureName = "pictures/Textures/Basic (4).png";}
			case TOP_LEFT -> {pictureName = "pictures/Textures/Basic (2).png";}
			case TOP_RIGHT -> {pictureName = "pictures/Textures/Basic (1).png";}
			case BOTTOM_LEFT -> {pictureName = "pictures/Textures/Basic (6).png";}
			case BOTTOM_RIGHT -> {pictureName = "pictures/Textures/Basic (5).png";}
		}

        Path path = Path.of(pictureName);
        Drawable.drawImage(graphics, column, line, path,squareSize);
        drawMonster(graphics,column,line,squareSize);
    }



	@Override
	public int hashCode() {
		int result = enemy != null ? enemy.hashCode() : 0;
		result = 31 * result + (orientation != null ? orientation.hashCode() : 0);
		return result;
	}

	@Override
	public boolean equals(Object o) {

		if (!(o instanceof Paths)){

			return false;

		}
		Paths paths = (Paths) o;

		return orientation == paths.orientation;
	}
}
