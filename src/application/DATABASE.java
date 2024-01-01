package application;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

public class DATABASE {
    private static String sql;
    private static ResultSet result ;
    private static Statement state ;
	private static List<CANN> CANN = new ArrayList<CANN>();
	private static List<CA_CL_ANN> CA_CL_ANN = new ArrayList<CA_CL_ANN>();
	private static List<ANN_CAMAX> ANN_CAMAX = new ArrayList<ANN_CAMAX>();
	private static List<Best_client_best_CA_ANN> Best_client_best_CA_ANN = new ArrayList<Best_client_best_CA_ANN>();
	public static List<Best_client_best_CA_ANN> getBest_client_best_CA_ANN() {
		return Best_client_best_CA_ANN;
	}



	private static List<Last_year_s_commands> Last_year_s_commands = new ArrayList<Last_year_s_commands>();
	private static List<Non_commanded_categ> Non_commanded_categ = new ArrayList<Non_commanded_categ>();
	private static List<CA_PROD_SAISON> CA_PROD_SAISON = new ArrayList<CA_PROD_SAISON>();
	private static Connection con = null;

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			con = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/XE", "BDDvente", "1111");
			state = con.createStatement();
		}  catch (SQLException e) {
			       System.out.println("Une erreur est survenue lors de la connection!!");
			   e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	// CA par année:
	public static List<CANN> CANN() {

		try {
			
			 sql = "\r\n"
					+ "		  SELECT EXTRACT (YEAR  FROM datecommande)as ANN,sum(quantite*prixunitaire*(1-remise/100))as Prix_vente\r\n"
					+ "		from  commandes ,produits ,lignecommandes\r\n"
					+ "		where commandes.idcommande=lignecommandes.idcommande\r\n"
					+ "		and lignecommandes.idcommande=produits.idproduit\r\n"
					+ "		group by extract (year from datecommande)"
					+ "order by EXTRACT (YEAR  FROM datecommande) ";
			 result =state.executeQuery(sql);
			
			while (result.next()) {
				CANN.add(new CANN(result.getInt("ANN"),result.getDouble("PRIX_VENTE") ));
			}
		}
			catch (SQLException e) {
				System.out.println("error while taking infos from Database(CANN)!!");
				e.printStackTrace();
			}
			
		return CANN ;
		
	}
	
	//CA par client et par année:
	public static List<CA_CL_ANN> CA_CL_ANN() {
		CA_CL_ANN.clear();
		try {
			 sql = "select  codeclient, societe,extract (year from datecommande) as ANN, sum(quantite*prixunitaire*(1-remise*0.01)) as CA\r\n"
					+ "from clients,commandes,lignecommandes,produits\r\n"
					+ "where commandes.idcommande=lignecommandes.idcommande\r\n"
					+ "and produits.idproduit=lignecommandes.idproduit\r\n"
					+ "and clients.idclient=commandes.idclient\r\n"
					+ "group by  codeclient,societe,extract (year from datecommande)";
					
			 result = state.executeQuery(sql);
			
			while (result.next()) {
				
			CA_CL_ANN.add(new CA_CL_ANN(result.getString("CODECLIENT"),result.getString("SOCIETE"),result.getInt("ANN"),result.getInt("CA")) );
			}
		}
			
			catch (SQLException e) {
				System.out.println("error while taking infos from Database(CANN-cl-ann)!!");
				e.printStackTrace();
			}
			
		return CA_CL_ANN ;
		
	}
	//Mellieur CA par année
	public static List<ANN_CAMAX> ANN_CAMAX() {

		try {
		      sql = "select ANN , max (CA) as CAMAX\r\n"
					+ "from RQ2\r\n"
					+ "group by ANN "
					+ "order by ANN";
					
			 result = state.executeQuery(sql);
			
			while (result.next()) {
				
			ANN_CAMAX.add(new ANN_CAMAX(result.getInt("ANN"),result.getDouble("CAMAX") ));
			}
		}
			catch (SQLException e) {
				System.out.println("error while taking infos from Database(ANN_CAMAX)!!");
				e.printStackTrace();
			}
			
		return ANN_CAMAX ;
		
	}
	//Le client qui a réalisé ce meilleur  CA par année:
	public static List<Best_client_best_CA_ANN> Best_client_best_CA_ANN() {

		try {
			 sql = "select rq2.ann, CodeClient, Societe, CAMAX FROM Rq2, Rq3 WHERE Rq2.Ann=Rq3.Ann AND Rq2.Ca=Rq3.Camax order by ann ";
					
			 result = state.executeQuery(sql);
			
			while (result.next()) 
				
			Best_client_best_CA_ANN.add(new Best_client_best_CA_ANN(result.getInt(1),result.getString("CODECLIENT") ,result.getString("SOCIETE"),result.getDouble("CAMAX")));
			
		}	
			catch (SQLException e) {
				System.out.println("error while taking infos from Database(Best_client_best_CA_ANN)!!");
				e.printStackTrace();
			}
			
		return Best_client_best_CA_ANN ;
		
	}
	//Le client qui a réalisé le meilleur  derinere  année:
		public static Best_client_best_CA_ANN_last  Best_client_best_CA_ANN_last() {

			try {
				 sql = "select  CodeClient, Societe FROM Rq2, Rq3 WHERE Rq2.Ann=Rq3.Ann AND Rq2.Ca=Rq3.Camax and  rq2.ann=2022 ";
						
				 result = state.executeQuery(sql);
				 result.next();
	  return new Best_client_best_CA_ANN_last(result.getString(1),result.getString(2));
				
			}	
				catch (SQLException e) {
					System.out.println("error while taking infos from Database(Best_client_best_CA_ANN_last)!!");
					e.printStackTrace();
				}
				
			return null ;
			
		}
	
	
	
	//les commandes réalisées l'années dernière:
	public static List<Last_year_s_commands> Last_year_s_commands() {

		try {
			 sql = "select codeclient,IDcommande, Datecommande\r\n"
			 		+ "FROM COmmandes,clients\r\n"
			 		+ "WHERE commandes.idclient=clients.idclient\r\n"
			 		+ "AND extract(year from Datecommande)= extract(year from sysdate)-1"
			 		+ "order by Datecommande  ";
					
			 result = state.executeQuery(sql);
			
			while (result.next()) {
				
			Last_year_s_commands.add(new Last_year_s_commands(result.getString(1),result.getInt("IDCOMMANDE") ,result.getDate("DATECOMMANDE")));
			}
		}
			catch (SQLException e) {
				System.out.println("error while taking infos from Database(Last_year_s_commands)!!");
				e.printStackTrace();
			}
			
		return Last_year_s_commands ;
		
	}
	//Nombre de commandes de l'année derniere:
	public static int N_of_commands() {

		try {
			 sql = "select count(IDcommande) as nbre\r\n"
			 		+ "	FROM Commandes\r\n"
			 		+ "	WHERE extract(year from Datecommande)= extract(year from sysdate)-1\r\n"
			 		+ "	\r\n";
					
			 result = state.executeQuery(sql);
			 result.next();
			 return result.getInt("NBRE");
		}
			 catch (SQLException e) {
					System.out.println("error while taking infos from Database(N_of_commands)!!");
					e.printStackTrace();
			 }
			
		
		return 0;
		
	}
	
	//catégorie des produits non encore commandés par chaque client:
	public static List<Non_commanded_categ> Non_commanded_categ() {

		try {
			 sql = "select * from RQ9";
					
			 result = state.executeQuery(sql);
			 while (result.next()) {
					
			 Non_commanded_categ.add(new Non_commanded_categ(result.getString("CODECLIENT"),result.getString("SOCIETE"),result.getString("NOMDECATEGORIE")));
				
		}
		}		
			 catch (SQLException e) {
					System.out.println("error while taking infos from Database(Non_commanded_categ)!!");
					e.printStackTrace();
			 }
	
				
				return Non_commanded_categ ;	
	}
	

	//CA des produits par saisons
	public static List<CA_PROD_SAISON> CA_PROD_SAISON() {
		CA_PROD_SAISON.clear();
		try {
			 sql = "select * from CA_PROD_SAISON";
					
			 result = state.executeQuery(sql);
			 while (result.next()) {
					
				 CA_PROD_SAISON.add(new CA_PROD_SAISON(result.getInt("year"),result.getString("saison"),result.getInt("idproduit"),result.getString("Designation"),result.getDouble("montant")));
				
		}
		}
			 catch (SQLException e) {
					System.out.println("error while taking infos from Database(CA_PROD_SAISON)!!");
					e.printStackTrace();
			 }
	
				
				return CA_PROD_SAISON ;	
	}
	
	public static String CA_Max_SAISON(int X,String Y) {

		try {
            CallableStatement callableStatement = con.prepareCall("{ ?= call MAXSAISON_annee(?,?) }");
            callableStatement.setInt(3, X); 
            callableStatement.setString(2, Y); 
            callableStatement.registerOutParameter(1, OracleTypes.VARCHAR);
            callableStatement.execute();
					
           return ((OracleCallableStatement) callableStatement).getString(1);
			
		}
			 catch (SQLException e) {
					System.out.println("error while taking infos from Database(CA_Max_SAISON)!!");
					e.printStackTrace();
			 }
		return null ;
	}
	public static int N_of_clients() {

		try {
			 sql = "select count(*) as nbre\r\n"
			 		+ "	FROM clients";
					
			 result = state.executeQuery(sql);
			 result.next();
			 return result.getInt("NBRE");
		}
			 catch (SQLException e) {
					System.out.println("error while taking infos from Database(N_of_clients)!!");
					e.printStackTrace();
			 }
			
		
		return 0;
		
	}
	public static int N_of_commands_ALL() {

		try {
			 sql = "select count(*) as nbre\r\n"
			 		+ "	FROM commandes";
					
			 result = state.executeQuery(sql);
			 result.next();
			 return result.getInt("NBRE");
		}
			 catch (SQLException e) {
					System.out.println("error while taking infos from Database(N_of_commands_ALL)!!");
					e.printStackTrace();
			 }
			
		
		return 0;
		
	}
	
	public static int N_of_Suppliers() {

		try {
			 sql = "select count(*) as nbre\r\n"
			 		+ "	FROM fournisseurs";
					
			 result = state.executeQuery(sql);
			 result.next();
			 return result.getInt("NBRE");
		}
			 catch (SQLException e) {
					System.out.println("error while taking infos from Database(N_of_Suppliers)!!");
					e.printStackTrace();
			 }
			
		
		return 0;
		
	}
	
	
	public static void main(String []args) {
	/*
		for (CANN  x : CANN()) {
			System.out.println(x.toString());	
			
		}
		
		for (ANN_CAMAX  x : ANN_CAMAX()) {
			System.out.println(x.toString());	
			
		}
		for (Last_year_s_commands  x : Last_year_s_commands()) {
			System.out.println(x.toString());	
			
		}
		System.out.println(N_of_commands());
		*/
		
		/*for (CA_PROD_SAISON  x : CA_PROD_SAISON()) {
			System.out.println(x.toString());
			}	}
	/**/
		for (CA_CL_ANN  x : CA_CL_ANN()) {
			System.out.println(x.toString());	
		}}
		/*
		System.out.print(Best_client_best_CA_ANN_last());
			
		for (Best_client_best_CA_ANN  x : Best_client_best_CA_ANN()) {
			System.out.println(x.toString());	
			
		}
		
		
		
		for (Non_commanded_categ  x : Non_commanded_categ()) {
			System.out.println(x.toString());	
			
		}
		*/
		
	
	//System.out.println(CA_Max_SAISON(2020,"Automne"));}
		
//}
	 
	
	

	
	

	public static void truncate() {
		CANN.clear();
		CA_CL_ANN.clear();
		ANN_CAMAX.clear();
		Best_client_best_CA_ANN.clear();
		Last_year_s_commands.clear();
		Non_commanded_categ.clear();
		
	}

}
