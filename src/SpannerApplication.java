import java.util.ArrayList;

import javax.swing.JFrame;


/**
 * application for displaying
 * @author RuiminCai
 *
 */
public class SpannerApplication {
	
	private static ArrayList<Vertex> list;

	public static void main( String[] args )
	{
		JFrame mineFrame = new JFrame();
		createPoints();
		Spanner s=new Spanner(list);
		mineFrame.getContentPane().add(new GUI(s));
		mineFrame.setSize(1000,500);
		mineFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		mineFrame.setVisible( true );
	}
	
	private static void createPoints(){
		list=new ArrayList<Vertex>();
		list.add(new Vertex(15,40));
		list.add(new Vertex(15,30));
		list.add(new Vertex(200,140));
		list.add(new Vertex(600,80));
		list.add(new Vertex(135,200));
		list.add(new Vertex(800,40));
		list.add(new Vertex(500,50));
		list.add(new Vertex(720,200));
	}

}
