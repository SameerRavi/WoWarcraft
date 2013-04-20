
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class WoWDB 
{
	private Connection conn = null;
	private Statement query = null;
	//private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public WoWDB()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager
			          .getConnection("jdbc:mysql://localhost/WoW","root","mysql");
			
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public ArrayList<String> getTransits(String name)
	{
		try
		{	
			query = conn.createStatement();
			
			name = name.replace("'", "\'");
			String temp = "select distinct nextzone from WhoDetailsExt where zone = '" + name + "'";
			System.out.println(temp);
			resultSet = query.executeQuery("select distinct nextzone from WhoDetailsExt where zone = '" + name + "'");
			
			ArrayList<String> zones = new ArrayList<String>();
			
			while(resultSet.next())			
				zones.add(resultSet.getString(1));
			
			return zones;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}

}
