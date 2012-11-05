/**
 * Edge
 * @author RuiminCai
 *
 */
public class Edge {
	private Vertex x;
	private Vertex y;
	
	public Edge(Vertex i,Vertex j){
		x=i;
		y=j;
		
	}
	
	public Vertex getX(){
		return x;
	}
	
	public Vertex getY(){
		return y;
	}

}
