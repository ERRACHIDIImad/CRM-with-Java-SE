package application;

public class Best_client_best_CA_ANN {
	private int ann;
	public int getAnn() {
		return ann;
	}

	public void setAnn(int ann) {
		this.ann = ann;
	}

	private String codeclient;
	private String societe;
	private double CAMAX;
	public String getCodeclient() {
		return codeclient;
	}

	public void setCodeclient(String codeclient) {
		this.codeclient = codeclient;
	}

	public String getSociete() {
		return societe;
	}

	public void setSociete(String societe) {
		this.societe = societe;
	}

	public double getCAMAX() {
	return CAMAX;
	}

	public void setCAMAX(double cAMAX) {
		CAMAX = cAMAX;
	}


	
  public Best_client_best_CA_ANN(int ann ,String codeclient,String societe,double CAMAX) {
	  this.CAMAX=CAMAX;
	  this.codeclient=codeclient;
	  this.societe=societe;
	  this.ann=ann;
  }
  
  public String toString() {
	 return  ann+" "+codeclient+"  "+societe+" "+CAMAX;
  }
	  
	  
  
}
