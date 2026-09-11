/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTUtil
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.StringUtils
 */
package riskyken.armourersWorkshop.common.tileentities;

import com.mojang.authlib.GameProfile;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StringUtils;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinPointer;
import riskyken.armourersWorkshop.client.render.EntityTextureInfo;
import riskyken.armourersWorkshop.client.render.MannequinFakePlayer;
import riskyken.armourersWorkshop.common.GameProfileCache;
import riskyken.armourersWorkshop.common.blocks.ModBlocks;
import riskyken.armourersWorkshop.common.data.BipedRotations;
import riskyken.armourersWorkshop.common.data.TextureType;
import riskyken.armourersWorkshop.common.tileentities.AbstractTileEntityInventory;

public class TileEntityMannequin
extends AbstractTileEntityInventory
implements GameProfileCache.IGameProfileCallback {
    public static final int CONS_OFFSET_MAX = 3;
    private static final String TAG_OWNER = "owner";
    private static final String TAG_ROTATION = "rotation";
    private static final String TAG_IS_DOLL = "isDoll";
    private static final String TAG_HEIGHT_OFFSET = "heightOffset";
    private static final String TAG_SKIN_COLOUR = "skinColour";
    private static final String TAG_HAIR_COLOUR = "hairColour";
    private static final String TAG_OFFSET_X = "offsetX";
    private static final String TAG_OFFSET_Y = "offsetY";
    private static final String TAG_OFFSET_Z = "offsetZ";
    private static final String TAG_RENDER_EXTRAS = "renderExtras";
    private static final String TAG_FLYING = "flying";
    private static final String TAG_VISIBLE = "visible";
    private static final String TAG_TEXTURE_TYPE = "textureType";
    private static final String TAG_IMAGE_URL = "imageUrl";
    public static final int INVENTORY_ROW_SIZE = 7;
    public static final int INVENTORY_ROWS_COUNT = 5;
    public static final int INVENTORY_SIZE = 35;
    private GameProfile gameProfile = null;
    private GameProfile newProfile = null;
    @SideOnly(value=Side.CLIENT)
    private MannequinFakePlayer fakePlayer;
    private BipedRotations bipedRotations;
    private int rotation;
    private float offsetX = 0.0f;
    private float offsetY = 0.0f;
    private float offsetZ = 0.0f;
    private boolean renderExtras = true;
    private boolean flying = false;
    private boolean visible = true;
    private TextureType textureType = TextureType.USER;
    private String imageUrl = null;
    private boolean isDoll;
    private int heightOffset;
    private int skinColour = -6723507;
    private int hairColour = -14083051;
    private boolean dropItems = true;
    @SideOnly(value=Side.CLIENT)
    private boolean skinsUpdated;
    @SideOnly(value=Side.CLIENT)
    public EntityTextureInfo skinTexture;
    @SideOnly(value=Side.CLIENT)
    public ISkinPointer[] sp;

    public TileEntityMannequin() {
        this(false);
    }

    public TileEntityMannequin(boolean isDoll) {
        super(35);
        this.bipedRotations = new BipedRotations();
        this.isDoll = isDoll;
    }

    public void gotUpdateFromClient(float offsetX, float offsetY, float offsetZ, int skinColour, int hairColour, String username, boolean renderExtras, boolean flying, boolean visible, TextureType textureType) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.offsetZ = offsetZ;
        this.skinColour = skinColour;
        this.hairColour = hairColour;
        this.flying = flying;
        this.visible = visible;
        this.textureType = textureType;
        if (this.textureType == TextureType.USER) {
            if (this.gameProfile == null) {
                this.setGameProfile(username);
            } else if (!this.gameProfile.getName().equals(username)) {
                this.setGameProfile(username);
            }
            this.imageUrl = null;
        } else {
            this.imageUrl = username;
            this.gameProfile = null;
        }
        this.renderExtras = renderExtras;
        this.func_70296_d();
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    public float getOffsetX() {
        return this.offsetX;
    }

    public float getOffsetY() {
        return this.offsetY;
    }

    public float getOffsetZ() {
        return this.offsetZ;
    }

    public boolean isRenderExtras() {
        return this.renderExtras;
    }

    public boolean isFlying() {
        return this.flying;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public TextureType getTextureType() {
        return this.textureType;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean haveSkinsUpdated() {
        if (this.skinsUpdated) {
            this.skinsUpdated = false;
            return true;
        }
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    private void setSkinsUpdated(boolean skinsUpdated) {
        this.skinsUpdated = skinsUpdated;
    }

    public boolean canUpdate() {
        return false;
    }

    @Override
    public void func_70299_a(int i, ItemStack itemstack) {
        super.func_70299_a(i, itemstack);
        if (this.field_145850_b.field_72995_K) {
            this.setSkinsUpdated(true);
        }
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    public void setOwner(ItemStack stack) {
        if (stack.func_82837_s()) {
            this.setGameProfile(stack.func_82833_r());
            --stack.field_77994_a;
            this.updateProfileData();
        }
    }

    public void setGameProfile(String username) {
        this.gameProfile = null;
        if (!StringUtils.func_151246_b((String)username)) {
            this.setGameProfile(new GameProfile(null, username));
        }
    }

    public boolean getIsDoll() {
        return this.isDoll;
    }

    public void setDoll(boolean isDoll) {
        this.isDoll = isDoll;
    }

    public void setDropItems(boolean dropItems) {
        this.dropItems = dropItems;
    }

    public boolean getDropItems() {
        return this.dropItems;
    }

    public int getSkinColour() {
        return this.skinColour;
    }

    public void setSkinColour(int skinColour) {
        this.skinColour = skinColour;
        this.func_70296_d();
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    public int getHairColour() {
        return this.hairColour;
    }

    public void setHairColour(int hairColour) {
        this.hairColour = hairColour;
        this.func_70296_d();
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    public ItemStack getDropStack() {
        ItemStack stack = new ItemStack(ModBlocks.mannequin);
        if (this.isDoll) {
            stack = new ItemStack(ModBlocks.doll);
        }
        if (this.gameProfile != null) {
            NBTTagCompound profileTag = new NBTTagCompound();
            NBTUtil.func_152460_a((NBTTagCompound)profileTag, (GameProfile)this.gameProfile);
            stack.func_77982_d(new NBTTagCompound());
            stack.func_77978_p().func_74782_a(TAG_OWNER, (NBTBase)profileTag);
        }
        if (!StringUtils.func_151246_b((String)this.imageUrl)) {
            stack.func_77982_d(new NBTTagCompound());
            stack.func_77978_p().func_74778_a(TAG_IMAGE_URL, this.imageUrl);
        }
        return stack;
    }

    public void setGameProfile(GameProfile gameProfile) {
        this.newProfile = null;
        this.gameProfile = gameProfile;
        if (gameProfile != null) {
            this.textureType = TextureType.USER;
        }
        if (!this.field_145850_b.field_72995_K) {
            this.updateProfileData();
        }
        this.func_70296_d();
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        if (!StringUtils.func_151246_b((String)imageUrl)) {
            this.textureType = TextureType.URL;
        }
        this.func_70296_d();
    }

    public int getHeightOffset() {
        return this.heightOffset;
    }

    public void setHeightOffset(int heightOffset) {
        this.heightOffset = heightOffset;
    }

    private void updateHeightOffset() {
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
        this.func_70296_d();
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    public int getRotation() {
        return this.rotation;
    }

    public GameProfile getGameProfile() {
        if (this.newProfile != null) {
            this.gameProfile = this.newProfile;
            this.newProfile = null;
        }
        return this.gameProfile;
    }

    public BipedRotations getBipedRotations() {
        return this.bipedRotations;
    }

    public void setBipedRotations(BipedRotations bipedRotations) {
        this.bipedRotations = bipedRotations;
        this.updateHeightOffset();
        this.func_70296_d();
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    @SideOnly(value=Side.CLIENT)
    public MannequinFakePlayer getFakePlayer() {
        return this.fakePlayer;
    }

    private void updateProfileData() {
        GameProfile newProfile = GameProfileCache.getGameProfile(this.gameProfile, this);
        if (newProfile != null) {
            this.profileDownloaded(newProfile);
        }
    }

    @Override
    public void readCommonFromNBT(NBTTagCompound compound) {
        super.readCommonFromNBT(compound);
        this.bipedRotations.loadNBTData(compound);
        this.isDoll = compound.func_74767_n(TAG_IS_DOLL);
        this.rotation = compound.func_74762_e(TAG_ROTATION);
        if (compound.func_150297_b(TAG_SKIN_COLOUR, 3)) {
            this.skinColour = compound.func_74762_e(TAG_SKIN_COLOUR);
        }
        if (compound.func_150297_b(TAG_HAIR_COLOUR, 3)) {
            this.hairColour = compound.func_74762_e(TAG_HAIR_COLOUR);
        }
        this.offsetX = compound.func_74760_g(TAG_OFFSET_X);
        this.offsetY = compound.func_74760_g(TAG_OFFSET_Y);
        this.offsetZ = compound.func_74760_g(TAG_OFFSET_Z);
        this.offsetX = MathHelper.func_76131_a((float)this.offsetX, (float)-3.0f, (float)3.0f);
        this.offsetY = MathHelper.func_76131_a((float)this.offsetY, (float)-3.0f, (float)3.0f);
        this.offsetZ = MathHelper.func_76131_a((float)this.offsetZ, (float)-3.0f, (float)3.0f);
        if (compound.func_74764_b(TAG_RENDER_EXTRAS)) {
            this.renderExtras = compound.func_74767_n(TAG_RENDER_EXTRAS);
        }
        if (compound.func_74764_b(TAG_FLYING)) {
            this.flying = compound.func_74767_n(TAG_FLYING);
        }
        if (compound.func_74764_b(TAG_VISIBLE)) {
            this.visible = compound.func_74767_n(TAG_VISIBLE);
        }
        if (compound.func_150297_b(TAG_TEXTURE_TYPE, 1)) {
            this.textureType = TextureType.values()[compound.func_74771_c(TAG_TEXTURE_TYPE)];
        }
        this.gameProfile = compound.func_150297_b(TAG_OWNER, 10) ? NBTUtil.func_152459_a((NBTTagCompound)compound.func_74775_l(TAG_OWNER)) : null;
        this.imageUrl = compound.func_150297_b(TAG_IMAGE_URL, 8) ? compound.func_74779_i(TAG_IMAGE_URL) : null;
    }

    @Override
    public void writeCommonToNBT(NBTTagCompound compound) {
        super.writeCommonToNBT(compound);
        this.bipedRotations.saveNBTData(compound);
        compound.func_74757_a(TAG_IS_DOLL, this.isDoll);
        compound.func_74768_a(TAG_ROTATION, this.rotation);
        compound.func_74768_a(TAG_SKIN_COLOUR, this.skinColour);
        compound.func_74768_a(TAG_HAIR_COLOUR, this.hairColour);
        compound.func_74776_a(TAG_OFFSET_X, this.offsetX);
        compound.func_74776_a(TAG_OFFSET_Y, this.offsetY);
        compound.func_74776_a(TAG_OFFSET_Z, this.offsetZ);
        compound.func_74757_a(TAG_RENDER_EXTRAS, this.renderExtras);
        compound.func_74757_a(TAG_FLYING, this.flying);
        compound.func_74757_a(TAG_VISIBLE, this.visible);
        compound.func_74774_a(TAG_TEXTURE_TYPE, (byte)this.textureType.ordinal());
        if (this.newProfile != null) {
            this.gameProfile = this.newProfile;
            this.newProfile = null;
        }
        if (this.gameProfile != null) {
            NBTTagCompound profileTag = new NBTTagCompound();
            NBTUtil.func_152460_a((NBTTagCompound)profileTag, (GameProfile)this.gameProfile);
            compound.func_74782_a(TAG_OWNER, (NBTBase)profileTag);
        }
        if (this.imageUrl != null) {
            compound.func_74778_a(TAG_IMAGE_URL, this.imageUrl);
        }
    }

    @Override
    public void func_145839_a(NBTTagCompound compound) {
        super.func_145839_a(compound);
        this.readCommonFromNBT(compound);
    }

    @Override
    public void func_145841_b(NBTTagCompound compound) {
        super.func_145841_b(compound);
        this.writeCommonToNBT(compound);
    }

    public Packet func_145844_m() {
        NBTTagCompound compound = new NBTTagCompound();
        this.func_145841_b(compound);
        return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 5, compound);
    }

    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
        NBTTagCompound compound = packet.func_148857_g();
        this.gameProfile = null;
        this.func_145839_a(compound);
        this.skinsUpdated = true;
        if (this.field_145850_b != null && this.field_145850_b.field_72995_K) {
            this.setupFakePlayer();
        }
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    @SideOnly(value=Side.CLIENT)
    private void setupFakePlayer() {
        if (this.fakePlayer != null) {
            return;
        }
        this.fakePlayer = new MannequinFakePlayer(this.field_145850_b, new GameProfile(null, "[Mannequin]"));
        this.fakePlayer.field_70165_t = this.field_145851_c;
        this.fakePlayer.field_70163_u = this.field_145848_d;
        this.fakePlayer.field_70161_v = this.field_145849_e;
        this.fakePlayer.field_70169_q = this.field_145851_c;
        this.fakePlayer.field_70167_r = this.field_145848_d;
        this.fakePlayer.field_70166_s = this.field_145849_e;
    }

    public AxisAlignedBB getRenderBoundingBox() {
        AxisAlignedBB bb = INFINITE_EXTENT_AABB;
        bb = AxisAlignedBB.func_72330_a((double)(this.field_145851_c - 1), (double)this.field_145848_d, (double)(this.field_145849_e - 1), (double)(this.field_145851_c + 2), (double)(this.field_145848_d + 3), (double)(this.field_145849_e + 2));
        return bb;
    }

    public String func_145825_b() {
        return "mannequin";
    }

    @Override
    public void profileDownloaded(GameProfile gameProfile) {
        this.newProfile = gameProfile;
        this.func_70296_d();
        if (this.field_145850_b != null) {
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }
    }
}

