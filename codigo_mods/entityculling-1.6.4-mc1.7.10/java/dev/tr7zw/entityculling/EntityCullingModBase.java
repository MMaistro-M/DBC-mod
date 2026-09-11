/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.IChatComponent
 */
package dev.tr7zw.entityculling;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.tr7zw.entityculling.Config;
import dev.tr7zw.entityculling.ConfigUpgrader;
import dev.tr7zw.entityculling.CullTask;
import dev.tr7zw.entityculling.EntityCullingMod;
import dev.tr7zw.entityculling.Provider;
import dev.tr7zw.entityculling.shadow.com.logisticscraft.occlusionculling.OcclusionCullingInstance;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public abstract class EntityCullingModBase {
    public static EntityCullingModBase instance = new EntityCullingMod();
    public OcclusionCullingInstance culling;
    public boolean debugHitboxes = false;
    public static boolean enabled = true;
    public CullTask cullTask;
    private Thread cullThread;
    protected KeyBinding keybind = new KeyBinding("key.entityculling.toggle", -1, "EntityCulling");
    protected boolean pressed = false;
    public Config config;
    private final File settingsFile = new File("config", "entityculling.json");
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public int renderedBlockEntities = 0;
    public int skippedBlockEntities = 0;
    public int renderedEntities = 0;
    public int skippedEntities = 0;

    public void onInitialize() {
        instance = this;
        if (this.settingsFile.exists()) {
            try {
                this.config = (Config)this.gson.fromJson(new String(Files.readAllBytes(this.settingsFile.toPath()), StandardCharsets.UTF_8), Config.class);
            }
            catch (Exception ex2) {
                System.out.println("Error while loading config! Creating a new one!");
                ex2.printStackTrace();
            }
        }
        if (this.config == null) {
            this.config = new Config();
            this.writeConfig();
        } else if (ConfigUpgrader.upgradeConfig(this.config)) {
            this.writeConfig();
        }
        this.culling = new OcclusionCullingInstance(this.config.tracingDistance, new Provider());
        this.cullTask = new CullTask(this.culling, this.config.blockEntityWhitelist);
        this.cullThread = new Thread((Runnable)this.cullTask, "CullThread");
        this.cullThread.setUncaughtExceptionHandler((thread, ex) -> {
            System.out.println("The CullingThread has crashed! Please report the following stacktrace!");
            ex.printStackTrace();
        });
        this.cullThread.start();
        this.initModloader();
    }

    public void writeConfig() {
        if (this.settingsFile.exists()) {
            this.settingsFile.delete();
        }
        try {
            Files.write(this.settingsFile.toPath(), this.gson.toJson((Object)this.config).getBytes(StandardCharsets.UTF_8), new OpenOption[0]);
        }
        catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void worldTick() {
        this.cullTask.requestCull = true;
    }

    public void clientTick() {
        if (this.keybind.func_151470_d()) {
            if (this.pressed) {
                return;
            }
            this.pressed = true;
            enabled = !enabled;
            EntityClientPlayerMP player = Minecraft.func_71410_x().field_71439_g;
            if (enabled) {
                if (player != null) {
                    player.func_145747_a((IChatComponent)new ChatComponentText("Culling on"));
                }
            } else if (player != null) {
                player.func_145747_a((IChatComponent)new ChatComponentText("Culling off"));
            }
        } else {
            this.pressed = false;
        }
        this.cullTask.requestCull = true;
    }

    public abstract void initModloader();
}

