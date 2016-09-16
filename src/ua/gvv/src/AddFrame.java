package ua.gvv.src;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public class AddFrame  implements Runnable{

	private JFrame frame;
	//panel1
	private JPanel panel1;
	private JLabel label1_1;
	private JTextField text1_1;
	private JLabel label1_2;
	private JTextField text1_2;
	private JRadioButton radio1_1;
	private JRadioButton radio1_2;
	private ButtonGroup bGrop1_1;
	//panel2
	private JLabel label2_1;
	private JComboBox comboBox;
	private JLabel label2_2;
	private SpinnerModel model1;
	private JSpinner spinnerColSt;
	private JLabel label2_3;
	private ButtonGroup bGrop2_1;
	private JRadioButton radio2_1;
	private JRadioButton radio2_2;
	
	private JButton button1;
	
	private JTextArea area;
	private JButton button2;
	private JButton button3;
	
	
	@Override
	public void run() {
		
		//Создание и оформление фрейма
		frame = new JFrame("Add Task");
		frame.setSize(400, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BorderLayout());
		//ССоздание и добавление первой панели
		panel1 = new JPanel();
		panel1.setLayout(null);
		label1_1 = new JLabel("<html>Цех, номер блока,<br>инвентарный номер<br>обрудования</html>");
		label1_1.setBounds(20, 20, 120, 45);
		text1_1 = new JTextField(20);
		text1_1.setBounds(20,75, 140, 20);
		label1_2 = new JLabel("Диаметр или метры погонные");
		label1_2.setBounds(20, 115, 190, 25);
		text1_2 = new JTextField(10);
		text1_2.setBounds(20,140, 140, 20);
		bGrop1_1 = new ButtonGroup();
		radio1_1 = new JRadioButton("Диаметр",true);
		radio1_1.setBounds(20, 170, 80, 20);
		radio1_2 = new JRadioButton("м.п.");
		radio1_2.setBounds(110, 170, 60, 20);
		bGrop1_1.add(radio1_1);
		bGrop1_1.add(radio1_2);
		
		panel1.add(label1_1);
		panel1.add(text1_1);
		panel1.add(label1_2);
		panel1.add(text1_2);
		panel1.add(radio1_1);
		panel1.add(radio1_2);
		
		label2_1 = new JLabel("Сталь");
		label2_1.setBounds(220, 20, 120, 45);
		String[] items = {
			    "Элемент списка 1",
			    "Элемент списка 2",
			    "Элемент списка 3"
			};
		comboBox = new JComboBox(items);
		comboBox.setBounds(220,75, 140, 20);
		label2_2 = new JLabel("Количество стыков");
		label2_2.setBounds(220, 115, 190, 25);
		model1 = new SpinnerNumberModel();
	    spinnerColSt = new JSpinner(model1);
	    spinnerColSt.setBounds(220,140, 40, 20);
		label2_3 = new JLabel("Вид ремонта");
		label2_3.setBounds(220, 170, 140, 20);
		bGrop2_1 = new ButtonGroup();
		radio2_1 = new JRadioButton("T",true);
		radio2_1.setBounds(220, 190, 40, 20);
		radio2_2 = new JRadioButton("K");
		radio2_2.setBounds(260, 190, 40, 20);
		bGrop2_1.add(radio2_1);
		bGrop2_1.add(radio2_2);
		
		panel1.add(label2_1);
		panel1.add(comboBox);
		panel1.add(label2_2);
		panel1.add(spinnerColSt);
		panel1.add(label2_3);
		panel1.add(radio2_1);
		panel1.add(radio2_2);
		
		button1 = new JButton("Особености работы");
		button1.setBounds(100, 230, 200, 30);
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {if (event.getSource().equals(button1))createCofFrame();
				
			}});
		
		area = new JTextArea();
		area.setBounds(20, 270, 360, 140);
		area.setWrapStyleWord(true);
		area.setLineWrap(true);
		panel1.add(area);
		
		button2 = new JButton("Отмена");
		button2.setBounds(300, 420, 80, 30);
		button2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent event) {
						if(event.getSource().equals(button2))frame.dispose();
			}
		});
		button3 = new JButton("Ок");
		button3.setBounds(20, 420, 50, 30);
		button3.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent event) {
				if(event.getSource().equals(button3))frame.dispose();
			}
		});
		
		
		panel1.add(button1);
		panel1.add(button2);
		panel1.add(button3);
		
		frame.add(panel1,BorderLayout.CENTER);
		frame.setVisible(true);
		
		
	}
	private void createCofFrame(){
		final JFrame extraFrame = new JFrame("Особые настройки");
		JPanel panel2 = new JPanel();
		panel2.setLayout(null);
		
		JCheckBox checkSpes = new JCheckBox("Кспец");
		checkSpes.setBounds(10, 170, 70, 20);
		JCheckBox check1 = new JCheckBox("K1");
		check1.setBounds(10, 10, 40, 20);
		JCheckBox check2 = new JCheckBox("K2");
		check2.setBounds(50, 10, 40, 20);
		JCheckBox check3 = new JCheckBox("K3");
		check3.setBounds(100, 10, 40, 20);
		JCheckBox check4= new JCheckBox("K4");
		check4.setBounds(140, 10, 40, 20);
		JCheckBox check5 = new JCheckBox("K5");
		check5.setBounds(10, 50, 40, 20);
		JCheckBox check6 = new JCheckBox("K6");
		check6.setBounds(50, 50, 40, 20);
		JCheckBox check7 = new JCheckBox("K7");
		check7.setBounds(100, 50, 40, 20);
		JCheckBox check8 = new JCheckBox("K8");
		check8.setBounds(140, 50, 40, 20);
		JCheckBox check9 = new JCheckBox("K9");
		check9.setBounds(10, 90, 40, 20);
		JCheckBox check10 = new JCheckBox("K10");
		check10.setBounds(50, 90, 50, 20);
		JCheckBox check11 = new JCheckBox("T2");
		check11.setBounds(10, 130, 40, 20);
		JCheckBox check12 = new JCheckBox("T3");
		check12.setBounds(50, 130, 40, 20);
		JCheckBox check13 = new JCheckBox("T4");
		check13.setBounds(100, 130, 40, 20);
		JCheckBox check14 = new JCheckBox("T5");
		check14.setBounds(140, 130, 40, 20);
		
		 SpinnerModel model = new SpinnerNumberModel();
	     JSpinner spinner = new JSpinner(model);
	     JLabel labelSpin = new JLabel("слоев");
	     spinner.setBounds(100, 90, 35, 20);
	     panel2.add(spinner);
	     labelSpin.setBounds(140, 90, 50, 20);
	     panel2.add(labelSpin);
	     
		
		checkSpes.setToolTipText("Работа в ЗСР (1.15)");
		check1.setToolTipText("Работа в положении лежа, на коленях, и др. усл. (1.15)");
		check2.setToolTipText("Рабата на высоте более 2 м, с люлек, вышек, лестниц (1.15)");
		check3.setToolTipText("Рабата с предохранительным поясом (1.5)");
		check4.setToolTipText("Контроль труб(деталей) из сталей аустенитных и др. классов (1.2)");
		check5.setToolTipText("ВИК корня шва (1.1)");
		check6.setToolTipText("Работы с темпер. воздуха на раб. месте более 40 град. (1.15)");
		check7.setToolTipText("Работа внутри емкости (3)");
		check8.setToolTipText("Конртоль сварного соединение с двух сторон (Нв п 3,4 умножить на 2 (таб. для труб))");
		check9.setToolTipText("Контроль сварного соединение с двух сторон (Нв п 3,4 умножить на 2 (таб. для листов и пластин))");
		check10.setToolTipText("Послойный контроль (Нв п 3 умножить на количество слоев)");
		check11.setToolTipText("Запись резулбтатов ВИК (+0.25 15 минут)");
		check12.setToolTipText("Переход от 50м до 200м (+0.25)");
		check13.setToolTipText("Переход от 200м до 500м (+0.4)");
		check14.setToolTipText("Переход от 500м  (+0.58)");
		
		panel2.add(check1);
		panel2.add(check2);
		panel2.add(check3);
		panel2.add(check4);
		panel2.add(check5);
		panel2.add(check6);
		panel2.add(check7);
		panel2.add(check8);
		panel2.add(check9);
		panel2.add(check10);
		panel2.add(check11);
		panel2.add(check12);
		panel2.add(check13);
		panel2.add(check14);
		panel2.add(checkSpes);
		
		
		final JButton okButt = new JButton("Ок");
		okButt.setBounds(20, 220, 50, 30);
		okButt.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent event) {
						if(event.getSource().equals(okButt))extraFrame.dispose();
			}
		});
		final JButton exButt = new JButton("Отмена");
		exButt.setBounds(90, 220, 80, 30);
		exButt.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent event) {
						if(event.getSource().equals(exButt))extraFrame.dispose();
			}
		});
		panel2.add(okButt);
		panel2.add(exButt);
		extraFrame.add(panel2);
		extraFrame.setSize(200, 300);
		extraFrame.setResizable(false);
		extraFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		extraFrame.setLocationRelativeTo(null);
		extraFrame.setVisible(true);
	}
	
	
	
}
