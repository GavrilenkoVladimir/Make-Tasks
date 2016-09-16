package ua.gvv.src;

import ua.gvv.src.StaticFunctionsConstants;

public class Tasks {
	private int id;
	private String nomerOborud;
	private String opisanie;
	private String	vidRem;
	private String edIzmer;
	private double col;
	private int diam;
	private boolean[] param;
	
	//расчетные велечины
	private double normNaEd;
	private double normNaOb;
	private double factNaOb;
	
	
	
	public Tasks(int id, String nomrOborud, String opisanie, String vidRem, String edImer, double col, int diam, boolean []param){
		
		this.id =id;
		this.nomerOborud = nomrOborud;
		this.opisanie = opisanie;
		this.vidRem = vidRem;
		this.edIzmer =edIzmer;
		this.col = col;
		this.diam = diam;
		this.param = param;
		
	}
}
