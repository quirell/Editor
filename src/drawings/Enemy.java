package drawings;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;


public class Enemy extends Entity{

	
	public Enemy(){
		super();
		type = Type.ENEMY;
	}
	
	public Enemy(float x,float y){
		super(x,y);
		type = Type.ENEMY;
	}
	public Enemy(DataInputStream stream) throws IOException{
		super(stream);
		type = Type.ENEMY;
		
	}

	@Override
	public void draw(Graphics2D g) {

		g.setColor(Color.RED);
		Rectangle r = pos.getBounds();
		g.drawOval(r.x, r.y, r.width, r.height);
		if (draggable) {
			g.setColor(Color.GRAY);
			g.setStroke(new BasicStroke(3));
			g.drawOval(r.x, r.y, r.width, r.height);
		}
		
	}

	@Override
	public Object[] getAllData() {
		
		Object[] t= new Object[4];
		t[0] = pos.x;
		t[1] = pos.y;
		t[2] = pos.width;
		t[3] = pos.height;
		return t;
	}

	@Override
	public void saveMe(DataOutputStream stream) throws IOException{

		stream.writeInt(type.ordinal());
		stream.writeFloat(pos.x);
		stream.writeFloat(pos.y);
		stream.writeFloat(pos.width);
		stream.writeFloat(pos.height);
		
	}
}
