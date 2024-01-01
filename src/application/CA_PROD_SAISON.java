package application;

public class CA_PROD_SAISON {
	private  int year;
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	private  int  idproduit;
	private double  CA;
	private String designation;
	private String saison;
	
	public CA_PROD_SAISON(int year ,String saison, int idproduit,  String designation, double cA) {
		this.idproduit = idproduit;
		this.CA = cA;
		this.designation = designation;
		this.saison = saison;
		this.year=year;
	}

	public int getIdproduit() {
		return idproduit;
	}

	public void setIdproduit(int idproduit) {
		this.idproduit = idproduit;
	}

	public double getCA() {
		return CA;
	}

	public void setCA(double cA) {
		CA = cA;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getSaison() {
		return saison;
	}

	public void setSaison(String saison) {
		this.saison = saison;
	}

	@Override
	public String toString() {
		return year+"  "+saison+"  "+idproduit +"  "+designation+"  "+CA;
	}

}
