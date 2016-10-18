
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class Main {
	private static Thread mainThread;
	
	public static FileHandler handler;
	public static void main(String[] args) throws InterruptedException, IOException 
	{
		
		//new Test();
		//JOptionPane.showMessageDialog(null,"Проблема с файлом!");
		//BufferedReader reader = new BufferedReader(new InputStreamReader (System.in));
		//while(true)
		//{
		//	double number = Double.valueOf(reader.readLine());
		//System.out.println("Ваше число ="+StaticFunctionsConstants.okrug(number));
		//}
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
		//boolean[] bool={true,true,false,true,false,false,false,false,false,false,false,true,false,true,false};
		//ArrayList<Tasks> list =StaticFunctionsConstants.seriazIn("Моя таблица2.ser");
		//ArrayList<Tasks> list = StaticFunctionsConstants.createNewTasksTabel("Моя таблица2.ser");
		mainThread = new Thread(new MainFrame(),"MainFrame");
		mainThread.start();
		mainThread.join();
		
		
		/**for(Tasks s : list){
			
			System.out.println(s.getColichestvoStycov());
			System.out.println(s.getDiametr());
			System.out.println(s.getEdIzmer());
			System.out.println(s.getId());
			System.out.println(s.getOpisanie());
			System.out.println(s.getVidRem());
			for(boolean b : s.getParam()){
				System.out.print("Коеф = "+ b + " ");
			}
		}*/
//		}
		//StaticFunctionsConstants.countUp(50, 32, bool, "", 1);
			//print(Double.toString(StaticFunctionsConstants.Nv));
		//MTAPI.createHTMLTable();
		
		//MTAPI.copyFile(5, "September2016");
		//while(true)
		//System.out.println(MTAPI.okrug(Double.valueOf(new BufferedReader(new InputStreamReader(System.in)).readLine()), 4));	
	}
	public static void print(String s)
	{
		System.out.println(s);
	}

}
