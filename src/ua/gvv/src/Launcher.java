package ua.gvv.src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Launcher {
	private static Thread mainThread;
	public static void main(String[] args) throws InterruptedException, IOException 
	{
		 
		/**BufferedReader reader = new BufferedReader(new InputStreamReader (System.in));
		String n="";
		String m="";
		double i;
		while(true)
		{
			n = reader.readLine();
			if (n.equals("ok"))
			{
				return;
			}
			i = Double.parseDouble(n);
			i= i/1000;
			m+=i+",";
				
		print(m);
		}
			*/
		boolean[] bool={true,true,false,true,false,false,false,false,false,false,false,true,false,true,false};
		//mainThread = new Thread(new MainFrame(),"MainFrame");
		//mainThread.start();
		//mainThread.join();
		StaticFunctionsConstants.countUp(50, 32, bool, "", 1);
			//print(Double.toString(StaticFunctionsConstants.Nv));
	}
	public static void print(String s)
	{
		System.out.println(s);
	}

}
