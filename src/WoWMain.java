import java.awt.Point;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JPanel;


import net.miginfocom.swing.MigLayout;




@SuppressWarnings("serial")
public class WoWMain //implements ActionListener
{
	private WoWDB wdb;
	private Image buttonIcon;
	private JFrame mainFrame;
	private Dimension screenSize;
    private JButton[] buttonArray;
    private WoWLine[] zone_lines;
	private Hashtable<String,Point> zones;
	private int zoneCount;
	private Hashtable<JButton, String> buttonList = new Hashtable<JButton, String>();
	private WoWActionListener actListener;
	
	
	
	public WoWMain()
	{
		
		wdb = new WoWDB();
		
		mainFrame = new JFrame("World of Warcraft");
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		zoneCount = 0;
		zone_lines = null;
		zones = new Hashtable<String, Point>();
		
		try 
		{
			buttonIcon = ImageIO.read(new File("data/icon1.png"));
			buttonIcon =   buttonIcon.getScaledInstance(10, 8, Image.SCALE_FAST);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//buttonArray = new JButton[250];
				
		mainFrame.setSize((int) screenSize.getWidth(), (int) screenSize.getHeight());
		
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		loadZones();
		
		actListener = new WoWActionListener(this);
	}
	
	
	public  Hashtable<JButton, String> getButtonList()
	{
		return buttonList;
	}
	
	public void loadZones()
	{
		int width =  (int) screenSize.getWidth();
		int height = (int) screenSize.getHeight();
		
		BufferedReader br;
		
		try 
		{
			br = new BufferedReader(new FileReader("data/zone pixel coordinates.csv"));

			String line;
			String name;
			int xLoc, yLoc;
			while ((line = br.readLine()) != null) 
			{
				String[] tokens = line.split(",");
				name = tokens[0];
				xLoc = Integer.parseInt(tokens[1]);
				yLoc = Integer.parseInt(tokens[2]);
				xLoc = (xLoc * width / 9000);
				yLoc = (yLoc * height / 6710);
				
				zones.put(name, new Point(xLoc,yLoc));
				zoneCount ++;
			}
			br.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	
	public void loadBackground()
	{
		try 
		{
			//mainFrame.removeAll();
			System.out.println("Painting Background..");
			mainFrame.setContentPane(new JPanel() {
				BufferedImage image = (BufferedImage) ImageIO.read(new File("data/AzerothFull.jpg"));
				public void paintComponent(Graphics g) {
					super.paintComponent(g);
					g.drawImage(image, 0, 0, (int) screenSize.getWidth(), (int) screenSize.getHeight(), this);
					System.out.println("Background loaded...");
					g.setColor(new Color(116,181,255));
					if (zone_lines != null)
					{	
						for(int i=0;i<zone_lines.length;i++)
						{
							System.out.println("Adding Transit lines..");
							if (zone_lines[i] != null)
							{
								System.out.println("Drawing line - (" 
											+zone_lines[i].getStartx() 
											+ "," +zone_lines[i].getStartx() 
											+ "),(" +zone_lines[i].getEndx() + "," 
											+ zone_lines[i].getEndy()+ ")");
								g.drawLine(zone_lines[i].getStartx(),
										zone_lines[i].getStarty(),
										zone_lines[i].getEndx(),
										zone_lines[i].getEndy());
							}
						}
						
					}
					else
						System.out.println("No Transit Lines to add");
					image.flush();
				}
			
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mainFrame.getContentPane().setLayout(new MigLayout());		
	}
	
	
	
	
	
	
	public void loadButtons()
	{		
		try 
		{	
			String name;
			int xLoc, yLoc;
			String loc ;
			buttonArray = null;
			buttonArray = new JButton[250];
			Enumeration<String> znames = zones.keys();
			for (int i=0;i<zones.size();i++) 
			{
				name = znames.nextElement();
				xLoc = zones.get(name).x;
				yLoc = zones.get(name).y;
				buttonArray[i] = new JButton(new ImageIcon(buttonIcon));
				buttonArray[i].setName(name);
				buttonArray[i].setBorder(null);
				buttonArray[i].setToolTipText(name);
				buttonArray[i].addActionListener(actListener);
				buttonList.put(buttonArray[i], name);
				if (buttonArray[i] == null){
					System.out.println("Button Creation Failed");
				}
				
				zones.put(name, new Point(xLoc,yLoc));

				loc = "pos " + (xLoc - 5) + "px " + (yLoc-4) +"px";

				mainFrame.getContentPane().add(buttonArray[i], loc );

			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void makeVisible()
	{
		mainFrame.setVisible(true);
	}
	
	public void rePaint()
	{
		//mainFrame.removeAll();
		mainFrame.revalidate();
		mainFrame.repaint();
		
		String name;
		int xLoc, yLoc;
		String loc ;
		Enumeration<String> znames = zones.keys();
		for(int i=0;i<zones.size();i++)
		{
			name = znames.nextElement();
			xLoc = zones.get(name).x;
			yLoc = zones.get(name).y;
	
			loc = "pos " + (xLoc - 5) + "px " + (yLoc-4) +"px";

			mainFrame.getContentPane().add(buttonArray[i], loc );
		}
	}
	
	
	public void getTransits(String name)
	{
		zone_lines = null;
		Point from = zones.get(name);
		ArrayList<String> tto = new ArrayList<String>();
		tto = wdb.getTransits(name);
		zone_lines = new WoWLine[tto.size()];
		for(int i=0;i<tto.size();i++)
		{
			Point to = zones.get(tto.get(i));
			if (to != null)
			{
				System.out.println("Adding trasit to " + tto.get(i));
				zone_lines[i] = new WoWLine(from.x, from.y, to.x, to.y);
			}
		}
		System.out.println("Added " + tto.size() + " transits");
		loadBackground();
	}
	
	
	
	public static void main(String[] args) 
	{		
		WoWMain w = new WoWMain();
		w.loadBackground();
		
		w.loadButtons();	
		w.makeVisible();
		
		//w.getTransits("Stormwind City");
		//w.loadBackground();
		//w.loadButtons();
		
	}
	
//	@Override
//	public void actionPerformed(ActionEvent e) 
//	{		
//		Object obj = e.getSource();
//		String zone = buttonList.get(obj);
//		System.out.println("Zone is " + zone);
//		this.getTransits(zone);
//	}	
	
	
	
	
}
