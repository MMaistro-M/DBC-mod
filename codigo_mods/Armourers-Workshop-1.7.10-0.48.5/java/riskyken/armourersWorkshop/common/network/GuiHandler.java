/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.IGuiHandler
 *  cpw.mods.fml.common.network.NetworkRegistry
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 *  org.apache.logging.log4j.Level
 */
package riskyken.armourersWorkshop.common.network;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import org.apache.logging.log4j.Level;
import riskyken.armourersWorkshop.ArmourersWorkshop;
import riskyken.armourersWorkshop.client.gui.GuiAdminPanel;
import riskyken.armourersWorkshop.client.gui.GuiColourMixer;
import riskyken.armourersWorkshop.client.gui.GuiDebugTool;
import riskyken.armourersWorkshop.client.gui.GuiDyeTable;
import riskyken.armourersWorkshop.client.gui.GuiEntityEquipment;
import riskyken.armourersWorkshop.client.gui.GuiGuideBook;
import riskyken.armourersWorkshop.client.gui.GuiOutfitMaker;
import riskyken.armourersWorkshop.client.gui.GuiSkinnable;
import riskyken.armourersWorkshop.client.gui.GuiSkinningTable;
import riskyken.armourersWorkshop.client.gui.GuiToolOptions;
import riskyken.armourersWorkshop.client.gui.armourer.GuiArmourer;
import riskyken.armourersWorkshop.client.gui.globallibrary.GuiGlobalLibrary;
import riskyken.armourersWorkshop.client.gui.hologramprojector.GuiHologramProjector;
import riskyken.armourersWorkshop.client.gui.mannequin.GuiMannequin;
import riskyken.armourersWorkshop.client.gui.miniarmourer.GuiMiniArmourer;
import riskyken.armourersWorkshop.client.gui.miniarmourer.GuiMiniArmourerBuilding;
import riskyken.armourersWorkshop.client.gui.skinlibrary.GuiSkinLibrary;
import riskyken.armourersWorkshop.client.gui.wardrobe.GuiWardrobe;
import riskyken.armourersWorkshop.common.inventory.ContainerArmourLibrary;
import riskyken.armourersWorkshop.common.inventory.ContainerArmourer;
import riskyken.armourersWorkshop.common.inventory.ContainerColourMixer;
import riskyken.armourersWorkshop.common.inventory.ContainerDyeTable;
import riskyken.armourersWorkshop.common.inventory.ContainerEntityEquipment;
import riskyken.armourersWorkshop.common.inventory.ContainerGlobalSkinLibrary;
import riskyken.armourersWorkshop.common.inventory.ContainerHologramProjector;
import riskyken.armourersWorkshop.common.inventory.ContainerMannequin;
import riskyken.armourersWorkshop.common.inventory.ContainerMiniArmourer;
import riskyken.armourersWorkshop.common.inventory.ContainerMiniArmourerBuilding;
import riskyken.armourersWorkshop.common.inventory.ContainerOutfitMaker;
import riskyken.armourersWorkshop.common.inventory.ContainerSkinWardrobe;
import riskyken.armourersWorkshop.common.inventory.ContainerSkinnable;
import riskyken.armourersWorkshop.common.inventory.ContainerSkinningTable;
import riskyken.armourersWorkshop.common.items.ModItems;
import riskyken.armourersWorkshop.common.painting.tool.IConfigurableTool;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.common.tileentities.TileEntityArmourer;
import riskyken.armourersWorkshop.common.tileentities.TileEntityColourMixer;
import riskyken.armourersWorkshop.common.tileentities.TileEntityDyeTable;
import riskyken.armourersWorkshop.common.tileentities.TileEntityGlobalSkinLibrary;
import riskyken.armourersWorkshop.common.tileentities.TileEntityHologramProjector;
import riskyken.armourersWorkshop.common.tileentities.TileEntityMannequin;
import riskyken.armourersWorkshop.common.tileentities.TileEntityMiniArmourer;
import riskyken.armourersWorkshop.common.tileentities.TileEntityOutfitMaker;
import riskyken.armourersWorkshop.common.tileentities.TileEntitySkinLibrary;
import riskyken.armourersWorkshop.common.tileentities.TileEntitySkinnable;
import riskyken.armourersWorkshop.common.tileentities.TileEntitySkinningTable;
import riskyken.armourersWorkshop.common.wardrobe.ExPropsPlayerSkinData;
import riskyken.armourersWorkshop.common.wardrobe.entity.ExPropsEntityEquipmentData;
import riskyken.armourersWorkshop.utils.ModLogger;

