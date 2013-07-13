package drawings;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;
import java.awt.geom.Rectangle2D;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class EntityManager {

	List<Entity> items;
	Entity marked;
	float px, py;

	public EntityManager() {
		items = new LinkedList<Entity>();
	}

	public void addEntity(Entity item) {
		items.add(item);
	}

	public void mark(Point2D p) {
		for (Entity e : items) {
			if (e.getRect().contains(p)) {

				if (e.draggable()) {
					
					marked = null;
					e.changeState();
				} else {
					if(marked != null)
						marked.changeState();
					marked = e;
					e.changeState();
				}
				break;
			}

		}
	}

	public void moveAndResize(Point p) {
		if (marked != null) {
			Rectangle2D.Float r = marked.getRect();
			if (r.contains(p)) {

				float dx = p.x - px;
				float dy = p.y - py;
				if (Math.abs(dx) < 10 && Math.abs(dy) < 10) {
					if (p.x >= r.x + r.width - 5 && p.x <= r.x + r.width) {
					
						marked.setWidth(r.width + dx);
					} else if (p.x >= r.x && p.x <= r.x + 5) {
						
						marked.setPos(new Float(r.x + dx, r.y + dy));
						marked.setWidth(r.width - dx);
					} else if (p.y >= r.y && p.y <= r.y + 5) {
						
						marked.setPos(new Float(r.x + dx, r.y + dy));
						marked.setHeight(r.height - dy);

					} else if (p.y >= r.y + r.height - 5 && p.y <= r.y + r.height) {
						
						marked.setHeight(r.height + dy);
					} else {												//zwyk³y drag&drop
						
						marked.setPos(new Float(r.x + dx, r.y + dy));
					}

				}
			}
		}
		px = p.x;
		py = p.y;

	}
	
	public void drawEntities(Graphics2D g){
		for (Entity e : items){
			e.draw(g);
		}
	}
	public void saveEntities(String p,int length){
	
		if(items.isEmpty())
			return;
		try {
			DataOutputStream data = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(p)));
			System.out.println("save l: "+length);
			data.writeInt(length);
			data.writeInt(items.size());
			for(Entity e:items){
				Rectangle2D.Float r = e.getRect();
				data.writeInt(e.getType().ordinal());
				data.writeFloat(r.x);
				data.writeFloat(r.y);
				data.writeFloat(r.width);
				data.writeFloat(r.height);
				
			}
			System.out.println("saved");
			data.flush();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	public int readEitities(String p){
		
		int length = 0;
		try {
			DataInputStream data = new DataInputStream(new BufferedInputStream(new FileInputStream(p)));
			length = data.readInt();
			System.out.println("l: "+length);
			items = new LinkedList<Entity>();
			float x,y,width,height;
			int l = data.readInt();
			for(int i = 0;i<l;i++){
				Type t = Type.values()[data.readInt()];
				Entity e = new Entity(data.readFloat(),data.readFloat(),data.readFloat(),data.readFloat());
				e.setType(t);
				items.add(e);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return length;
	}

	

}
