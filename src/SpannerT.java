import java.util.ArrayList;
import java.util.HashMap;


public class SpannerT {
	//store all the 
	private HashMap<Integer, ArrayList<Vertex>> myHierarchyList;
	private HashMap<Integer,ArrayList<Edge>>myEdgeList;
	private int level=0;
	private double para=16;
	private double two=2;
	private double ratio=10;
	private boolean reached=false;
	private int counterForPoints=0;
	
	
	public SpannerT(ArrayList<Vertex> origin){
		myHierarchyList=new HashMap<Integer, ArrayList<Vertex>>();
		myEdgeList=new HashMap<Integer,ArrayList<Edge>>();
		buildTop();
		//myHierarchyList.put(level, origin);
		
	}
	
	public void buildTop(){
		ArrayList<Vertex> list=new ArrayList<Vertex>();
		list.add(list.get(counterForPoints));
		counterForPoints++;
		
	}
	
	/**
	 * build the edge
	 */
	public void getSpanner(){
		ArrayList<Vertex> list=myHierarchyList.get(level);
		double c=4+16/para;
		int r=(int) (c*getRadius(level));
		ArrayList<Edge> edges=new ArrayList<Edge>();
		//walk over each vertex to check
		for(int i=list.size()-1;i>0;i--){
			Vertex v1=list.get(i);
			if(!v1.isRemoved()){
				//check if it's in the radius 
			for(int j=0;j<i;j++){
				Vertex v2=list.get(j);
				if(!v2.isRemoved()&&checkDistance(v1,v2,r)){
					Edge temp=new Edge(v1,v2);
					edges.add(temp);
				}
			}
		  }
		}
		myEdgeList.put(level, edges);
	}
	
/*	public void buildOneHierarchy(){
		ArrayList<Vertex> list=myHierarchyList.get(level);
		ArrayList<Vertex> newList=new ArrayList<Vertex>();
		if(!reachTop()){
			level++;
			int r=getRadius(level);
			for(int i=list.size()-1;i>0;i--){
				Vertex v1=list.get(i);
				if(v1.isCenter()){
					newList.add(v1);
					for(int j=0;j<i;j++){
						Vertex v2=list.get(j);
						if(checkDistance(v1,v2,r)){
							v2.removeCenter();
						}
					}
				}
			}
		}
		myHierarchyList.put(level,newList);
	}
	*/
	
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
		if(!reachTop()){
		for(int k=0;k<list.size();k++){
			Vertex v=list.get(k);
			if(v.isCenter()){
				counter++;
				newList.add(new Vertex(v.getX(),v.getY()));
			}else{
				Vertex temp=new Vertex(v.getX(),v.getY());
				newList.add(temp);
				temp.remove();
			}
			
		}
		if(counter==1){
			reached=true;
			System.out.println(reached);
		}
		/**
		 * if we are not at the top level
		 */
		
			level++;
			int r=getRadius(level);
			for(int i=newList.size()-1;i>0;i--){
				Vertex v1=newList.get(i);
				if(v1.isCenter()){
					for(int j=0;j<i;j++){
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
		buildHierarchy();
		getSpanner();
	}
	
	public int getRadius(int l){
		if(l!=0){
			return (int) ((Math.pow(two, l))*ratio);
		}else{
			return 1;
		}
	}
	
	public boolean reachTop(){
		return reached;
	}
	public boolean checkDistance(Vertex i,Vertex j,int r){
		int x=i.getX()-j.getX();
		int y=i.getY()-j.getY();
		int distanceSquare=x*x+y*y;
		if(distanceSquare<=r*r){
			return true;
		}else{
			return false;
		}
	}
	
	public int getMaxLevel(){
		return level;
	}
	
	public ArrayList<Vertex> getLevelVertexList(int l){
		return  myHierarchyList.get(l);
	}
	public ArrayList<Edge> getLevelEdgeList(int l){
		return  myEdgeList.get(l);
	}
}

