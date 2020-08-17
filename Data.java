package data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Data
{
			public static final String DB_NAME = "amcore.sqlite";
			public static final String CONNECTION_STRING ="jdbc:sqlite:D:\\Softwares\\Databases\\"+ DB_NAME;
			public static final String TABLE_RECORD = "qwerty";
			private Connection conn;
			public boolean open()
			{
				try {
					conn = DriverManager.getConnection(CONNECTION_STRING);
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
					if(conn!= null)
					{
						conn.close();
					}
				}
				catch(SQLException e)
				{
					System.out.println(e.getMessage());
				}
			}
			public List<Record> queryRecords(String b, String c)
			{
				StringBuilder sb = new StringBuilder("SELECT * FROM ");
				sb.append(TABLE_RECORD);
				sb.append(" WHERE ");
				sb.append(c);
				sb.append(" LIKE \"");
				String a= "%"+b;
				sb.append(a);
				sb.append("%\"");
				System.out.println(sb.toString());
				try(Statement statement = conn.createStatement();
					ResultSet results = statement.executeQuery(sb.toString()))
				{
					List<Record> records = new ArrayList<>();
					while(results.next())
					{
						Record record = new Record();
						
						record.setNo(results.getString("SystemName"));
						record.setET(results.getString("Last Communication"));
						record.setU(results.getString("Managed State"));
						String j = results.getString("DAT Version (VirusScan Enterprise)");
						/*String h="";
						int k= j.length();
						int i;
						for(i=0;i<k;i++)
						{
							if(j.charAt(i)== '"')
							{
								h=h+'"';
								}
							h=h+j.charAt(i);
						}*/
						record.setURL(j);
						record.setPA(results.getString("AMCore Content Version"));
						record.setCAC(results.getString("Assignment Path"));
						record.setCA(results.getString("Product Version (DLP Endpoint)"));
						record.setURLC(results.getString("Product Version (Endpoint Security Platform)"));
						record.setURLSC(results.getString("Product Version (Endpoint Security Threat Prevention)"));
						record.setURLCa(results.getString("Product Version (VirusScan Enterprise)"));
						record.setTSC(results.getString("CPU Serial Number"));
						record.setTC(results.getString("CPU Type"));
						record.setMD5(results.getString("Free Disk Space"));
						record.setTN(results.getString("Free Memory"));
						record.setSC(results.getString("Free System Drive Space"));
						record.setL(results.getString("Is Laptop"));
						record.setD(results.getString("IP address"));
						record.setCI(results.getString("OS Build Number"));
						record.setCEI(results.getString("OS Platform"));
						record.setURLCM(results.getString("OS Type"));
						record.setFN(results.getString("OS Version"));
						record.setFN(results.getString("OS Service Pack Version"));
						record.setPT(results.getString("System Description"));
						record.setPTy(results.getString("SystemName2"));
						record.setCSR(results.getString("Total Disk Space"));
						record.setCCV(results.getString("Total Physical Memory"));
						record.setSSR(results.getString("Total System Drive Space"));
						record.setSCVT(results.getString("User Name"));
						record.setSCVP(results.getString("Is 64-bit OS"));
						record.setSWC(results.getString("MAC Address"));
						
						records.add(record);
					}
					return records;
				}
				catch (SQLException e)
				{
					System.out.println(e.getMessage());
					return null;
				}
				
			}
			
			}
