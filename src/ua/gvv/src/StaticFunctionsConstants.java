package ua.gvv.src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class StaticFunctionsConstants 
	{
	
	private static final double [] K = {1.15,1.15,1.5,1.2,1.1,1.5,3,1.15};
	private static final double [] T = {0.25,0.25,0.4,0.58};
	private static double Ksum;
	private static double Tsum;
	public static double Nv;
	//Табличные значения по нормам
	private static double [] Nv1 = {0.188,0.222,0.266,0.341,0.403,0.466,0.530,0.589,0.649,0.708,0.769,0.830};
	private static double [] Nv2 = {0.105,0.107,0.114,0.17,0.196,0.222,0.299,0.334,0.369,0.41,0.445,0.48};
	private static double [] Nv3 = {0.184,0.192,0.202,0.239,0.261,0.314,0.348,0.372,0.426,0.46,0.508,0.534};
	private static double [] Nv4 = {0.094,0.102,0.111,0.119,0.135,0.152,0.171,0.19,0.208,0.229,0.247,0.275};
	private static double [] Nv5 = {0.571,0.623,0.693,0.869,0.995,1.154,1.348,1.485,1.652,1.807,1.969,2.119};
	private static int indexTrub;
	private static int indexMetr;
	private static int colSloysl;
	public static void  countUp(int col, int diam,boolean [] bool,String  vidRabot, int colSloys)
	{
		StaticFunctionsConstants.colSloysl = colSloys;
		vuborNvDiam(diam);
		countUpKoeff(bool);
		System.out.println(Ksum+": Коэф");
		Nv = Nv5[indexTrub];
		System.out.println(Nv+": Без коэфф");
		Nv = Nv * Ksum;
		System.out.println(Nv + ": C Коэфф");
		Nv = Nv *col;
		System.out.println(Nv + ": С расчетом на количество стыков");
		System.out.println(Tsum + ": ДО");
		if(Nv>=8){
			int mnozhutel = (int)Nv/8;
				if(bool[11])
				{
					Tsum +=T[0]*mnozhutel;
				}
				if(bool[12])
				{
					Tsum +=T[1]*mnozhutel;
				}
				if(bool[13])
				{System.out.println("YEST");
					Tsum +=T[2]*mnozhutel;
				}
				if(bool[14])
				{
					
					Tsum +=T[3]*mnozhutel;
				}
				System.out.println(Tsum + ": после");
				System.out.println("Моножитель: "+mnozhutel);
		}
		Nv = Nv+Tsum;
		Nv = (int)(Nv*100);
		Nv = Nv/100;
		System.out.println(Nv + ": Конечное");
		
		
		
		
		
		
	}
	public static void print(){
		
		for(int i = 0 ; i < Nv1.length;i++){
			System.out.print(Nv1[i]+"////");
		}
		System.out.println();
		System.out.println("-------------------------------------------------------------------------------");
		for(int i = 0 ; i < Nv1.length;i++){
			System.out.print(Nv2[i]+"////");
		}
		System.out.println();
		System.out.println("-------------------------------------------------------------------------------");
		for(int i = 0 ; i < Nv3.length;i++){
			System.out.print(Nv3[i]+"////");
		}
		System.out.println();
		System.out.println("-------------------------------------------------------------------------------");
		for(int i = 0 ; i < Nv1.length;i++){
			System.out.print(Nv4[i]+"////");
		}
		System.out.println();
		System.out.println("-------------------------------------------------------------------------------");
		for(int i = 0 ; i < Nv1.length;i++){
			System.out.print(Nv5[i]+"////");
		}
		
		
		
	}
	private static void vuborNvDiam(int diam)
	{
		if(diam<=28)
		{
			
			indexTrub = 0;
		}	
		else	
			if(diam<=60)
			{
				indexTrub = 1;
			}
		else	
			if(diam<=108)
			{
				indexTrub = 2;
			}
		else	
			if(diam<=219)
			{
				indexTrub = 3;	
			}
		else	
			if(diam<=273)
			{
				indexTrub = 4;
			}
		else	
			if(diam<=377)
			{
				indexTrub = 5;			
			}
		else	
			if(diam<=465)
			{
				indexTrub = 6;			
			}
		else	
			if(diam<=530)
			{
				indexTrub = 7;				
			}
		else	
			if(diam<=680)
			{
				indexTrub = 8;					
			}
		else	
			if(diam<=720)
			{
				indexTrub = 9;						
			}
		else	
			if(diam<=820)
			{
				indexTrub = 10;						
			}
		else	
			if(diam<=920)
			{
				indexTrub = 11;						
			}
	}
	
	private static void countUpKoeff(boolean [] bool)
	{	double zap;
		for(int i = 0; i < bool.length; i++)
		{
			if(bool[i])
			{
				if(i<8)
				{
					if(Ksum == 0)
						Ksum=Ksum + K[i];
					else
						Ksum*=K[i];
				}
				if(i==8)
				{
					Nv = Nv + Nv3[indexTrub] + Nv4[indexTrub]; 
				}
				//if(i==9){} пока не работает так так таблицу для платин еще не вбивал
				if(i==10)
				{
					Nv = Nv - Nv3[indexTrub];
					Nv = Nv + (Nv3[indexTrub]*colSloysl);
				}
				//расчет T коеффициента
				if(i==11)
				{System.out.println(1);
					Tsum +=T[0];
				}
				if(i==12)
				{System.out.println(2);
					Tsum +=T[1];
				}
				if(i==13)
				{System.out.println(3);
					Tsum +=T[2];
				}
				if(i==14)
				{System.out.println(4);
					Tsum +=T[3];
				}
			}
		}
	}
}
