package me.BenLoe.TestSpells.Spells;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import me.BenLoe.TestSpells.Main;
import me.BenLoe.TestSpells.ParticleEffect;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class DefenseSphere {

	@SuppressWarnings("deprecation")
	public static void execute(final Player p){
		final List<Location> blocks = new ArrayList<Location>();
		int radius = 4;
		int bX = p.getLocation().getBlockX();
		int bY = p.getLocation().getBlockY();
		int bZ = p.getLocation().getBlockZ();
		for (int x = bX - radius; x <= bX + radius; x++){
			for (int y = bY - radius; y <= bY + radius; y++){
				for (int z = bZ - radius; z <= bZ + radius; z++){
					double distance = ((bX-x)*(bX-x) + ((bZ-z)*(bZ-z)) + ((bY-y)*(bY-y)));
					if (distance < radius*radius && !(distance < ((radius - 1) * (radius - 1)))){
						Location loc = new Location(p.getWorld(), x, y, z);
						if (loc.getBlock().getType() == Material.AIR){
							blocks.add(loc);
						}
					}
				}
			}
		}
		List<Location> up1 = new ArrayList<Location>();
		final List<Location> up2 = new ArrayList<Location>();
		final List<Location> up3 = new ArrayList<Location>();
		final List<Location> up4 = new ArrayList<Location>();
		for (Location loc : blocks){
			if (loc.getBlock().getType() == Material.AIR){
				Random r = new Random();
				int i = r.nextInt(4) + 1;
				switch(i){
				case 1: up1.add(loc);
				break;
				case 2: up3.add(loc);
				break;
				case 3: up2.add(loc);
				break;
				case 4: up4.add(loc);
				}
			}
		}
		final HashMap<Location,Material> Materials1 = new HashMap<Location,Material>();
		final List<FallingBlock> falls = new ArrayList<FallingBlock>();
		for (Location loc : up1){
			for (int i = 1; i < 10; i++){
				Location locu = loc.clone().subtract(0, i, 0);
				if (locu.getBlock().getType() != Material.AIR){
					Materials1.put(loc, locu.getBlock().getType());
					FallingBlock f = locu.getWorld().spawnFallingBlock(locu.clone().add(0, 1, 0), locu.getBlock().getType(), (byte)0);
					f.setVelocity(new Vector(0, loc.distance(locu) * 0.145, 0));
					f.setFallDistance(1000f);
					f.setDropItem(false);
					falls.add(f);
					break;
				}
			}
		}
		Bukkit.getScheduler().runTaskLater(Main.getPlugin(Main.class), new Runnable(){
			public void run(){
				for (FallingBlock f : falls){
					f.teleport(f.getLocation().subtract(0, 500, 0));
				}
				falls.clear();
				for (Location loc : Materials1.keySet()){
					Material m = Materials1.get(loc);
					loc.getBlock().setType(m);
					ParticleEffect.BLOCK_CRACK.display(new ParticleEffect.BlockData(m, (byte)0), 0.3f, 0.3f, 0.3f, 0.3f, 40, loc, 20);
				}
				for (Player p1 : Bukkit.getOnlinePlayers()){
					p1.playSound(p.getLocation(), Sound.DIG_STONE, 1f, 1f);
				}
				Materials1.clear();
				for (Location loc : up2){
					for (int i = 1; i < 10; i++){
						Location locu = loc.clone().subtract(0, i, 0);
						if (locu.getBlock().getType() != Material.AIR){
							Materials1.put(loc, locu.getBlock().getType());
							FallingBlock f = locu.getWorld().spawnFallingBlock(locu.clone().add(0, 1, 0), locu.getBlock().getType(), (byte)0);
							f.setVelocity(new Vector(0, loc.distance(locu) * 0.145, 0));
							f.setFallDistance(1000f);
							f.setDropItem(false);
							falls.add(f);
							break;
						}
					}
				}
				Bukkit.getScheduler().runTaskLater(Main.getPlugin(Main.class), new Runnable(){
					public void run(){
						for (FallingBlock f : falls){
							f.teleport(f.getLocation().subtract(0, 500, 0));
						}
						falls.clear();
						for (Location loc : Materials1.keySet()){
							Material m = Materials1.get(loc);
							loc.getBlock().setType(m);
							ParticleEffect.BLOCK_CRACK.display(new ParticleEffect.BlockData(m, (byte)0), 0.3f, 0.3f, 0.3f, 0.3f, 40, loc, 20);
						}
						for (Player p1 : Bukkit.getOnlinePlayers()){
							p1.playSound(p.getLocation(), Sound.DIG_STONE, 1f, 1f);
						}
						Materials1.clear();
						for (Location loc : up3){
							for (int i = 1; i < 10; i++){
								Location locu = loc.clone().subtract(0, i, 0);
								if (locu.getBlock().getType() != Material.AIR){
									Materials1.put(loc, locu.getBlock().getType());
									FallingBlock f = locu.getWorld().spawnFallingBlock(locu.clone().add(0, 1, 0), locu.getBlock().getType(), (byte)0);
									f.setVelocity(new Vector(0, loc.distance(locu) * 0.145, 0));
									f.setFallDistance(1000f);
									f.setDropItem(false);
									falls.add(f);
									break;
								}
							}
						}
						Bukkit.getScheduler().runTaskLater(Main.getPlugin(Main.class), new Runnable(){
							public void run(){
								for (FallingBlock f : falls){
									f.teleport(f.getLocation().subtract(0, 500, 0));
								}
								falls.clear();
								for (Location loc : Materials1.keySet()){
									Material m = Materials1.get(loc);
									loc.getBlock().setType(m);
									ParticleEffect.BLOCK_CRACK.display(new ParticleEffect.BlockData(m, (byte)0), 0.3f, 0.3f, 0.3f, 0.3f, 40, loc, 20);
								}
								for (Player p1 : Bukkit.getOnlinePlayers()){
									p1.playSound(p.getLocation(), Sound.DIG_STONE, 1f, 1f);
								}
								Materials1.clear();
								for (Location loc : up4){
									for (int i = 1; i < 10; i++){
										Location locu = loc.clone().subtract(0, i, 0);
										if (locu.getBlock().getType() != Material.AIR){
											Materials1.put(loc, locu.getBlock().getType());
											FallingBlock f = locu.getWorld().spawnFallingBlock(locu.clone().add(0, 1, 0), locu.getBlock().getType(), (byte)0);
											f.setVelocity(new Vector(0, loc.distance(locu) * 0.145, 0));
											f.setFallDistance(1000f);
											f.setDropItem(false);
											falls.add(f);
											break;
										}
									}
								}
								Bukkit.getScheduler().runTaskLater(Main.getPlugin(Main.class), new Runnable(){
									public void run(){
										for (FallingBlock f : falls){
											f.teleport(f.getLocation().subtract(0, 500, 0));
										}
										falls.clear();
										for (Location loc : Materials1.keySet()){
											Material m = Materials1.get(loc);
											loc.getBlock().setType(m);
											ParticleEffect.BLOCK_CRACK.display(new ParticleEffect.BlockData(m, (byte)0), 0.3f, 0.3f, 0.3f, 0.3f, 40, loc, 20);
										}
										for (Player p1 : Bukkit.getOnlinePlayers()){
											p1.playSound(p.getLocation(), Sound.DIG_STONE, 1f, 1f);
										}
										Bukkit.getScheduler().runTaskLater(Main.getPlugin(Main.class), new Runnable(){
											public void run(){
												for (Location loc : blocks){
													ParticleEffect.BLOCK_CRACK.display(new ParticleEffect.BlockData(loc.getBlock().getType(), (byte)0), 0.2f, 0.2f, 0.2f, 0.3f, 14, loc, 20);
													loc.getBlock().setType(Material.AIR);
												}
											}
										}, 5 * 20l);
									}
								}, 10l);
							}
						}, 10l);
					}
				}, 10l);
			}
		}, 10l);
		}
}
