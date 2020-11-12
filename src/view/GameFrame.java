package view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GameFrame extends JFrame{
    
    private JPanel contentPane;
	private BorderLayout borderLayout = new BorderLayout();

    //CONSTRUCTOR
    public GameFrame(){
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
        try {
            initUI();
        } catch(Exception e) {
            e.printStackTrace();
        }    
    }

    /*Initialize the frame and set the border*/
    private void initUI() throws Exception{
        
        contentPane = (JPanel) this.getContentPane();
        contentPane.setLayout(borderLayout);
    
    }

    @Override
	protected void processWindowEvent(WindowEvent e) {
		super.processWindowEvent(e);
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			System.exit(0);
		}
	}
    

}



