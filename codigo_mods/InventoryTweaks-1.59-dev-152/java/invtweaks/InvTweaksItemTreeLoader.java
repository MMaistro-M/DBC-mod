/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.common.MinecraftForge
 */
package invtweaks;

import invtweaks.InvTweaksItemTree;
import invtweaks.InvTweaksItemTreeCategory;
import invtweaks.InvTweaksItemTreeItem;
import invtweaks.api.IItemTreeListener;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import net.minecraftforge.common.MinecraftForge;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class InvTweaksItemTreeLoader
extends DefaultHandler {
    private static final String ATTR_ID = "id";
    private static final String ATTR_DAMAGE = "damage";
    private static final String ATTR_RANGE_DMIN = "dmin";
    private static final String ATTR_RANGE_DMAX = "dmax";
    private static final String ATTR_OREDICT_NAME = "oreDictName";
    private static final String ATTR_TREE_VERSION = "treeVersion";
    private static InvTweaksItemTree tree;
    private static String treeVersion;
    private static int itemOrder;
    private static LinkedList<String> categoryStack;
    private static boolean treeLoaded;
    private static final List<IItemTreeListener> onLoadListeners;

    private static void init() {
        treeVersion = null;
        tree = new InvTweaksItemTree();
        itemOrder = 0;
        categoryStack = new LinkedList();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static synchronized InvTweaksItemTree load(File file) throws Exception {
        InvTweaksItemTreeLoader.init();
        SAXParserFactory parserFactory = SAXParserFactory.newInstance();
        SAXParser parser = parserFactory.newSAXParser();
        parser.parse(file, (DefaultHandler)new InvTweaksItemTreeLoader());
        List<IItemTreeListener> list = onLoadListeners;
        synchronized (list) {
            treeLoaded = true;
            for (IItemTreeListener onLoadListener : onLoadListeners) {
                onLoadListener.onTreeLoaded(tree);
            }
        }
        MinecraftForge.EVENT_BUS.register((Object)tree);
        return tree;
    }

    public static synchronized boolean isValidVersion(File file) throws Exception {
        InvTweaksItemTreeLoader.init();
        if (file.exists()) {
            treeVersion = null;
            SAXParserFactory parserFactory = SAXParserFactory.newInstance();
            SAXParser parser = parserFactory.newSAXParser();
            parser.parse(file, (DefaultHandler)new InvTweaksItemTreeLoader());
            return "1.7.0".equals(treeVersion);
        }
        return false;
    }

    public static synchronized void addOnLoadListener(IItemTreeListener listener) {
        onLoadListeners.add(listener);
        if (treeLoaded) {
            listener.onTreeLoaded(tree);
        }
    }

    public static synchronized boolean removeOnLoadListener(IItemTreeListener listener) {
        return onLoadListeners.remove(listener);
    }

    @Override
    public synchronized void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {
        String rangeDMinAttr = attributes.getValue(ATTR_RANGE_DMIN);
        String newTreeVersion = attributes.getValue(ATTR_TREE_VERSION);
        String oreDictNameAttr = attributes.getValue(ATTR_OREDICT_NAME);
        if (attributes.getLength() == 0 || treeVersion == null || rangeDMinAttr != null) {
            if (treeVersion == null) {
                treeVersion = newTreeVersion;
            }
            if (categoryStack.isEmpty()) {
                tree.setRootCategory(new InvTweaksItemTreeCategory(name));
            } else {
                tree.addCategory(categoryStack.getLast(), new InvTweaksItemTreeCategory(name));
            }
            if (rangeDMinAttr != null) {
                String id = attributes.getValue(ATTR_ID);
                int rangeDMin = Integer.parseInt(rangeDMinAttr);
                int rangeDMax = Integer.parseInt(attributes.getValue(ATTR_RANGE_DMAX));
                for (int damage = rangeDMin; damage <= rangeDMax; ++damage) {
                    tree.addItem(name, new InvTweaksItemTreeItem((name + id + "-" + damage).toLowerCase(), id, damage, itemOrder++));
                }
            }
            categoryStack.add(name);
        } else if (attributes.getValue(ATTR_ID) != null) {
            String id = attributes.getValue(ATTR_ID);
            int damage = Short.MAX_VALUE;
            if (attributes.getValue(ATTR_DAMAGE) != null) {
                damage = Integer.parseInt(attributes.getValue(ATTR_DAMAGE));
            }
            tree.addItem(categoryStack.getLast(), new InvTweaksItemTreeItem(name.toLowerCase(), id, damage, itemOrder++));
        } else if (oreDictNameAttr != null) {
            tree.registerOre(categoryStack.getLast(), name.toLowerCase(), oreDictNameAttr, itemOrder++);
        }
    }

    @Override
    public synchronized void endElement(String uri, String localName, String name) throws SAXException {
        if (!categoryStack.isEmpty() && name.equals(categoryStack.getLast())) {
            categoryStack.removeLast();
        }
    }

    static {
        treeLoaded = false;
        onLoadListeners = new ArrayList<IItemTreeListener>();
    }
}

