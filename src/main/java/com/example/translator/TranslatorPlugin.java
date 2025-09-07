package com.example.translator;

import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TranslatorPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("AI Translator Plugin aktif!");

        File modsFolder = new File("mods");
        if (modsFolder.exists() && modsFolder.isDirectory()) {
            getLogger().info("Scan folder mods...");
            // TODO: Baca tiap .jar (Forge/Fabric)
            generateDummyAddon();
        } else {
            getLogger().warning("Folder mods/ tidak ditemukan!");
        }
    }

    private void generateDummyAddon() {
        try {
            File outFolder = new File(getDataFolder(), "bedrock_addons");
            if (!outFolder.exists()) outFolder.mkdirs();

            File itemJson = new File(outFolder, "ruby_sword.json");
            FileWriter writer = new FileWriter(itemJson);

            String json = "{\\n" +
                    "  \\\"format_version\\\": \\\"1.16.100\\\",\\n" +
                    "  \\\"minecraft:item\\\": {\\n" +
                    "    \\\"description\\\": {\\n" +
                    "      \\\"identifier\\\": \\\"mod:ruby_sword\\\",\\n" +
                    "      \\\"category\\\": \\\"Equipment\\\"\\n" +
                    "    },\\n" +
                    "    \\\"components\\\": {\\n" +
                    "      \\\"minecraft:damage\\\": 7,\\n" +
                    "      \\\"minecraft:hand_equipped\\\": true,\\n" +
                    "      \\\"minecraft:durability\\\": { \\\"max_durability\\\": 1561 }\\n" +
                    "    }\\n" +
                    "  }\\n" +
                    "}";
            writer.write(json);
            writer.close();
            getLogger().info("Dummy addon ruby_sword.json berhasil dibuat!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
