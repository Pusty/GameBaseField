package me.game.event;

import me.game.dialog.Dialog;
import me.game.entity.Entity;
import me.game.entity.EntityLiving;
import me.game.entity.EntityScript;
import me.game.main.Engine;

public class EntityEvent implements Event{
	EntityLiving player;
	Entity collide;
	int x;
	int z;
	int index;
	public EntityEvent(int index,EntityLiving user,int x,int z,Entity target) {
		player=user;
		collide=target;
		this.x=x;
		this.z=z;
		this.index=index;
	}

	@Override
	public String getName() {
		return "entity";
	}

	/**
	 * Index:
	 * 
	 *  0 = EntityLiving turn Event
	 * 
	 */
	
	@Override
	public void event(Engine game) {
		if(index==0) {
			if(player.getDirection()==1)
				((EntityLiving)collide).setLastDirection(2);
			else if(player.getDirection()==2)
				((EntityLiving)collide).setLastDirection(1);
			else if(player.getDirection()==3)
				((EntityLiving)collide).setLastDirection(4);
			else if(player.getDirection()==4)
				((EntityLiving)collide).setLastDirection(3);	
		}else if(index==1) {
			String[] text = {"Hello!","How are you?","I'm good :3"};
			game.setDialog(new Dialog("cat",text,null));
		}
	}
	

	public static void handleEntityCollision(Engine engine, EntityLiving e,
			int x, int z, Entity collide) {
		if(collide instanceof EntityScript) {
			EntityScript script = (EntityScript)collide;
			if(script.getEntityIndex()==EntityScript.LEEK)
				engine.getEventHandler().queueEvent(new EntityEvent(0,e,x,z,collide));
			else if(script.getEntityIndex()==EntityScript.CAT)
				engine.getEventHandler().queueEvent(new EntityEvent(1,e,x,z,collide));
		}
	}

	@Override
	public int getIndex() {
		return index;
	}
}
