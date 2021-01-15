package br.com.jazzmc;

import org.bukkit.plugin.java.JavaPlugin;

import api.Configs;


public class Main extends JavaPlugin {
	
	Configs configuracao = new Configs("configuracao.yml");
	
	
	@Override
	public void onEnable() {
		configuracao.saveDefaultConfig();
		Conexao.open();
		
	}
	
	@Override
	public void onDisable() {
		Conexao.close();
	}
	
	public static Main getPlugin() {
		return getPlugin(Main.class);
	}

}
