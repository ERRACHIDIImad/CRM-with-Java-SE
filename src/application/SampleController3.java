package application;


import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.util.*;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class SampleController3 implements Initializable {
	private ObservableList<String> series3 = FXCollections.observableArrayList();
	 @FXML
     private Pane ID;
	 @FXML
     private Label code,soc ; 
	 @FXML
     private TextField year;
	 @FXML
	 private ListView<String> non_commanded_categories_clt ;
		@FXML
		private  Label Host_name;
		@FXML
		private  Label date_sys ;
	 @FXML
	 private ScatterChart<Number, Number> bubble;
	 
	 
	 @SuppressWarnings({ "unchecked", "rawtypes" })
	 @Override
     public void initialize(URL arg0, ResourceBundle arg1) {	
		 
		 
		 SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm   dd MMMM yyyy");
	        date_sys.setText(dateFormat.format(new Date()));
	  
	               
	        try {
	            InetAddress localMachine = InetAddress.getLocalHost();
	            Host_name.setText(localMachine.getHostName());
	        } catch (UnknownHostException e) {
	            e.printStackTrace();
	        }

	        
	        
	        
		 bubble.getXAxis().setLabel("YEAR");  
	     bubble.getYAxis().setLabel("PROFITS");  
	     
		 XYChart.Series<Number, Number> series = new XYChart.Series<>();    
		series.setName("MAX BENEFIT");
	   for (Best_client_best_CA_ANN X : DATABASE.Best_client_best_CA_ANN())
		series.getData().add(new XYChart.Data(Integer.toString(X.getAnn()),X.getCAMAX()));
	   bubble.getData().add(series); 
       

        

        
    	}
   @FXML
   public void  search_best_client(){
	   series3.clear();
	   code.setText(null);
	   soc.setText(null);
	   for (Best_client_best_CA_ANN X : DATABASE.getBest_client_best_CA_ANN())
		   if(Integer.toString(X.getAnn()).equals(year.getText()))
		   {
		   code.setText(X.getCodeclient());
		   soc.setText(X.getSociete());
		   
		   }
	   

	   
	   series3.addAll("CODE DE CLIENT\t\tSOCIETE\t\t\t\t\tCATEGORIE");
	   for (Non_commanded_categ x : DATABASE.Non_commanded_categ())
		   if(x.getCODECLIENT().equals(code.getText())){
	   series3.add(x.getCODECLIENT()+"\t\t\t\t"+x.getSOCIETE()+"\t\t\t"+x.getNOMCATEGORIE());
	   
	   non_commanded_categories_clt.setItems(series3);
		   }
   }
    

@FXML
public void handlemousclicked() throws IOException {
      BorderPane root= FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
		 Scene scene= new Scene(root);
		 Stage x= (Stage)ID.getScene().getWindow();
		 x.setScene(scene);
		 
	}

@FXML
public void handlemousclicked2() throws IOException {
      BorderPane root= FXMLLoader.load(getClass().getResource("Dashboard2.fxml"));
		 Scene scene= new Scene(root);
		 Stage x= (Stage)ID.getScene().getWindow();
		 x.setScene(scene);
		 
	}
@FXML
	public void handlemousclicked3() throws IOException {
	      BorderPane root= FXMLLoader.load(getClass().getResource("Dashboard4.fxml"));
			 Scene scene= new Scene(root);
			 Stage x= (Stage)ID.getScene().getWindow();
			 x.setScene(scene);
			 
		}
}

