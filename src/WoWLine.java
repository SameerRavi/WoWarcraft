
public class WoWLine 
{
	private int startx;
	private int starty;
	private int endx;
	private int endy;
	
	public WoWLine(int x1,int y1,int x2,int y2)
	{
		startx = x1;starty=y1;
		endx = x2;endy=y2;
	}
	
	
	public int getStartx() {
		return startx;
	}
	public int getStarty() {
		return starty;
	}
	public int getEndx() {
		return endx;
	}
	public int getEndy() {
		return endy;
	}

}
