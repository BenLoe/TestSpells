package me.BenLoe.TestSpells.Spells;

import java.util.ArrayList;
import java.util.List;

import me.BenLoe.TestSpells.Main;
import me.BenLoe.TestSpells.ParticleEffect;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.util.Vector;

public class SeismicWave {
	
	@SuppressWarnings("deprecation")
	public static void execute(final Player p){
		p.setVelocity(new Vector(0, 0, 0));
		Location ploc = p.getLocation().clone();
		Vector vector1 = getDirection(ploc, 0, 90);
		Vector vector2 = getDirection(ploc, 180, 90);
		final Location loc1 = ploc.toVector().add(vector1.multiply(0.4)).toLocation(p.getWorld()).add(0, 1.8, 0);
		Location loc2 = ploc.toVector().add(vector2.multiply(0.4)).toLocation(p.getWorld()).add(0, 1.8, 0);
	    Location locu1 = ploc.toVector().add(vector1.multiply(0.2)).toLocation(p.getWorld()).add(0, 1.8, 0);
		Location locu2 = ploc.toVector().add(vector2.multiply(0.2)).toLocation(p.getWorld()).add(0, 1.8, 0);
		List<Location> util = new ArrayList<Location>();
		for (int i = 1; i < 6; i++){
			Location loc3 = loc1.toVector().add(getDirection(ploc, 90, 90).multiply(i)).toLocation(p.getWorld());
			Location loc4 = loc2.toVector().add(getDirection(ploc, 90, 90).multiply(i)).toLocation(p.getWorld());
			Location loc5 = ploc.toVector().add(getDirection(ploc, 90, 90).multiply(i)).toLocation(p.getWorld()).add(0, 1.8, 0);
			Location loc6 = locu1.toVector().add(getDirection(ploc, 90, 90).multiply(i)).toLocation(p.getWorld());
			Location loc7 = locu2.toVector().add(getDirection(ploc, 90, 90).multiply(i)).toLocation(p.getWorld());
			util.add(loc3);
			util.add(loc4);
			util.add(loc5);
			util.add(loc6);
			util.add(loc7);
		}
		List<Location> util2 = new ArrayList<Location>();
		for (Location locutil : util){
			for (int i = 0; i < 7; i++){
				Location utilloc = locutil;
				utilloc.setY(p.getLocation().getY() + 1.8);
				utilloc.subtract(0, i, 0);
				if (utilloc.getBlock().getType() != Material.AIR && !util2.contains(utilloc) && utilloc.clone().add(0, 1, 0).getBlock().getType() == Material.AIR){
					util2.add(utilloc);
					break;
				}
			}
		}
		List<Location> ses1 = new ArrayList<Location>();
		final List<Location> ses2 = new ArrayList<Location>();
		final List<Location> ses3 = new ArrayList<Location>();
		for (Location loc : util2){
			double distance = loc.distance(p.getLocation());
			if (distance < 3){
				ses1.add(loc);
			}else if (distance < 6){
				ses2.add(loc);
			}else if (distance < 100){
				ses3.add(loc);
			}
		}
		final List<FallingBlock> falls = new ArrayList<FallingBlock>();
		for (Player p1 : Bukkit.getOnlinePlayers()){
			p1.playSound(loc1, Sound.DIG_STONE, 1f, 1f);
		}
		final Vector throwback = p.getLocation().getDirection().add(new Vector(0, 0.8, 0)).multiply(1.2);
		for (Location loc : ses1){
			FallingBlock b = p.getWorld().spawnFallingBlock(loc.clone().add(0, 0.2, 0), loc.getBlock().getType(), loc.getBlock().getData());
			b.setVelocity(new Vector(0, 0.18, 0));
			b.setFallDistance(1000f);
			falls.add(b);
			ParticleEffect.BLOCK_CRACK.display(new ParticleEffect.BlockData(loc.getBlock().getType(), loc.getBlock().getData()), 0.3f, 0.3f, 0.3f, 0.2f, 40, loc.add(0, 1.4, 0), 20);
			for (Entity e : b.getNearbyEntities(1.2, 1.2, 1.2)){
				if (e instanceof Zombie){
					((Zombie) e).damage(0);
					e.setVelocity(throwback);
				}
			}
		}
		Bukkit.getScheduler().runTaskLater(Main.getPlugin(Main.class), new Runnable(){
			public void run(){
				for (FallingBlock f : falls){
					f.teleport(f.getLocation().subtract(0, 500, 0));
				}
				falls.clear();
				for (Player p1 : Bukkit.getOnlinePlayers()){
					p1.playSound(loc1, Sound.DIG_STONE, 1f, 1f);
				}
				for (Location loc : ses2){
					FallingBlock b = p.getWorld().spawnFallingBlock(loc.clone().add(0, 0.2, 0), loc.getBlock().getType(), loc.getBlock().getData());
					b.setVelocity(new Vector(0, 0.18, 0));
					b.setFallDistance(1000f);
					falls.add(b);
					ParticleEffect.BLOCK_CRACK.display(new ParticleEffect.BlockData(loc.getBlock().getType(), loc.getBlock().getData()), 0.3f, 0.3f, 0.3f, 0.2f, 40, loc.add(0, 1.4, 0), 20);
					for (Entity e : b.getNearbyEntities(1.2, 1.2, 1.2)){
						if (e instanceof Zombie){
							((Zombie) e).damage(0);
							e.setVelocity(throwback);
						}
					}
				}
				Bukkit.getScheduler().runTaskLater(Main.getPlugin(Main.class), new Runnable(){
					public void run(){
						for (FallingBlock f : falls){
							f.teleport(f.getLocation().subtract(0, 500, 0));
						}
						for (Player p1 : Bukkit.getOnlinePlayers()){
							p1.playSound(loc1, Sound.DIG_STONE, 1f, 1f);
						}
						falls.clear();
						for (Location loc : ses3){
							FallingBlock b = p.getWorld().spawnFallingBlock(loc.clone().add(0, 0.2, 0), loc.getBlock().getType(), loc.getBlock().getData());
							b.setVelocity(new Vector(0, 0.18, 0));
							b.setFallDistance(1000f);
							falls.add(b);
							ParticleEffect.BLOCK_CRACK.display(new ParticleEffect.BlockData(loc.getBlock().getType(), loc.getBlock().getData()), 0.3f, 0.3f, 0.3f, 0.2f, 40, loc.add(0, 1.4, 0), 20);
							for (Entity e : b.getNearbyEntities(1.2, 1.2, 1.2)){
								if (e instanceof Zombie){
									((Zombie) e).damage(0);
									e.setVelocity(throwback);
								}
							}
						}
						Bukkit.getScheduler().runTaskLater(Main.getPlugin(Main.class), new Runnable(){
							public void run(){
								for (FallingBlock f : falls){
									f.teleport(f.getLocation().subtract(0, 500, 0));
								}
								falls.clear();
							}
						}, 3l);
					}
				}, 3l);
			}
		}, 3l);
	}
	
	public static Vector getDirection(Location loc, int yawadd, int pitchadd){
		double pitch = ((0 + pitchadd) * Math.PI) / 180;
		double yaw  = ((loc.getYaw() + yawadd)  * Math.PI) / 180;
		double x = Math.sin(pitch) * Math.cos(yaw);
		double z = Math.sin(pitch) * Math.sin(yaw);
		double y = Math.cos(pitch);
		return new Vector(x, y, z).multiply(1.5);
	}
}
