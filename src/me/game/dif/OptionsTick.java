package me.game.dif;

import com.badlogic.gdx.graphics.Color;

import me.game.main.Engine;
import me.game.util.Location;
import me.game.util.RenderUtil;

public class OptionsTick extends Tick {
	

	
	public OptionsTick(Engine engine) {
		super(engine);
	}


	float banana = 0;
	@Override
	public void tick(Engine engine,float delta){
		if(Tick.down && ticks<=0){
			if(engine.getOption()+1>3)
				engine.setOption(0);
			else
				engine.setOption(engine.getOption()+1);
			ticks=5;
		}
		if(Tick.up && ticks<=0){
			if(engine.getOption()-1<0)
				engine.setOption(3);
			else
				engine.setOption(engine.getOption()-1);	
			ticks=5;
		}	
		
		if(Tick.esc && ticks==0) {
			engine.setGameStatus(0);
			engine.getSound().playClip("select");
			ticks=10;
		}
		
		if(ticks>0)
			ticks=ticks-(1*delta*30);
		if(banana>0)
			banana=banana-(1*delta*30);
		if(Tick.enter){
			Tick.enter=false;
			engine.getSound().playClip("select");
			if(engine.getOption()==0) {
		
			}else if(engine.getOption()==1 && banana == 0) {
				try {
				engine.getVariable().clear();
				engine.saveVariables();
				engine.loadDefault();
				engine.loadVariables();
				System.out.println("Deletes Variables");
				banana = 20;
				} catch(Exception ex) { ex.printStackTrace(); }
			}
			else if(engine.getOption()==2) {}
//				engine.setGameStatus(3);
			else if(engine.getOption()==3){
				engine.setGameStatus(0);
			}
		}
	}
	int xOffset = -52;
	int yOffset = 12;
	
	@Override
	public void mouse(Engine engine, int screenX, int screenY, int pointer,
			int button) {
    	Location mouseLocation = new Location(screenX,screenY);
		if(Tick.overCentured(engine,mouseLocation,new Location(0 +xOffset,24+yOffset),"Options")){}
		else if(Tick.overCentured(engine,mouseLocation,new Location(0+xOffset,8+yOffset),"Size: "+4)){Tick.selectOption(engine,0);}
		else if(Tick.overCentured(engine,mouseLocation,new Location(0+xOffset,0+yOffset),"New Game")){Tick.selectOption(engine,1);}
		else if(Tick.overCentured(engine,mouseLocation,new Location(0+xOffset,-16+yOffset),"Back")){Tick.selectOption(engine,3); }
	}

	@Override
	public void render(Engine e, float delta) {
		//e.getBatch().draw(e.getImageHandler().getImage("bg"), 0, 0,e.getCamera().viewportWidth,e.getCamera().viewportHeight);
		e.getBatch().setColor(new Color(1f,1f,1f,1f));
		e.getBatch().draw(e.getImageHandler().getImage("empty"), 0, 0,e.getCamera().viewportWidth,e.getCamera().viewportHeight);
		e.getBatch().setColor(new Color(0f,0f,0f,1f));
		RenderUtil.renderCentured(e,e.getBatch(),new Location(0+xOffset,24+yOffset),"Options");
		RenderUtil.renderCentured(e,e.getBatch(),new Location(0+xOffset,8+yOffset),"Size: "+4);
		if(banana==0)
			RenderUtil.renderCentured(e,e.getBatch(),new Location(0+xOffset,0+yOffset),"New Game");
		else
			RenderUtil.renderCentured(e,e.getBatch(),new Location(0+xOffset,0+yOffset),"-New Game-");

		RenderUtil.renderCentured(e,e.getBatch(),new Location(0+xOffset,-16+yOffset),"Back");
		
		
		RenderUtil.renderCentured(e,e.getBatch(),new Location(-32+xOffset,-e.getOption()*8 + 6+yOffset),"[");
		RenderUtil.renderCentured(e,e.getBatch(),new Location(32+xOffset,-e.getOption()*8 + 6+yOffset),"]");
		e.getBatch().setColor(new Color(1f,1f,1f,1f));
	}

	@Override
	public void show() {
	}
}
