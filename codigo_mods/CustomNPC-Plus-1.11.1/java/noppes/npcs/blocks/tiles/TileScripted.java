/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Event
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.ITickable
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.EnumSkyBlock
 *  net.minecraft.world.World
 *  net.minecraft.world.chunk.Chunk
 */
package noppes.npcs.blocks.tiles;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kamkeel.npcs.network.packets.request.script.BlockScriptPacket;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.ITickable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import noppes.npcs.CustomItems;
import noppes.npcs.EventHooks;
import noppes.npcs.NBTTags;
import noppes.npcs.NoppesUtilPlayer;
import noppes.npcs.TextBlock;
import noppes.npcs.api.IBlock;
import noppes.npcs.api.block.ITextPlane;
import noppes.npcs.client.renderer.blocks.BlockScriptedRenderer;
import noppes.npcs.constants.EnumScriptType;
import noppes.npcs.constants.ScriptContext;
import noppes.npcs.controllers.ScriptController;
import noppes.npcs.controllers.data.IScriptBlockHandler;
import noppes.npcs.controllers.data.IScriptHandlerPacket;
import noppes.npcs.controllers.data.IScriptUnit;
import noppes.npcs.entity.data.DataTimers;
import noppes.npcs.scripted.BlockScriptedWrapper;
import noppes.npcs.util.ValueUtil;

