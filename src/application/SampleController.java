package application;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.text.SimpleDateFormat;
import java.util.*;
import javafx.scene.layout.BorderPane;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SampleController implements Initializable {
	
	@FXML
	private Pane ID;
	@FXML
	private Label best_clt  ;
	@FXML
	private Label best_clt_soc  ;
	@FXML
	private  Label Host_name;
	@FXML
	private  Label date_sys ;
	@FXML
	private Label N_commands;
	@FXML
	private Label N_commands_ALL;
	@FXML
	private Label N_suppliers;
	@FXML
	private Label N_clients;
	
    @FXML
    private LineChart<String, Double> LineChrat;
    @SuppressWarnings("rawtypes")
	@FXML
    private BarChart BARCHART;
    @FXML
    private PieChart PieChart;
    @FXML
    private ListView<String> this_months_commands;
    private Best_client_best_CA_ANN_last x = DATABASE.Best_client_best_CA_ANN_last();
    
    
	
    @SuppressWarnings("unchecked")
	@Override
    public void initialize(URL arg0, ResourceBundle arg1) {	
    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm   dd MMMM yyyy");
    date_sys.setText(dateFormat.format(new Date()));
    best_clt.setText(x.getCodeclient());
    best_clt_soc.setText(x.getSociete());
    try {
        InetAddress localMachine = InetAddress.getLocalHost();
        Host_name.setText(localMachine.getHostName());
    } catch (UnknownHostException e) {
        e.printStackTrace();
    }
    	
	XYChart.Series<String,Double> series = new XYChart.Series<String,Double> ();
    series.setName("Sales");	
    for (CANN X : DATABASE.CANN())
	series.getData().add(new XYChart.Data<String,Double>(Integer.toString(X.getANN()),X.getPRIX_VENTE())); 
    LineChrat.getData().addAll(series);
    LineChrat.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");
    series.getNode().setStyle("-fx-stroke: #FFD6DC");
    
    
    N_commands.setText(Integer.toString(DATABASE.N_of_commands()));
    N_commands_ALL.setText(Integer.toString(DATABASE.N_of_commands_ALL()));
    
    N_clients.setText(Integer.toString(DATABASE.N_of_clients()));
    N_suppliers.setText(Integer.toString(DATABASE.N_of_Suppliers()));

    BarChart.Series<String,Double> series2 = new BarChart.Series<String,Double> ();
    series2.setName("High Revenue / Year");	
    for (ANN_CAMAX X : DATABASE.ANN_CAMAX())
	series2.getData().add(new BarChart.Data<String,Double>(Integer.toString(X.getANN()),X.getCAMAX())); 
    BARCHART.getData().addAll(series2);
    BARCHART.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");
    
   
    
   ObservableList<String> series3 = FXCollections.observableArrayList();
   series3.addAll("Id de commande\tDate de commande\t\tCode de client");
   for (Last_year_s_commands x : DATABASE.Last_year_s_commands())
   series3.add(Integer.toString(x.getId_com())+"\t\t\t\t"+x.getDate_comm().toString()+"\t\t\t"+x.getCODECLIENT());
   
  
   this_months_commands.setItems(series3);
   
  

	}
    
   
    
    
    @FXML
   	public void handlemousclicked() throws IOException {
    	      BorderPane root= FXMLLoader.load(getClass().getResource("Dashboard2.fxml"));
   			 Scene scene= new Scene(root);
   			 Stage x= (Stage)ID.getScene().getWindow();
   			 x.setScene(scene);
   			 
   		}
    @FXML
   	public void handlemousclicked2() throws IOException {
    	      BorderPane root= FXMLLoader.load(getClass().getResource("Dashboard3.fxml"));
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