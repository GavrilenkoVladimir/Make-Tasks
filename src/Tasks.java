
import java.io.Serializable;
import java.util.ArrayList;

public class Tasks implements Serializable {
	private int id;
	private String nomerOborud;
	private String stal;
	private String opisanie;
	private String	vidRem;
	private String edIzmer;
	private double colichestvoStycov;
	private double diametr;
	private boolean[] param ;
	private int colSloyov;
	private double coffK;
	private double coffT;
	private double tolshina;
	
	private String tableNum;
	private String coffOpisK;
	private String coffOpisT;
	
	
	//расчетные велечины
	private double normNaEd;
	private double normNaOb;
	private double factNaOb;
	private int indexNorm;
	private double normDop;
	//Табличные значения для расчетов индекцы от 0 - 12
	private static double [] Nv1 = {0.188,0.222,0.266,0.341,0.403,0.466,0.530,0.589,0.649,0.708,0.769,0.830,0.28};
	private static double [] Nv2 = {0.105,0.107,0.114,0.17,0.196,0.222,0.299,0.334,0.369,0.41,0.445,0.48,0.14};
	private static double [] Nv3 = {0.184,0.192,0.202,0.239,0.261,0.314,0.348,0.372,0.426,0.46,0.508,0.534,0.14};
	private static double [] Nv4 = {0.094,0.102,0.111,0.119,0.135,0.152,0.171,0.19,0.208,0.229,0.247,0.275,0.07};
	private static double [] Nv5 = {0.571,0.623,0.693,0.869,0.995,1.154,1.348,1.485,1.652,1.807,1.969,2.119,0.63};
	//Табличные значения коеффициентов
	private static final double [] K = {1.15,1.15,1.15,1.5,1.2,1.1,1.5,3};
	private static final double [] T = {0.25,0.25,0.4,0.58};
	
	
	
