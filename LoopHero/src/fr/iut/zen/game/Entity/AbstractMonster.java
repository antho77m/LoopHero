package fr.iut.zen.game.Entity;


import java.util.List;
import java.util.Objects;

abstract class AbstractMonster extends AbstractEntity implements Monster {
	
	private int itemChance;

	AbstractMonster(int itemChance,int maxLife,float damage,float defense,float counter,float evade,float regen,float vampirism,int round){
		super((float) (maxLife*round*0.95*(1+(round-1)*0.02)), List.of((float)(damage * round * 0.95 * (1 + ((round - 1) * 0.02))),(float) (damage * round * 0.95 * (1 + ((round - 1) * 0.02)))) ,defense, counter, evade, regen, vampirism);
		this.itemChance = itemChance;
	}

	@Override
	public int getItemChance(){
		return itemChance;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass())
		{
			return false;
		}
		if (!super.equals(o)) return false;
		AbstractMonster that = (AbstractMonster) o;
		return super.equals(that) && itemChance == that.itemChance;
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), itemChance);
	}
}
