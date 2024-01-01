package application;

public class ANN_CAMAX {
	private int ANN;
	private double 	CAMAX;
	public int getANN() {
		return ANN;
	}
	public void setANN(int aNN) {
		ANN = aNN;
	}
	public double getCAMAX() {
		return CAMAX;
	}
	public void setCAMAX(double cAMAX) {
		CAMAX = cAMAX;
	}
	public ANN_CAMAX(int ANN,double 	CAMAX) {
		this.ANN=ANN;
		this.CAMAX=CAMAX;
		
	}
	public String toString() {
		 return  ANN+" "+CAMAX;
	  }
	}


