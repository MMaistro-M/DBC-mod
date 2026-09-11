/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.player;

import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.player.SaveSignPacket;
import noppes.npcs.blocks.tiles.TileBigSign;
import noppes.npcs.client.gui.SubGuiNpcTextArea;

public class GuiBigSign
extends SubGuiNpcTextArea {
    public TileBigSign tile;

    public GuiBigSign(int x, int y, int z) {
        super("");
        this.tile = (TileBigSign)this.player.field_70170_p.func_147438_o(x, y, z);
        this.text = this.tile.getText();
    }

    @Override
    public void close() {
        super.close();
        PacketClient.sendClient(new SaveSignPacket(this.tile.field_145851_c, this.tile.field_145848_d, this.tile.field_145849_e, this.text));
    }
}

