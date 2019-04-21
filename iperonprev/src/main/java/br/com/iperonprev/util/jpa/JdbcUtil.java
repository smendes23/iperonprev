package br.com.iperonprev.util.jpa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class JdbcUtil {
	

	private static JdbcUtil conexao;
	public static JdbcUtil getInstance() {
	if (conexao == null) {
		conexao = new JdbcUtil();
	}
	return conexao;
}
	public Connection getConnection() throws ClassNotFoundException, SQLException {
//		Class.forName("com.mysql.jdbc.Driver");
//		return DriverManager.getConnection("jdbc:mysql://localhost:3306/iperonprev?allowMultiQueries=true","root","123");
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		/*Produção*/
		return DriverManager.getConnection("jdbc:sqlserver://192.168.130.30:1433;"+"databaseName=DBIPERON;user=sa;password=1p3r0n.2015;");
		
		/*Base Teste*/
//		return DriverManager.getConnection("jdbc:sqlserver://172.16.31.135:1433;"+"databaseName=DBIPERON-IMPORTACAO;user=sa;password=1p3r0n@2019;");
				
	}
}
