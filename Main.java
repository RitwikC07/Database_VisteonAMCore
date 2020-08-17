package data;

import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter; 
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException 
	{
		Data d = new Data();
		Vdata v = new Vdata();
		if(!d.open())
		{
			System.out.println("Can't open Data");
			return;
		}
		if(!v.open())
		{
			System.out.println("Can't open Data");
			return;
		}
		int i,L;
		FileWriter fn=new FileWriter("AMCore.csv");
		
		fn.write("System Name"+","+ "Last Communication"+ ","+ "Managed State"+ ","+
		"DAT Version (VirusScan Enterprise)"+ ","+"AMCore Content Version"+ ","+"Assignment Path"+ ","+
				"Product Version (DLP Endpoint)"+ ","+ " Product Version (Endpoint Security Platform)" +","+
		"Product Version (Endpoint Security Threat Prevention)"+ ","+"Product Version (VirusScan Enterprise)"+ ","+
				"CPU Serial Number"+ ","+"CPU Type"+ ","+"Free Disk Space"+ ","+"Free Memory"+ ","+
		"Free System Drive Space"+ ","+"Is Laptop"+ ","+"IP address"+ ","+"OS Build Number"+ ","+"OS Platform"+ ","+
				"OS Type"+ ","+"OS Version"+ ","+"OS Service Pack Version"+ ","+"System Description"+ ","+
		"System Name"+ ","+"Total Disk Space"+ ","+"Total Physical Memory"+ ","+"Total System Drive Space"+ ","+
				"User Name"+ ","+"Is 64-bit OS"+ ","+"MAC Address"+ '\n');
		
		
		List<Virus> virus = v.queryVirus();
		if( virus== null)
		{
			System.out.println("No virus");
			return;
		}
		List<String> V = new ArrayList<>();
		for(Virus viru : virus)
		{
			V.add(viru.getV());
		}
		int j = V.size();
		int k;
		String q[]= new String[j];
		for(k=0;k<j;k++)
		{
			q[k]= V.get(k).replaceAll("\\r\\n|\\r|\\n", " ");
			q[k]=q[k].trim();
		}
		
		for(k=0;k<j;k++)
		{
			if(q[k].length()>1)
			{
		List<Record> rec = d.queryRecords(q[k], "SystemName");
		if( rec== null)
		{
			System.out.println("No records");
			return;
		}
		int l1= rec.size();
		
		if (l1>3)
		{
		for(Record recor : rec)
		{
			fn.write(recor.getNo()+ ","+ recor.getET()+ ","+recor.getU()+ ","+recor.getURL()+ ","+recor.getPA()+ ","+
		recor.getCAC()+ ","+recor.getCA()+ ","+recor.getURLC()+ ","+recor.getURLSC()+ ","+recor.getURLCa()+ ","+
					recor.getTSC()+ ","+recor.getTC()+ ","+recor.getMD5()+ ","+"\""+recor.getTN() + "\""+ ","+recor.getSC()+ ","+
		recor.getL()+ ","+recor.getD()+ ","+recor.getCI()+ ","+recor.getCEI()+ ","+recor.getURLCM()+ ","+
					recor.getFT()+ ","+recor.getFN()+ ","+recor.getPT()+ ","+recor.getPTy()+ ","+recor.getCSR()+ ","+
		"\""+recor.getCCV()+"\""+ ","+recor.getSSR()+ ","+"\""+recor.getSCVT()+"\""+ ","+recor.getSCVP()+ ","+recor.getSWC()+ ","+ '\n');
		}
		}
		}	
		}
        System.out.println("Finished");
		fn.close();
		d.close();
		v.close();
	}
}