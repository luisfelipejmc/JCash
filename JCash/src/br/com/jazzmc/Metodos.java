package br.com.jazzmc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Metodos extends Conexao{
	
	public static String prefix = Main.getPlugin(Main.class).configuracao.getConfig().getString("Prefix").replaceAll("&", "§");
	
	public static boolean contains(String player) {
		
		PreparedStatement stm = null;
		
		try {
			stm = con.prepareStatement("SELECT * FROM `cash` WHERE `player` = ?");
			stm.setString(1, player.toString());
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			return false;
		}
		
	}
	
	public static void setPlayer(String player) {
		PreparedStatement stm = null;
		try {
			stm = con.prepareStatement("INSERT INTO `cash`(`player`,`quantia`) VALUES (?,?)");
			stm.setString(1, player.toString());
			stm.setDouble(2, 0);
			stm.executeUpdate();
			Bukkit.getConsoleSender().sendMessage(prefix + "§aO jogador §f" + player + "§a foi adicionado a tabela `cash`!");
		} catch (Exception e) {
			Bukkit.getConsoleSender().sendMessage(prefix + "§cNão foi possível adicionar jogador §f" + player + "§c na tabela `cash`!");
		}
		
	}
	
	public static void set(String player, Double quantia) {
		if (contains(player)) {
			PreparedStatement stm = null;
			try {
				stm = con.prepareStatement("UPDATE `cash` SET `quantia` = ? WHERE `player` = ?");
				stm.setDouble(1, quantia);
				stm.setString(2, player.toString());
				stm.executeUpdate();
			} catch (SQLException e) {
				sc.sendMessage(prefix + "§cnão foi possível setar o cash do jogador!");
			}
			
		}else {
			setPlayer(player);
		}
		
	}

	public static Double get(String player) {
		if (contains(player)) {
			PreparedStatement stm = null;
			try {
				stm = con.prepareStatement("SELECT * FROM `cash` WHERE `player` = ?");
				stm.setString(1, player.toString());
				ResultSet rs = stm.executeQuery();
				while (rs.next()) {
					return rs.getDouble("quantia");
				}
				return 0.0;
			} catch (SQLException e) {
				return 0.0;
			}
		}else {
			setPlayer(player);
			return 0.0;
		}
	}
	
	public static void add(String player, Double quantia) {
		if (contains(player)) {
			set(player, get(player) + quantia);
			
		}else {
			setPlayer(player);
		}
	}
	
	public static void remove(String player, Double quantia) {
		if (contains(player)) {
			set(player, get(player) - quantia);
			
		}else {
			setPlayer(player);
		}
	}
	
	public static void delete(String player) {
		if (contains(player)) {
			
			PreparedStatement stm = null;
			
			try {
				stm = con.prepareStatement("DELETE FROM `cash` WHERE `player` = ?");
				stm.setString(1, player.toString());
				stm.executeUpdate();
			} catch (SQLException e) {
				sc.sendMessage(prefix + "§cnão foi possível remover o jogador §f" + player + "§c do banco de dados!");
			}
			
		}
	}
	
}
