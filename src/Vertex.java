/**
 * the vertex class
 * @author RuiminCai
 *
 */
public class Vertex {
	// x position
	private int x;
	// y position
	private int y;
	//if it's a center on the level
	private boolean center=true;
	//if it's removed from the graph
	private boolean removed=false;
	
	/**
	 * constructor
	 * @param i x-coordinate
	 * @param j y-coordinate
	 */
	public Vertex(int i,int j){
		x=i;
		y=j;
	}
	
	/**
	 * get the x coordinate
	 * @return x
	 */
	public int getX(){
		return x;
	}
	
	/**
	 * get the y coordinate
	 * @return y
	 */
	public int getY(){
		return y;
	}
	
	/**
	 * set the vertex as a noncenter
	 */
	public void removeCenter(){
		//set the center to false
		center=false;
	}
	
	/**
	 * check if it's a center
	 * @return it's a center
	 */
	public boolean isCenter(){
		return center;
	}
	
	/**
	 * check if the vertex as removed
	 * @return it's removed
	 */
	public boolean isRemoved(){
		return  removed;
	}
	
	/**
	 * set the vertex as removed
	 */
	public void remove(){
		removed=true;
	}

}
