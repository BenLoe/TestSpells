package me.BenLoe.TestSpells.Spells;

import java.util.ArrayList;
import java.util.List;

import me.BenLoe.TestSpells.ParticleEffect;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class LightOrb {

	public static List<List<Location>> orbs = new ArrayList<List<Location>>();
	
	public static void execute(Player p){
		List<Location> locs = new ArrayList<Location>();
		for (int i = 1 ; i < 100; i++){
			Location loc = p.getLocation().add(0, 1.7, 0).toVector().add(p.getLocation().add(0, 1.7, 0).getDirection().multiply(i)).toLocation(p.getWorld());
			Location locnext = p.getLocation().add(0, 1.7, 0).toVector().add(p.getLocation().add(0, 1.7, 0).getDirection().multiply(i + 1)).toLocation(p.getWorld());
			locs.add(loc);
			if (locnext.getBlock().getType() != Material.AIR){
				break;
			}
		}
		orbs.add(locs);
	}
	
	public static void check(){
		if (!orbs.isEmpty()){
		List<List<Location>> orbsutil = new ArrayList<List<Location>>();
		for (List<Location> locs : orbs){
			List<Location> locsutil = locs;
			if (locs.size() == 0){
				locsutil.remove(orbsutil.add(locsutil));
				break;
			}
			Location loc = locs.get(0);
			if (locs.size() == 1){
				ParticleEffect.EXPLOSION_LARGE.display(0.3f, 0.3f, 0.3f, 1.1f, 5, loc, 100);
				ParticleEffect.EXPLOSION_NORMAL.display(0.5f, 0.5f, 0.5f, 0.8f, 20, loc, 100);
				ParticleEffect.FIREWORKS_SPARK.display(0.5f, 0.5f, 0.5f, 0.5f, 50, loc, 100);
				for (Player p : Bukkit.getOnlinePlayers()){
					p.playSound(loc, Sound.EXPLODE, 1.5f, 1f);
				}
			}else{
				Location loc1 = locs.get(1);
				locsutil.remove(loc);
				if (locs.size() > 2){
				locsutil.remove(loc1);
				}
				orbsutil.add(locsutil);
				ParticleEffect.BLOCK_CRACK.display(new ParticleEffect.BlockData(Material.SNOW_BLOCK, (byte)0), 0.3f, 0.3f, 0.3f, 0.3f, 10, loc, 100);
				ParticleEffect.EXPLOSION_NORMAL.display(0.001f, 0.001f, 0.001f, 0.001f, 3, loc, 100);
			}
		}
		orbs.clear();
		orbs.addAll(orbsutil);
	}
	}
}
