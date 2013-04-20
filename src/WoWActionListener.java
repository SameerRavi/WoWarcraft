import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.JButton;


public class WoWActionListener implements ActionListener {

	private WoWMain main;
	private Hashtable<JButton, String> buttonList = new Hashtable<JButton, String>();
	
	public WoWActionListener(WoWMain w)
	{
		main = w;
		buttonList = w.getButtonList();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		String zone = buttonList.get(obj);
		System.out.println("Zone is " + zone);
		main.getTransits(zone);
		main.rePaint();
		
	}

}
