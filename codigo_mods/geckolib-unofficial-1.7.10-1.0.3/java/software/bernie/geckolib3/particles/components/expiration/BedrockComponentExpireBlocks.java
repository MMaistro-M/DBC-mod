/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonPrimitive
 *  cpw.mods.fml.common.registry.GameData
 *  javax.vecmath.Vector3d
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 */
package software.bernie.geckolib3.particles.components.expiration;

import com.eliotlash.molang.MolangException;
import com.eliotlash.molang.MolangParser;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import cpw.mods.fml.common.registry.GameData;
import java.util.ArrayList;
import java.util.List;
import javax.vecmath.Vector3d;
import net.geckominecraft.util.math.BlockPos;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import software.bernie.geckolib3.particles.components.BedrockComponentBase;
import software.bernie.geckolib3.particles.emitter.BedrockEmitter;
import software.bernie.geckolib3.particles.emitter.BedrockParticle;

public abstract class BedrockComponentExpireBlocks
extends BedrockComponentBase {
    public List<Block> blocks = new ArrayList<Block>();
    private BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

    @Override
    public BedrockComponentBase fromJson(JsonElement element, MolangParser parser) throws MolangException {
        if (element.isJsonArray()) {
            for (JsonElement value : element.getAsJsonArray()) {
                Block block = (Block)GameData.getBlockRegistry().func_82594_a(value.getAsString());
                if (block == null) continue;
                this.blocks.add(block);
            }
        }
        return super.fromJson(element, parser);
    }

    @Override
    public JsonElement toJson() {
        JsonArray array = new JsonArray();
        for (Block block : this.blocks) {
            String rl = GameData.getBlockRegistry().func_148750_c((Object)block);
            if (rl == null) continue;
            array.add((JsonElement)new JsonPrimitive(rl));
        }
        return array;
    }

    public Block getBlock(BedrockEmitter emitter, BedrockParticle particle) {
        if (emitter.world == null) {
            return Blocks.field_150350_a;
        }
        Vector3d position = particle.getGlobalPosition(emitter);
        this.pos.set((int)position.x, (int)position.y, (int)position.z);
        return emitter.world.func_147439_a(this.pos.getX(), this.pos.getY(), this.pos.getZ());
    }
}

