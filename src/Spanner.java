import java.util.ArrayList;
import java.util.HashMap;


/**
 * this class is the levels and spanners
 * @author RuiminCai
 *
 */
public class Spanner {
	//store all the levels
	private HashMap<Integer, ArrayList<Vertex>> myHierarchyList;
	//store all the edges
	private HashMap<Integer,ArrayList<Edge>>myEdgeList;
	//keep track the current level
	private int level=0;
	//the parameter determine the parent and child
	private double para=16;
	//to balance the ratio
	private double two=2;
	private double ratio=10;
	//keep track of the reached 
	private boolean reached=false;
	
	
	/**
	 * constructor 
	 * @param origin
	 */
	public Spanner(ArrayList<Vertex> origin){
		//initialize the maps
		myHierarchyList=new HashMap<Integer, ArrayList<Vertex>>();
		myEdgeList=new HashMap<Integer,ArrayList<Edge>>();
		//first put the origins in the map
		myHierarchyList.put(level, origin);
		
	}
	
	/**
	 * build the edge
	 */
	public void getSpanner(){
		//get the vertex list
		ArrayList<Vertex> list=myHierarchyList.get(level);
		//calculate the parameter
		double c=4+16/para;
		//calculate the radius 
		int r=(int) (c*getRadius(level));
		// create a new list for edges 
		ArrayList<Edge> edges=new ArrayList<Edge>();
		//walk over each vertex to check
		for(int i=list.size()-1;i>0;i--){
			Vertex v1=list.get(i);
			if(!v1.isRemoved()){
				//check if it's in the radius 
			for(int j=0;j<i;j++){
				//if any other vertexes is inside the radius
				Vertex v2=list.get(j);
				if(!v2.isRemoved()&&checkDistance(v1,v2,r)){
					//add new edge
					Edge temp=new Edge(v1,v2);
					edges.add(temp);
				}
			}
		  }
		}
		// put the new list to the set
		myEdgeList.put(level, edges);
	}
	
	
	/**
	 * add a level
	 */
	public void buildHierarchy(){
		//old list 
		ArrayList<Vertex> list=myHierarchyList.get(level);
		//new list
		ArrayList<Vertex> newList=new ArrayList<Vertex>();
		//walk over to add all the discrete centers
		int counter=0;
		//if it's not reached top
		if(!reachTop()){
			//walk over every vertex
		for(int k=0;k<list.size();k++){
			Vertex v=list.get(k);
			//if it is a center
			if(v.isCenter()){
				counter++;
				//update to the new list as a parent
				newList.add(new Vertex(v.getX(),v.getY()));
			}else{
				//or add the vettex to new list
				//but as a child
				Vertex temp=new Vertex(v.getX(),v.getY());
				newList.add(temp);
				temp.remove();
			}
			
		}
		if(counter==1){
			//we reach the final level
			reached=true;
		}
		/**
		 * if we are not at the top level
		 */
		//update the level
			level++;
		//get the radius
			int r=getRadius(level);
			//walk over the list
			for(int i=newList.size()-1;i>0;i--){
				Vertex v1=newList.get(i);
				//if from the list we have a center
				if(v1.isCenter()){
					for(int j=0;j<i;j++){
						//we check all points see if any is in the radius
						Vertex v2=newList.get(j);
						if(checkDistance(v1,v2,r)){
							v2.removeCenter();
						}
					}
				}
			}
		}
		myHierarchyList.put(level,newList);
	}
	
	/**
	 * add a level 
	 */
	public void addLevel(){
		//build a new level
		buildHierarchy();
		getSpanner();
	}
	
	/**
	 * get the radius for the level
	 * @param l the level
	 * @return the radius
	 */
	public int getRadius(int l){
		//if it nonzero
		if(l!=0){
			//return calculated
			return (int) ((Math.pow(two, l))*ratio);
		}else{
			return 1;
		}
	}
	
	/**
	 * if we reach the final level
	 * @return reach the final level
	 */
	public boolean reachTop(){
		return reached;
	}
	
	/**
	 * check if two vertexes are in the radius
	 * @param i vertex one
	 * @param j vertex two
	 * @param r the radius
	 * @return if it's in the radius of the other
	 */
	public boolean checkDistance(Vertex i,Vertex j,int r){
		//calculate the x y difference
		int x=i.getX()-j.getX();
		int y=i.getY()-j.getY();
		//calculate the distance
		int distanceSquare=x*x+y*y;
		//check if it's in the distance
		if(distanceSquare<=r*r){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * get the number of the final level
	 * @return the final level number
	 */
	public int getMaxLevel(){
		return level;
	}
	
	/**
	 * get the list of vertexes of the same level
	 * @param l the level number (key)
	 * @return the list
	 */
	public ArrayList<Vertex> getLevelVertexList(int l){
		return  myHierarchyList.get(l);
	}
	
	/**
	 * get the edge list 
	 * @param l the level number(key)
	 * @return the list 
	 */
	public ArrayList<Edge> getLevelEdgeList(int l){
		return  myEdgeList.get(l);
	}


}