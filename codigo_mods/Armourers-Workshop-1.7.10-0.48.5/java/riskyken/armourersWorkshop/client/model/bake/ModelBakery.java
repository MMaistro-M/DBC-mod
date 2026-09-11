/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$ClientTickEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$Phase
 *  cpw.mods.fml.common.gameevent.TickEvent$Type
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  org.apache.logging.log4j.Level
 */
package riskyken.armourersWorkshop.client.model.bake;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import org.apache.logging.log4j.Level;
import riskyken.armourersWorkshop.client.model.bake.SkinBaker;
import riskyken.armourersWorkshop.client.skin.ClientSkinPartData;
import riskyken.armourersWorkshop.client.skin.SkinModelTexture;
import riskyken.armourersWorkshop.client.skin.cache.ClientSkinCache;
import riskyken.armourersWorkshop.client.skin.cache.FastCache;
import riskyken.armourersWorkshop.common.config.ConfigHandlerClient;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.common.skin.data.SkinIdentifier;
import riskyken.armourersWorkshop.common.skin.data.SkinPart;
import riskyken.armourersWorkshop.utils.BitwiseUtils;
import riskyken.armourersWorkshop.utils.ModLogger;

@SideOnly(value=Side.CLIENT)
public final class ModelBakery {
    public static final ModelBakery INSTANCE = new ModelBakery();
    private final Executor skinBakeExecutor;
    private final Executor skinDownloadExecutor;
    private final CompletionService<BakedSkin> skinCompletion;
    private final AtomicInteger bakingQueue = new AtomicInteger(0);
    private final AtomicIntegerArray bakeTimes = new AtomicIntegerArray(100);
    private final AtomicInteger bakeTimesIndex = new AtomicInteger(0);

