package drawings;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Platform extends Entity{

	private int start;
	private int end;
	public Platform(float x,float y,float width, float height){
		super(x,y,width,height);
		start = (int)x;
		end = (int)width;
		type = Type.PLATFORM;
	}
	public Platform(float x,float y,float width, float height,int start,int end){
		super(x,y,width,height);
		this.start = start;
		this.end = end;
		type = Type.PLATFORM;
	}
	public Platform(DataInputStream stream) throws IOException{
		super(stream);
		start = stream.readInt();
		end = stream.readInt();
		type = Type.PLATFORM;
		
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
		g.setColor(Color.RED);
		g.drawLine(start, r.y+r.height, end,r.y+r.height);
		
	}
	@Override
	public Object[] getAllData() {
		
		Object[] t= new Object[6];
		t[0] = pos.x;
		t[1] = pos.y;
		t[2] = pos.width;
		t[3] = pos.height;
		t[4] = start;
		t[5] = end;
		return t;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	@Override
	public void saveMe(DataOutputStream stream) throws IOException {
			
		stream.writeInt(type.ordinal());
		stream.writeFloat(pos.x);
		stream.writeFloat(pos.y);
		stream.writeFloat(pos.width);
		stream.writeFloat(pos.height);
		stream.writeInt(start);
		stream.writeInt(end);
	}
	
	
}
