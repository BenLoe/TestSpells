package me.BenLoe.TestSpells;

import me.BenLoe.TestSpells.Spells.LightOrb;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{

	@SuppressWarnings("deprecation")
	public void onEnable(){
		Bukkit.getPluginManager().registerEvents(new Events(this), this);
		Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, new Runnable(){
			public void run(){
				LightOrb.check();
			}
		}, 0l, 1l);
	}
}
