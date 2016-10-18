import java.awt.Color;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MainFrame  implements Runnable{

	private JFrame frame;
	private JPanel panel;
	private JButton button;
	private JButton retry;
	private JTextArea table;
	private Thread addThread;
	private ScrollPane  scroll;
	private Listener listener;
	private JFileChooser chooser;
	private JMenuBar menuBar;
	private JMenu menuFile;
	private JMenu menuSetting;
	private JMenu menuHelp;
	private JMenuItem itemFileClose;
	private JMenuItem itemFileOpen;
	private JMenuItem itemFileCreate;
	private JMenuItem itemFileSave;
	private String nameFile;
	private ArrayList<Tasks> list;
	private File path;
	private String[][] data;
	private JButton deleteButton;
	private double vsegoChasov = 0;
	private JLabel vsegoChasovLabel;
	private JLabel ostalosLabel;
	private double ostalosChasov = 0;
	private JMenuItem itemSettingChasov;
	private JMenuItem itemSettingIz;
	private JMenuItem itemFileHTML;
	private double dou;
	private static Logger log = Logger.getLogger(MainFrame.class.getName());
		
	
	public MainFrame(){
		list = new ArrayList<Tasks>();
		path = new File("Data//");
		
		frame = new JFrame("Make Tasks");
		panel = new JPanel();
		listener = new Listener();
		ImageIcon icon = new ImageIcon("Images//greenPlus.png");
		button = new JButton("Добавить");
		button.setIcon(icon);
		retry = new JButton("Обновить");
		table = new JTextArea();
		table.setWrapStyleWord(true);
		table.setLineWrap(true);
		table.setEnabled(true);
		table.setFont(new Font("Times New Roman", Font.PLAIN, 15));

		scroll = new ScrollPane();
		scroll.add(table);
		frame.setSize(800, 500);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		panel.setLayout(null);
		panel.add(button);
		panel.add(retry);
		retry.setBounds(160, 20, 100,40 );
		retry.addActionListener(listener);
		deleteButton = new JButton("Удалить");
		deleteButton.setBounds(280, 20, 100, 40); 
		deleteButton.addActionListener(listener);
		vsegoChasovLabel = new JLabel("Всего: ");
		vsegoChasovLabel.setFont(new Font("Times New Roman",Font.BOLD,20));
		vsegoChasovLabel.setBounds(400, 5, 200, 30);
		ostalosLabel = new JLabel("Осталось набрать: ");
		ostalosLabel.setFont(new Font("Times New Roman",Font.BOLD,20));
		ostalosLabel.setBounds(400, 35, 300, 30);
		ostalosLabel.setForeground(Color.BLUE);
		panel.add(deleteButton);
		panel.add(ostalosLabel);
		panel.add(scroll);
		panel.add(vsegoChasovLabel);
		scroll.setBounds(20, 80, 760, 360);
		button.setBounds(20, 20,120, 40);
		
		menuBar = new JMenuBar();
		menuFile = new JMenu("Файл");
		menuSetting = new JMenu("Настройка");
		menuHelp = new JMenu("Помощь");
		itemFileOpen = new JMenuItem("Загрузить таблицу");
		itemFileOpen.addActionListener(listener);
		itemFileSave = new JMenuItem("Сохранить");
		itemFileSave.addActionListener(listener);
		itemFileCreate = new JMenuItem("Создать");
		itemFileCreate.addActionListener(listener);
		itemFileClose = new JMenuItem("Эакрыть");
		itemFileClose.addActionListener(listener);
		itemFileHTML = new JMenuItem("Сгенерировать таблицу");
		itemFileHTML.addActionListener(listener);
		itemSettingChasov = new JMenuItem("Количество часов");
		itemSettingChasov.addActionListener(listener);
		itemSettingIz = new JMenuItem("Изменить по №");
		itemSettingIz.addActionListener(listener);
		menuSetting.add(itemSettingIz);
		menuSetting.addSeparator();
		menuSetting.add(itemSettingChasov);
		
		menuFile.add(itemFileCreate);
		menuFile.addSeparator();
		menuFile.add(itemFileOpen);
		menuFile.add(itemFileSave);
		menuFile.addSeparator();
		menuFile.add(itemFileClose);
		menuFile.add(itemFileHTML);
		menuBar.add(menuFile);
		menuBar.add(menuSetting);
		menuBar.add(menuHelp);
		frame.setJMenuBar(menuBar);
		
		
		frame.setVisible(true);
		
		
	}


	@Override
	public void run() {
		System.out.println("run run");
	
		button.addActionListener(listener);
	
		

		
	}
	class Listener implements ActionListener {

		
		public void actionPerformed(ActionEvent event) {
			
			if(event.getSource().equals(itemFileOpen))
			{
				chooserOpen();
				repaintTable();
				
				
			}
			if(event.getSource().equals(button))
			{
				if(list != null && nameFile != null)
				{
					addThread = new Thread(new AddFrame(list,nameFile));
					addThread.start();
					try {
						addThread.join();
						
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} 
				else
				{
					JOptionPane.showMessageDialog(null, "Загрузите таблицу или создайте новую");
				}
				
			}

			if(event.getSource().equals(itemFileCreate))
			{
				chooserCreate();
				
				
			}
			if(event.getSource().equals(itemFileClose))
			{
				System.exit(0);
			}
			if(event.getSource().equals(retry))
			{
				if(list != null && nameFile != null)
				{
					log.info("перерисовка");
					repaintTable();
										
				} 
				else
				{
					JOptionPane.showMessageDialog(null, "Загрузите таблицу или создайте новую");
				}
							
			}
			if(event.getSource().equals(deleteButton))
			{
				log.info("Нажатие на удалить");
				if(list != null && nameFile != null)
				{
					int idDeleteTask = Integer.valueOf(JOptionPane.showInputDialog(null,"Введите номер удаляемой задачи"));
					MTAPI.deleteTask(list, idDeleteTask);
					MTAPI.seriazOut(list, nameFile);
				}else
				{
					JOptionPane.showMessageDialog(null, "Загрузите таблицу или создайте новую");
				}
			}
			if(event.getSource().equals(itemSettingChasov))
			{
				ostalosChasov =Double.valueOf(JOptionPane.showInputDialog(null,"Введите часы"));
				ostalosLabel.setText("Осталось набрать: "+ostalosChasov);
			}
			if(event.getSource().equals(itemSettingIz))
			{
				
				if(list != null && nameFile != null)
				{
					int id =Integer.valueOf(JOptionPane.showInputDialog(null,"Введите номер задачи"));
					id = id-1;
					addThread = new Thread(new ChangeFrame(list,nameFile,id));
					addThread.start();
					try {
						addThread.join();
						
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} 
				else
				{
					JOptionPane.showMessageDialog(null, "Загрузите таблицу или создайте новую");
				}
				
				
				
			}
			if(event.getSource().equals(itemFileSave))
			{
				
				if(list != null && nameFile != null)
				{
					MTAPI.seriazOut(list, nameFile);
					JOptionPane.showMessageDialog(null, "Сохранено");
				} 
				else
				{
					JOptionPane.showMessageDialog(null, "Загрузите таблицу или создайте новую");
				}
			}
			if(event.getSource().equals(itemFileHTML))
			{
				if(list != null && nameFile != null)
				{
					MTAPI.createHTMLTable(list, nameFile);
				} 
				else
				{
					JOptionPane.showMessageDialog(null, "Загрузите таблицу или создайте новую");
				}
			}
			
			
		}
		
	}
	private void chooserOpen(){
		File file ;
		log.info("");
		chooser = new JFileChooser();
		chooser.setCurrentDirectory(path);
		int i = chooser.showOpenDialog(null);
		if (i == JFileChooser.APPROVE_OPTION)
		{
			log.info("Все ок");
			file = chooser.getSelectedFile();
			nameFile = file.getName();
			log.info("Был закружен " + nameFile);
			list = MTAPI.seriazIn(nameFile);
		}
		
		
		
	}

	private void chooserCreate(){
		File file ;
		log.info("");
		list = new ArrayList<Tasks>();
		nameFile = JOptionPane.showInputDialog(null,"Введите имя файла");
		file = new File("Data//"+nameFile+".ser");
		nameFile = file.getName();
		try {
			if(file.createNewFile())
			{
				JOptionPane.showMessageDialog(null, "Файл создан");
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Файл существует");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MTAPI.seriazOut(list, nameFile);
		
		
	}
	private void repaintTable(){
		table.setText("");
		vsegoChasov = 0;
		dou=ostalosChasov;
		String add ="";
		for(Tasks task : list)
		{
			if(task.getEdIzmer().equals("Диаметр"))
			{
				add = task.getId()+"\n"+task.getNomerOborud()+"|"+ task.getOpisanie()+" | " + "\n" + 
				"Вид ремонта: " + task.getVidRem()+ " | " + "Единица измерения: " + task.getEdIzmer()+" | " +"Диаметр "+ (int)task.getDiametr()+
				" | " +"Количество: " + (int)task.getColichestvoStycov() +" | " +"Норма на единицу: " + MTAPI.okrug(task.getNormNaEd()*task.getCoffK(),2)+" | " +"\n" + 
				"Норма на факт: " + task.getFactNaOb()+"\n" ;
			}
			if(task.getEdIzmer().equals("Метры"))
			{
				add = task.getId()+"\n"+task.getNomerOborud()+"|"+ task.getOpisanie()+" | " + "\n" + 
				"Вид ремонта: " + task.getVidRem()+ " | " + "Единица измерения: " + task.getEdIzmer()+" | " + task.getDiametr()
				+" | " +"Норма на единицу: " + MTAPI.okrug(task.getNormNaEd()*task.getCoffK(),2)+" | " +"\n" + 
				"Норма на факт: " + task.getFactNaOb()+"\n" ;
			}
			
			String razd="";
			for(int i = 0; i<140;i++){
				razd+="-";
			}
			razd+="\n";
			table.append(add);
			table.append(razd);
			vsegoChasov+=task.getFactNaOb();
			dou=MTAPI.okrug(dou-task.getFactNaOb(),3);
			
		}
		vsegoChasovLabel.setText("Всего: "+vsegoChasov);
		ostalosLabel.setText("Осталось набрать: "+dou);
		
	}
}

 