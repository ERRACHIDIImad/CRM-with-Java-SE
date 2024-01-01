package application;


import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.text.SimpleDateFormat;
import java.util.*;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SampleController2 implements Initializable {
	 double a=0,b=0,c=0,d=0;
	 private boolean n=false;
	 ObservableList<PieChart.Data>
	 	dataPoints = FXCollections.observableArrayList()
			 ,dataPoints1= FXCollections.observableArrayList();
	 
	 
	 @FXML
		private Pane ID;
	 
	
	 @FXML
	private   Label Host_name;
	@FXML
	private   Label date_sys ;
	
	@FXML
	private   Label produit_saison1 ;
	private  Integer x;
	private  String y;
	@FXML
	private   TextField yr;
	@FXML
	private   TextField  s;
		
		
	@FXML  
	private   TextField saison ;
	@FXML
	private  TextField clt ;
	@FXML
	private  TextField soc ;
    @FXML 
    private PieChart bubble = new PieChart(); 
    @FXML 
    private PieChart bubble1 = new PieChart();
        private SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm   dd MMMM yyyy");


	@Override
    public void initialize(URL arg0, ResourceBundle arg1) {	
    	
    	
        date_sys.setText(dateFormat.format(new Date()));
  
               
        try {
            InetAddress localMachine = InetAddress.getLocalHost();
            Host_name.setText(localMachine.getHostName());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        
    	reload();
    	reload1();
    	}
    
    @FXML
    public void search_saison_data() {
    	dataPoints.clear();
    for (CA_PROD_SAISON X : DATABASE.CA_PROD_SAISON())
    	
    	if (X.getDesignation().equals(saison.getText()))
    	{
    	if(X.getSaison().equals("Ete"))c+=X.getCA();
    	if(X.getSaison().equals("Printemps"))d+=X.getCA();
    	if(X.getSaison().equals("Automne"))a+=X.getCA();
    	if(X.getSaison().equals("Hiver"))b+=X.getCA();
    	}
       if (a==0 && b==0 && c==0 &&d==0) 
	    {
		dataPoints.add(new PieChart.Data("PRODUCT DOES NOT EXIST !!!" ,0)); 
	    bubble.setData(dataPoints);
       }
       
       else {
    	dataPoints.add(new PieChart.Data("Automn" ,a)); 
    	dataPoints.add(new PieChart.Data("Winter" ,b)); 
    	dataPoints.add(new PieChart.Data("Summer" ,c)); 
    	dataPoints.add(new PieChart.Data("Spring" ,d)); 
    
        bubble.setData(dataPoints);
        a=0;b=0;c=0;d=0;
       }
       
    }  
       @FXML
       public void search_CA_client_data() {
    	 dataPoints1.clear();
       for (CA_CL_ANN X : DATABASE.CA_CL_ANN())
       {
       	if (X.getCodeclient().equals(clt.getText())&&X.getSOCIETE().equals(soc.getText()))
       	{n=true;
        if (X.getANN()==2012) 	dataPoints1.add(new PieChart.Data("2012" ,X.getCA())); 
        if (X.getANN()==2013)	dataPoints1.add(new PieChart.Data("2013" ,X.getCA())); 
        if (X.getANN()==2014)	dataPoints1.add(new PieChart.Data("2014" ,X.getCA())); 
        if (X.getANN()==2015)	dataPoints1.add(new PieChart.Data("2015" ,X.getCA())); 
        if (X.getANN()==2016)	dataPoints1.add(new PieChart.Data("2016" ,X.getCA())); 
        if (X.getANN()==2017)	dataPoints1.add(new PieChart.Data("2017" ,X.getCA())); 
        if (X.getANN()==2018)	dataPoints1.add(new PieChart.Data("2018" ,X.getCA())); 
        if (X.getANN()==2019)	dataPoints1.add(new PieChart.Data("2019" ,X.getCA()));  
      	if (X.getANN()==2020)	dataPoints1.add(new PieChart.Data("2020" ,X.getCA())); 
      	if (X.getANN()==2021)	dataPoints1.add(new PieChart.Data("2021" ,X.getCA())); 
      	if (X.getANN()==2022)	dataPoints1.add(new PieChart.Data("2022" ,X.getCA()));
       	}
       }
   	
        if (n==false) reload1();
        else {
        	n=false;
       	    bubble1.setData(dataPoints1);
        }
       	}
       
        
private void reload() {
	
	dataPoints.add(new PieChart.Data("Automn" ,25)); 
	dataPoints.add(new PieChart.Data("Winter" ,25)); 
	dataPoints.add(new PieChart.Data("Summer",25)); 
	dataPoints.add(new PieChart.Data("Spring",25)); 
	bubble.setData(dataPoints);
}
private void reload1() {
	dataPoints1.clear();
	dataPoints1.add(new PieChart.Data("2012" ,9.99)); 
   	dataPoints1.add(new PieChart.Data("2013" ,9.99)); 
   	dataPoints1.add(new PieChart.Data("2014" ,9.99)); 
   	dataPoints1.add(new PieChart.Data("2015" ,9.99));
   	dataPoints1.add(new PieChart.Data("2016" ,9.99)); 
   	dataPoints1.add(new PieChart.Data("2017" ,9.99)); 
   	dataPoints1.add(new PieChart.Data("2018" ,9.99)); 
   	dataPoints1.add(new PieChart.Data("2019" ,9.99)); 
   	dataPoints1.add(new PieChart.Data("2020" ,9.99));
   	dataPoints1.add(new PieChart.Data("2021" ,9.99)); 
   	dataPoints1.add(new PieChart.Data("2022" ,9.99));
   	bubble1.setData(dataPoints1);
}
@FXML
	public void handlemousclicked1() throws IOException {
	      BorderPane root= FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
			 Scene scene= new Scene(root);
			 Stage x= (Stage)ID.getScene().getWindow();
			 x.setScene(scene);
			 
		}
@FXML
public void handlemousclicked3() throws IOException {
      BorderPane root= FXMLLoader.load(getClass().getResource("Dashboard3.fxml"));
		 Scene scene= new Scene(root);
		 Stage x= (Stage)ID.getScene().getWindow();
		 x.setScene(scene);
		 
	}
@FXML
	public void handlemousclicked4() throws IOException {
	      BorderPane root= FXMLLoader.load(getClass().getResource("Dashboard4.fxml"));
			 Scene scene= new Scene(root);
			 Stage x= (Stage)ID.getScene().getWindow();
			 x.setScene(scene);
			 
		}

@FXML
public void product_saison_year() {
	produit_saison1.setText("");
	try {
        x =	Integer.valueOf(yr.getText());
        y =  s.getText();
	    }
	catch(Exception e) {e.printStackTrace();}
	
	if (x!=null&&y!=null)	{
	String a= DATABASE.CA_Max_SAISON(x,y);
	if( a != null && !a.isEmpty()) 	produit_saison1.setText(a);
	}
	x=null;
	y=null;
	s.setText("");
	yr.setText("");
	
	
}





}
   



