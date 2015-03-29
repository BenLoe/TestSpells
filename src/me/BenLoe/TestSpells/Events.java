package me.BenLoe.TestSpells;

import me.BenLoe.TestSpells.Spells.DefenseSphere;
import me.BenLoe.TestSpells.Spells.LightOrb;
import me.BenLoe.TestSpells.Spells.SeismicWave;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class Events implements Listener{

	public static Main plugin;
	public Events(Main instance){
		plugin = instance;
	}
	
	@EventHandler
	public void interact(PlayerInteractEvent event){
		Player p = event.getPlayer();
		if (p.getItemInHand() != null && p.getItemInHand().getType() == Material.IRON_SWORD){
			LightOrb.execute(p);
		}
		if (p.getItemInHand() != null && p.getItemInHand().getType() == Material.DIAMOND_SWORD){
			SeismicWave.execute(p);
		}
		if (p.getItemInHand() != null && p.getItemInHand().getType() == Material.GOLD_SWORD){
			DefenseSphere.execute(p);
		}
	}
}
