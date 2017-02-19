
package com.exclusively.web.common;


import java.sql.*;
import javax.sql.*;

public class DBSample{

public static void main(String args[]){
String dbtime;
String dbUrl = "jdbc:mysql://54.87.66.84/exin_community"; 
String username="eqclisve";
String password="G00Dw0rd$5";
String dbClass = "com.mysql.jdbc.Driver";
String query = "show tables;";

try {

Class.forName(dbClass);
Connection con = DriverManager.getConnection (dbUrl,username,password);
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(query);

while (rs.next()) {
dbtime = rs.getString(1);
System.out.println(dbtime);
} //end while

con.close();
} //end try

catch(ClassNotFoundException e) {
e.printStackTrace();
}

catch(SQLException e) {
e.printStackTrace();
}

}  //end main

}  //end class