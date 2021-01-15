package br.com.jazzmc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.YamlConfiguration;

public class Conexao {
	
	public static Connection con = null;
	public static ConsoleCommandSender sc = Bukkit.getConsoleSender();
	
	protected static void open() {
		YamlConfiguration main = Main.getPlugin(Main.class).configuracao.getConfig();
		String user = main.getString("MySQL.user");
		String password = main.getString("MySQL.password");
		String database = main.getString("MySQL.database");
		String url = "jdbc:mysql://localhost:3306/" + database;
		
		try {
			con = DriverManager.getConnection(url, user, password);
			sc.sendMessage(Metodos.prefix + "§aConexão com MySQL aberta!");
		} catch (SQLException e) {
			sc.sendMessage(Metodos.prefix + "§cConexão com MySQL falhou!");
			Main.getPlugin(Main.class).getPluginLoader().disablePlugin(Main.getPlugin(Main.class));
			
		}
		
	}
	
	protected static void close() {
		if (con != null) {
			try {
				con.close();
				sc.sendMessage(Metodos.prefix + "§aConexão com MySQL foi fechada!");
			} catch (SQLException e) {
				sc.sendMessage(Metodos.prefix + "§cNão foi possivel fechar a conexão com o MySQL!");
			}
		}
	}
	
	protected static void createTable() {
		if (con != null) {
			
			PreparedStatement stm = null;
			
			try {
				stm = con.prepareStatement("CREATE TABLE IF NOT EXIST `cash`(`player`, VARCHAR(24), `quantia DOUBLE()`)");
				stm.executeUpdate();
				sc.sendMessage(Metodos.prefix + "§aTabela carregada com sucesso!");
			} catch (SQLException e) {
				sc.sendMessage(Metodos.prefix + "§cNão foi possivel carregar a tabela!");
			}
			
		}
	}

}
