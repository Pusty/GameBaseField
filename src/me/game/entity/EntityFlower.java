package me.game.entity;



public class EntityFlower extends Entity {

	public EntityFlower(int x, int y) {
		super(x, y);
	}
	
	@Override
	public String getTextureName() {
		return "flower_red";
	}
	
}


