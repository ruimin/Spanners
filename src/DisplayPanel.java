
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JComponent;

/**
 * it's the display panel of the gui
 * @author RuiminCai
 *
 */
public class DisplayPanel extends JComponent {

	// the spanner
	private Spanner spanner;
	//the level
	private int level=0;
	//color to mark the center(parent)
	private static final Color centerColor=Color.red;
	//color to mark the child
	private static final Color overlapColor=Color.black;
	//color of the edges
	private static final Color edgeColor=Color.blue;

	/**
	 * Constructor
	 * @param s
	 */
	public DisplayPanel(Spanner s){
		spanner=s;
	}
	
	/**
	 * paint the component
	 */
	 public void paintComponent(Graphics g) {
	        super.paintComponent(g);
	      //set the pen color to white 
			g.setColor(Color.white);
			// draw a rectangle that fills the component
			g.fillRect(0, 0, getWidth(),getHeight());
	        drawPoints(g);
	        drawCircles(g);
	        drawEdges(g);
	        //System.out.println(level);
	        this.repaint();

	 }

	 /**
	  * update to a different level
	  * @param l
	  */
	 public void updateLevel(int l){
		 level=l;
	 }
	
	 /**
	  * draw all the points on the graph
	  * @param g graph
	  */
	public void drawPoints(Graphics g){
		//get the vertex list 
		ArrayList<Vertex> list=spanner.getLevelVertexList(level);
		// walker over the list
		for(int i=0;i<list.size();i++){
			// paint different colors
			Vertex v=list.get(i);
			if(v.isCenter()){
			 g.setColor(centerColor);
			}else if(v.isRemoved()){
				g.setColor(Color.green);
			}
			else{
			g.setColor(overlapColor);
			}
			g.fillRect(v.getX(),v.getY(),3,3);
		}
	}
	
	/**
	 * paint all the circles
	 * @param g graph
	 */
	public void drawCircles(Graphics g){
		//paint circles around the center vertex(parents)
		ArrayList<Vertex> list=spanner.getLevelVertexList(level);
		for(int i=0;i<list.size();i++){
			Vertex v=list.get(i);
			if(v.isCenter()){
			 g.setColor(Color.black);
			 int r=spanner.getRadius(level);
			 g.drawOval(v.getX()-r,v.getY()-r,r*2,r*2);
			}
		}
		
	}
	
	/**
	 * draw the edges
	 * @param g graph
	 */
	public void drawEdges(Graphics g){
		//get the list of edges
		ArrayList<Edge> list=spanner.getLevelEdgeList(level);
		if(list!=null){
			//draw the edges
		for(int i=0;i<list.size();i++){
			Edge e=list.get(i);
			Vertex v1=e.getX();
			Vertex v2=e.getY();
			g.setColor(edgeColor);
			g.drawLine(v1.getX(), v1.getY(), v2.getX(), v2.getY());
		}
		}
	}
	

}
