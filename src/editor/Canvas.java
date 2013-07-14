package editor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

import drawings.Block;
import drawings.Entity;
import drawings.EntityManager;

public class Canvas extends JPanel{

	
	private int length=1000;
	private int start= 0;
	private int end=540;
	EntityManager m = new EntityManager();
	public Canvas(){
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(length,480));
		addMouseListener(new MouseAdapter() {
				
			@Override
			public void mouseClicked(MouseEvent event) {
				if(event.getButton() == MouseEvent.BUTTON1){
					m.mark(event.getPoint());
					repaint();
				}
			}
		});
		
		addMouseMotionListener(new MouseMotionAdapter() {

			@Override
			public void mouseDragged(MouseEvent event) {
				
				m.moveAndResize(event.getPoint());
				repaint();
			}
		});
	}
	
	
	@Override
	public void paint(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, getWidth(), getHeight());
		m.drawEntities(g2d);
		
	}
	
	
	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
		System.out.println(length);
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
	
	public void updatePosition(Integer pos) {
		start = pos;
		end = pos+540;
		System.out.println("start: "+start+", end: " + end);
		
	}


	public void addRect() {
		m.addEntity(new Block(start+50,50));
		
	}
	
	public EntityManager getManager(){
		return m;
		
	}


	public void save() {
		m.saveEntities("test.map", length);
		
	}
	public void load(){
		length = m.readEitities("test.map");
		setPreferredSize(new Dimension(length,480));
		repaint();
	}

}
