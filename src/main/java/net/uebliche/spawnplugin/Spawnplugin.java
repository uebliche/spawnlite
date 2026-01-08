package net.uebliche.spawnplugin;

/**
 * this is just for the luls
 */
public final class Spawnplugin extends org.bukkit.plugin.java.JavaPlugin {
    private static boolean isFolia() {
        try {
            Class.forName("io.papermc.paper.threadedregions.RegionizedServer");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
    @Override
    public void onEnable() {
        org.bukkit.Bukkit.getCommandMap().register("spawn", new org.bukkit.command.Command("spawn") {
            @Override
            public boolean execute(@org.jetbrains.annotations.NotNull org.bukkit.command.CommandSender sender, @org.jetbrains.annotations.NotNull String commandLabel, @org.jetbrains.annotations.NotNull String[] args) {
                if(sender instanceof org.bukkit.entity.Player player && isFolia()) player.teleportAsync(player.getWorld().getSpawnLocation());
                if(sender instanceof org.bukkit.entity.Player player && !isFolia())player.teleport(player.getWorld().getSpawnLocation());
                return true;
            }
        });
        org.bukkit.Bukkit.getCommandMap().register("setspawn", new org.bukkit.command.Command("setspawn") {
            @Override
            public boolean execute(@org.jetbrains.annotations.NotNull org.bukkit.command.CommandSender sender, @org.jetbrains.annotations.NotNull String commandLabel, @org.jetbrains.annotations.NotNull String[] args) {
                if(sender instanceof org.bukkit.entity.Player player){
                    player.getWorld().setSpawnLocation(player.getLocation());
                    player.sendMessage("Spawn set!");
                    return true;
                }
                return false;
            }
        });
        org.bukkit.Bukkit.getCommandMap().getCommand("setspawn").setPermission("minecraft.command.setworldspawn");
    }
}
