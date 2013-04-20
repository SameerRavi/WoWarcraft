//import java.awt.Dimension;
//import java.awt.Graphics;
//import java.awt.Image;
//import java.awt.Toolkit;
//import java.awt.image.BufferedImage;
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.io.IOException;
//
//import javax.imageio.ImageIO;
//import javax.swing.ImageIcon;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//
//import javax.swing.JPanel;
//
//
//import net.miginfocom.swing.MigLayout;


public class canvas 
{
//	static Image buttonIcon;
//	static BufferedImage myImage;
//	
//	
//	public void loadBackground()
//	{
//		
//	}
//	
//	
//	public static void main(String[] args) 
//	{
//		
//		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//		JFrame mainFrame = new JFrame("World of Warcraft");
//		
//		int width =  (int) screenSize.getWidth();
//		int height = (int) screenSize.getHeight();
//		
//		System.out.println(width + "  " + height) ;
//		try {
//			mainFrame.setContentPane(new JPanel() {
//				BufferedImage image = (BufferedImage) ImageIO.read(new File("data/AzerothFull.jpg"));
//				public void paintComponent(Graphics g) {
//					super.paintComponent(g);
//					g.drawImage(image, 0, 0, 1920, 1080, this);
//				}
//			});
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		mainFrame.getContentPane().setLayout(new MigLayout());
//
//		mainFrame.setSize(width, height);
//
//		try {
//			buttonIcon = ImageIO.read(new File("data/icon1.png"));
//			buttonIcon =   buttonIcon.getScaledInstance(10, 8, Image.SCALE_FAST);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		JButton[] buttonArray = new JButton[200];
//
//		BufferedReader br;
//		try {
//			br = new BufferedReader(new FileReader("data/zone pixel coordinates.csv"));
//
//			String line;
//			String name;
//			int xLoc, yLoc;
//			int i = 0;
//			String loc ;
//			while ((line = br.readLine()) != null) 
//			{
//				String[] tokens = line.split(",");
//				name = tokens[0];
//				xLoc = Integer.parseInt(tokens[1]);
//				yLoc = Integer.parseInt(tokens[2]);
//				buttonArray[i] = new JButton(new ImageIcon(buttonIcon));
//				buttonArray[i].setName(name);
//				buttonArray[i].setBorder(null);
//				buttonArray[i].setToolTipText(name);
//				if (buttonArray[i] == null){
//					System.out.println("dsadsgfadf");
//				}
//
//				xLoc = xLoc * width / 9000;
//				yLoc = yLoc * height / 6710 ;
//
//				loc = "pos " + xLoc + "px " + yLoc +"px";
//
//				mainFrame.getContentPane().add(buttonArray[i], loc );
//
//
//
//				System.out.println(xLoc + "    " + yLoc);	
//
//				i++;
//
//			}
//			br.close();
//
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		mainFrame.setVisible(true);
//
//	}

	
	
	
}
