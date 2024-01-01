package application;

public class CANN {
	
	private int ANN;
	private double PRIX_VENTE;
    public CANN(int ANN,double PRIX_VENTE) {
	   this.ANN=ANN;
	   this.PRIX_VENTE=PRIX_VENTE;
	   
   }
	public int getANN() {
		return ANN;
	}
	public void setANN(int aNN) {
		ANN = aNN;
	}
	public double getPRIX_VENTE() {
		return PRIX_VENTE;
	}
	public void setPRIX_VENTE(double pRIX_VENTE) {
		PRIX_VENTE = pRIX_VENTE;
	}
	public String toString() {
		 return  this.ANN+" "+this.PRIX_VENTE;
	  }
   
   
}
