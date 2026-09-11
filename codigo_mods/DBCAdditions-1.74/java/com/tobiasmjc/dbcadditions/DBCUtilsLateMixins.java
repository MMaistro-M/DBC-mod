/*
 * Decompiled with CFR 0.152.
 */
package com.tobiasmjc.dbcadditions;

import com.gtnewhorizon.gtnhmixins.ILateMixinLoader;
import com.gtnewhorizon.gtnhmixins.LateMixin;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.spongepowered.asm.mixin.MixinEnvironment;

@LateMixin
public class DBCUtilsLateMixins
implements ILateMixinLoader {
    public static final MixinEnvironment.Side side = MixinEnvironment.getCurrentEnvironment().getSide();

    @Override
    public String getMixinConfig() {
        return "mixins.dbcadditions.late.json";
    }

    @Override
    public List<String> getMixins(Set<String> loadedMods) {
        ArrayList<String> mixins = new ArrayList<String>();
        mixins.add("late.npcs.MixinEntityMasterKaio");
        mixins.add("late.npcs.MixinEntityMasterWhis");
        mixins.add("late.npcs.MixinEntityMasterGohan");
        mixins.add("late.npcs.MixinEntityMasterGoku");
        mixins.add("late.npcs.MixinEntityMasterVegeta");
        mixins.add("late.npcs.MixinEntityMasterTrunks");
        mixins.add("late.npcs.MixinEntityMasterCell");
        mixins.add("late.npcs.MixinEntityMasterBabidi");
        mixins.add("late.npcs.MixinEntityMasterRoshi");
        mixins.add("late.npcs.MixinEntityMasterPiccolo");
        mixins.add("late.npcs.MixinEntityMasterFreeza");
        mixins.add("late.MixinJRMCoreComTickH");
        if (!loadedMods.contains("npcdbc")) {
            mixins.add("late.MixinJGRaceHelper");
            mixins.add("late.MixinJRMCoreMm");
            mixins.add("late.MixinJRMCoreH");
            mixins.add("late.MixinJRMCorePacHanS");
            mixins.add("late.packet.MixinDBCPacketHandler");
            mixins.add("late.MixinDBCKiTech");
            if (side == MixinEnvironment.Side.CLIENT) {
                mixins.add("late.MixinJRMCoreH2");
                mixins.add("late.jbra.MixinModelBipedDBC");
                mixins.add("late.jbra.MixinModelBipedBody");
                mixins.add("late.MixinJRMCoreCliTicH");
                mixins.add("late.gui.MixinJRMCoreGui");
                mixins.add("late.gui.MixinDBCWishGui");
                mixins.add("late.gui.MixinJRMCoreGuiScreen");
                mixins.add("late.MixinJRMCoreHDBC");
                mixins.add("late.jbra.MixinRenderPlayerJBRA");
            }
        }
        return mixins;
    }
}

