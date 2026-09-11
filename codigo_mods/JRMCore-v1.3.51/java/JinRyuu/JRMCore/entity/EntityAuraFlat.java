/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 */
package JinRyuu.JRMCore.entity;

import JinRyuu.JRMCore.JRMCoreGuiScreen;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.manager.AttributeArray;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

@SideOnly(value=Side.CLIENT)
public class EntityAuraFlat
extends EntityLiving {
    private final float LIFE = 0.5f;
    public AttributeArray array;
    public AttributeArray transparency;
    private Instant spawnTime;
    private float age;
    private boolean directionX;
    private boolean lrX;
    private boolean lrZ;
    private float startY;
    public Color aura;
    private byte auraID;
    private ResourceLocation texture;

    public EntityAuraFlat(World w) {
        super(w);
        Random r = new Random();
        float ARRAY_SPEED = 2.0f;
        this.transparency = new AttributeArray(0.0f, 0.0f, 0.125f, 0.7f, 0.25f, 0.0f);
        Color base = new Color(191, 215, 255);
        Color kk = new Color(235, 65, 35);
        Color ssj = new Color(255, 237, 117);
        Color god = new Color(250, 36, 25);
        Color blue = new Color(25, 205, 250);
        float combo = JRMCoreGuiScreen.instance.miniGameAirBoxing.comboCounter;
        this.aura = combo >= 200.0f ? blue : (combo >= 150.0f ? god : (combo >= 100.0f ? ssj : (combo >= 50.0f ? kk : base)));
        this.auraID = (byte)(combo >= 150.0f ? 4 : (byte)(r.nextInt(3) + 1));
        this.texture = new ResourceLocation(JRMCoreH.tjjrmc + ":textures/auras/auraflat" + this.auraID + ".png");
        float auraSpeed = combo >= 150.0f ? 0.35f : 1.0f;
        this.array = new AttributeArray(0.0f, 0.0f, 0.125f, 1.0f * auraSpeed, 0.25f, 2.0f * auraSpeed);
        this.spawnTime = Instant.now();
        this.age = 0.0f;
        boolean xz = r.nextBoolean();
        this.lrX = r.nextBoolean();
        this.lrZ = r.nextBoolean();
        float value = ((float)r.nextInt(1000) / 100.0f - 5.0f) / 4.0f;
        float limit = 1.25f;
        this.field_70165_t = (xz ? value : 1.25f) * (float)(this.lrX ? -1 : 1);
        this.startY = -((float)r.nextInt(1000) / 100.0f - 5.0f) / 3.0f + 2.0f;
        this.field_70161_v = (!xz ? value : 1.25f) * (float)(this.lrZ ? -1 : 1);
        this.directionX = xz;
    }

    public void update() {
        this.age += (float)Duration.between(this.spawnTime, Instant.now()).toMillis() / 1000.0f;
        this.array.update(this.age);
        this.transparency.update(this.age);
        this.spawnTime = Instant.now();
        if (this.age >= 0.25f) {
            this.func_70106_y();
        }
        this.field_70163_u = this.startY - this.array.lastValue;
        if (!this.directionX) {
            this.field_70165_t = this.array.lastValue * (float)(this.lrX ? -1 : 1);
        } else {
            this.field_70161_v = this.array.lastValue * (float)(this.lrZ ? -1 : 1);
        }
    }

    public ResourceLocation getTexture() {
        return this.texture;
    }
}

