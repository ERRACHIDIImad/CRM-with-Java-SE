package application;

public class CA_CL_ANN {
	private String Codeclient;
	private String SOCIETE;
	private int ANN;
	private double CA;
	public String getCodeclient() {
		return Codeclient;
	}
	public void setCodeclient(String codeclient) {
		Codeclient = codeclient;
	}
	public String getSOCIETE() {
		return SOCIETE;
	}
	public void setSOCIETE(String sOCIETE) {
		SOCIETE = sOCIETE;
	}
	public int getANN() {
		return ANN;
	}
	public void setANN(int aNN) {
		ANN = aNN;
	}
	public double getCA() {
		return CA;
	}
	public void setCA(double cA) {
		CA = cA;
	}

    public CA_CL_ANN(String Codeclient,String SOCIETE,int ANN,double CA) {
	   this.ANN=ANN;
	   this.CA=CA;
	   this.Codeclient=Codeclient;
	   this.SOCIETE=SOCIETE;
	   
   }
    public String toString() {
   	 return  this.ANN+" "+this.Codeclient+" "+this.SOCIETE+" "+this.CA;
     }
}

