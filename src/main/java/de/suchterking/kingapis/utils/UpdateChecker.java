package de.suchterking.kingapis.utils;

import de.suchterking.kingapis.KingApis;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;

public class UpdateChecker {
    private KingApis plugin;
    private int resourceId;

    public UpdateChecker(KingApis plugin, int resourceId) {
        this.plugin = plugin;
        this.resourceId = resourceId;
    }

    public void getLatestVersion(Consumer<String> consumer) {
        Bukkit.getScheduler().runTaskAsynchronously(this.plugin, () -> {
            try (InputStream inputStream = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + this.resourceId).openStream();
                 Scanner scanner = new Scanner(inputStream)) {
                if (scanner.hasNext()) {
                    consumer.accept(scanner.next());
                }
            }catch (IOException e) {
                plugin.getLogger().warning(e.getMessage());
            }
        });
    }

    public void checkUpdate(KingApis pl, int id) {
        new UpdateChecker(pl, id).getLatestVersion(version -> {
            if (pl.getDescription().getVersion().equalsIgnoreCase(version)) {
                pl.getLogger().info("Du hast die neuste Plugin version");
            }else {
                pl.getLogger().warning("Es gibt ein Update");
            }
        });
    }
}
