

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public class AddFrame  implements Runnable{
	
	
	private JFrame frame;
	private JPanel panel1;
	private Listener listener = new Listener();
	
	//Для аполнения талицы
	private ArrayList<Tasks> list;
	private String nameFile;
	private boolean [] cof = new boolean [15];
	private int colSloy;
	
	private JLabel labelBlok;
	private JLabel labelDiam;
	private JLabel labelStal;
	private JLabel labelColStyk;
	private JLabel labelVidRem;
	private JLabel labelTolsh;
	
	private JTextField textDiam;
	private JTextField textTolsh;
	private JTextArea areaOpisanie;
	private JTextArea areaBlok;

	
	private JRadioButton radioDiam;
	private JRadioButton radioMetru;
	private ButtonGroup bGrop1_1;
	private ButtonGroup bGrop2_1;
	private JRadioButton radioTekush;
	private JRadioButton radioKapital;
	
	private JComboBox comboStal;
	private SpinnerModel model1;
	private JSpinner spinnerColSt;
	
	private JButton buttonKoeff;
	private JButton buttonCancel;
	private JButton buttonOk;
	
	private static Logger log = Logger.getLogger(MainFrame.class.getName());
	
	//Выкидное меню стали
		private	String[] items = {
				    "Сталь 20",
				    "Нерж.",
				    "Сталь 20"+","+ " Нерж."
				    };
	
	

	
	
	public AddFrame(ArrayList<Tasks> list,String nameFile){
		super();
		this.list = list;
		this.nameFile =nameFile;

	}
	@Override
	public void run() {
		
		//Создание и оформление фрейма
		frame = new JFrame("Add Task");
		frame.setSize(400, 500);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BorderLayout());
		//ССоздание и добавление первой панели
		panel1 = new JPanel();
		panel1.setLayout(null);
		
		//Текстовые метки
		labelBlok = new JLabel("<html>Цех, номер блока,<br>инвентарный номер<br>обрудования</html>");
		labelBlok.setBounds(20, 20, 120, 45);
		labelDiam = new JLabel("Диаметр или метры погонные");
		labelDiam.setBounds(20, 115, 190, 25);
		labelStal = new JLabel("Сталь");
		labelStal.setBounds(220, 20, 120, 45);
		labelColStyk = new JLabel("Количество стыков");
		labelColStyk.setBounds(220, 115, 190, 25);
		labelVidRem = new JLabel("Вид ремонта");
		labelVidRem.setBounds(220, 170, 140, 20);
		labelTolsh = new JLabel("Толщина");
		labelTolsh.setBounds(20, 170, 120, 25);
		
		//Текстовые поля
		textDiam = new JTextField(5);
		textDiam.setBounds(20,140, 60, 20);
		textTolsh = new JTextField(5);
		textTolsh.setBounds(20, 200, 60, 20);
		
		//Радиобытоны диаметр или метры погонные
		bGrop1_1 = new ButtonGroup();
		radioDiam = new JRadioButton("Диаметр",true);
		radioDiam.setBounds(80, 140, 80, 20);
		radioDiam.addActionListener(listener);
		radioMetru = new JRadioButton("м.п.");
		radioMetru.setBounds(160, 140, 60, 20);
		radioMetru.addActionListener(listener);
		bGrop1_1.add(radioDiam);
		bGrop1_1.add(radioMetru);
		
		//Радиоатоны вида ремонта
		bGrop2_1 = new ButtonGroup();
		radioTekush = new JRadioButton("T",true);
		radioTekush.setBounds(220, 190, 40, 20);
		radioKapital = new JRadioButton("K");
		radioKapital.setBounds(260, 190, 40, 20);
		bGrop2_1.add(radioTekush);
		bGrop2_1.add(radioKapital);
		
		
		comboStal = new JComboBox(items);
		comboStal.setBounds(220,75, 140, 20);
		
		//Спинер для количества стыков
		model1 = new SpinnerNumberModel();
	    spinnerColSt = new JSpinner(model1);
	    spinnerColSt.setBounds(220,140, 40, 20);
	    spinnerColSt.setValue(0);
	    
		//Поле с текстом для описание работ
		areaOpisanie = new JTextArea();
		areaOpisanie.setBounds(20, 270, 360, 140);
		areaOpisanie.setWrapStyleWord(true);
		areaOpisanie.setLineWrap(true);
		areaBlok = new JTextArea();
		areaBlok.setBounds(20,75, 140, 40);
		areaBlok.setWrapStyleWord(true);
		areaBlok.setLineWrap(true);
		
		//3 Кнопки "ок","Особености работы","Отмена"
		buttonKoeff = new JButton("Особености работы");
		buttonKoeff.setBounds(100, 230, 200, 30);
		buttonKoeff.addActionListener(listener);
		buttonCancel = new JButton("Отмена");
		buttonCancel.setBounds(300, 420, 80, 30);
		buttonCancel.addActionListener(listener);
		buttonOk = new JButton("Ок");
		buttonOk.setBounds(20, 420, 50, 30);
		buttonOk.addActionListener(listener);
		
		//Добавление элементов в панель
		panel1.add(labelBlok);
		panel1.add(areaBlok);
		panel1.add(labelDiam);
		panel1.add(labelTolsh);
		panel1.add(textDiam);
		panel1.add(textTolsh);
		panel1.add(radioDiam);
		panel1.add(radioMetru);
		panel1.add(areaOpisanie);
		panel1.add(labelStal);
		panel1.add(comboStal);
		panel1.add(labelColStyk);
		panel1.add(spinnerColSt);
		panel1.add(labelVidRem);
		panel1.add(radioTekush);
		panel1.add(radioKapital);		
		panel1.add(buttonKoeff);
		panel1.add(buttonCancel);
		panel1.add(buttonOk);
		
		//Добавление панели во фрейм
		frame.add(panel1,BorderLayout.CENTER);
		frame.setVisible(true);
		
		
	}
	private void createCofFrame(){
		final JFrame extraFrame = new JFrame("Особые настройки");
		JPanel panel2 = new JPanel();
		panel2.setLayout(null);
		
		final JCheckBox checkSpes = new JCheckBox("Кспец");
		checkSpes.setBounds(10, 170, 70, 20);
		final JCheckBox check1 = new JCheckBox("K1");
		check1.setBounds(10, 10, 40, 20);
		final JCheckBox check2 = new JCheckBox("K2");
		check2.setBounds(50, 10, 40, 20);
		final JCheckBox check3 = new JCheckBox("K3");
		check3.setBounds(100, 10, 40, 20);
		final JCheckBox check4= new JCheckBox("K4");
		check4.setBounds(140, 10, 40, 20);
		final JCheckBox check5 = new JCheckBox("K5");
		check5.setBounds(10, 50, 40, 20);
		final JCheckBox check6 = new JCheckBox("K6");
		check6.setBounds(50, 50, 40, 20);
		final JCheckBox check7 = new JCheckBox("K7");
		check7.setBounds(100, 50, 40, 20);
		final JCheckBox check8 = new JCheckBox("K8");
		check8.setBounds(140, 50, 40, 20);
		final JCheckBox check9 = new JCheckBox("K9");
		check9.setBounds(10, 90, 40, 20);
		final JCheckBox check10 = new JCheckBox("K10");
		check10.setBounds(50, 90, 50, 20);
		final JCheckBox check11 = new JCheckBox("T2");
		check11.setBounds(10, 130, 40, 20);
		final JCheckBox check12 = new JCheckBox("T3");
		check12.setBounds(50, 130, 40, 20);
		final JCheckBox check13 = new JCheckBox("T4");
		check13.setBounds(100, 130, 40, 20);
		final JCheckBox check14 = new JCheckBox("T5");
		check14.setBounds(140, 130, 40, 20);
		
		 SpinnerModel model = new SpinnerNumberModel();
	     final JSpinner spinner = new JSpinner(model);
	     JLabel labelSpin = new JLabel("слоев");
	     spinner.setBounds(100, 90, 35, 20);
	     spinner.setValue(0);
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
						if(event.getSource().equals(okButt))
						{
							for(int i = 0; i<cof.length;i++)cof[i]=false;
							colSloy = 0;
							if(checkSpes.isSelected())cof[0]=true;
							if(check1.isSelected())cof[1] =true;
							if(check2.isSelected())cof[2] =true;
							if(check3.isSelected())cof[3] =true;
							if(check4.isSelected())cof[4] =true;
							if(check5.isSelected())cof[5] =true;
							if(check6.isSelected())cof[6] =true;
							if(check7.isSelected())cof[7] =true;
							if(check8.isSelected())cof[8] =true;
							if(check9.isSelected())cof[9] =true;
							if(check10.isSelected()){cof[10] =true;}
							if(check11.isSelected())cof[11] =true;
							if(check12.isSelected())cof[12] =true;
							if(check13.isSelected())cof[13] =true;
							if(check14.isSelected())cof[14] =true;
							colSloy = (int)spinner.getValue();
							extraFrame.dispose();
						
						}
						
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
	private int integ(String s){
		
		int integ = Integer.parseInt(s);
		return integ;
	}
	class Listener implements ActionListener{

		
		public void actionPerformed(ActionEvent event) {
			
			
			//Прослушка главного фрейма
			if (event.getSource().equals(radioDiam))
			{
				if(radioDiam.isSelected()) 
				{
					textTolsh.setEditable(true);
					spinnerColSt.setEnabled(true);
				}
			}
			if (event.getSource().equals(radioMetru))
			{
				if(radioMetru.isSelected())
				{
					textTolsh.setEditable(false);
					textTolsh.setText("1");
					spinnerColSt.setEnabled(false);
				}
			}
			if (event.getSource().equals(buttonKoeff))
			{
				createCofFrame();
			}
			
			if(event.getSource().equals(buttonCancel))
			{
				frame.dispose();
			}
			if(event.getSource().equals(buttonOk))
			{
				//В полученый массив задач добавляем новую задачу с данными получиными от пользователя
				String nomrOborud = areaBlok.getText();
				String opisanie = areaOpisanie.getText();
				String stal =items[comboStal.getSelectedIndex()];
				String vidRem = "";
				double tolsh=Double.valueOf(textTolsh.getText());
				if(radioTekush.isSelected())vidRem="Т";
				if(radioKapital.isSelected())vidRem="К";
				
				String edIzmer = "";
				if(radioDiam.isSelected())edIzmer ="Диаметр";
				if(radioMetru.isSelected())edIzmer ="Метры";
				
				int col = (int) spinnerColSt.getValue();
				double diam = Double.valueOf(textDiam.getText());					
				boolean [] param = cof;
				
								
				list.add(new Tasks(nomrOborud, opisanie, vidRem,stal , edIzmer, col, diam, tolsh, param, list));
				MTAPI.seriazOut(list, nameFile);
				frame.dispose();
			}		
		}			
	}	
}
