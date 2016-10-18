
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class MTAPI 
	{
	
	private static Logger log = Logger.getLogger(MainFrame.class.getName());
	
	/**Метод принимает число с плаваюшей точкой и количество знаков после запятой 
	 * округляет его и возврашает*/
	public static double okrug(double doub,int colZnakov){
		log.info("Округление до: "+colZnakov);
		
		double returnValue = doub;
		int lastNumber;
		char lastChar;
		String stringnValue="";
		int devision = 1;
		switch (colZnakov)
		{
		case 1: 
			returnValue = returnValue*10;
			log.info("Исполнилось 1 :"+returnValue);
			devision = 10;
			break;
		case 2:
			returnValue = returnValue*100;
			log.info("Исполнилось 2 :"+returnValue);
			devision =100;
			break;
		case 3:
			returnValue = returnValue*1000;
			log.info("Исполнилось 3 :"+returnValue);
			devision =1000;
			break;
		case 4:
			returnValue = returnValue*10000;
			log.info("Исполнилось 4 :"+returnValue);
			devision = 10000;
			break;
		default:
			break;
		}
		lastNumber = (int) returnValue;
		log.info("lastNumber = "+lastNumber);
		stringnValue =stringnValue + (int)((doub*devision)*10);
		log.info("stringnValue = "+stringnValue);
		lastChar = stringnValue.charAt(stringnValue.length()-1);
		log.info("lastChar = "+lastChar);
		
		if(Integer.valueOf(stringnValue.valueOf(lastChar))>=5)
		{
			lastNumber = lastNumber + 1;
			log.info("lastNumber = "+lastNumber);			
		}
		returnValue = (double)lastNumber/devision;
			
		
		return returnValue;
	}


	//Далее идут методы сериализации
	/**Метод записывает в файл с полученым именем name, полученый обьект ArrayList<Tasks>;*/
	public static void seriazOut(ArrayList<Tasks> list, String name){
		log.info("Сериализация в файл" + name);
		ArrayList<Tasks> table = list;
		String nameFile = name;
		File file2 = new File("Data//"+name);
					
		try(FileOutputStream fileWriter = new FileOutputStream("Data//"+name);
			ObjectOutputStream writer = new ObjectOutputStream(fileWriter);)
		{			
			writer.writeObject(table);			
		}
		catch(IOException ioEx)
		{
			JOptionPane.showMessageDialog(null,"Проблема с чтением  файла");
			ioEx.getStackTrace();
		}
		
		
	}
	/**Метод возвращяет ссылку на обьект ArrayList<Tasks> считаный с файла с именем name; */
	public static ArrayList<Tasks> seriazIn(String name){
		log.info("Чтение обьекта с файла " + name);
		ArrayList<Tasks> table = null;
		if(!new File("Data//"+name).exists())
		{
			JOptionPane.showMessageDialog(null, "Файл отсутствует.");
			return table;
		}
		try(FileInputStream file = new FileInputStream("Data//"+name);
				ObjectInputStream reader = new ObjectInputStream(file);)
		{			
			table = (ArrayList)reader.readObject();
			
		}catch(IOException ioEx){
			JOptionPane.showMessageDialog(null,"Проблема с чтением файла IOEx");
			System.out.println(ioEx.getStackTrace());
		}catch(ClassNotFoundException e){
			JOptionPane.showMessageDialog(null,"Проблема с чтением файла ClassNotFound");
			System.out.println(e.getStackTrace());
		}
		return table;
		
	}
	/**Метод создает новый файл с полученым именем name, записывает в него пустой обьект 
	 *ArrayList<Tasks> и возвращяет на него ссылку */
	public static ArrayList<Tasks> createNewTasksTabel(String name){
		log.info("Создание нового файла" + name);
		ArrayList<Tasks> list = new ArrayList<Tasks>();
		MTAPI.seriazOut(list, name);
		return list;
	}
	/**Метод принимает обьект Arraylist<Tasks> и id записи. Удаяет указаную запись
	 * и изменяет id всех записей по порядку*/
	public static void deleteTask(ArrayList<Tasks> list, int id){
		log.info("Удаление задачи с таблицы под номером " + id);
		list.remove(id-1);
		int idChanger = 1;
		for(Tasks task: list)
		{
			task.setId(idChanger);
			idChanger++;
		}
		
	}
	//создает количество файлов соответствено количеству записей
	//все ок работает
	public static void copyFile(int pieces, String name)  {
		int loop = pieces;
		
		File newFile = null;
		for(int i = 1; i<=loop;i++)
		{
			newFile = new File("Table//"+name+"_"+i+".html");
			if(new File("Table//"+name+".html").exists())
			{
				JOptionPane.showMessageDialog(null, "Файл уже существует.");		
				return;
			}
			else
			{
				try 
				{
					newFile.createNewFile();
				}
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		
		
			if(!new File("Table//Table.html").exists())
			{
				JOptionPane.showMessageDialog(null, "Файл отсутствует.");			
			}	    
			try(FileInputStream is = new FileInputStream("Table//Table.html");
	    		FileOutputStream os = new FileOutputStream(newFile);) 
		    {	        
	        	byte[] buffer = new byte[1024];
	        	int length;
	        	while ((length = is.read(buffer)) > 0) 
	        	{
	            	os.write(buffer, 0, length);
	        	}
	    	}
	    	catch(IOException ex)
	    	{
	      		JOptionPane.showMessageDialog(null,"Проблема с чтением файла IOEx");
	      		System.out.println(ex.getStackTrace());
	    	}
	    }
	}
	
	public static void createHTMLTable(ArrayList<Tasks> list,String name){
		
		name = name.substring(0,name.length()-4);
		copyFile(howManyFileCopy(list), name);
		Document doc;
		
			int i = 1;
			int j = 1;
			String idHTML;
			for(Tasks task: list){
		try 
		{
			doc = Jsoup.parse(new File("Table//"+name+"_"+j+".html"),"UTF-8");
			idHTML = "r"+i+"c1";
			Element td1 = doc.getElementById(idHTML);
			td1.text(String.valueOf(task.getId()));
			idHTML = "r"+i+"c2";
			Element td2 = doc.getElementById(idHTML);
			td2.text(task.getNomerOborud());
			idHTML = "r"+i+"c3";
			Element td3 = doc.getElementById(idHTML);
			td3.text(task.getOpisanie());
			idHTML = "r"+i+"c4";
			Element td4 = doc.getElementById(idHTML);
			td4.text(task.getVidRem());
			idHTML = "r"+i+"c5";
			Element td5 = doc.getElementById(idHTML);
			td5.text(task.getEdIzmer());
			idHTML = "r"+i+"c6";
			Element td6 = doc.getElementById(idHTML);
			td6.text(String.valueOf(task.getColichestvoStycov()));
			idHTML = "r"+i+"c7";
			Element td7 = doc.getElementById(idHTML);
			td7.text(String.valueOf(task.getNormNaEd()));
			idHTML = "r"+i+"c8";
			Element td8 = doc.getElementById(idHTML);
			td8.text(String.valueOf(task.getNormNaOb()));
			idHTML = "r"+i+"c9";
			Element td9 = doc.getElementById(idHTML);
			td9.text(String.valueOf(task.getNormNaOb()));
			
			try(Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Table//"+name+"_"+j+".html"),"UTF-8")))
			{out.write(doc.html());}catch(Exception ex){}
			
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}if(i%5==0){j++;i = 0; System.out.println(" И равно " + i + "И єтот пункт работает");}
		i++;
			}
		
		
		
		
		
		 		
	}
	//дополнительный метод для упрощения итак залупистого createHTMLTable
	private static int howManyFileCopy(ArrayList<Tasks> list){
		
		int piecesFile =1;
		if(list.size()%5 == 0)
		{
			piecesFile = (list.size()/5);
		}
		else
		{
			piecesFile = (list.size()/5)+1;
		}
		return piecesFile;
	}
}
