package fr.iut.zen.game.Entity;

import fr.iut.zen.game.BattleDetails;
import fr.iut.zen.game.Property.Drawable;
import fr.iut.zen.game.Equipments.Bag;
import fr.iut.zen.game.Equipments.Equipment;
import fr.iut.zen.game.Equipments.EquipmentPart;
import fr.iut.zen.game.GridPosition;
import fr.iut.zen.game.Equipments.Statistics;

import java.awt.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Hero  extends AbstractEntity {

	private GridPosition position;
	private final List<GridPosition> possiblePositions;
	private int positionNumber = 0; // indique la position de la liste ou se situe le hero
	private int timeInCombat = 0;
	private Bag bag = new Bag();
	private int round = 1;
	private HashMap<EquipmentPart, Statistics> equipments = new HashMap<>();
	private BattleDetails battleDetails;


	public Hero(List<GridPosition> possiblePositions, int maxLife) {

		super(Objects.requireNonNull(maxLife),List.of(4f,6f) ,0, 0, 0, 0, 0);
		Objects.requireNonNull(possiblePositions);
		this.possiblePositions = possiblePositions;
		this.position = possiblePositions.get(positionNumber);
		for (EquipmentPart part : EquipmentPart.values()) {
			equipments.put(part, null);
		}



	}

	public float maxLife() {
		return super.getMaxLife();
	}
	public float life() {
		return getLife();
	}

	public GridPosition Position() {
		return position;
	}

	/**
	 * @param impact life add to hero life's
	 */


	public void lifeMaxImpact(float impact) {
		setMaxLife(getMaxLife() + impact);


		if (getLife() > getMaxLife()) {
			setLife(getMaxLife());
		}

	}

	public GridPosition getNextPosition() {
		return positionNumber + 1 < possiblePositions.size() ? possiblePositions.get(positionNumber + 1) : possiblePositions.get(0);
	}

	/**
	 * move hero to next path
	 */
	public void moveToNextPosition() {

		positionNumber++;
		if (positionNumber == possiblePositions.size()) {
			positionNumber = 0;
			round++;
		}
		position = possiblePositions.get(positionNumber);
	}

	public int getRound() {
		return round;
	}

	public Equipment getEquipment(int index){
		return bag.getEquipment(index);
	}

	public void equip(int index){
		Equipment equipment = bag.equip(index);
		Statistics oldStatEquipment = equipments.get(equipment.equipmentPart());
		equipments.put(equipment.equipmentPart(),equipment.stats());
		updateStats(oldStatEquipment,equipment.stats());

	}

	private void updateStats(Statistics oldStatEquipment, Statistics stats) {
		if (oldStatEquipment != null) { // enlevement de l'ancienne statistique

			setMaxLife(getMaxLife() - oldStatEquipment.maxLife());
			setLife(getLife() - oldStatEquipment.maxLife());
			setDefense((int) (getDefense() - oldStatEquipment.defense()));
			setEvade(getEvade() - oldStatEquipment.evade());
			setRegen(getRegen() - oldStatEquipment.regen());
			setVampirism(getVampirism() - oldStatEquipment.vampirism());
			List<Float> oldDamage = getRangeDamage();
			setDamage(List.of(oldDamage.get(0) - oldStatEquipment.damage().get(0),oldDamage.get(1) - oldStatEquipment.damage().get(1)));
		}
		// ajout des nouvelles statistiques

		setMaxLife(getMaxLife() + stats.maxLife());
		setLife(getLife() + stats.maxLife());
		setDefense((int) (getDefense() + stats.defense()));
		setEvade(getEvade() + stats.evade());
		setRegen(getRegen() + stats.regen());
		setVampirism(getVampirism() + stats.vampirism());
		List<Float> damage = getRangeDamage();
		setDamage(List.of(damage.get(0) + stats.damage().get(0),damage.get(1) + stats.damage().get(1)));
	}

	public ArrayList<EquipmentPart> getBagEquipmentParts(){
		return bag.getEquipmentParts();
	}

	public ArrayList<EquipmentPart> getEquipEquipmentParts(){
		return new ArrayList<>(equipments.keySet());
	}

	public Statistics getEquipmentStats(EquipmentPart part){
		return equipments.get(part);
	}


	@Override
	public String name() {
		return "héro";
	}

	/**
	 * exit the program if hero life is 0
	 */
	@Override
	public boolean isDied() {
		if (getLife() == 0) {
			System.out.println("le hero est mort ^^' ");
			System.exit(0);
		}
		return false;
	}

	public boolean isInCombat() {
		return timeInCombat!=0;

	}

	public void addTimeInCombat() {
		timeInCombat++;
	}
	public void removeTimeInCombat() {
		timeInCombat--;
		if (timeInCombat<0){
			timeInCombat=0;
		}
	}

	public void addEquipmentInBag(Equipment equipment) {
		bag.addEquipment(equipment);
	}

	public void setBattleDetails(BattleDetails battleDetails) {
		this.battleDetails = battleDetails;
	}

	public BattleDetails getBattleDetails() {
		return battleDetails;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Hero)) {
			return false;
		}
		Hero hero = (Hero) o;
		return super.equals(hero) && positionNumber == hero.positionNumber && timeInCombat == hero.timeInCombat
				&& (position.equals(hero.position) && possiblePositions.equals(hero.possiblePositions));
	}

	@Override
	public int hashCode() {
		return Objects.hash(position, possiblePositions, positionNumber, timeInCombat);
	}

	@Override
	public void draw(Graphics2D graphics, float line, float column, float squareSize) {
		String pictureName = "pictures/Hero/Hero_3.png";
		Path heroPath = Path.of(pictureName);
		Drawable.drawImage(graphics, line, column, heroPath, squareSize);
	}
}
