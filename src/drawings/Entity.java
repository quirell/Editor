package drawings;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Entity implements Drawable{

	private Rectangle2D.Float pos = new Rectangle2D.Float();
	private boolean draggable;
	private Type type = Type.BLOCK;
	public Entity(){
		pos.x = 50;
		pos.y = 50;
		pos.width = 50;
		pos.height = 50;
	}
	public Entity(int x,int y){
		pos.x = x;
		pos.y = y;
		pos.width = 50;
		pos.height = 50;
	}
	public Entity(float x,float y,float width,float height){
		pos.x = x;
		pos.y = y;
		pos.width = width;
		pos.height = height;
	}
	public Rectangle2D.Float getRect(){
		return pos;
	}
	@Override
	public void draw(Graphics2D g) {
		
		g.setColor(Color.black);
		Rectangle r = pos.getBounds();
		g.fillRect(r.x, r.y, r.width, r.height);
		if(draggable){
			g.setColor(Color.GRAY);
			g.setStroke(new BasicStroke(3));
			g.drawRect(r.x, r.y, r.width, r.height);
		}
	}
	@Override
	public String toString(){
		return "("+pos.x+", "+pos.y+" : "+pos.width+", "+pos.height+")";
	}
	public void changeState() {
		draggable = !draggable;
		
	}
	
	public boolean draggable(){
		return draggable;
	}

	public void setPos(Point2D p){
		pos.x = (float) p.getX();
		pos.y = (float) p.getY();
	}
	public void setWidth(float width){
		pos.width=width;
	}
	public void setHeight(float height) {
		pos.height = height;
	}
	public Type getType(){
		return type;
	}
	public void setType(Type t){
		type = t;
	}
}
