package drawings;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public abstract class Entity implements Drawable {

	protected Rectangle2D.Float pos = new Rectangle2D.Float();
	protected boolean draggable;
	protected Type type;

	public Entity() {
		pos.x = 50;
		pos.y = 50;
		pos.width = 50;
		pos.height = 50;
	}

	public Entity(float x, float y) {
		pos.x = x;
		pos.y = y;
		pos.width = 50;
		pos.height = 50;
	}

	public Entity(float x, float y, float width, float height) {
		pos.x = x;
		pos.y = y;
		pos.width = width;
		pos.height = height;
	}

	public Entity(DataInputStream stream) throws IOException{
		
		pos.x = stream.readFloat();
		pos.y = stream.readFloat();
		pos.width = stream.readFloat();
		pos.height = stream.readFloat();
		
	}
	public Rectangle2D.Float getRect() {
		return pos;
	}

	public abstract Object[] getAllData();//returns all data for View to show

	@Override
	public String toString() {
		return "(" + pos.x + ", " + pos.y + " : " + pos.width + ", " + pos.height + ")";
	}

	/* GETTERS AND SETTERS */
	public void changeState() {
		draggable = !draggable;

	}

	public boolean draggable() {
		return draggable;
	}

	public void setPos(Point2D p) {
		pos.x = (float) p.getX();
		pos.y = (float) p.getY();
	}

	public void setWidth(float width) {
		pos.width = width;
	}

	public void setHeight(float height) {
		pos.height = height;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type t) {
		type = t;
	}
	
	public abstract void saveMe(DataOutputStream stream) throws IOException;
}
