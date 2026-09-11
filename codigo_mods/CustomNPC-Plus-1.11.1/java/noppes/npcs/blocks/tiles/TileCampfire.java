/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 */
package noppes.npcs.blocks.tiles;

import java.util.Random;
import net.minecraft.block.Block;
import noppes.npcs.CustomItems;
import noppes.npcs.CustomNpcs;
import noppes.npcs.blocks.tiles.TileVariant;

public class TileCampfire
extends TileVariant {
    public static final Random RAND = new Random();
    private boolean rainAndSky = false;
    private boolean previousTickLit = false;

    public void func_145845_h() {
        if (this.func_145831_w().field_72995_K) {
            if (this.isLit()) {
                if (!this.previousTickLit || this.distributedInterval(50L)) {
                    this.rainAndSky = this.isBeingRainedOn();
                }
                this.addParticles();
            }
            this.previousTickLit = this.isLit();
        }
    }

    public boolean distributedInterval(long interval) {
        if (this.func_145830_o()) {
            return (this.func_145831_w().func_82737_E() + (long)(this.field_145851_c + this.field_145848_d + this.field_145849_e)) % interval == 0L;
        }
        return false;
    }

    protected void addParticles() {
        CustomNpcs.proxy.generateBigSmokeParticles(this.func_145831_w(), this.field_145851_c, this.field_145848_d, this.field_145849_e, false);
        if (this.rainAndSky) {
            for (int i = 0; i < RAND.nextInt(3); ++i) {
                this.func_145831_w().func_72869_a("smoke", (double)this.field_145851_c + RAND.nextDouble(), (double)this.field_145848_d + 0.9, (double)this.field_145849_e + RAND.nextDouble(), 0.0, 0.0, 0.0);
            }
        }
    }

    public boolean isBeingRainedOn() {
        return this.func_145831_w().func_72896_J() && this.func_145831_w().func_72874_g(this.field_145851_c, this.field_145849_e) <= this.field_145848_d + 1 && this.func_145831_w().func_72807_a(this.field_145851_c, this.field_145849_e).func_76738_d();
    }

    public boolean isLit() {
        Block block = this.func_145838_q();
        return block == CustomItems.campfire;
    }
}

