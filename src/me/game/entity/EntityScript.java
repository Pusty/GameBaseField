package me.game.entity;



public class EntityScript extends EntityLiving {
	public static final int LEEK=100,CAT=101;
	private int entityIndex;
	public EntityScript(int x, int y,int e) {
		super(x, y);
		entityIndex = e;
	}
	public int getEntityIndex() {
		return entityIndex;
	}
	
	public String getTextureName() {
		if(entityIndex==LEEK)
			return "leek_idle";
		if(entityIndex==CAT)
			return "cat";
		return "empty";
	}
	public String getMovingTexture() {
		if(entityIndex==LEEK)
			return "leek_moving";
		return "empty";
	}
	
	public boolean hasDirections() {
		if(entityIndex==LEEK)
			return true;
		if(entityIndex==CAT)
			return false;
		return true;
		}

	//Get Speed 30 = 1 sec
	public int getSpeed() {
		return 10;
	}
	
}


