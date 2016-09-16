package ua.gvv.src;

import java.awt.ScrollPane;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;

public class MainFrame  implements Runnable{

	private JFrame frame;
	private JPanel panel;
	private JButton button;
	private JButton retry;
	private JTable table;
	private Thread addThread;
	private ScrollPane  scroll;
	
	
	public MainFrame(){
		
		frame = new JFrame("Make Tasks");
		panel = new JPanel();
		ImageIcon icon = new ImageIcon("Images//greenPlus.png");
		button = new JButton("Добавить");
		button.setIcon(icon);
		retry = new JButton("Обновить");
		 String[] columnNames = {
                 "Name",
                 "Last modified",
                 "Type",
                 "Size"
       };
		 String[][] data ={{"","","",""}};
		table = new JTable(data,columnNames);
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
		panel.add(scroll);
		scroll.setBounds(20, 80, 760, 370);
		button.setBounds(20, 20,120, 40);
		frame.setVisible(true);
		
		
	}


	@Override
	public void run() {
		System.out.println("run run");
	
	
		

		
	}
}

 