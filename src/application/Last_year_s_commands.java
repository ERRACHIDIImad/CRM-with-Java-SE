package application;

import java.sql.Date;

public class Last_year_s_commands{
private int id_com;
private String CODECLIENT;
public String getCODECLIENT() {
	return CODECLIENT;
}
public void setCODECLIENT(String cODECLIENT) {
	CODECLIENT = cODECLIENT;
}

private Date date_comm;
 public int getId_com() {
	return id_com;
}
public void setId_com(int id_com) {
	this.id_com = id_com;
}
public Date getDate_comm() {
	return date_comm;
}
public void setDate_comm(Date date_comm) {
	this.date_comm = date_comm;
} 
public Last_year_s_commands(String CODECLIENT,int id_com,Date date_comm) {
	 this.date_comm=date_comm;
	 this.id_com=id_com	 ;
	 this.CODECLIENT=CODECLIENT;
	 
	 
}
	 
	 public String toString() {
		 return  id_com+" "+CODECLIENT+" "+date_comm ;
	  }
}
