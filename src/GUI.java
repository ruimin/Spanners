import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * this GUI displays the visualization 
 * @author RuiminCai
 *
 */
public class GUI extends JPanel implements MouseListener,ChangeListener{
	//create the spanner
	private Spanner spanner;
	//Jpanel for displaying
	private DisplayPanel displayPanel;
	//create the button 
	private JButton button;
	//keep track of levels of hierarchy 
	private int level=0;
	/** slider for changing posterize ratio**/
	private JSlider slider;
	/** the tick space for JSlider scale**/
	private static final int TICK_MARK_INTERVAL=50;


	
	/**
	 * Constructor
	 * @param s
	 */
	public GUI(Spanner s){
		//get the spanner
		spanner=s;
		//init 
		init();
	}
	

	/**
	 * initialize
	 */
	public void init(){
		//set the layout
		setGeneralLayout();
	}

	/**
	 * set general layout
	 */
	public void setGeneralLayout(){
		//set the layout as border
		setLayout(new BorderLayout());
		//create a display with the spanner
		displayPanel=new DisplayPanel(spanner);
		//add the panel to the center
		add(displayPanel,BorderLayout.CENTER);
		//create the button
		button=new JButton("next");
		//add event listener to the button
		button.addMouseListener(this);
		//add it to the display
		add(button,BorderLayout.SOUTH);
		//create a panel for slider
		JPanel panel=createSliderPanel();
		//add the slider to the display
		add(panel,BorderLayout.NORTH);
		//disable the slider for now
		slider.setEnabled(false);
	}
	
	/**
	 * create a panel that holds slider
	 * @return the panel that holds the slider
	 */
	public JPanel createSliderPanel(){
		
		// create the new panel
		JPanel sliderPanel= new JPanel();
		
		// set the range of the slider
		slider=new JSlider(0,spanner.getMaxLevel());
		
		// add a new JLabel to the panel
		sliderPanel.add(new JLabel("Level of Hierarchy"));
		
		// add the listener to the slider
		slider.addChangeListener(this);
		
		// set the tick's space
		slider.setMajorTickSpacing(TICK_MARK_INTERVAL);
		
		// paint the slider
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		
		//add the JSlider to the panel
		sliderPanel.add(slider);
		
		// return the panel
		return sliderPanel;
		
	}
	
	/**
	 * this method is called when the slider is moved
	 */
	public void stateChanged(ChangeEvent e){
		level=slider.getValue();
		displayPanel.updateLevel(level);
	}
	
	/**
	 * enable the slider and disable the next button
	 */
	private void addSlider(){
		//set with the maximum level
		slider.setMaximum(spanner.getMaxLevel());
		//enable the slider
		slider.setEnabled(true);
		button.setEnabled(false);
	}

	
	/**
	 * respond to mouse click
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		//if the button clicked 
		if(e.getSource()==button){
			//if we haven't reached the top
			if(!spanner.reachTop()){
				//add another level
				level++;
				spanner.addLevel();
				//update the display panel
				displayPanel.updateLevel(level);
			}else{
				//enable the slider
				addSlider();
			}
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
