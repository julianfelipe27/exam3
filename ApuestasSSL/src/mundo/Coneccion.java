package mundo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Coneccion {

	private Connection con;
	
	public Coneccion () {
		
		System.out.println("-------- Prueba de Registro de Oracle JDBC ------");
		 try {
		 Class.forName("oracle.jdbc.driver.OracleDriver");
		 } catch (ClassNotFoundException e) {
		 System.out.println("¡Oracle JDBC Driver no encontrado!");
		 e.printStackTrace();
		 return;
		 }
		 System.out.println("¡Oracle JDBC Driver Registrado!");
		 
		
	}
	
	public void conectarBase() {
		String usuario = "P09551_1_3";
		String password = "P09551_1_3";
		
		try {
			con = DriverManager.getConnection(
					 "jdbc:oracle:thin:@172.16.0.103:1522:ESTUD",
					 usuario,
					 password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void insertarUsuario(String cedula, String nombre) {
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("INSERT INTO usuario VALUES ('"+cedula+"','"+nombre+"')");
			ResultSet rs2 = stmt.executeQuery("COMMIT"); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Usuario ya registrado");
		} 
	}
	
	
	
	public String verificarLoging(String cedula) {
		String retorno = "";
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT cedula FROM usuario");
			while (rs.next()) {
				 String id = rs.getString("cedula");
				 if(id.equals(cedula)) {
					 
					 
					 ResultSet rs3 = stmt.executeQuery("SELECT a.monto, a.fecha, a.caballo, a.resultado, a.usuario_cedula FROM usuario u, apuesta a WHERE u.cedula = '"+cedula+"' AND u.cedula = a.usuario_cedula");
					 while(rs3.next()) {
						 int monto = rs3.getInt("monto");
						 String fecha = rs3.getString("fecha");
						 String caballo = rs3.getString("caballo");
						 String resultado = rs3.getString("resultado");
						 String id2 = rs3.getString("usuario_cedula");
						 retorno += monto+","+fecha+","+caballo+","+resultado+","+id2;
						
						 
					 }
					 
				 }
			}
			
			return retorno;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null; 
	}
	
	public void insertarDatos(String resultado, String monto, String cedula, String fecha, String caballo) {
		try {
			Statement stmt = con.createStatement();
			ResultSet rs3 = stmt.executeQuery("SELECT cedula FROM usuario WHERE cedula = '"+cedula+"'"); 
			rs3.next();
			if(rs3.getString("cedula").equals(cedula)) {
				ResultSet rs = stmt.executeQuery("INSERT INTO apuesta VALUES ("+monto+",'"+fecha+"','"+caballo+"','"+resultado+"','"+cedula+"')");
				ResultSet rs2 = stmt.executeQuery("COMMIT"); 				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Usuario no registrado");
		} 
	}

}
