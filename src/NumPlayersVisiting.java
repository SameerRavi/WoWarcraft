import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Day;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;




/**
 * A time series chart with all zero data.  When the data range is zero, you may want to modify
 * the default behaviour of the range axis.
 *
 */
public class NumPlayersVisiting extends ApplicationFrame {

    /**
     * Creates a new instance.
     *
     * @param title  the frame title.
     */
    public NumPlayersVisiting(String title, XYDataset dataset, int height, int width) {
        super(title);
        JFreeChart chart = createChart(dataset,title);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(width,height));
        setContentPane(chartPanel);
        XYSeries s = new XYSeries("test");
    }

    /**
     * Creates a chart.
     * 
     * @param dataset  the dataset.
     * 
     * @return a chart.
     */
    private JFreeChart createChart(XYDataset dataset,String title) {
    	
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
            title, 
            "Date", 
            "Value",
            dataset, 
            true, 
            true, 
            false
        );

        XYPlot plot = chart.getXYPlot();
        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("MMM-dd-yyyy"));
        ValueAxis rangeAxis = plot.getRangeAxis();
        rangeAxis.setAutoRangeMinimumSize(1.0);    
        return chart;   
    
    }   
    
    // ****************************************************************************
    // * JFREECHART DEVELOPER GUIDE                                               *
    // * The JFreeChart Developer Guide, written by David Gilbert, is available   *
    // * to purchase from Object Refinery Limited:                                *
    // *                                                                          *
    // * http://www.object-refinery.com/jfreechart/guide.html                     *
    // *                                                                          *
    // * Sales are used to provide funding for the JFreeChart project - please    * 
    // * support us so that we can continue developing free software.             *
    // ****************************************************************************
    
    /**
     * Creates a dataset, consisting of two series of monthly data.
     *
     * @return the dataset.
     */
    @SuppressWarnings("deprecation")
	public static XYDataset createDataset(ArrayList<ArrayList<String>> csv_data, String series_name)
    {
    	TimeSeries s1=new TimeSeries(series_name,Day.class);
    	for (ArrayList<String> row : csv_data) {
			Day date=dateParser(row);
			s1.add(date,Integer.parseInt(row.get(2)));
		}

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(s1);
        return dataset;
    }
    
    public static ArrayList<ArrayList<String>> csvParser(String csv_input, int header_flag)
    {
    	ArrayList<ArrayList<String>> result=new ArrayList<ArrayList<String>>();
    	
    	File file = new File(csv_input);
    	 
    	BufferedReader bufRdr=null;
		try {
			bufRdr = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	String line = null;
    	 
    	//read each line of text file
    	try {
    		if(header_flag==1)
    			line=bufRdr.readLine();
    		
			while((line = bufRdr.readLine()) != null)
			{
				ArrayList<String> one_row=new ArrayList<String>();
				StringTokenizer st = new StringTokenizer(line,",");
				while (st.hasMoreTokens())
				{
					//get next token and store it in the array
					one_row.add(st.nextToken());
				}
				result.add(one_row);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			try {
				bufRdr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    	 
		return result;
    }
    
    public static Day dateParser(ArrayList<String> row_input)
    {
    	String time=row_input.get(0);
    	String[] time_tokens =time.split("-");
    	String parsed_time=time_tokens[0]+"-"+time_tokens[1]+"-20"+time_tokens[2];
    	
    	SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
    	Date tmpdate=null;
    	Day date=null;
		try {
			tmpdate = formatter.parse(parsed_time);
			date=new Day(tmpdate);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return date;
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
    	String csv_input="D:/unique players in orgrimmar by day.csv";
    	File csv_file=new File(csv_input);
    	String title=csv_file.getName().replace(".csv","");
    	int height=500;
    	int width=500;
    	
    	ArrayList<ArrayList<String>> csv_data=csvParser(csv_input,1);
    	
    	XYDataset dataset =createDataset(csv_data, title);
    	
    	NumPlayersVisiting demo = new NumPlayersVisiting(title, dataset, height, width);
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    	
    }

}



