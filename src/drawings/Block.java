package drawings;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Block extends Entity{

	public Block(){
		type = Type.BLOCK;
		
	}
	
	public Block(float x, float y, float width, float height){
		super(x,y,width,height);
		type = Type.BLOCK;
	}
	public Block(float x, float y) {
		pos.x = x;
		pos.y = y;
		pos.width = 50;
		pos.height = 50;
	}
	public Block(DataInputStream stream) throws IOException{
		super(stream);
		type = Type.BLOCK;
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
	public void draw(Graphics2D g) {

		g.setColor(Color.black);
		Rectangle r = pos.getBounds();
		g.fillRect(r.x, r.y, r.width, r.height);
		if (draggable) {
			g.setColor(Color.GRAY);
			g.setStroke(new BasicStroke(3));
			g.drawRect(r.x, r.y, r.width, r.height);
		}
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
