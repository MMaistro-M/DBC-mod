/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandException
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 */
package kamkeel.npcs.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import kamkeel.npcs.command.CommandKamkeelBase;
import kamkeel.npcs.controllers.AttributeController;
import kamkeel.npcs.controllers.data.attribute.AttributeDefinition;
import kamkeel.npcs.controllers.data.attribute.AttributeValueType;
import kamkeel.npcs.util.AttributeItemUtil;
import kamkeel.npcs.util.ColorUtil;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import noppes.npcs.controllers.MagicController;

public class AttributeCommand
extends CommandKamkeelBase {
    public String func_71517_b() {
        return "attribute";
    }

    @Override
    public String getDescription() {
        return "Manage attributes on your held item";
    }

    @CommandKamkeelBase.SubCommand(name="list", usage="[page]", desc="Lists all available attributes")
    public void list(ICommandSender sender, String[] args) throws CommandException {
        ArrayList<AttributeDefinition> defs = new ArrayList<AttributeDefinition>(AttributeController.getAllAttributes());
        if (defs.isEmpty()) {
            ColorUtil.sendError(sender, "No attributes found.");
            return;
        }
        Collections.sort(defs, new Comparator<AttributeDefinition>(){

            @Override
            public int compare(AttributeDefinition a, AttributeDefinition b) {
                return a.getKey().compareToIgnoreCase(b.getKey());
            }
        });
        int page = 1;
        if (args.length > 0) {
            try {
                page = Integer.parseInt(args[0]);
            }
            catch (NumberFormatException numberFormatException) {
                // empty catch block
            }
        }
        int perPage = 10;
        int total = defs.size();
        int totalPages = (int)Math.ceil((double)total / (double)perPage);
        if (page < 1) {
            page = 1;
        }
        if (page > totalPages) {
            page = totalPages;
        }
        int startIndex = (page - 1) * perPage;
        int endIndex = Math.min(startIndex + perPage, total);
        ColorUtil.sendResult(sender, "--------------------");
        ColorUtil.sendResult(sender, "Attributes (Page " + page + "/" + totalPages + "):");
        for (int i = startIndex; i < endIndex; ++i) {
            AttributeDefinition def = (AttributeDefinition)defs.get(i);
            ColorUtil.sendResult(sender, i + 1 + ". " + def.getDisplayName());
            ColorUtil.sendResult(sender, "   - " + def.getKey() + ", " + (Object)((Object)def.getValueType()));
        }
        ColorUtil.sendResult(sender, "--------------------");
    }

    @CommandKamkeelBase.SubCommand(name="apply", usage="<attribute> <value> [magicID]", desc="Applies an attribute to your held item. For magic attributes, provide a magicID.")
    public void apply(ICommandSender sender, String[] args) throws CommandException {
        if (!(sender instanceof EntityPlayer)) {
            ColorUtil.sendError(sender, "Only players can use this command.");
            return;
        }
        EntityPlayer player = (EntityPlayer)sender;
        ItemStack held = player.func_70694_bm();
        if (held == null) {
            ColorUtil.sendError(sender, "You are not holding an item.");
            return;
        }
        String attrKey = args[0];
        AttributeDefinition def = AttributeController.getAttribute(attrKey);
        if (def == null) {
            ColorUtil.sendError(sender, "Unknown attribute: " + attrKey);
            return;
        }
        if (def.getValueType() == AttributeValueType.MAGIC) {
            int magicId;
            float value;
            if (args.length < 3) {
                ColorUtil.sendError(sender, "Usage for magic attribute: apply <attribute> <value> <magicID>");
                return;
            }
            try {
                value = Float.parseFloat(args[1]);
                magicId = Integer.parseInt(args[2]);
            }
            catch (NumberFormatException e) {
                ColorUtil.sendError(sender, "Invalid number format for magicID or value.");
                return;
            }
            if (MagicController.getInstance().getMagic(magicId) == null) {
                ColorUtil.sendError(sender, "No Magic Found for Magic ID %d", magicId);
                return;
            }
            AttributeItemUtil.writeMagicAttribute(held, attrKey, magicId, value);
            ColorUtil.sendResult(sender, "Applied magic attribute " + attrKey + " with magicID " + magicId + " and value " + value + " to held item.");
        } else {
            float value;
            if (args.length < 2) {
                ColorUtil.sendError(sender, "Usage: <attribute> <value>");
                return;
            }
            try {
                value = Float.parseFloat(args[1]);
            }
            catch (NumberFormatException e) {
                ColorUtil.sendError(sender, "Invalid number format for value.");
                return;
            }
            AttributeItemUtil.applyAttribute(held, attrKey, value);
            ColorUtil.sendResult(sender, "Applied attribute " + attrKey + " with value " + value + " to held item.");
        }
    }

    @CommandKamkeelBase.SubCommand(name="remove", usage="<attribute> [magicID)", desc="Removes an attribute from your held item. For magic attributes, provide a magicID.")
    public void remove(ICommandSender sender, String[] args) throws CommandException {
        if (!(sender instanceof EntityPlayer)) {
            ColorUtil.sendError(sender, "Only players can use this command.");
            return;
        }
        EntityPlayer player = (EntityPlayer)sender;
        ItemStack held = player.func_70694_bm();
        if (held == null) {
            ColorUtil.sendError(sender, "You are not holding an item.");
            return;
        }
        String attrKey = args[0];
        AttributeDefinition def = AttributeController.getAttribute(attrKey);
        if (def == null) {
            ColorUtil.sendError(sender, "Unknown attribute: " + attrKey);
            return;
        }
        if (def.getValueType() == AttributeValueType.MAGIC) {
            int magicId;
            if (args.length < 2) {
                ColorUtil.sendError(sender, "Usage for magic attribute: remove <attribute> <magicID>");
                return;
            }
            try {
                magicId = Integer.parseInt(args[1]);
            }
            catch (NumberFormatException e) {
                ColorUtil.sendError(sender, "Invalid magicID format.");
                return;
            }
            if (MagicController.getInstance().getMagic(magicId) == null) {
                ColorUtil.sendError(sender, "No Magic Found for Magic ID %d", magicId);
                return;
            }
            AttributeItemUtil.removeMagicAttribute(held, attrKey, magicId);
            ColorUtil.sendResult(sender, "Removed magic attribute " + attrKey + " (magicID " + magicId + ") from held item.");
        } else {
            AttributeItemUtil.removeAttribute(held, attrKey);
            ColorUtil.sendResult(sender, "Removed attribute " + attrKey + " from held item.");
        }
    }

    @CommandKamkeelBase.SubCommand(name="requirement", usage="<apply|remove> <requirement> [value]", desc="Apply or remove a requirement from your held item.")
    public void requirement(ICommandSender sender, String[] args) throws CommandException {
        if (!(sender instanceof EntityPlayer)) {
            ColorUtil.sendError(sender, "Only players can use this command.");
            return;
        }
        EntityPlayer player = (EntityPlayer)sender;
        ItemStack held = player.func_70694_bm();
        if (held == null) {
            ColorUtil.sendError(sender, "You are not holding an item.");
            return;
        }
        if (args.length < 2) {
            ColorUtil.sendError(sender, "Usage: requirement <apply|remove> <requirementKey> [value]");
            return;
        }
        String action = args[0];
        String reqKey = args[1];
        if (action.equalsIgnoreCase("apply")) {
            Object value;
            if (args.length < 3) {
                ColorUtil.sendError(sender, "Usage: requirement apply <requirementKey> <value>");
                return;
            }
            String valueStr = args[2];
            try {
                value = Integer.parseInt(valueStr);
            }
            catch (NumberFormatException e1) {
                try {
                    value = Float.valueOf(Float.parseFloat(valueStr));
                }
                catch (NumberFormatException e2) {
                    value = valueStr;
                }
            }
            AttributeItemUtil.applyRequirement(held, reqKey, value);
            ColorUtil.sendResult(sender, "Applied requirement " + reqKey + " with value " + value + " to held item.");
        } else if (action.equalsIgnoreCase("remove")) {
            AttributeItemUtil.removeRequirement(held, reqKey);
            ColorUtil.sendResult(sender, "Removed requirement " + reqKey + " from held item.");
        } else {
            ColorUtil.sendError(sender, "Invalid action. Use apply or remove.");
        }
    }
}