public class GuiHandler
implements IGuiHandler {
    public GuiHandler() {
        NetworkRegistry.INSTANCE.registerGuiHandler((Object)ArmourersWorkshop.instance, (IGuiHandler)this);
    }

    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = null;
        if (ID != 9) {
            te = world.func_147438_o(x, y, z);
        }
        switch (ID) {
            case 0: {
                if (!(te instanceof TileEntityColourMixer)) break;
                return new ContainerColourMixer(player.field_71071_by, (TileEntityColourMixer)te);
            }
            case 1: {
                if (!(te instanceof TileEntityArmourer)) break;
                return new ContainerArmourer(player.field_71071_by, (TileEntityArmourer)te);
            }
            case 4: {
                if (!(te instanceof TileEntitySkinLibrary)) break;
                return new ContainerArmourLibrary(player.field_71071_by, (TileEntitySkinLibrary)te);
            }
            case 5: {
                ExPropsPlayerSkinData customEquipmentData = ExPropsPlayerSkinData.get(player);
                return new ContainerSkinWardrobe(player.field_71071_by, customEquipmentData);
            }
            case 6: {
                if (!(te instanceof TileEntityMannequin)) break;
                return new ContainerMannequin(player.field_71071_by, (TileEntityMannequin)te);
            }
            case 7: {
                if (!(te instanceof TileEntityMiniArmourer)) break;
                return new ContainerMiniArmourer(player.field_71071_by, (TileEntityMiniArmourer)te);
            }
            case 8: {
                if (te instanceof TileEntityMiniArmourer) {
                    return new ContainerMiniArmourerBuilding((TileEntityMiniArmourer)te);
                }
            }
            case 9: {
                Entity entity = player.field_70170_p.func_73045_a(x);
                if (entity != null) {
                    ExPropsEntityEquipmentData entityProps = ExPropsEntityEquipmentData.getExtendedPropsForEntity(entity);
                    if (entityProps == null) break;
                    return new ContainerEntityEquipment(player.field_71071_by, entityProps.getSkinInventory());
                }
                ModLogger.log(Level.WARN, "Error entity not found");
                break;
            }
            case 10: {
                if (!(te instanceof TileEntitySkinningTable)) break;
                return new ContainerSkinningTable(player.field_71071_by, (TileEntitySkinningTable)te);
            }
            case 11: {
                if (!(te instanceof TileEntityDyeTable)) break;
                return new ContainerDyeTable(player.field_71071_by, (TileEntityDyeTable)te);
            }
            case 13: {
                if (!(te instanceof TileEntityGlobalSkinLibrary)) break;
                return new ContainerGlobalSkinLibrary(player.field_71071_by, (TileEntityGlobalSkinLibrary)te);
            }
            case 15: {
                Skin skin;
                if (!(te instanceof TileEntitySkinnable) || (skin = ((TileEntitySkinnable)te).getSkin(((TileEntitySkinnable)te).getSkinPointer())) == null) break;
                return new ContainerSkinnable(player.field_71071_by, (TileEntitySkinnable)te, skin);
            }
            case 16: {
                if (!(te instanceof TileEntityHologramProjector)) break;
                return new ContainerHologramProjector(player.field_71071_by, (TileEntityHologramProjector)te);
            }
            case 17: {
                if (!(te instanceof TileEntityOutfitMaker)) break;
                return new ContainerOutfitMaker(player, (TileEntityOutfitMaker)te);
            }
        }
        return null;
    }

    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = null;
        if (ID != 9) {
            te = world.func_147438_o(x, y, z);
        }
        switch (ID) {
            case 0: {
                if (!(te instanceof TileEntityColourMixer)) break;
                return new GuiColourMixer(player.field_71071_by, (TileEntityColourMixer)te);
            }
            case 1: {
                if (!(te instanceof TileEntityArmourer)) break;
                return new GuiArmourer(player.field_71071_by, (TileEntityArmourer)te);
            }
            case 2: {
                if (player.func_71045_bC().func_77973_b() != ModItems.guideBook) break;
                return new GuiGuideBook(player.func_71045_bC());
            }
            case 4: {
                if (!(te instanceof TileEntitySkinLibrary)) break;
                return new GuiSkinLibrary(player.field_71071_by, (TileEntitySkinLibrary)te);
            }
            case 5: {
                ExPropsPlayerSkinData customEquipmentData = ExPropsPlayerSkinData.get(player);
                return new GuiWardrobe(player.field_71071_by, customEquipmentData);
            }
            case 3: {
                if (!(player.func_71045_bC().func_77973_b() instanceof IConfigurableTool)) break;
                return new GuiToolOptions(player.func_71045_bC());
            }
            case 6: {
                if (!(te instanceof TileEntityMannequin)) break;
                return new GuiMannequin(player.field_71071_by, (TileEntityMannequin)te);
            }
            case 7: {
                if (!(te instanceof TileEntityMiniArmourer)) break;
                return new GuiMiniArmourer(player.field_71071_by, (TileEntityMiniArmourer)te);
            }
            case 8: {
                if (te instanceof TileEntityMiniArmourer) {
                    return new GuiMiniArmourerBuilding((TileEntityMiniArmourer)te);
                }
            }
            case 9: {
                Entity entity = player.field_70170_p.func_73045_a(x);
                if (entity != null) {
                    ExPropsEntityEquipmentData entityProps = ExPropsEntityEquipmentData.getExtendedPropsForEntity(entity);
                    if (entityProps == null) break;
                    return new GuiEntityEquipment(player.field_71071_by, entityProps.getSkinInventory());
                }
                ModLogger.log(Level.WARN, "Error entity not found");
                break;
            }
            case 10: {
                if (!(te instanceof TileEntitySkinningTable)) break;
                return new GuiSkinningTable(player.field_71071_by, (TileEntitySkinningTable)te);
            }
            case 11: {
                if (!(te instanceof TileEntityDyeTable)) break;
                return new GuiDyeTable(player.field_71071_by, (TileEntityDyeTable)te);
            }
            case 13: {
                if (!(te instanceof TileEntityGlobalSkinLibrary)) break;
                return new GuiGlobalLibrary((TileEntityGlobalSkinLibrary)te, player.field_71071_by);
            }
            case 12: {
                return new GuiDebugTool();
            }
            case 14: {
                return new GuiAdminPanel();
            }
            case 15: {
                Skin skin;
                if (!(te instanceof TileEntitySkinnable) || (skin = ((TileEntitySkinnable)te).getSkin(((TileEntitySkinnable)te).getSkinPointer())) == null) break;
                return new GuiSkinnable(player.field_71071_by, (TileEntitySkinnable)te, skin);
            }
            case 16: {
                if (!(te instanceof TileEntityHologramProjector)) break;
                return new GuiHologramProjector(player.field_71071_by, (TileEntityHologramProjector)te);
            }
            case 17: {
                if (!(te instanceof TileEntityOutfitMaker)) break;
                return new GuiOutfitMaker(player, (TileEntityOutfitMaker)te);
            }
        }
        return null;
    }
}

