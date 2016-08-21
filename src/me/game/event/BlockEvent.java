package me.game.event;

import me.game.entity.EntityLiving;
import me.game.main.Engine;
import me.game.util.Location;

public class BlockEvent implements Event{
	EntityLiving living;
	int x;
	int z;
	int id;
	int index;
	public BlockEvent(int index,EntityLiving l,int x,int z,int id) {
		this.living=l;
		this.x=x;
		this.z=z;
		this.id=id;
		this.index=index;
	}
	
	public static boolean handleBlockCollision(Engine g,EntityLiving e,int x,int z,int id) {
		if(id >= 25 && id <= 31)
			return false;
		if(id >= 35 && id <= 49)return false;
		if(id >= 54 && id != 55 && id <= 58) return false;
		if(id >= 60 && id != 62 && id <= 64) return false;
		if(id == 52) return false;
		if(id == 53) {
			int dir = e.getDirection();
			if(dir==2) { //Goint in from below
				g.getEventHandler().queueEvent(new ScreenEvent(0));
				g.getEventHandler().queueEvent(new BlockEvent(0,e,x,z,id));
			}
			return false;
		}
		if(id == 62) {
			int dir = e.getDirection();
			if(dir==1) { //Goint in from below
				g.getEventHandler().queueEvent(new ScreenEvent(0));
				g.getEventHandler().queueEvent(new BlockEvent(1,e,x,z,id));
			}
			return false;
		}
		return true;
	}
	
	
	@Override
	public String getName() {
		return "block";
	}
	

	/**
	 * Index:
	 * 
	 *  0 = EntityLiving teleport event (on block id 19)
	 * 
	 */

	@Override
	public void event(Engine game) {
		if(index==0) {
			int dir = living.getDirection();
			if(dir==2) { //Goint in from below
				living.setLocation(new Location(100+3,100+2));
			}
		}else if(index==1) {
			int dir = living.getDirection();
			if(dir==1) { //Goint in from below
				living.setLocation(new Location(0+17,0+15));
			}
		}
	}

	@Override
	public int getIndex() {
		return index;
	}
}