	public Tasks(String nomrOborud, String opisanie, String vidRem,String stal, 
			String edIzmer, double col, double diam,double tolshina, boolean []param,
			ArrayList<Tasks> list){
		
		setId(list.size()+1);
		this.tolshina = tolshina;
		this.stal = stal;
		setNomerOborud(nomrOborud);
		setVidRem(vidRem);
		setEdIzmer(edIzmer);
		setParam(param);
		if (col == 0) setColichestvoStycov(diam);
		else
		setColichestvoStycov(col);
		setDiametr(diam);
		doNormNaEd();
		doKCoff();
		doKPlus();
		doTCoff();
		doNormNaOb();
		setCoffOpis();
		setTableNum();
		setOpisanie(opisanie);

		

		
		
	}


	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNomerOborud() {
		return nomerOborud;
	}
	public void setNomerOborud(String nomerOborud) {
		this.nomerOborud = nomerOborud;
	}
	public String getOpisanie() {
		return opisanie;
	}
	public void setOpisanie(String opisanie) {
		this.opisanie = "";
		if(edIzmer.equals("Диаметр"))
		{
			this.opisanie = opisanie + "\n"+stal+"; "+"\u00D8"+(int)diametr+"x"+(int)tolshina+"; " + "Нв = " 
					+ normNaEd+"(n.1-4)"+ " таб. "+ tableNum +" ;"+ coffOpisK + "\u003d"+coffK+" ;"+  coffOpisT + "\u003d"+coffT;
		}
		if(edIzmer.equals("Метры"))
		{
			this.opisanie = opisanie + "\n"+stal+"; "+ "Нв = " 
					+ normNaEd+"(n.1-4)"+ " таб. "+ tableNum +" ;"+ coffOpisK+"\u003d" + coffK+" ;"+  coffOpisT+ "\u003d"+ coffT;
		}
	}
	public String getVidRem() {
		return vidRem;
	}
	public void setVidRem(String vidRem) {
		this.vidRem = vidRem;
	}
	public String getEdIzmer() {
		return edIzmer;
	}
	public void setEdIzmer(String edIzmer) {
		this.edIzmer = edIzmer;
	}
	public double getColichestvoStycov() {
		return colichestvoStycov;
	}
	public void setColichestvoStycov(double colichestvoStycov) {
		this.colichestvoStycov = colichestvoStycov;
	}
	public double getDiametr() {
		return diametr;
	}
	public void setDiametr(double diametr) {
		this.diametr = diametr;
	}
	public boolean[] getParam() {
		return param;
	}
	public void setParam(boolean[] param) {
		this.param = param;
	}
	public double getNormNaEd() {
		return normNaEd;
	}
	public double getNormNaOb() {
		return normNaOb;
	}
	public double getFactNaOb() {
		return factNaOb;
	}
	public void change(){
		doNormNaEd();
		doKCoff();
		doKPlus();
		doTCoff();
		doNormNaOb();
		setCoffOpis();
		setTableNum();
		setOpisanie(opisanie);
	}
	/**В зависимости от едениц измерения и диаметра берет табличную норму времени на единицу*/
	private void doNormNaEd(){
		if(edIzmer.equalsIgnoreCase("Метры"))
		{
			normNaEd = Nv5[12];
			indexNorm = 12;
		}
		if(edIzmer.equalsIgnoreCase("Диаметр"))
		{
			if(diametr<=28)
			{				
				indexNorm = 0;
				normNaEd = Nv5[0];
			}
			else
			if(diametr<=60)
				{
					indexNorm = 1;
					normNaEd = Nv5[1];
				}
			else
			if(diametr<=108)
				{
					indexNorm = 2;
					normNaEd = Nv5[2];
				}
			else
			if(diametr<=219)
				{
					indexNorm = 3;
					normNaEd = Nv5[3];
				}
			else
			if(diametr<=273)
				{
					indexNorm = 4;
					normNaEd = Nv5[4];
				}
			else
			if(diametr<=377)
				{
					indexNorm = 5;
					normNaEd = Nv5[5];
				}
			else
			if(diametr<=465)
				{
					indexNorm = 6;		
					normNaEd = Nv5[6];
				}
			else
			if(diametr<=530)
				{
					indexNorm = 7;
					normNaEd = Nv5[7];
				}
			else
			if(diametr<=680)
				{
					indexNorm = 8;	
					normNaEd = Nv5[8];
				}
			else
			if(diametr<=720)
				{
					indexNorm = 9;		
					normNaEd = Nv5[9];
				}
			else
			if(diametr<=820)
				{
					indexNorm = 10;
					normNaEd = Nv5[10];					
				}
			else
			if(diametr<=920)
				{
					indexNorm = 11;		
					normNaEd = Nv5[11];
				}
		}
	}	
	/**Расчитывает коэфициент для коректировки нормы времени на единицу*/
	private void doKCoff(){
		
		coffK=0;
						
		for(int i = 0; i < 8; i++)
		{
			if(param[i])
			{				
				if(coffK == 0)
					coffK=coffK + K[i];
				else
					coffK*=K[i];				
			}
		}
		coffK =MTAPI.okrug(coffK, 3);
	
		
	}
	/**Расчитывает коеффициент для многослойной сварки или сварки с двух сторон*/
	private void doKPlus(){
		
		for(int i = 8; i < 11; i++)
		{
			if(param[8]||param[9])
			{System.out.println("Какого хуяти 89");
				normDop = normDop + Nv3[indexNorm] + Nv4[indexNorm]; 
			}
			if(param[10])
			{System.out.println("Какого хуяти 10");
			normDop = normDop + (Nv3[indexNorm]*colSloyov);
			}
		}
		normDop = MTAPI.okrug(normDop, 3);
		
	}
	/**Расчитывает дополнительные затраты время на переход(В зависимости от дистанции) и 
	 * время на составление документации.
	 * Так же учитываеться количество дней на проведение работы*/
	private void doTCoff(){				
		coffT=0;
		
		int mnozh = (int)(((normNaEd*colichestvoStycov*coffK)+normDop)/8 + 1);
		System.out.println("МНож " + mnozh);
		for(int i = 11; i < 15; i++)
		{
			if(param[i])
			{
				coffT +=T[i-11]*mnozh;
			}
			
		}
		coffT = MTAPI.okrug(coffT, 3);
	}
	/**Окончательные часы на выполнение работы с заданым обьемом*/
	private void doNormNaOb(){
		
		System.out.println("норма на ед "+ normNaEd +"коф к "+ coffK +"Метры "+colichestvoStycov + " НОрм доп "+normDop+ coffT);
		normNaOb = (MTAPI.okrug(normNaEd*coffK, 2)*colichestvoStycov)+normDop+coffT;
		factNaOb = normNaOb;
		normNaOb = MTAPI.okrug(normNaOb, 2);
		factNaOb = MTAPI.okrug(factNaOb, 2);
		
	}


	public int getColSloyov() {
		return colSloyov;
	}



	public void setColSloyov(int colSloyov) {
		this.colSloyov = colSloyov;
	}
	
	private void setCoffOpis(){
		coffOpisK="";
		coffOpisT="";
		int i = 0;
		for(Boolean b:param)
		{
			
			if(b)
			{
				if(i==0) coffOpisK +="Kсп ";
				
				if(coffOpisK.length()>1)
				{
					if(i<11)
						coffOpisK = coffOpisK+ "\u002b " + "K"+i;																
				}
				else
				{	if(i<11)
						coffOpisK = coffOpisK+"K"+i;
									}
				
				
				if (coffOpisT.length()>1)
				{
					if(i>=11)
					coffOpisT = coffOpisT+ "\u002b " + "T"+(i-9);
				}
				else
				{
					if(i>=11)
					coffOpisT = coffOpisT + "T" + (i-9);
				}
				
			}
			i++;
			
		}
	}
	private void setTableNum(){
		
		if (indexNorm == 12) 
			tableNum = "4.2";
		else 
			tableNum = "4.1";
	}
	public double getCoffK(){
		return coffK;
	}
	
}
