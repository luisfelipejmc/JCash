package br.com.jazzmc;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Evento implements Listener {
	
	@EventHandler
	void evento(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		
		if(!Metodos.contains(p.getName())) {
			Metodos.setPlayer(p.getName());
		}
		
	}

}
