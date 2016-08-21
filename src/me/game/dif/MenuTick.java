package me.game.dif;

import com.badlogic.gdx.graphics.Color;

import me.game.main.Engine;
import me.game.util.Location;
import me.game.util.RenderUtil;

public class MenuTick extends Tick {


	public MenuTick(Engine engine) {
		super(engine);
	}
	@Override
	public void tick(Engine engine,float delta){
		
		if(Tick.esc && ticks==0) {
			engine.setGameStatus(0);
			engine.getSound().playClip("select");
			ticks=10;
		}
		
		if(ticks>0)
			ticks--;
		
		
		if(Tick.enter){
			Tick.enter=false;
			engine.getSound().playClip("select");
			engine.setGameStatus(0);
		}
	}
	
	int xOffset = -52;
	int yOffset = 12;
	
	@Override
	public void mouse(Engine engine, int screenX, int screenY, int pointer,
			int button) {
    	Location mouseLocation = new Location(screenX,screenY);
		if(Tick.overCentured(engine,mouseLocation,new Location(0+xOffset,-20+yOffset),"Back")){Tick.selectOption(engine,3);}
	}

	@Override
	public void render(Engine e, float delta) {
	//	e.getBatch().draw(e.getImageHandler().getImage("bg"), 0, 0,e.getCamera().viewportWidth,e.getCamera().viewportHeight);
		e.getBatch().setColor(new Color(1f,1f,1f,1f));
		e.getBatch().draw(e.getImageHandler().getImage("empty"), 0, 0,e.getCamera().viewportWidth,e.getCamera().viewportHeight);
		e.getBatch().setColor(new Color(0f,0f,0f,1f));
		RenderUtil.renderCentured(e,e.getBatch(),new Location(0+xOffset,20+yOffset),e.getText()[0]);
		for(int i=1;i<e.getText().length;i++)
			RenderUtil.renderCentured(e,e.getBatch(),new Location(0+xOffset,22 -8*i +yOffset),e.getText()[i]);
		
		RenderUtil.renderCentured(e,e.getBatch(),new Location(0+xOffset,-20 +yOffset),"Back");
		
		
		RenderUtil.renderCentured(e,e.getBatch(),new Location(-32+xOffset,-21 +yOffset),"[");
		RenderUtil.renderCentured(e,e.getBatch(),new Location(32+xOffset,-21 +yOffset),"]");
		e.getBatch().setColor(new Color(1f,1f,1f,1f));
	}
	@Override
	public void show() {
	}
}