    public ModelBakery() {
        this.skinBakeExecutor = Executors.newFixedThreadPool(ConfigHandlerClient.maxModelBakingThreads);
        this.skinDownloadExecutor = Executors.newFixedThreadPool(2);
        this.skinCompletion = new ExecutorCompletionService<BakedSkin>(this.skinBakeExecutor);
        FMLCommonHandler.instance().bus().register((Object)this);
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.side == Side.CLIENT & event.type == TickEvent.Type.CLIENT & event.phase == TickEvent.Phase.END) {
            this.checkBakery();
        }
    }

    public int getAverageBakeTime() {
        int totalItems = 0;
        int totalTime = 0;
        for (int i = 0; i < 100; ++i) {
            int time = this.bakeTimes.get(i);
            if (time == 0) continue;
            ++totalItems;
            totalTime += time;
        }
        return (int)((double)totalTime / (double)totalItems);
    }

    public void receivedUnbakedModel(Skin skin, SkinIdentifier skinIdentifierRequested, SkinIdentifier skinIdentifierUpdated) {
        this.bakingQueue.incrementAndGet();
        this.skinCompletion.submit(new BakingOven(skin, skinIdentifierRequested, skinIdentifierUpdated));
    }

    private void checkBakery() {
        Future<BakedSkin> futureSkin = this.skinCompletion.poll();
        while (futureSkin != null) {
            try {
                BakedSkin bakedSkin = futureSkin.get();
                if (bakedSkin != null) {
                    this.bakingQueue.decrementAndGet();
                    if (bakedSkin.skin != null) {
                        ClientSkinCache.INSTANCE.receivedModelFromBakery(bakedSkin);
                    } else {
                        ModLogger.log(Level.ERROR, "A skin failed to bake.");
                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            futureSkin = this.skinCompletion.poll();
        }
    }

    public int getBakingQueueSize() {
        return this.bakingQueue.get();
    }

    public void handleModelDownload(Thread downloadThread) {
        downloadThread.setPriority(1);
        this.skinDownloadExecutor.execute(downloadThread);
    }

    public static class BakedSkin {
        private final Skin skin;
        private final SkinIdentifier skinIdentifierRequested;
        private final SkinIdentifier skinIdentifierUpdated;

        public BakedSkin(Skin skin, SkinIdentifier skinIdentifierRequested, SkinIdentifier skinIdentifierUpdated) {
            this.skin = skin;
            this.skinIdentifierRequested = skinIdentifierRequested;
            this.skinIdentifierUpdated = skinIdentifierUpdated;
        }

        public Skin getSkin() {
            return this.skin;
        }

        public SkinIdentifier getSkinIdentifierRequested() {
            return this.skinIdentifierRequested;
        }

        public SkinIdentifier getSkinIdentifierUpdated() {
            return this.skinIdentifierUpdated;
        }
    }

    private class BakingOven
    implements Callable<BakedSkin> {
        private final Skin skin;
        private final SkinIdentifier skinIdentifierRequested;
        private final SkinIdentifier skinIdentifierUpdated;

        public BakingOven(Skin skin, SkinIdentifier skinIdentifierRequested, SkinIdentifier skinIdentifierUpdated) {
            this.skin = skin;
            this.skinIdentifierRequested = skinIdentifierRequested;
            this.skinIdentifierUpdated = skinIdentifierUpdated;
        }

        @Override
        public BakedSkin call() throws Exception {
            int i;
            Thread.currentThread().setPriority(1);
            FastCache.INSTANCE.saveSkin(this.skin);
            long startTime = System.currentTimeMillis();
            this.skin.lightHash();
            int[][] dyeColour = new int[3][10];
            int[] dyeUseCount = new int[10];
            for (int i2 = 0; i2 < this.skin.getParts().size(); ++i2) {
                SkinPart partData = this.skin.getParts().get(i2);
                partData.setClientSkinPartData(new ClientSkinPartData());
                int[][][] cubeArray = SkinBaker.cullFacesOnEquipmentPart(partData);
                SkinBaker.buildPartDisplayListArray(partData, dyeColour, dyeUseCount, cubeArray);
                partData.clearCubeData();
            }
            if (this.skin.hasPaintData()) {
                this.skin.skinModelTexture = new SkinModelTexture();
                for (int ix = 0; ix < 64; ++ix) {
                    for (int iy = 0; iy < 32; ++iy) {
                        int paintColour = this.skin.getPaintData()[ix + iy * 64];
                        int paintType = BitwiseUtils.getUByteFromInt(paintColour, 0);
                        byte r = (byte)(paintColour >>> 16 & 0xFF);
                        byte g = (byte)(paintColour >>> 8 & 0xFF);
                        byte b = (byte)(paintColour & 0xFF);
                        if (paintType >= 1 && paintType <= 8) {
                            int n = paintType - 1;
                            dyeUseCount[n] = dyeUseCount[n] + 1;
                            int[] nArray = dyeColour[0];
                            int n2 = paintType - 1;
                            nArray[n2] = nArray[n2] + (r & 0xFF);
                            int[] nArray2 = dyeColour[1];
                            int n3 = paintType - 1;
                            nArray2[n3] = nArray2[n3] + (g & 0xFF);
                            int[] nArray3 = dyeColour[2];
                            int n4 = paintType - 1;
                            nArray3[n4] = nArray3[n4] + (b & 0xFF);
                        }
                        if (paintType == 253) {
                            dyeUseCount[8] = dyeUseCount[8] + 1;
                            int[] nArray = dyeColour[0];
                            nArray[8] = nArray[8] + (r & 0xFF);
                            int[] nArray4 = dyeColour[1];
                            nArray4[8] = nArray4[8] + (g & 0xFF);
                            int[] nArray5 = dyeColour[2];
                            nArray5[8] = nArray5[8] + (b & 0xFF);
                        }
                        if (paintType != 254) continue;
                        dyeUseCount[9] = dyeUseCount[9] + 1;
                        int[] nArray = dyeColour[0];
                        nArray[9] = nArray[9] + (r & 0xFF);
                        int[] nArray6 = dyeColour[1];
                        nArray6[9] = nArray6[9] + (g & 0xFF);
                        int[] nArray7 = dyeColour[2];
                        nArray7[9] = nArray7[9] + (b & 0xFF);
                    }
                }
            }
            int[] averageR = new int[10];
            int[] averageG = new int[10];
            int[] averageB = new int[10];
            for (i = 0; i < 10; ++i) {
                averageR[i] = (int)((double)dyeColour[0][i] / (double)dyeUseCount[i]);
                averageG[i] = (int)((double)dyeColour[1][i] / (double)dyeUseCount[i]);
                averageB[i] = (int)((double)dyeColour[2][i] / (double)dyeUseCount[i]);
            }
            for (i = 0; i < this.skin.getParts().size(); ++i) {
                SkinPart partData = this.skin.getParts().get(i);
                partData.getClientSkinPartData().setAverageDyeValues(averageR, averageG, averageB);
            }
            this.skin.setAverageDyeValues(averageR, averageG, averageB);
            if (this.skin.hasPaintData()) {
                this.skin.skinModelTexture.createTextureForColours(this.skin, null);
            }
            long totalTime = System.currentTimeMillis() - startTime;
            int index = ModelBakery.this.bakeTimesIndex.getAndIncrement();
            if (index > 99) {
                index = 0;
                ModelBakery.this.bakeTimesIndex.set(0);
            }
            ModelBakery.this.bakeTimes.set(index, (int)totalTime);
            return new BakedSkin(this.skin, this.skinIdentifierRequested, this.skinIdentifierUpdated);
        }
    }
}

