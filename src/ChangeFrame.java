
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
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public class ChangeFrame  implements Runnable{
	
	
	private JFrame frame;
	private JPanel panel1;
	private Listener listener = new Listener();
	
	//��� ��������� ������
	private ArrayList<Tasks> list;
	private String nameFile;
	private boolean [] cof = new boolean [15];
	private int colSloy;
	
	private JLabel labelBlok;
	private JLabel labelDiam;
	private JLabel labelStal;
	private JLabel labelColStyk;
	private JLabel labelVidRem;
	
	private JTextField textDiam;
	private JTextField textBlok;
	private JTextArea areaOpisanie;
	
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
	
	private int idChangeTasks;
	private static Logger log = Logger.getLogger(MainFrame.class.getName());
	
	

	
	
	public ChangeFrame(ArrayList<Tasks> list,String nameFile,int id){
		super();
		this.list = list;
		this.nameFile =nameFile;
		idChangeTasks = id;
		
	}
	@Override
	public void run() {
		
		//�������� � ���������� ������
		frame = new JFrame("Add Task");
		frame.setSize(400, 500);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BorderLayout());
		//��������� � ���������� ������ ������
		panel1 = new JPanel();
		panel1.setLayout(null);
		
		//��������� �����
		labelBlok = new JLabel("<html>���, ����� �����,<br>����������� �����<br>�����������</html>");
		labelBlok.setBounds(20, 20, 120, 45);
		labelDiam = new JLabel("������� ��� ����� ��������");
		labelDiam.setBounds(20, 115, 190, 25);
		labelStal = new JLabel("�����");
		labelStal.setBounds(220, 20, 120, 45);
		labelColStyk = new JLabel("���������� ������");
		labelColStyk.setBounds(220, 115, 190, 25);
		labelVidRem = new JLabel("��� �������");
		labelVidRem.setBounds(220, 170, 140, 20);
		
		//��������� ����
		textBlok = new JTextField(20);
		textBlok.setBounds(20,75, 140, 20);
		textDiam = new JTextField(10);
		textDiam.setBounds(20,140, 140, 20);
		
		//����������� ������� ��� ����� ��������
		bGrop1_1 = new ButtonGroup();
		radioDiam = new JRadioButton("�������",true);
		radioDiam.setBounds(20, 170, 80, 20);
		radioMetru = new JRadioButton("�.�.");
		radioMetru.setBounds(110, 170, 60, 20);
		bGrop1_1.add(radioDiam);
		bGrop1_1.add(radioMetru);
		
		//���������� ���� �������
		bGrop2_1 = new ButtonGroup();
		radioTekush = new JRadioButton("T",true);
		radioTekush.setBounds(220, 190, 40, 20);
		radioKapital = new JRadioButton("K");
		radioKapital.setBounds(260, 190, 40, 20);
		bGrop2_1.add(radioTekush);
		bGrop2_1.add(radioKapital);
		
		//�������� ���� �����
		String[] items = {
			    "����� 20",
			    "����."
			    };
		comboStal = new JComboBox(items);
		comboStal.setBounds(220,75, 140, 20);
		
		//������ ��� ���������� ������
		model1 = new SpinnerNumberModel();
	    spinnerColSt = new JSpinner(model1);
	    spinnerColSt.setBounds(220,140, 40, 20);
	    spinnerColSt.setValue(0);
	    
		//���� � ������� ��� �������� �����
		areaOpisanie = new JTextArea();
		areaOpisanie.setBounds(20, 270, 360, 140);
		areaOpisanie.setWrapStyleWord(true);
		areaOpisanie.setLineWrap(true);
		
		//3 ������ "��","���������� ������","������"
		buttonKoeff = new JButton("���������� ������");
		buttonKoeff.setBounds(100, 230, 200, 30);
		buttonKoeff.addActionListener(listener);
		buttonCancel = new JButton("������");
		buttonCancel.setBounds(300, 420, 80, 30);
		buttonCancel.addActionListener(listener);
		buttonOk = new JButton("��");
		buttonOk.setBounds(20, 420, 50, 30);
		buttonOk.addActionListener(listener);
		
		//���������� ��������� � ������
		panel1.add(labelBlok);
		panel1.add(textBlok);
		panel1.add(labelDiam);
		panel1.add(textDiam);
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
		
		
		textBlok.setText(list.get(idChangeTasks).getNomerOborud());
		areaOpisanie.setText(list.get(idChangeTasks).getOpisanie());
		if(list.get(idChangeTasks).getVidRem().equals("T"))
		{
			radioTekush.setSelected(true);
		}
		if(list.get(idChangeTasks).getVidRem().equals("K"))
		{
			radioKapital.setSelected(true);
		}
		list.get(idChangeTasks).getEdIzmer();
		
		if(list.get(idChangeTasks).getEdIzmer().equals("�������"))
		{
			radioDiam.setSelected(true);
		}
		if(list.get(idChangeTasks).getEdIzmer().equals("�����"))
		{
			radioMetru.setSelected(true);
		}
		spinnerColSt.setValue((int)(list.get(idChangeTasks).getColichestvoStycov()));
		textDiam.setText(String.valueOf(list.get(idChangeTasks).getDiametr()));
		
		//���������� ������ �� �����
		frame.add(panel1,BorderLayout.CENTER);
		frame.setVisible(true);
		
		
	}
	private void createCofFrame(){
		final JFrame extraFrame = new JFrame("������ ���������");
		JPanel panel2 = new JPanel();
		panel2.setLayout(null);
		
		final JCheckBox checkSpes = new JCheckBox("�����");
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
	     JLabel labelSpin = new JLabel("�����");
	     spinner.setBounds(100, 90, 35, 20);
	     spinner.setValue(0);
	     panel2.add(spinner);
	     labelSpin.setBounds(140, 90, 50, 20);
	     panel2.add(labelSpin);
	     
		
		checkSpes.setToolTipText("������ � ��� (1.15)");
		check1.setToolTipText("������ � ��������� ����, �� �������, � ��. ���. (1.15)");
		check2.setToolTipText("������ �� ������ ����� 2 �, � �����, �����, ������� (1.15)");
		check3.setToolTipText("������ � ����������������� ������ (1.5)");
		check4.setToolTipText("�������� ����(�������) �� ������ ����������� � ��. ������� (1.2)");
		check5.setToolTipText("��� ����� ��� (1.1)");
		check6.setToolTipText("������ � ������. ������� �� ���. ����� ����� 40 ����. (1.15)");
		check7.setToolTipText("������ ������ ������� (3)");
		check8.setToolTipText("�������� �������� ���������� � ���� ������ (�� � 3,4 �������� �� 2 (���. ��� ����))");
		check9.setToolTipText("�������� �������� ���������� � ���� ������ (�� � 3,4 �������� �� 2 (���. ��� ������ � �������))");
		check10.setToolTipText("��������� �������� (�� � 3 �������� �� ���������� �����)");
		check11.setToolTipText("������ ����������� ��� (+0.25 15 �����)");
		check12.setToolTipText("������� �� 50� �� 200� (+0.25)");
		check13.setToolTipText("������� �� 200� �� 500� (+0.4)");
		check14.setToolTipText("������� �� 500�  (+0.58)");
		
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
		
		
		final JButton okButt = new JButton("��");
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
		
		
		final JButton exButt = new JButton("������");
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
			
			//��������� �������� ������
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
				//� ��������� ������ ����� ��������� ����� ������ � ������� ���������� �� ������������
				String nomerOborud = textBlok.getText();
				String opisanie = areaOpisanie.getText();
				
				String vidRem = "";
				if(radioTekush.isSelected())vidRem="�";
				if(radioKapital.isSelected())vidRem="�";
				
				String edIzmer = "";
				if(radioDiam.isSelected())edIzmer ="�������";
				if(radioMetru.isSelected())edIzmer ="�����";
				
				int col = (int)spinnerColSt.getValue();
				double diam = Double.valueOf(textDiam.getText());				
				boolean [] param = cof;
				//list.add(new Tasks(nomrOborud, opisanie, vidRem, edIzmer, col, diam, param, list));
				list.get(idChangeTasks).setNomerOborud(nomerOborud);
				list.get(idChangeTasks).setOpisanie(opisanie);
				list.get(idChangeTasks).setVidRem(vidRem);
				list.get(idChangeTasks).setEdIzmer(edIzmer);
				list.get(idChangeTasks).setColichestvoStycov(col);
				list.get(idChangeTasks).setDiametr(diam);
				list.get(idChangeTasks).setParam(param);
				list.get(idChangeTasks).change();
				
				
				MTAPI.seriazOut(list, nameFile);
				frame.dispose();
			}		
		}			
	}	
}
