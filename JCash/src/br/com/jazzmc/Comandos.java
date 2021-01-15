package br.com.jazzmc;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Comandos extends Metodos implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
		
		if (cmd.getName().equalsIgnoreCase("cash")) {
			if(args.length == 0) {
				if(sender instanceof Player) {
					Player p = (Player)sender;
					p.sendMessage(prefix + "§aSeu saldo: §b" + get(p.getName()));
				}else {
					sender.sendMessage("§cComando apena para jogadores.");
				}
			}
			if (args.length >= 1) {
				if(args[0].equalsIgnoreCase("set")) {
					if(args.length < 3) {
						sender.sendMessage("§cUse: §f/cash set §7<player, quantia>");
						return true;
					}
					
					String target = String.valueOf(args[1]);
					Double quantia;
					
					try {
						quantia = Double.parseDouble(args[2]);
					} catch (NumberFormatException e) {
						sender.sendMessage(prefix + "§cutilize um valor válido.");
						return true;
					}
					set(target, quantia);
					sender.sendMessage(prefix + "§aforam setados §f" + quantia + " §ade cash para o jogador §f" + target); 
					
				}
				
				if(args[0].equalsIgnoreCase("add")) {
					if(args.length < 3) {
						sender.sendMessage("§cUse: §f/cash add §7<player, quantia>");
						return true;
					}
					
					String target = String.valueOf(args[1]);
					Double quantia;
					
					try {
						quantia = Double.parseDouble(args[2]);
					} catch (NumberFormatException e) {
						sender.sendMessage(prefix + "§cutilize um valor válido.");
						return true;
					}
					add(target, quantia);
					sender.sendMessage(prefix + "§avocê adiciounou §f" + quantia + " §ade cash para o jogador §f" + target); 
				}			
				if(args[0].equalsIgnoreCase("remove")) {
					if(args.length < 3) {
						sender.sendMessage("§cUse: §f/cash remove §7<player, quantia>");
						return true;
					}
					
					String target = String.valueOf(args[1]);
					Double quantia;
					
					try {
						quantia = Double.parseDouble(args[2]);
					} catch (NumberFormatException e) {
						sender.sendMessage(prefix + "§cutilize um valor válido.");
						return true;
					}
					remove(target, quantia);
					sender.sendMessage(prefix + "§aforam removidos §f" + quantia + " §ade cash para o jogador §f" + target); 
					
				}				
			}
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		return false;
	}

}