public class TileScripted
extends TileEntity
implements IScriptBlockHandler,
IScriptHandlerPacket {
    public List<IScriptUnit> scripts = new ArrayList<IScriptUnit>();
    public Map<String, Object> tempData = new HashMap<String, Object>();
    private NBTTagCompound customTileData;
    public String scriptLanguage = "ECMAScript";
    public boolean enabled = false;
    private IBlock blockDummy = null;
    public DataTimers timers = new DataTimers(this);
    public long lastInited = -1L;
    private short ticksExisted = 0;
    public ItemStack itemModel = new ItemStack(CustomItems.scripted);
    public Block blockModel = null;
    private boolean hideModel;
    private int metadata;
    public boolean needsClientUpdate = false;
    public int powering = 0;
    public int activePowering = 0;
    public int newPower = 0;
    public int prevPower = 0;
    public boolean isPassible = false;
    public boolean isLadder = false;
    public int lightValue = 0;
    public float blockHardness = 5.0f;
    public float blockResistance = 10.0f;
    public int rotationX = 0;
    public int rotationY = 0;
    public int rotationZ = 0;
    public float scaleX = 1.0f;
    public float scaleY = 1.0f;
    public float scaleZ = 1.0f;
    public TileEntity renderTile;
    public boolean renderTileErrored = true;
    public boolean renderFullBlock = true;
    public ITickable renderTileUpdate = null;
    public TextPlane text1 = new TextPlane();
    public TextPlane text2 = new TextPlane();
    public TextPlane text3 = new TextPlane();
    public TextPlane text4 = new TextPlane();
    public TextPlane text5 = new TextPlane();
    public TextPlane text6 = new TextPlane();

    @Override
    public IBlock getBlock() {
        if (this.blockDummy == null) {
            this.blockDummy = new BlockScriptedWrapper(this.field_145850_b, this.func_145838_q(), this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }
        return this.blockDummy;
    }

    public void func_145839_a(NBTTagCompound compound) {
        super.func_145839_a(compound);
        this.setNBT(compound);
        this.setDisplayNBT(compound);
        this.timers.readFromNBT(compound);
    }

    public void setNBT(NBTTagCompound compound) {
        this.scripts = NBTTags.GetScriptOld(compound.func_150295_c("Scripts", 10), this);
        this.scriptLanguage = compound.func_74779_i("ScriptLanguage");
        this.enabled = compound.func_74767_n("ScriptEnabled");
        this.activePowering = this.powering = compound.func_74762_e("BlockPowering");
        this.prevPower = compound.func_74762_e("BlockPrevPower");
        if (compound.func_74764_b("BlockHardness")) {
            this.blockHardness = compound.func_74760_g("BlockHardness");
            this.blockResistance = compound.func_74760_g("BlockResistance");
        }
    }

    public void setDisplayNBT(NBTTagCompound compound) {
        this.itemModel = ItemStack.func_77949_a((NBTTagCompound)compound.func_74775_l("ScriptBlockModel"));
        if (this.itemModel == null || this.itemModel.field_77994_a == 0) {
            this.itemModel = new ItemStack(Item.func_150898_a((Block)CustomItems.scripted));
        }
        if (compound.func_74764_b("ScriptBlockModelBlock")) {
            this.blockModel = Block.func_149684_b((String)compound.func_74779_i("ScriptBlockModelBlock"));
        }
        this.renderTileUpdate = null;
        this.renderTile = null;
        this.renderTileErrored = false;
        this.lightValue = compound.func_74762_e("LightValue");
        this.isLadder = compound.func_74767_n("IsLadder");
        this.isPassible = compound.func_74767_n("IsPassible");
        this.rotationX = compound.func_74762_e("RotationX");
        this.rotationY = compound.func_74762_e("RotationY");
        this.rotationZ = compound.func_74762_e("RotationZ");
        this.scaleX = compound.func_74760_g("ScaleX");
        this.scaleY = compound.func_74760_g("ScaleY");
        this.scaleZ = compound.func_74760_g("ScaleZ");
        if (this.scaleX <= 0.0f) {
            this.scaleX = 1.0f;
        }
        if (this.scaleY <= 0.0f) {
            this.scaleY = 1.0f;
        }
        if (this.scaleZ <= 0.0f) {
            this.scaleZ = 1.0f;
        }
        if (compound.func_74764_b("Text1")) {
            this.text1.setNBT(compound.func_74775_l("Text1"));
        }
        if (compound.func_74764_b("Text2")) {
            this.text2.setNBT(compound.func_74775_l("Text2"));
        }
        if (compound.func_74764_b("Text3")) {
            this.text3.setNBT(compound.func_74775_l("Text3"));
        }
        if (compound.func_74764_b("Text4")) {
            this.text4.setNBT(compound.func_74775_l("Text4"));
        }
        if (compound.func_74764_b("Text5")) {
            this.text5.setNBT(compound.func_74775_l("Text5"));
        }
        if (compound.func_74764_b("Text6")) {
            this.text6.setNBT(compound.func_74775_l("Text6"));
        }
    }

    @Override
    public ScriptContext getContext() {
        return ScriptContext.BLOCK;
    }

    @Override
    public void requestData() {
        BlockScriptPacket.Get(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    @Override
    public void sendSavePacket(int index, int totalCount, NBTTagCompound nbt) {
        BlockScriptPacket.Save(this.field_145851_c, this.field_145848_d, this.field_145849_e, nbt);
    }

    @Override
    public IScriptHandlerPacket.GuiDataResult setGuiData(NBTTagCompound compound) {
        if (compound.func_74764_b("LoadComplete")) {
            return new IScriptHandlerPacket.GuiDataResult(IScriptHandlerPacket.GuiDataKind.LOAD_COMPLETE, -1);
        }
        this.setNBT(compound);
        return new IScriptHandlerPacket.GuiDataResult(IScriptHandlerPacket.GuiDataKind.METADATA, -1);
    }

    @Override
    public void sync() {
        this.sendSavePacket(-1, this.getScripts().size(), this.getNBT(new NBTTagCompound()));
    }

    public void func_145841_b(NBTTagCompound compound) {
        super.func_145841_b(compound);
        this.getNBT(compound);
        this.getDisplayNBT(compound);
        this.timers.writeToNBT(compound);
    }

    public NBTTagCompound getNBT(NBTTagCompound compound) {
        compound.func_74782_a("Scripts", (NBTBase)NBTTags.NBTScript(this.scripts));
        compound.func_74778_a("ScriptLanguage", this.scriptLanguage);
        compound.func_74757_a("ScriptEnabled", this.enabled);
        compound.func_74768_a("BlockPowering", this.powering);
        compound.func_74768_a("BlockPrevPower", this.prevPower);
        compound.func_74776_a("BlockHardness", this.blockHardness);
        compound.func_74776_a("BlockResistance", this.blockResistance);
        return compound;
    }

    public void getDisplayNBT(NBTTagCompound compound) {
        NBTTagCompound itemcompound = new NBTTagCompound();
        this.itemModel.func_77955_b(itemcompound);
        if (this.blockModel != null) {
            compound.func_74778_a("ScriptBlockModelBlock", Block.field_149771_c.func_148750_c((Object)this.blockModel));
        }
        compound.func_74782_a("ScriptBlockModel", (NBTBase)itemcompound);
        compound.func_74768_a("LightValue", this.lightValue);
        compound.func_74757_a("IsLadder", this.isLadder);
        compound.func_74757_a("IsPassible", this.isPassible);
        compound.func_74768_a("RotationX", this.rotationX);
        compound.func_74768_a("RotationY", this.rotationY);
        compound.func_74768_a("RotationZ", this.rotationZ);
        compound.func_74776_a("ScaleX", this.scaleX);
        compound.func_74776_a("ScaleY", this.scaleY);
        compound.func_74776_a("ScaleZ", this.scaleZ);
        compound.func_74782_a("Text1", (NBTBase)this.text1.getNBT());
        compound.func_74782_a("Text2", (NBTBase)this.text2.getNBT());
        compound.func_74782_a("Text3", (NBTBase)this.text3.getNBT());
        compound.func_74782_a("Text4", (NBTBase)this.text4.getNBT());
        compound.func_74782_a("Text5", (NBTBase)this.text5.getNBT());
        compound.func_74782_a("Text6", (NBTBase)this.text6.getNBT());
    }

    private boolean isEnabled() {
        return this.enabled && ScriptController.HasStart && !this.field_145850_b.field_72995_K;
    }

    public void func_145845_h() {
        if (this.renderTileUpdate != null) {
            try {
                this.renderTileUpdate.func_110550_d();
            }
            catch (Exception e) {
                this.renderTileUpdate = null;
            }
        }
        this.ticksExisted = (short)(this.ticksExisted + 1);
        if (this.prevPower != this.newPower && this.powering <= 0) {
            EventHooks.onScriptBlockRedstonePower(this, this.prevPower, this.newPower);
            this.prevPower = this.newPower;
        }
        this.timers.update();
        if (this.ticksExisted >= 10) {
            EventHooks.onScriptBlockUpdate(this);
            this.ticksExisted = 0;
            if (this.needsClientUpdate) {
                this.field_145850_b.func_147451_t(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                this.field_145850_b.func_147459_d(this.field_145851_c, this.field_145848_d, this.field_145849_e, CustomItems.scripted);
                Chunk chunk = this.field_145850_b.func_72964_e(this.field_145851_c >> 4, this.field_145849_e >> 4);
                chunk.func_76589_b(this.field_145851_c & 0xF, this.field_145848_d, this.field_145849_e & 0xF, this.metadata);
                this.field_145847_g = this.metadata;
                this.needsClientUpdate = false;
            }
        }
        if (this.field_145850_b.field_72995_K) {
            boolean markForUpdate = false;
            boolean prevHideModel = this.hideModel;
            boolean hideModel = BlockScriptedRenderer.overrideModel();
            if (hideModel != prevHideModel) {
                this.hideModel = hideModel;
                markForUpdate = true;
            }
            if (markForUpdate) {
                this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            }
        }
    }

    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        this.handleUpdateTag(pkt.func_148857_g());
    }

    @SideOnly(value=Side.CLIENT)
    public boolean shouldRenderInPass(int arg0) {
        if (this.blockModel != null && this.blockModel.func_149662_c() && !BlockScriptedRenderer.overrideModel()) {
            return true;
        }
        return super.shouldRenderInPass(arg0);
    }

    public void handleUpdateTag(NBTTagCompound tag) {
        int light = this.lightValue;
        this.setDisplayNBT(tag);
        if (light != this.lightValue) {
            this.checkLight(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    public boolean checkLight(World world, int x, int y, int z) {
        boolean flag = false;
        if (!world.field_73011_w.field_76576_e) {
            flag |= world.func_147463_c(EnumSkyBlock.Sky, x, y, z);
        }
        return flag |= world.func_147463_c(EnumSkyBlock.Block, x, y, z);
    }

    public Packet func_145844_m() {
        return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 0, this.getUpdateTag());
    }

    public NBTTagCompound getUpdateTag() {
        NBTTagCompound compound = new NBTTagCompound();
        compound.func_74768_a("x", this.field_145851_c);
        compound.func_74768_a("y", this.field_145848_d);
        compound.func_74768_a("z", this.field_145849_e);
        this.getDisplayNBT(compound);
        return compound;
    }

    public void setItemModel(ItemStack item, Block b) {
        if (item == null || item.field_77994_a == 0) {
            item = new ItemStack(CustomItems.scripted);
        }
        if (NoppesUtilPlayer.compareItems(item, this.itemModel, false, false) && b != this.blockModel) {
            return;
        }
        this.field_145847_g = this.metadata = item.func_77960_j();
        this.itemModel = item;
        this.blockModel = b;
        this.needsClientUpdate = true;
    }

    public void setLightValue(int value) {
        if (value == this.lightValue) {
            return;
        }
        this.lightValue = ValueUtil.clamp(value, 0, 15);
        this.needsClientUpdate = true;
    }

    public void setRedstonePower(int strength) {
        if (this.powering == strength) {
            return;
        }
        this.prevPower = this.activePowering = ValueUtil.clamp(strength, 0, 15);
        this.field_145850_b.func_147444_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.func_145838_q());
        this.powering = this.activePowering;
    }

    public void setScale(float x, float y, float z) {
        if (this.scaleX == x && this.scaleY == y && this.scaleZ == z) {
            return;
        }
        this.scaleX = ValueUtil.clamp(x, 0.0f, 10.0f);
        this.scaleY = ValueUtil.clamp(y, 0.0f, 10.0f);
        this.scaleZ = ValueUtil.clamp(z, 0.0f, 10.0f);
        this.needsClientUpdate = true;
    }

    public void setRotation(int x, int y, int z) {
        if (this.rotationX == x && this.rotationY == y && this.rotationZ == z) {
            return;
        }
        this.rotationX = ValueUtil.clamp(x, 0, 359);
        this.rotationY = ValueUtil.clamp(y, 0, 359);
        this.rotationZ = ValueUtil.clamp(z, 0, 359);
        this.needsClientUpdate = true;
    }

    @Override
    public void callScript(EnumScriptType type, Event event) {
        if (!this.isEnabled()) {
            return;
        }
        if (ScriptController.Instance.lastLoaded > this.lastInited) {
            this.lastInited = ScriptController.Instance.lastLoaded;
            if (type != EnumScriptType.INIT) {
                EventHooks.onScriptBlockInit(this);
            }
        }
        for (IScriptUnit script : this.scripts) {
            script.run(type, event);
        }
    }

    @Override
    public void callScript(String hookName, Event event) {
        this.callScript(EnumScriptType.valueOf(hookName), event);
    }

    @Override
    public boolean isClient() {
        return this.field_145850_b.field_72995_K;
    }

    @Override
    public boolean getEnabled() {
        return this.enabled;
    }

    @Override
    public void setEnabled(boolean bo) {
        this.enabled = bo;
    }

    @Override
    public String noticeString() {
        return "x: " + this.field_145851_c + ", y: " + this.field_145848_d + ", z: " + this.field_145849_e;
    }

    @Override
    public String getLanguage() {
        return this.scriptLanguage;
    }

    @Override
    public void setLanguage(String lang) {
        this.scriptLanguage = lang;
    }

    @Override
    public void setScripts(List<IScriptUnit> list) {
        this.scripts = list;
    }

    @Override
    public List<IScriptUnit> getScripts() {
        return this.scripts;
    }

    @SideOnly(value=Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox() {
        return AxisAlignedBB.func_72330_a((double)0.0, (double)0.0, (double)0.0, (double)1.0, (double)1.0, (double)1.0).func_72317_d((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e);
    }

    public NBTTagCompound getTileData() {
        if (this.customTileData == null) {
            this.customTileData = new NBTTagCompound();
        }
        return this.customTileData;
    }

    public void func_70296_d() {
        super.func_70296_d();
    }

    public class TextPlane
    implements ITextPlane {
        public boolean textHasChanged = true;
        public TextBlock textBlock;
        public String text = "";
        public int rotationX = 0;
        public int rotationY = 0;
        public int rotationZ = 0;
        public float offsetX = 0.0f;
        public float offsetY = 0.0f;
        public float offsetZ = 0.5f;
        public float scale = 1.0f;

        @Override
        public String getText() {
            return this.text;
        }

        @Override
        public void setText(String text) {
            if (this.text.equals(text)) {
                return;
            }
            this.text = text;
            this.textHasChanged = true;
            TileScripted.this.needsClientUpdate = true;
        }

        @Override
        public int getRotationX() {
            return this.rotationX;
        }

        @Override
        public int getRotationY() {
            return this.rotationY;
        }

        @Override
        public int getRotationZ() {
            return this.rotationZ;
        }

        @Override
        public void setRotationX(int x) {
            if (this.rotationX == (x = ValueUtil.clamp(x % 360, 0, 359))) {
                return;
            }
            this.rotationX = x;
            TileScripted.this.needsClientUpdate = true;
        }

        @Override
        public void setRotationY(int y) {
            if (this.rotationY == (y = ValueUtil.clamp(y % 360, 0, 359))) {
                return;
            }
            this.rotationY = y;
            TileScripted.this.needsClientUpdate = true;
        }

        @Override
        public void setRotationZ(int z) {
            if (this.rotationZ == (z = ValueUtil.clamp(z % 360, 0, 359))) {
                return;
            }
            this.rotationZ = z;
            TileScripted.this.needsClientUpdate = true;
        }

        @Override
        public float getOffsetX() {
            return this.offsetX;
        }

        @Override
        public float getOffsetY() {
            return this.offsetY;
        }

        @Override
        public float getOffsetZ() {
            return this.offsetZ;
        }

        @Override
        public void setOffsetX(float x) {
            if (this.offsetX == (x = ValueUtil.clamp(x, -1.0f, 1.0f))) {
                return;
            }
            this.offsetX = x;
            TileScripted.this.needsClientUpdate = true;
        }

        @Override
        public void setOffsetY(float y) {
            if (this.offsetY == (y = ValueUtil.clamp(y, -1.0f, 1.0f))) {
                return;
            }
            this.offsetY = y;
            TileScripted.this.needsClientUpdate = true;
        }

        @Override
        public void setOffsetZ(float z) {
            if (this.offsetZ == (z = ValueUtil.clamp(z, -1.0f, 1.0f))) {
                return;
            }
            this.offsetZ = z;
            TileScripted.this.needsClientUpdate = true;
        }

        @Override
        public float getScale() {
            return this.scale;
        }

        @Override
        public void setScale(float scale) {
            if (this.scale == scale) {
                return;
            }
            this.scale = scale;
            TileScripted.this.needsClientUpdate = true;
        }

        public NBTTagCompound getNBT() {
            NBTTagCompound compound = new NBTTagCompound();
            compound.func_74778_a("Text", this.text);
            compound.func_74768_a("RotationX", this.rotationX);
            compound.func_74768_a("RotationY", this.rotationY);
            compound.func_74768_a("RotationZ", this.rotationZ);
            compound.func_74776_a("OffsetX", this.offsetX);
            compound.func_74776_a("OffsetY", this.offsetY);
            compound.func_74776_a("OffsetZ", this.offsetZ);
            compound.func_74776_a("Scale", this.scale);
            return compound;
        }

        public void setNBT(NBTTagCompound compound) {
            this.setText(compound.func_74779_i("Text"));
            this.rotationX = compound.func_74762_e("RotationX");
            this.rotationY = compound.func_74762_e("RotationY");
            this.rotationZ = compound.func_74762_e("RotationZ");
            this.offsetX = compound.func_74760_g("OffsetX");
            this.offsetY = compound.func_74760_g("OffsetY");
            this.offsetZ = compound.func_74760_g("OffsetZ");
            this.scale = compound.func_74760_g("Scale");
        }
    }
}

