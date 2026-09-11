/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ClientRegistry
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.particle.EntityFX
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 */
package JinRyuu.JRMCore;

import JinRyuu.JRMCore.JRMCore;
import JinRyuu.JRMCore.JRMCoreA;
import JinRyuu.JRMCore.JRMCoreCliTicH;
import JinRyuu.JRMCore.JRMCoreDamInd;
import JinRyuu.JRMCore.JRMCoreGui;
import JinRyuu.JRMCore.JRMCoreGuiBars;
import JinRyuu.JRMCore.JRMCoreKeyHandler;
import JinRyuu.JRMCore.JRMCorePacHanC;
import JinRyuu.JRMCore.client.JGRecipeHandler;
import JinRyuu.JRMCore.client.config.jrmc.JGConfigClientSettings;
import JinRyuu.JRMCore.entity.EntitiesJRMC;
import JinRyuu.JRMCore.entity.EntityCusPar;
import JinRyuu.JRMCore.items.GiTurtleMdl;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class JRMCoreClient
extends JRMCore {
    public static Minecraft mc = Minecraft.func_71410_x();
    static JRMCoreGui JFCGui;
    public static JRMCoreGuiBars bars;
    public static JRMCorePacHanC phc;
    @SideOnly(value=Side.CLIENT)
    public static final ModelBiped armor1;
    @SideOnly(value=Side.CLIENT)
    public static final ModelBiped armor2;

    @Override
    public void initialize() {
        super.initialize();
        FMLCommonHandler.instance().bus().register((Object)new JRMCoreCliTicH(mc));
    }

    @Override
    public void postInit() {
        super.postInit();
        JRMCoreA.actionInit();
        JGRecipeHandler.registerRecipes();
    }

    @Override
    public void registerRenderThings() {
        JFCGui = new JRMCoreGui();
        bars = new JRMCoreGuiBars();
        EntitiesJRMC.client();
    }

    @Override
    public void registerKeys() {
        ClientRegistry.registerKeyBinding((KeyBinding)JRMCoreKeyHandler.DS);
        ClientRegistry.registerKeyBinding((KeyBinding)JRMCoreKeyHandler.Fn);
        ClientRegistry.registerKeyBinding((KeyBinding)JRMCoreKeyHandler.actionMenu);
        ClientRegistry.registerKeyBinding((KeyBinding)JRMCoreKeyHandler.KiCharge);
        ClientRegistry.registerKeyBinding((KeyBinding)JRMCoreKeyHandler.KiAscend);
        ClientRegistry.registerKeyBinding((KeyBinding)JRMCoreKeyHandler.KiDescend);
        ClientRegistry.registerKeyBinding((KeyBinding)JRMCoreKeyHandler.KiDash);
        ClientRegistry.registerKeyBinding((KeyBinding)JRMCoreKeyHandler.lockOn);
        ClientRegistry.registerKeyBinding((KeyBinding)JRMCoreKeyHandler.KiFlight);
        ClientRegistry.registerKeyBinding((KeyBinding)JRMCoreKeyHandler.Sagasys);
        ClientRegistry.registerKeyBinding((KeyBinding)JRMCoreKeyHandler.infopanel);
    }

    @Override
    public void registerTicks() {
    }

    @Override
    public EntityPlayer getPlayerEntity(MessageContext ctx) {
        return ctx.side.isClient() ? JRMCoreClient.mc.field_71439_g : super.getPlayerEntity(ctx);
    }

    @Override
    public EntityPlayer getPlayerEntity() {
        return JRMCoreClient.mc.field_71439_g;
    }

    @Override
    public EntityPlayer getPlayerClient() {
        return JRMCoreClient.mc.field_71439_g;
    }

    @Override
    public void generateDamIndParticles(double x, double y, double z, double amount, float timeleft) {
        double motionX = this.getPlayerClient().field_70170_p.field_73012_v.nextGaussian() * 0.02;
        double motionY = this.getPlayerClient().field_70170_p.field_73012_v.nextGaussian() * 0.02;
        double motionZ = this.getPlayerClient().field_70170_p.field_73012_v.nextGaussian() * 0.02;
        JRMCoreDamInd particleMysterious = new JRMCoreDamInd(amount, timeleft, this.getPlayerClient().field_70170_p, x, y, z, 0.0, 0.0, 0.0);
        JRMCoreClient.mc.field_71452_i.func_78873_a((EntityFX)particleMysterious);
    }

    @Override
    public void func_gcp(Entity entity, EntityCusPar e, double poX, double poY, double poZ, double moX, double moY, double moZ) {
        for (int i = 0; i < JGConfigClientSettings.get_da1(); ++i) {
            EntityCusPar p = new EntityCusPar(e.getdata3(), entity.field_70170_p, e.field_70131_O, e.field_70130_N, entity.field_70165_t + poX, entity.field_70163_u + poY, entity.field_70161_v + poZ, 0.0, 0.0, 0.0, moX, moY, moZ, 0.0f, (int)(Math.random() * (double)e.getId_max()) + e.getId_min(), e.getId_min(), e.getId_max(), 32, e.getRotate(), e.getMaxRotation_Sp(), e.getRotate2(), e.getMaxRotation_Sp2(), e.getdata1(), e.getrr(), e.getdata2(), e.getdata4(), e.getdata5(), e.getdata6(), e.getdata7(), e.getdata31(), e.getdata8(), e.getdata9(), e.getdata10(), e.getdata11(), e.getdata12(), e.getdata13(), e.getdata14(), e.getdata15(), e.getdata16(), e.getdata20(), e.getdata21(), e.getdata22(), e.getdata23(), e.getdata24(), e.getdata25(), false, -1, false, null);
            entity.field_70170_p.func_72838_d((Entity)p);
        }
    }

    @Override
    public void func_gcp(Entity entity, EntityCusPar e, double poX, double poY, double poZ, double moX, double moY, double moZ, float size_min, float size_max, float size_speed) {
        for (int i = 0; i < JGConfigClientSettings.get_da1(); ++i) {
            EntityCusPar p = new EntityCusPar(e.getdata3(), entity.field_70170_p, e.field_70131_O, e.field_70130_N, entity.field_70165_t + poX, entity.field_70163_u + poY, entity.field_70161_v + poZ, 0.0, 0.0, 0.0, moX, moY, moZ, 0.0f, (int)(Math.random() * (double)e.getId_max()) + e.getId_min(), e.getId_min(), e.getId_max(), 32, e.getRotate(), e.getMaxRotation_Sp(), e.getRotate2(), e.getMaxRotation_Sp2(), e.getdata1(), e.getrr(), e.getdata2(), e.getdata4(), e.getdata5() * size_min, e.getdata6() * size_max, e.getdata7() * size_speed, e.getdata31(), e.getdata8(), e.getdata9(), e.getdata10(), e.getdata11(), e.getdata12(), e.getdata13(), e.getdata14(), e.getdata15(), e.getdata16(), e.getdata20(), e.getdata21(), e.getdata22(), e.getdata23(), e.getdata24(), e.getdata25(), false, -1, false, null);
            entity.field_70170_p.func_72838_d((Entity)p);
        }
    }

    @Override
    public void func_gcp(Entity entity, EntityCusPar e, double poX, double poY, double poZ, double moX, double moY, double moZ, float size_min, float size_max, float size_speed, float motion) {
        for (int i = 0; i < JGConfigClientSettings.get_da1(); ++i) {
            EntityCusPar p = new EntityCusPar(e.getdata3(), entity.field_70170_p, e.field_70131_O, e.field_70130_N, entity.field_70165_t + poX, entity.field_70163_u + poY, entity.field_70161_v + poZ, 0.0, 0.0, 0.0, moX, moY, moZ, motion, (int)(Math.random() * (double)e.getId_max()) + e.getId_min(), e.getId_min(), e.getId_max(), 32, e.getRotate(), e.getMaxRotation_Sp(), e.getRotate2(), e.getMaxRotation_Sp2(), e.getdata1(), e.getrr(), e.getdata2(), e.getdata4(), e.getdata5() * size_min, e.getdata6() * size_max, e.getdata7() * size_speed, e.getdata31(), e.getdata8(), e.getdata9(), e.getdata10(), e.getdata11(), e.getdata12(), e.getdata13(), e.getdata14(), e.getdata15(), e.getdata16(), e.getdata20(), e.getdata21(), e.getdata22(), e.getdata23(), e.getdata24(), e.getdata25(), false, -1, false, null);
            entity.field_70170_p.func_72838_d((Entity)p);
        }
    }

    @Override
    public void func_gcp(Entity entity, EntityCusPar e, double poX, double poY, double poZ, double moX, double moY, double moZ, float size_min, float size_max, float size_speed, float motion, int death) {
        for (int i = 0; i < JGConfigClientSettings.get_da1(); ++i) {
            EntityCusPar p = new EntityCusPar(e.getdata3(), entity.field_70170_p, e.field_70131_O, e.field_70130_N, entity.field_70165_t + poX, entity.field_70163_u + poY, entity.field_70161_v + poZ, 0.0, 0.0, 0.0, moX, moY, moZ, motion, (int)(Math.random() * (double)e.getId_max()) + e.getId_min(), e.getId_min(), e.getId_max(), 32, e.getRotate(), e.getMaxRotation_Sp(), e.getRotate2(), e.getMaxRotation_Sp2(), e.getdata1(), e.getrr(), death, e.getdata4(), e.getdata5() * size_min, e.getdata6() * size_max, e.getdata7() * size_speed, e.getdata31(), e.getdata8(), e.getdata9(), e.getdata10(), e.getdata11(), e.getdata12(), e.getdata13(), e.getdata14(), e.getdata15(), e.getdata16(), e.getdata20(), e.getdata21(), e.getdata22(), e.getdata23(), e.getdata24(), e.getdata25(), false, -1, false, null);
            entity.field_70170_p.func_72838_d((Entity)p);
        }
    }

    @Override
    public void func_gcp(Entity entity, EntityCusPar e, double poX, double poY, double poZ, double moX, double moY, double moZ, int img_s) {
        for (int i = 0; i < JGConfigClientSettings.get_da1(); ++i) {
            EntityCusPar p = new EntityCusPar(e.getdata3(), entity.field_70170_p, e.field_70131_O, e.field_70130_N, entity.field_70165_t + poX, entity.field_70163_u + poY, entity.field_70161_v + poZ, 0.0, 0.0, 0.0, moX, moY, moZ, 0.0f, (int)(Math.random() * (double)e.getId_max()) + e.getId_min(), e.getId_min(), e.getId_max(), img_s, e.getRotate(), e.getMaxRotation_Sp(), e.getRotate2(), e.getMaxRotation_Sp2(), e.getdata1(), e.getrr(), e.getdata2(), e.getdata4(), e.getdata5(), e.getdata6(), e.getdata7(), e.getdata31(), e.getdata8(), e.getdata9(), e.getdata10(), e.getdata11(), e.getdata12(), e.getdata13(), e.getdata14(), e.getdata15(), e.getdata16(), e.getdata20(), e.getdata21(), e.getdata22(), e.getdata23(), e.getdata24(), e.getdata25(), false, -1, false, null);
            entity.field_70170_p.func_72838_d((Entity)p);
        }
    }

    @Override
    public void func_gcp(String data3, World w, float box1, float box2, double poX, double poY, double poZ, double start_poX, double start_poY, double start_poZ, double moX, double moY, double moZ, float data29, int id, int id_min, int id_max, int data32, boolean rotate, float max_rotation_sp, boolean rotate2, float max_rotation_sp2, int data1, String rr, int data2, int data4, float data5, float data6, float data7, int data31, float data8, float data9, float data10, float data11, float data12, float data13, float data14, float data15, float data16, int data20, float data21, float data22, float data23, float data24, float data25, boolean data33, int data34, boolean data35, Entity ent) {
        for (int i = 0; i < JGConfigClientSettings.get_da1(); ++i) {
            EntityCusPar p = new EntityCusPar(data3, w, box1, box2, poX, poY, poZ, start_poX, start_poY, start_poZ, moX, moY, moZ, data29, id, id_min, id_max, data32, rotate, max_rotation_sp, rotate2, max_rotation_sp2, data1, rr, data2, data4, data5, data6, data7, data31, data8, data9, data10, data11, data12, data13, data14, data15, data16, data20, data21, data22, data23, data24, data25, data33, data34, data35, ent);
            w.func_72838_d((Entity)p);
        }
    }

    @Override
    public void func_gcp(String data3, World w, float box1, float box2, double poX, double poY, double poZ, double start_poX, double start_poY, double start_poZ, double moX, double moY, double moZ, float data29, int id, int id_min, int id_max, int data32, boolean rotate, float max_rotation_sp, boolean rotate2, float max_rotation_sp2, int data1, String rr, int data2, int data4, float data5, float data6, float data7, int data31, float data8, float data9, float data10, float data11, float data12, float data13, float data14, float data15, float data16, int data20, float data21, float data22, float data23, float data24, float data25, boolean data33, int data34, boolean data35) {
        boolean found = false;
        EntityLivingBase base = null;
        double minx = poX - 1.0;
        double miny = poY - 1.0;
        double minz = poZ - 1.0;
        double maxx = minx + 2.0;
        double maxy = miny + 2.0;
        double maxz = minz + 2.0;
        List var6 = w.func_72839_b(null, AxisAlignedBB.func_72330_a((double)minx, (double)miny, (double)minz, (double)maxx, (double)maxy, (double)maxz));
        Entity var10 = null;
        for (int var9 = 0; var9 < var6.size(); ++var9) {
            var10 = (Entity)var6.get(var9);
            if (!(var10 instanceof EntityLivingBase) || !var10.func_70067_L()) continue;
            found = true;
            break;
        }
        if (found) {
            base = (EntityLivingBase)var10;
        }
        for (int i = 0; i < JGConfigClientSettings.get_da1(); ++i) {
            EntityCusPar p = new EntityCusPar(data3, w, box1, box2, found ? base.field_70165_t : poX, found ? base.field_70163_u + (double)(base instanceof EntityPlayerSP ? -1.6f : 0.0f) : poY, found ? base.field_70161_v : poZ, start_poX, start_poY, start_poZ, moX, moY, moZ, data29, id, id_min, id_max, data32, rotate, max_rotation_sp, rotate2, max_rotation_sp2, data1, rr, data2, data4, data5, data6, data7, data31, data8, data9, data10, data11, data12, data13, data14, data15, data16, data20, data21, data22, data23, data24, data25, data33, data34, data35, (Entity)base);
            w.func_72838_d((Entity)p);
        }
    }

    static {
        phc = new JRMCorePacHanC();
        armor1 = new GiTurtleMdl(0.205f);
        armor2 = new GiTurtleMdl(0.11f);
    }
}

