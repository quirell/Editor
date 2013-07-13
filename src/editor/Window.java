package editor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import drawings.Entity;

public class Window extends JFrame {

	JPanel toolBar;
	JButton rect;
	JButton load;
	JButton save;
	Canvas canvas;
	JScrollPane cScroll;
	LinkedList<Entity> items = new LinkedList<Entity>();
	public Window(){
		
		setSize(640,480);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setBackground(Color.white);
		setLayout(new BorderLayout());
		
	}
	public static void main(String[] args) {
		Window w = new Window();
		w.initWindow();
		w.repaint();
		w.setVisible(true);

	}
	public void initWindow(){
		
		/**\ToolBar/**\*/
		toolBar = new JPanel();
		toolBar.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20) );
		toolBar.setPreferredSize(new Dimension(100,480));
		add(toolBar,BorderLayout.WEST);
		rect = new JButton("rectangle");
		rect.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e){
				
				canvas.addRect();
				canvas.repaint();
			}
		});
		toolBar.add(rect);
		save = new JButton("save");
		save.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				
				canvas.save();
			}
		});
		toolBar.add(save);
		load = new JButton("load");
		load.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				
				canvas.load();
				System.out.println(canvas.getPreferredSize());
			}
		});
		toolBar.add(load);
		canvas = new Canvas();
		cScroll = new JScrollPane(canvas);
		cScroll.setPreferredSize(new Dimension(520,480));
		cScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		cScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		cScroll.getHorizontalScrollBar().addAdjustmentListener(new AdjustmentListener() {
			
			@Override
			public void adjustmentValueChanged(AdjustmentEvent arg0) {
				if(!arg0.getValueIsAdjusting())
				canvas.updatePosition(arg0.getValue());
				
			}
		});
		
		canvas.setFocusable(true);
		add(cScroll,BorderLayout.EAST);
		System.out.println(canvas.getPreferredSize());
		
		
	}

}
