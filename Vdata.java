package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Vdata {
	public static final String DB_NAME = "system.sqlite";
	public static final String CONNECTION_STRING ="jdbc:sqlite:D:\\Softwares\\Databases\\"+ DB_NAME;
	public static final String TABLE_VIRUS = "qwerty";
	private Connection con;
	public boolean open()
	{
		try {
			con = DriverManager.getConnection(CONNECTION_STRING);
			return true;
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
			return false;
		}	
	}
	public void close()
	{
		try
		{
			if(con!= null)
			{
				con.close();
			}
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}
	public List<Virus> queryVirus()
	{
		StringBuilder s = new StringBuilder("SELECT * FROM ");
		s.append(TABLE_VIRUS);
		//s.append(" WHERE ");
		//s.append(" IOC_Detection_Count <>"+ 0);
		System.out.println(s.toString());
		try(Statement stat = con.createStatement();
			ResultSet result = stat.executeQuery(s.toString()))
		{
			List<Virus> virus = new ArrayList<>();
			while(result.next())
			{
				Virus vir = new Virus();
				vir.setV(result.getString("SystemName"));
				virus.add(vir);
			}
			return virus;
		}
		catch (SQLException e)
		{
			System.out.println(e.getMessage());
			return null;
		}
		
	}
	
}
