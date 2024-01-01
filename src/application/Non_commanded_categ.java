package application;

public class Non_commanded_categ {
	private String CODECLIENT;
	private String SOCIETE;
	private String NOMCATEGORIE;
	
	
	public Non_commanded_categ(String CODECLIENT,String SOCIETE,String NOMCATEGORIE) {
		
		this.CODECLIENT=CODECLIENT;
		this.SOCIETE=SOCIETE;
		this.NOMCATEGORIE=NOMCATEGORIE;
		
	}
	public String getCODECLIENT() {
		return CODECLIENT;
	}
	public void setCODECLIENT(String cODECLIENT) {
		CODECLIENT = cODECLIENT;
	}
	public String getSOCIETE() {
		return SOCIETE;
	}
	public void setSOCIETE(String sOCIETE) {
		SOCIETE = sOCIETE;
	}
	public String getNOMCATEGORIE() {
		return NOMCATEGORIE;
	}
	public void setNOMCATEGORIE(String nOMCATEGORIE) {
		NOMCATEGORIE = nOMCATEGORIE;
	}
	public String toString() {
		 return  this.CODECLIENT+" "+this.SOCIETE+"  "+this.NOMCATEGORIE;
	  }
}
