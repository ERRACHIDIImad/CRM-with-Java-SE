package application;


import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import javafx.fxml.FXML;
import javafx.scene.text.TextFlow;
import javafx.fxml.FXMLLoader;
import java.util.*;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class SampleController4 implements Initializable {
	
	 @FXML
     private Pane ID;
		@FXML
		private  Label Host_name;
		@FXML
		private  Label date_sys ;
		@FXML
		private  TextArea txt ;

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

	        
	 }    
        

        
   
    

@FXML
public void handlemousclicked() throws IOException {
      BorderPane root= FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
		 Scene scene= new Scene(root);
		 Stage x= (Stage)ID.getScene().getWindow();
		 x.setScene(scene);
		 
	}
@FXML
public void handlemousclicked1() throws IOException {
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
public void cleartext()  {
  txt.setText(null);
		 
	}
@FXML
public void savetext()  {
	try {
	  FileWriter writer = new FileWriter("C:\\Users\\hp\\eclipse-workspace\\Sales_Visualisation\\Mynotes\\Notes.txt",true);

      writer.write(txt.getText()+'\n');
      writer.close();
      txt.setText(null);
  
	}
	catch (IOException e) {
      e.printStackTrace();
  }
		 
	}
@FXML
public void open() {
	
try {
	Desktop.getDesktop().open(new File("C:\\Users\\hp\\eclipse-workspace\\Sales_Visualisation\\Mynotes\\Notes.txt"));
} catch (IOException e) {
	e.printStackTrace();
}
}
@FXML
private void printTextArea() {
        PrinterJob printerJob = PrinterJob.createPrinterJob();
        if (printerJob != null && printerJob.showPrintDialog(txt.getScene().getWindow())) {
            TextFlow textFlow = new TextFlow(new Text(txt.getText()));
            printerJob.printPage(textFlow);
            printerJob.endJob();
        }
    }

}

