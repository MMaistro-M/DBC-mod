/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.entity.RenderItem
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.util;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.entity.RenderItem;
import noppes.npcs.client.gui.util.GuiNPCInterface;
import noppes.npcs.constants.EnumDiagramLayout;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public abstract class GuiDiagram
extends Gui {
    protected RenderItem renderItem = new RenderItem();
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected float panX = 0.0f;
    protected float panY = 0.0f;
    protected float zoom = 1.0f;
    protected boolean dragging = false;
    protected int lastDragX;
    protected int lastDragY;
    protected int iconSize = 16;
    protected int slotPadding = 4;
    protected int slotSize = this.iconSize + this.slotPadding;
    protected int iconBackgroundColor = -6710887;
    protected int iconBorderColor = -11184811;
    protected int iconBorderThickness = 1;
    protected boolean showArrowHeads = true;
    protected boolean useColorScaling = true;
    protected float arrowSize = 6.0f;
    protected float lineThickness = 2.0f;
    protected boolean allowTwoWay = false;
    protected EnumDiagramLayout layout = EnumDiagramLayout.CIRCULAR;
    protected boolean curvedArrows = true;
    protected int curveAngle = 15;
    protected Map<Integer, Point> cachedPositions = null;
    protected List<DiagramIcon> iconsCache = null;
    protected List<DiagramConnection> connectionsCache = null;
    protected GuiNPCInterface parent;
    protected DiagramIcon currentlyPressedIcon = null;

    public void setLayout(EnumDiagramLayout layout) {
        this.layout = layout;
        this.invalidateCache();
    }

    public void setCurvedArrows(boolean curved) {
        this.curvedArrows = curved;
    }

    public void setCurveAngle(int angle) {
        this.curveAngle = angle;
    }

    public void invalidateCache() {
        this.cachedPositions = null;
        this.iconsCache = null;
        this.connectionsCache = null;
    }

    public GuiDiagram(GuiNPCInterface parent, int x, int y, int width, int height) {
        this.parent = parent;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    protected abstract List<DiagramIcon> createIcons();

    protected abstract List<DiagramConnection> createConnections();

    protected final List<DiagramIcon> getIcons() {
        if (this.iconsCache == null) {
            this.iconsCache = this.createIcons();
        }
        return this.iconsCache;
    }

    protected final List<DiagramConnection> getConnections() {
        if (this.connectionsCache == null) {
            this.connectionsCache = this.createConnections();
        }
        return this.connectionsCache;
    }

    protected abstract void renderIcon(DiagramIcon var1, int var2, int var3, IconRenderState var4);

    protected List<String> getIconTooltip(DiagramIcon icon) {
        return null;
    }

    protected List<String> getConnectionTooltip(DiagramConnection conn) {
        DiagramConnection reverse;
        ArrayList<String> tooltip = new ArrayList<String>();
        DiagramIcon iconFrom = this.getIconById(conn.idFrom);
        DiagramIcon iconTo = this.getIconById(conn.idTo);
        String nameFrom = iconFrom != null ? this.getIconName(iconFrom) : "Unknown";
        String nameTo = iconTo != null ? this.getIconName(iconTo) : "Unknown";
        tooltip.add(nameFrom + " > " + nameTo + ":");
        if (conn.hoverText != null && !conn.hoverText.isEmpty()) {
            String[] lines = conn.hoverText.split("\n");
            tooltip.addAll(Arrays.asList(lines));
        }
        if (this.allowTwoWay && (reverse = this.getConnectionByIds(conn.idTo, conn.idFrom)) != null) {
            tooltip.add(nameTo + " > " + nameFrom + ":");
            if (reverse.hoverText != null && !reverse.hoverText.isEmpty()) {
                String[] lines = reverse.hoverText.split("\n");
                tooltip.addAll(Arrays.asList(lines));
            }
        }
        return tooltip;
    }

    protected String getIconName(DiagramIcon icon) {
        return "Icon " + icon.id;
    }

    protected Map<Integer, Point> calculatePositions() {
        if (this.cachedPositions != null) {
            return this.cachedPositions;
        }
        if (this.layout == EnumDiagramLayout.CHART) {
            return new HashMap<Integer, Point>();
        }
        switch (this.layout) {
            case CIRCULAR: {
                this.cachedPositions = this.calculateCircularPositions();
                break;
            }
            case SQUARE: {
                this.cachedPositions = this.calculateSquarePositions();
                break;
            }
            case TREE: {
                this.cachedPositions = this.calculateTreePositions();
                break;
            }
            case GENERATED: {
                this.cachedPositions = this.calculateGeneratedPositions();
                break;
            }
            case CIRCULAR_MANUAL: {
                this.cachedPositions = this.calculateCircularManualPositions();
                break;
            }
            case SQUARE_MANUAL: {
                this.cachedPositions = this.calculateSquareManualPositions();
                break;
            }
            case TREE_MANUAL: {
                this.cachedPositions = this.calculateTreeManualPositions();
                break;
            }
            case MANUAL: {
                this.cachedPositions = this.calculateManualPositions();
                break;
            }
            default: {
                this.cachedPositions = this.calculateCircularPositions();
            }
        }
        return this.cachedPositions;
    }

    protected Map<Integer, Point> calculateManualPositions() {
        HashMap<Integer, Point> positions = new HashMap<Integer, Point>();
        int centerX = this.x + this.width / 2;
        int centerY = this.y + this.height / 2;
        for (DiagramIcon icon : this.getIcons()) {
            positions.put(icon.id, new Point(centerX + icon.index, centerY - icon.priority));
        }
        return positions;
    }

    protected Map<Integer, Point> calculateCircularPositions() {
        List<DiagramIcon> icons = this.getIcons();
        HashMap<Integer, Point> positions = new HashMap<Integer, Point>();
        int count = icons.size();
        if (count == 0) {
            return positions;
        }
        int centerX = this.x + this.width / 2;
        int centerY = this.y + this.height / 2;
        int radius = (Math.min(this.width, this.height) - this.slotSize) / 2;
        for (int i = 0; i < count; ++i) {
            double angle = Math.PI * 2 * (double)i / (double)count;
            int posX = centerX + (int)((double)radius * Math.cos(angle));
            int posY = centerY + (int)((double)radius * Math.sin(angle));
            positions.put(icons.get((int)i).id, new Point(posX, posY));
        }
        return positions;
    }

    protected Map<Integer, Point> calculateSquarePositions() {
        List<DiagramIcon> icons = this.getIcons();
        HashMap<Integer, Point> positions = new HashMap<Integer, Point>();
        int count = icons.size();
        if (count == 0) {
            return positions;
        }
        int n = (int)Math.ceil(Math.sqrt(count));
        int cellWidth = this.width / n;
        int cellHeight = this.height / n;
        int startX = this.x;
        int startY = this.y;
        for (int i = 0; i < count; ++i) {
            int row = i / n;
            int col = i % n;
            int posX = startX + col * cellWidth + cellWidth / 2;
            int posY = startY + row * cellHeight + cellHeight / 2;
            positions.put(icons.get((int)i).id, new Point(posX, posY));
        }
        return positions;
    }

    protected Map<Integer, Point> calculateTreePositions() {
        List<DiagramIcon> icons = this.getIcons();
        HashMap<Integer, Point> positions = new HashMap<Integer, Point>();
        int count = icons.size();
        if (count == 0) {
            return positions;
        }
        int levels = (int)Math.ceil(Math.log(count + 1) / Math.log(2.0));
        for (int level = 0; level < levels; ++level) {
            int nodesThisLevel = level == 0 ? 1 : (int)Math.min(Math.pow(2.0, level), (double)(count - (int)Math.pow(2.0, level) + 1));
            int posY = levels == 1 ? this.y + this.height / 2 : this.y + (int)((double)(this.height * level) / ((double)levels - 1.0));
            for (int i = 0; i < nodesThisLevel; ++i) {
                int posX = nodesThisLevel == 1 ? this.x + this.width / 2 : this.x + (int)((double)(this.width * i) / ((double)nodesThisLevel - 1.0));
                int index = (int)(Math.pow(2.0, level) - 1.0 + (double)i);
                if (index >= count) continue;
                positions.put(icons.get((int)index).id, new Point(posX, posY));
            }
        }
        return positions;
    }

    protected Map<Integer, Point> calculateGeneratedPositions() {
        List<DiagramIcon> icons = this.getIcons();
        List<DiagramConnection> connections = this.getConnections();
        Map<Integer, List<Integer>> graph = this.buildGraph(icons, connections);
        List<Set<Integer>> clusters = this.getConnectedComponents(graph);
        int clusterCount = clusters.size();
        int gridCols = (int)Math.ceil(Math.sqrt(clusterCount));
        int gridRows = (int)Math.ceil((double)clusterCount / (double)gridCols);
        HashMap<Integer, Point> globalPositions = new HashMap<Integer, Point>();
        int clusterIndex = 0;
        for (Set<Integer> cluster : clusters) {
            int col = clusterIndex % gridCols;
            int row = clusterIndex / gridCols;
            int compX = this.x + col * (this.width / gridCols);
            int compY = this.y + row * (this.height / gridRows);
            int compWidth = this.width / gridCols;
            int compHeight = this.height / gridRows;
            Map<Integer, Point> compPositions = this.isCycle(cluster, graph) ? this.layoutCycle(cluster, compX, compY, compWidth, compHeight) : (this.isTree(cluster, graph) ? this.layoutTree(cluster, compX, compY, compWidth, compHeight, graph) : (this.isSquare(cluster) ? this.layoutSquare(cluster, compX, compY, compWidth, compHeight) : this.layoutForceDirected(cluster, compX, compY, compWidth, compHeight, connections)));
            globalPositions.putAll(compPositions);
            ++clusterIndex;
        }
        return globalPositions;
    }

    private Map<Integer, List<Integer>> buildGraph(List<DiagramIcon> icons, List<DiagramConnection> connections) {
        HashMap<Integer, List<Integer>> graph = new HashMap<Integer, List<Integer>>();
        for (DiagramIcon icon : icons) {
            graph.put(icon.id, new ArrayList());
        }
        for (DiagramConnection conn : connections) {
            if (!graph.containsKey(conn.idFrom) || !graph.containsKey(conn.idTo)) continue;
            ((List)graph.get(conn.idFrom)).add(conn.idTo);
            ((List)graph.get(conn.idTo)).add(conn.idFrom);
        }
        return graph;
    }

    private List<Set<Integer>> getConnectedComponents(Map<Integer, List<Integer>> graph) {
        HashSet<Integer> visited = new HashSet<Integer>();
        ArrayList<Set<Integer>> components = new ArrayList<Set<Integer>>();
        for (Integer node : graph.keySet()) {
            if (visited.contains(node)) continue;
            HashSet<Integer> comp = new HashSet<Integer>();
            this.dfsComponent(node, graph, visited, comp);
            components.add(comp);
        }
        return components;
    }

    private void dfsComponent(Integer current, Map<Integer, List<Integer>> graph, Set<Integer> visited, Set<Integer> comp) {
        visited.add(current);
        comp.add(current);
        for (Integer neighbor : graph.get(current)) {
            if (visited.contains(neighbor)) continue;
            this.dfsComponent(neighbor, graph, visited, comp);
        }
    }

    private boolean isCycle(Set<Integer> cluster, Map<Integer, List<Integer>> graph) {
        if (cluster.size() < 3) {
            return false;
        }
        for (Integer node : cluster) {
            int degree = 0;
            for (Integer neighbor : graph.get(node)) {
                if (!cluster.contains(neighbor)) continue;
                ++degree;
            }
            if (degree == 2) continue;
            return false;
        }
        return true;
    }

    private boolean isTree(Set<Integer> cluster, Map<Integer, List<Integer>> graph) {
        int edgeCount = 0;
        for (Integer node : cluster) {
            for (Integer neighbor : graph.get(node)) {
                if (!cluster.contains(neighbor)) continue;
                ++edgeCount;
            }
        }
        return (edgeCount /= 2) == cluster.size() - 1;
    }

    private boolean isSquare(Set<Integer> cluster) {
        int n = cluster.size();
        int sqrt = (int)Math.round(Math.sqrt(n));
        return sqrt * sqrt == n;
    }

    private Map<Integer, Point> layoutCycle(Set<Integer> cluster, int compX, int compY, int compWidth, int compHeight) {
        HashMap<Integer, Point> positions = new HashMap<Integer, Point>();
        if (cluster.isEmpty()) {
            return positions;
        }
        int centerX = compX + compWidth / 2;
        int centerY = compY + compHeight / 2;
        int radius = (Math.min(compWidth, compHeight) - this.slotSize) / 2;
        int i = 0;
        for (Integer id : cluster) {
            double angle = Math.PI * 2 * (double)i / (double)cluster.size();
            int posX = centerX + (int)((double)radius * Math.cos(angle));
            int posY = centerY + (int)((double)radius * Math.sin(angle));
            positions.put(id, new Point(posX, posY));
            ++i;
        }
        return positions;
    }

    private Map<Integer, Point> layoutTree(Set<Integer> cluster, int compX, int compY, int compWidth, int compHeight, Map<Integer, List<Integer>> fullGraph) {
        HashMap<Integer, Point> positions = new HashMap<Integer, Point>();
        if (cluster.isEmpty()) {
            return positions;
        }
        HashMap subgraph = new HashMap();
        for (Integer id : cluster) {
            ArrayList<Integer> neighbors = new ArrayList<Integer>();
            for (Integer neighbor : fullGraph.get(id)) {
                if (!cluster.contains(neighbor)) continue;
                neighbors.add(neighbor);
            }
            subgraph.put(id, neighbors);
        }
        Integer root = Collections.min(cluster);
        HashMap<Integer, Integer> levelMap = new HashMap<Integer, Integer>();
        HashMap<Integer, List> levels = new HashMap<Integer, List>();
        LinkedList<Integer> queue = new LinkedList<Integer>();
        HashSet<Integer> visited = new HashSet<Integer>();
        queue.add(root);
        visited.add(root);
        levelMap.put(root, 0);
        while (!queue.isEmpty()) {
            Integer curr = (Integer)queue.poll();
            int lvl = (Integer)levelMap.get(curr);
            levels.computeIfAbsent(lvl, k -> new ArrayList()).add(curr);
            for (Integer neighbor : (List)subgraph.get(curr)) {
                if (visited.contains(neighbor)) continue;
                visited.add(neighbor);
                levelMap.put(neighbor, lvl + 1);
                queue.add(neighbor);
            }
        }
        int maxLevel = levels.keySet().stream().max(Integer::compare).orElse(0);
        for (Map.Entry entry : levels.entrySet()) {
            int lvl = (Integer)entry.getKey();
            int posY = maxLevel == 0 ? compY + compHeight / 2 : compY + compHeight * lvl / maxLevel;
            List nodesAtLevel = (List)entry.getValue();
            nodesAtLevel.sort(Comparator.naturalOrder());
            int m = nodesAtLevel.size();
            for (int i = 0; i < m; ++i) {
                int posX = m == 1 ? compX + compWidth / 2 : compX + (int)((double)(compWidth * i) / ((double)m - 1.0));
                positions.put((Integer)nodesAtLevel.get(i), new Point(posX, posY));
            }
        }
        return positions;
    }

    private Map<Integer, Point> layoutSquare(Set<Integer> cluster, int compX, int compY, int compWidth, int compHeight) {
        HashMap<Integer, Point> positions = new HashMap<Integer, Point>();
        if (cluster.isEmpty()) {
            return positions;
        }
        int n = (int)Math.round(Math.sqrt(cluster.size()));
        int gridCellWidth = compWidth / n;
        int gridCellHeight = compHeight / n;
        int index = 0;
        ArrayList<Integer> nodes = new ArrayList<Integer>(cluster);
        nodes.sort(Comparator.naturalOrder());
        for (int row = 0; row < n; ++row) {
            for (int col = 0; col < n && index < nodes.size(); ++index, ++col) {
                int posX = compX + col * gridCellWidth + gridCellWidth / 2;
                int posY = compY + row * gridCellHeight + gridCellHeight / 2;
                positions.put((Integer)nodes.get(index), new Point(posX, posY));
            }
        }
        return positions;
    }

    private Map<Integer, Point> layoutForceDirected(Set<Integer> cluster, int compX, int compY, int compWidth, int compHeight, List<DiagramConnection> connections) {
        HashMap<Integer, Point> positions = new HashMap<Integer, Point>();
        if (cluster.isEmpty()) {
            return positions;
        }
        Random rand = new Random(100L);
        for (Integer id : cluster) {
            int posX = compX + rand.nextInt(compWidth);
            int posY = compY + rand.nextInt(compHeight);
            positions.put(id, new Point(posX, posY));
        }
        double area = compWidth * compHeight;
        double k = Math.sqrt(area / (double)cluster.size());
        int iterations = 100;
        double temperature = (double)compWidth / 10.0;
        double cooling = temperature / (double)(iterations + 1);
        double minDistance = this.iconSize + this.slotPadding;
        ArrayList<DiagramConnection> compConnections = new ArrayList<DiagramConnection>();
        for (DiagramConnection conn : connections) {
            if (!cluster.contains(conn.idFrom) || !cluster.contains(conn.idTo)) continue;
            compConnections.add(conn);
        }
        for (int iter = 0; iter < iterations; ++iter) {
            Point posV;
            HashMap<Integer, double[]> disp = new HashMap<Integer, double[]>();
            for (Integer id : cluster) {
                disp.put(id, new double[]{0.0, 0.0});
            }
            ArrayList<Integer> nodes = new ArrayList<Integer>(cluster);
            for (int i = 0; i < nodes.size(); ++i) {
                Integer v = (Integer)nodes.get(i);
                posV = (Point)positions.get(v);
                for (int j = i + 1; j < nodes.size(); ++j) {
                    Integer u = (Integer)nodes.get(j);
                    Point posU = (Point)positions.get(u);
                    double dx = posV.x - posU.x;
                    double dy = posV.y - posU.y;
                    double distance = Math.sqrt(dx * dx + dy * dy);
                    if (distance < minDistance) {
                        distance = minDistance;
                    }
                    double repForce = k * k / distance;
                    double[] dispV = (double[])disp.get(v);
                    double[] dispU = (double[])disp.get(u);
                    dispV[0] = dispV[0] + dx / distance * repForce;
                    dispV[1] = dispV[1] + dy / distance * repForce;
                    dispU[0] = dispU[0] - dx / distance * repForce;
                    dispU[1] = dispU[1] - dy / distance * repForce;
                }
            }
            for (DiagramConnection conn : compConnections) {
                posV = (Point)positions.get(conn.idFrom);
                Point posU = (Point)positions.get(conn.idTo);
                double dx = posV.x - posU.x;
                double dy = posV.y - posU.y;
                double distance = Math.sqrt(dx * dx + dy * dy);
                if (distance < 0.01) {
                    distance = 0.01;
                }
                double attrForce = distance * distance / k;
                double[] dispV = (double[])disp.get(conn.idFrom);
                double[] dispU = (double[])disp.get(conn.idTo);
                dispV[0] = dispV[0] - dx / distance * attrForce;
                dispV[1] = dispV[1] - dy / distance * attrForce;
                dispU[0] = dispU[0] + dx / distance * attrForce;
                dispU[1] = dispU[1] + dy / distance * attrForce;
            }
            for (Integer id : cluster) {
                double[] d = (double[])disp.get(id);
                double dispLength = Math.sqrt(d[0] * d[0] + d[1] * d[1]);
                if (dispLength < 0.01) {
                    dispLength = 0.01;
                }
                int newX = ((Point)positions.get((Object)id)).x + (int)(d[0] / dispLength * Math.min(dispLength, temperature));
                int newY = ((Point)positions.get((Object)id)).y + (int)(d[1] / dispLength * Math.min(dispLength, temperature));
                newX = Math.max(compX, Math.min(compX + compWidth, newX));
                newY = Math.max(compY, Math.min(compY + compHeight, newY));
                ((Point)positions.get((Object)id)).x = newX;
                ((Point)positions.get((Object)id)).y = newY;
            }
            temperature -= cooling;
        }
        return positions;
    }

    private Map<Integer, Point> calculateCircularManualPositions() {
        List<DiagramIcon> icons = this.getIcons();
        HashMap<Integer, Point> positions = new HashMap<Integer, Point>();
        if (icons.isEmpty()) {
            return positions;
        }
        TreeMap<Integer, List> groups = new TreeMap<Integer, List>();
        for (DiagramIcon icon2 : icons) {
            groups.computeIfAbsent(icon2.index, k -> new ArrayList()).add(icon2);
        }
        int centerX = this.x + this.width / 2;
        int centerY = this.y + this.height / 2;
        int maxRing = groups.isEmpty() ? 0 : (Integer)Collections.max(groups.keySet());
        int maxRadius = (Math.min(this.width, this.height) - this.iconSize) / 2;
        double spacingExponent = 1.5;
        double ringMultiplier = 1.5;
        for (Map.Entry entry : groups.entrySet()) {
            int ring = (Integer)entry.getKey();
            List groupIcons = (List)entry.getValue();
            if (groupIcons.isEmpty()) continue;
            groupIcons.sort(Comparator.comparingInt(icon -> icon.priority));
            double normalizedRing = (double)(ring + 1) / (double)(maxRing + 1);
            double radius = (double)maxRadius * Math.pow(normalizedRing, spacingExponent) * ringMultiplier;
            int count = groupIcons.size();
            double startAngle = ring == 0 ? -1.5707963267948966 : -2.356194490192345;
            for (int i = 0; i < count; ++i) {
                double angle = startAngle + Math.PI * 2 * (double)i / (double)count;
                int posX = centerX + (int)(radius * Math.cos(angle));
                int posY = centerY + (int)(radius * Math.sin(angle));
                positions.put(((DiagramIcon)groupIcons.get((int)i)).id, new Point(posX, posY));
            }
        }
        return positions;
    }

    private Map<Integer, Point> calculateSquareManualPositions() {
        List<DiagramIcon> icons = this.getIcons();
        HashMap<Integer, Point> positions = new HashMap<Integer, Point>();
        if (icons.isEmpty()) {
            return positions;
        }
        TreeMap<Integer, List> groups = new TreeMap<Integer, List>();
        for (DiagramIcon icon2 : icons) {
            groups.computeIfAbsent(icon2.index, k -> new ArrayList()).add(icon2);
        }
        int centerX = this.x + this.width / 2;
        int centerY = this.y + this.height / 2;
        int maxRing = groups.isEmpty() ? 0 : (Integer)Collections.max(groups.keySet());
        int halfSide = (Math.min(this.width, this.height) - this.iconSize) / 2;
        double ringSpacing = maxRing + 1 > 0 ? (double)halfSide / (double)(maxRing + 1) : 0.0;
        for (Map.Entry entry : groups.entrySet()) {
            int ring = (Integer)entry.getKey();
            List groupIcons = (List)entry.getValue();
            if (groupIcons.isEmpty()) continue;
            groupIcons.sort(Comparator.comparingInt(icon -> icon.priority));
            double offset = ringSpacing * (double)(ring + 1);
            int count = groupIcons.size();
            double perimeter = 8.0 * offset;
            for (int i = 0; i < count; ++i) {
                int posY;
                int posX;
                double posAlong = perimeter * (double)i / (double)count;
                if (posAlong < 2.0 * offset) {
                    posX = centerX - (int)offset + (int)posAlong;
                    posY = centerY - (int)offset;
                } else if (posAlong < 4.0 * offset) {
                    posX = centerX + (int)offset;
                    posY = centerY - (int)offset + (int)(posAlong - 2.0 * offset);
                } else if (posAlong < 6.0 * offset) {
                    posX = centerX + (int)offset - (int)(posAlong - 4.0 * offset);
                    posY = centerY + (int)offset;
                } else {
                    posX = centerX - (int)offset;
                    posY = centerY + (int)offset - (int)(posAlong - 6.0 * offset);
                }
                positions.put(((DiagramIcon)groupIcons.get((int)i)).id, new Point(posX, posY));
            }
        }
        return positions;
    }

    private Map<Integer, Point> calculateTreeManualPositions() {
        List<DiagramIcon> icons = this.getIcons();
        TreeMap<Integer, List> levels = new TreeMap<Integer, List>();
        for (DiagramIcon icon2 : icons) {
            levels.computeIfAbsent(icon2.index, k -> new ArrayList()).add(icon2);
        }
        HashMap<Integer, Point> positions = new HashMap<Integer, Point>();
        if (icons.isEmpty()) {
            return positions;
        }
        int maxLevel = levels.isEmpty() ? 0 : (Integer)Collections.max(levels.keySet());
        for (Map.Entry entry : levels.entrySet()) {
            int level = (Integer)entry.getKey();
            List levelIcons = (List)entry.getValue();
            levelIcons.sort(Comparator.comparingInt(icon -> icon.priority));
            int m = levelIcons.size();
            int posY = m > 0 && maxLevel > 0 ? this.y + this.height * level / maxLevel : this.y + this.height / 2;
            for (int i = 0; i < m; ++i) {
                int posX = m > 1 ? this.x + (int)((double)(this.width * i) / ((double)m - 1.0)) : this.x + this.width / 2;
                positions.put(((DiagramIcon)levelIcons.get((int)i)).id, new Point(posX, posY));
            }
        }
        return positions;
    }

    private Point computeControlPoint(int x1, int y1, int x2, int y2, int angleDegrees) {
        double dx = x2 - x1;
        double dy = y2 - y1;
        double len = Math.sqrt(dx * dx + dy * dy);
        if (len == 0.0) {
            len = 1.0;
        }
        double angleRad = Math.toRadians(angleDegrees);
        double offset = len * Math.tan(angleRad) / 2.0;
        double perpX = -dy / len;
        double perpY = dx / len;
        return new Point((x1 + x2) / 2 + (int)(perpX * offset), (y1 + y2) / 2 + (int)(perpY * offset));
    }

    private double getMinDistanceToIcon(Point control, DiagramConnection conn) {
        double minDist = Double.MAX_VALUE;
        Map<Integer, Point> positions = this.calculatePositions();
        for (DiagramIcon icon : this.getIcons()) {
            double dist;
            Point pos;
            if (icon.id == conn.idFrom || icon.id == conn.idTo || (pos = positions.get(icon.id)) == null || !((dist = pos.distance(control)) < minDist)) continue;
            minDist = dist;
        }
        return minDist;
    }

    protected void drawConnectionLine(int x1, int y1, int x2, int y2, DiagramConnection conn, boolean dim) {
        boolean useCustomCurveAngle;
        DiagramConnection reverse = this.getConnectionByIds(conn.idTo, conn.idFrom);
        boolean twoWay = reverse != null && this.allowTwoWay;
        boolean separateTwoWay = reverse != null && !this.allowTwoWay;
        int effectiveCurveAngle = conn.customCurveAngle != null ? conn.customCurveAngle : this.curveAngle;
        boolean bl = useCustomCurveAngle = conn.customAllowCurve != null ? conn.customAllowCurve : this.curvedArrows;
        if (!useCustomCurveAngle) {
            if (separateTwoWay) {
                int offsetAmount = 4;
                double dx = x2 - x1;
                double dy = y2 - y1;
                double len = Math.sqrt(dx * dx + dy * dy);
                if (len == 0.0) {
                    len = 1.0;
                }
                double offsetX = -dy / len * (double)offsetAmount;
                double offsetY = dx / len * (double)offsetAmount;
                if (conn.idFrom >= conn.idTo) {
                    offsetX = -offsetX;
                    offsetY = -offsetY;
                }
                int newX1 = x1 + (int)offsetX;
                int newY1 = y1 + (int)offsetY;
                int newX2 = x2 + (int)offsetX;
                int newY2 = y2 + (int)offsetY;
                int color = this.getConnectionColor(conn);
                if (!this.useColorScaling) {
                    color = -1;
                }
                this.drawColoredLine(newX1, newY1, newX2, newY2, color, dim);
            } else if (twoWay) {
                int midX = (x1 + x2) / 2;
                int midY = (y1 + y2) / 2;
                int color1 = this.getConnectionColor(reverse);
                int color2 = this.getConnectionColor(conn);
                if (!this.useColorScaling) {
                    color2 = -1;
                    color1 = -1;
                }
                this.drawColoredLine(x1, y1, midX, midY, color1, dim);
                this.drawColoredLine(midX, midY, x2, y2, color2, dim);
            } else {
                int color = this.getConnectionColor(conn);
                if (!this.useColorScaling) {
                    color = -1;
                }
                this.drawColoredLine(x1, y1, x2, y2, color, dim);
            }
        } else if (separateTwoWay) {
            int offsetAmount = 4;
            double dx = x2 - x1;
            double dy = y2 - y1;
            double len = Math.sqrt(dx * dx + dy * dy);
            if (len == 0.0) {
                len = 1.0;
            }
            double normX = -dy / len;
            double normY = dx / len;
            double sign = conn.idFrom < conn.idTo ? 1.0 : -1.0;
            Point baseControl = this.computeControlPoint(x1, y1, x2, y2, effectiveCurveAngle);
            Point offsetControl = new Point(baseControl.x + (int)(normX * (double)offsetAmount * sign), baseControl.y + (int)(normY * (double)offsetAmount * sign));
            int segments = 800;
            int color = this.getConnectionColor(conn);
            if (!this.useColorScaling) {
                color = -1;
            }
            GL11.glPushAttrib((int)57344);
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            GL11.glEnable((int)2848);
            GL11.glHint((int)3154, (int)4354);
            GL11.glDisable((int)3553);
            GL11.glLineWidth((float)this.lineThickness);
            this.setColor(color, dim);
            GL11.glBegin((int)3);
            for (int i = 0; i <= segments; ++i) {
                double t = (double)i / (double)segments;
                int bx = (int)((1.0 - t) * (1.0 - t) * (double)x1 + 2.0 * (1.0 - t) * t * (double)offsetControl.x + t * t * (double)x2);
                int by = (int)((1.0 - t) * (1.0 - t) * (double)y1 + 2.0 * (1.0 - t) * t * (double)offsetControl.y + t * t * (double)y2);
                GL11.glVertex2i((int)bx, (int)by);
            }
            GL11.glEnd();
            GL11.glEnable((int)3553);
            GL11.glDisable((int)2848);
            GL11.glDisable((int)3042);
            GL11.glPopAttrib();
        } else if (twoWay) {
            int by;
            int bx;
            double t;
            int i;
            double d2;
            Point cp1 = this.computeControlPoint(x1, y1, x2, y2, effectiveCurveAngle);
            Point cp2 = this.computeControlPoint(x1, y1, x2, y2, -effectiveCurveAngle);
            double d1 = this.getMinDistanceToIcon(cp1, conn);
            Point control = d1 >= (d2 = this.getMinDistanceToIcon(cp2, conn)) ? cp1 : cp2;
            int segments = 800;
            int color1 = this.getConnectionColor(reverse);
            int color2 = this.getConnectionColor(conn);
            if (!this.useColorScaling) {
                color2 = -1;
                color1 = -1;
            }
            int halfSegments = segments / 2;
            GL11.glPushAttrib((int)57344);
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            GL11.glEnable((int)2848);
            GL11.glHint((int)3154, (int)4354);
            GL11.glDisable((int)3553);
            GL11.glLineWidth((float)this.lineThickness);
            this.setColor(color1, dim);
            GL11.glBegin((int)3);
            for (i = 0; i <= halfSegments; ++i) {
                t = (double)i / (double)halfSegments;
                bx = (int)((1.0 - t) * (1.0 - t) * (double)x1 + 2.0 * (1.0 - t) * t * (double)control.x + t * t * (double)x2);
                by = (int)((1.0 - t) * (1.0 - t) * (double)y1 + 2.0 * (1.0 - t) * t * (double)control.y + t * t * (double)y2);
                GL11.glVertex2i((int)bx, (int)by);
            }
            GL11.glEnd();
            this.setColor(color2, dim);
            GL11.glBegin((int)3);
            for (i = halfSegments; i <= segments; ++i) {
                t = (double)i / (double)segments;
                bx = (int)((1.0 - t) * (1.0 - t) * (double)x1 + 2.0 * (1.0 - t) * t * (double)control.x + t * t * (double)x2);
                by = (int)((1.0 - t) * (1.0 - t) * (double)y1 + 2.0 * (1.0 - t) * t * (double)control.y + t * t * (double)y2);
                GL11.glVertex2i((int)bx, (int)by);
            }
            GL11.glEnd();
            GL11.glEnable((int)3553);
            GL11.glDisable((int)2848);
            GL11.glDisable((int)3042);
            GL11.glPopAttrib();
        } else {
            double d2;
            int color = this.getConnectionColor(conn);
            if (!this.useColorScaling) {
                color = -1;
            }
            Point cp1 = this.computeControlPoint(x1, y1, x2, y2, effectiveCurveAngle);
            Point cp2 = this.computeControlPoint(x1, y1, x2, y2, -effectiveCurveAngle);
            double d1 = this.getMinDistanceToIcon(cp1, conn);
            Point cp = d1 >= (d2 = this.getMinDistanceToIcon(cp2, conn)) ? cp1 : cp2;
            int segments = 800;
            GL11.glPushAttrib((int)57344);
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            GL11.glEnable((int)2848);
            GL11.glHint((int)3154, (int)4354);
            GL11.glDisable((int)3553);
            GL11.glLineWidth((float)this.lineThickness);
            this.setColor(color, dim);
            GL11.glBegin((int)3);
            for (int i = 0; i <= segments; ++i) {
                double t = (double)i / (double)segments;
                int bx = (int)((1.0 - t) * (1.0 - t) * (double)x1 + 2.0 * (1.0 - t) * t * (double)cp.x + t * t * (double)x2);
                int by = (int)((1.0 - t) * (1.0 - t) * (double)y1 + 2.0 * (1.0 - t) * t * (double)cp.y + t * t * (double)y2);
                GL11.glVertex2i((int)bx, (int)by);
            }
            GL11.glEnd();
            GL11.glEnable((int)3553);
            GL11.glDisable((int)2848);
            GL11.glDisable((int)3042);
            GL11.glPopAttrib();
        }
    }

    private void drawColoredLine(int x1, int y1, int x2, int y2, int color, boolean dim) {
        GL11.glPushAttrib((int)8192);
        GL11.glDisable((int)3553);
        GL11.glLineWidth((float)this.lineThickness);
        this.setColor(color, dim);
        GL11.glBegin((int)1);
        GL11.glVertex2i((int)x1, (int)y1);
        GL11.glVertex2i((int)x2, (int)y2);
        GL11.glEnd();
        GL11.glEnable((int)3553);
        GL11.glPopAttrib();
    }

    private void setColor(int color, boolean dim) {
        float r = (float)(color >> 16 & 0xFF) / 255.0f;
        float g = (float)(color >> 8 & 0xFF) / 255.0f;
        float b = (float)(color & 0xFF) / 255.0f;
        if (dim) {
            r *= 0.4f;
            g *= 0.4f;
            b *= 0.4f;
        }
        GL11.glColor4f((float)r, (float)g, (float)b, (float)1.0f);
    }

    protected void drawArrowHead(int x1, int y1, int x2, int y2, DiagramConnection conn, boolean dim) {
        double angle;
        boolean useCustomCurveAngle;
        int color;
        DiagramConnection reverse = this.getConnectionByIds(conn.idFrom, conn.idTo);
        int n = color = reverse != null ? this.getConnectionColor(reverse) : this.getConnectionColor(conn);
        if (!this.useColorScaling) {
            color = -1;
        }
        boolean separateTwoWay = reverse != null && !this.allowTwoWay;
        boolean bl = useCustomCurveAngle = conn.customAllowCurve != null ? conn.customAllowCurve : this.curvedArrows;
        if (!useCustomCurveAngle) {
            if (separateTwoWay) {
                int offsetAmount = 4;
                double dx = x2 - x1;
                double dy = y2 - y1;
                double len = Math.sqrt(dx * dx + dy * dy);
                if (len == 0.0) {
                    len = 1.0;
                }
                double offsetX = -dy / len * (double)offsetAmount;
                double offsetY = dx / len * (double)offsetAmount;
                if (conn.idFrom >= conn.idTo) {
                    offsetX = -offsetX;
                    offsetY = -offsetY;
                }
                int newX2 = x2 + (int)offsetX;
                int newY2 = y2 + (int)offsetY;
                angle = Math.atan2(newY2 - y1, newX2 - x1);
                x2 = newX2;
                y2 = newY2;
            } else {
                angle = Math.atan2(y2 - y1, x2 - x1);
            }
        } else {
            int effectiveCurveAngle;
            int n2 = effectiveCurveAngle = conn.customCurveAngle != null ? conn.customCurveAngle : this.curveAngle;
            if (separateTwoWay) {
                int offsetAmount = 4;
                double dx = x2 - x1;
                double dy = y2 - y1;
                double len = Math.sqrt(dx * dx + dy * dy);
                if (len == 0.0) {
                    len = 1.0;
                }
                double normX = -dy / len;
                double normY = dx / len;
                double sign = conn.idFrom < conn.idTo ? 1.0 : -1.0;
                Point baseControl = this.computeControlPoint(x1, y1, x2, y2, effectiveCurveAngle);
                Point offsetControl = new Point(baseControl.x + (int)(normX * (double)offsetAmount * sign), baseControl.y + (int)(normY * (double)offsetAmount * sign));
                double t = 0.95;
                double bx = (1.0 - t) * (1.0 - t) * (double)x1 + 2.0 * (1.0 - t) * t * (double)offsetControl.x + t * t * (double)x2;
                double by = (1.0 - t) * (1.0 - t) * (double)y1 + 2.0 * (1.0 - t) * t * (double)offsetControl.y + t * t * (double)y2;
                double dBx = 2.0 * (1.0 - t) * (double)(offsetControl.x - x1) + 2.0 * t * (double)(x2 - offsetControl.x);
                double dBy = 2.0 * (1.0 - t) * (double)(offsetControl.y - y1) + 2.0 * t * (double)(y2 - offsetControl.y);
                angle = Math.atan2(dBy, dBx);
            } else {
                double d2;
                Point cp1 = this.computeControlPoint(x1, y1, x2, y2, effectiveCurveAngle);
                Point cp2 = this.computeControlPoint(x1, y1, x2, y2, -effectiveCurveAngle);
                double d1 = this.getMinDistanceToIcon(cp1, conn);
                Point control = d1 >= (d2 = this.getMinDistanceToIcon(cp2, conn)) ? cp1 : cp2;
                double t = 0.95;
                double bx = (1.0 - t) * (1.0 - t) * (double)x1 + 2.0 * (1.0 - t) * t * (double)control.x + t * t * (double)x2;
                double by = (1.0 - t) * (1.0 - t) * (double)y1 + 2.0 * (1.0 - t) * t * (double)control.y + t * t * (double)y2;
                double dBx = 2.0 * (1.0 - t) * (double)(control.x - x1) + 2.0 * t * (double)(x2 - control.x);
                double dBy = 2.0 * (1.0 - t) * (double)(control.y - y1) + 2.0 * t * (double)(y2 - control.y);
                angle = Math.atan2(dBy, dBx);
            }
        }
        float defenderEdgeX = (float)x2 - (float)this.slotSize / 2.0f * (float)Math.cos(angle);
        float defenderEdgeY = (float)y2 - (float)this.slotSize / 2.0f * (float)Math.sin(angle);
        float leftX = defenderEdgeX - this.arrowSize * (float)Math.cos(angle - 0.5235987755982988);
        float leftY = defenderEdgeY - this.arrowSize * (float)Math.sin(angle - 0.5235987755982988);
        float rightX = defenderEdgeX - this.arrowSize * (float)Math.cos(angle + 0.5235987755982988);
        float rightY = defenderEdgeY - this.arrowSize * (float)Math.sin(angle + 0.5235987755982988);
        GL11.glPushAttrib((int)8192);
        GL11.glDisable((int)3553);
        float rr = (float)(color >> 16 & 0xFF) / 255.0f;
        float gg = (float)(color >> 8 & 0xFF) / 255.0f;
        float bb = (float)(color & 0xFF) / 255.0f;
        if (dim) {
            rr *= 0.4f;
            gg *= 0.4f;
            bb *= 0.4f;
        }
        GL11.glColor4f((float)rr, (float)gg, (float)bb, (float)1.0f);
        GL11.glDisable((int)2884);
        GL11.glBegin((int)4);
        GL11.glVertex2f((float)defenderEdgeX, (float)defenderEdgeY);
        GL11.glVertex2f((float)leftX, (float)leftY);
        GL11.glVertex2f((float)rightX, (float)rightY);
        GL11.glEnd();
        GL11.glEnable((int)2884);
        GL11.glEnable((int)3553);
        GL11.glPopAttrib();
    }

    private double distanceToBezier(Point start, Point control, Point end, int segments, int px, int py) {
        double minDist = Double.MAX_VALUE;
        Point prev = null;
        for (int i = 0; i <= segments; ++i) {
            double d;
            double t = (double)i / (double)segments;
            int x = (int)((1.0 - t) * (1.0 - t) * (double)start.x + 2.0 * (1.0 - t) * t * (double)control.x + t * t * (double)end.x);
            int y = (int)((1.0 - t) * (1.0 - t) * (double)start.y + 2.0 * (1.0 - t) * t * (double)control.y + t * t * (double)end.y);
            Point curr = new Point(x, y);
            if (prev != null && (d = this.distancePointToSegment(px, py, prev, curr)) < minDist) {
                minDist = d;
            }
            prev = curr;
        }
        return minDist;
    }

    private double distancePointToSegment(int px, int py, Point a, Point b) {
        double yy;
        double xx;
        double param;
        double A = px - a.x;
        double B = py - a.y;
        double C = b.x - a.x;
        double D = b.y - a.y;
        double dot = A * C + B * D;
        double lenSq = C * C + D * D;
        double d = param = lenSq != 0.0 ? dot / lenSq : -1.0;
        if (param < 0.0) {
            xx = a.x;
            yy = a.y;
        } else if (param > 1.0) {
            xx = b.x;
            yy = b.y;
        } else {
            xx = (double)a.x + param * C;
            yy = (double)a.y + param * D;
        }
        double dx = (double)px - xx;
        double dy = (double)py - yy;
        return Math.sqrt(dx * dx + dy * dy);
    }

    private void drawChartDiagram(int mouseX, int mouseY, boolean subGui) {
        int row;
        Minecraft mc = Minecraft.func_71410_x();
        ScaledResolution sr = new ScaledResolution(mc, mc.field_71443_c, mc.field_71440_d);
        int factor = sr.func_78325_e();
        GuiDiagram.func_73734_a((int)this.x, (int)this.y, (int)(this.x + this.width), (int)(this.y + this.height), (int)-13421773);
        GL11.glEnable((int)3089);
        GL11.glScissor((int)(this.x * factor), (int)((sr.func_78328_b() - (this.y + this.height)) * factor), (int)(this.width * factor), (int)(this.height * factor));
        GL11.glPushMatrix();
        int centerX = this.x + this.width / 2;
        int centerY = this.y + this.height / 2;
        GL11.glTranslatef((float)((float)centerX + this.panX), (float)((float)centerY + this.panY), (float)0.0f);
        GL11.glScalef((float)this.zoom, (float)this.zoom, (float)1.0f);
        GL11.glTranslatef((float)(-centerX), (float)(-centerY), (float)0.0f);
        List<DiagramIcon> icons = this.getIcons();
        int n = icons.size();
        int cols = n + 1;
        int rows = n + 1;
        int cellSize = Math.min(this.width / cols, this.height / rows);
        int gridWidth = cellSize * cols;
        int gridHeight = cellSize * rows;
        int startX = this.x + (this.width - gridWidth) / 2;
        int startY = this.y + (this.height - gridHeight) / 2;
        for (row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {
                int cellX = startX + col * cellSize;
                int cellY = startY + row * cellSize;
                int fillColor = -3355444;
                if (row > 0 && col > 0) {
                    DiagramIcon leftIcon = icons.get(row - 1);
                    DiagramIcon topIcon = icons.get(col - 1);
                    DiagramConnection conn = this.getConnectionByIds(leftIcon.id, topIcon.id);
                    fillColor = conn != null ? this.getConnectionColor(conn) : -8947849;
                }
                GuiDiagram.func_73734_a((int)cellX, (int)cellY, (int)(cellX + cellSize), (int)(cellY + cellSize), (int)fillColor);
            }
        }
        GL11.glPushAttrib((int)8192);
        GL11.glDisable((int)3553);
        GL11.glColor4f((float)0.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glLineWidth((float)1.0f);
        GL11.glBegin((int)1);
        for (int col = 0; col <= cols; ++col) {
            int xLine = startX + col * cellSize;
            GL11.glVertex2i((int)xLine, (int)startY);
            GL11.glVertex2i((int)xLine, (int)(startY + gridHeight));
        }
        for (row = 0; row <= rows; ++row) {
            int yLine = startY + row * cellSize;
            GL11.glVertex2i((int)startX, (int)yLine);
            GL11.glVertex2i((int)(startX + gridWidth), (int)yLine);
        }
        GL11.glEnd();
        GL11.glEnable((int)3553);
        GL11.glPopAttrib();
        float iconMargin = 1.0f;
        float iconScale = ((float)cellSize - iconMargin) / (float)this.iconSize;
        for (int i = 0; i < n; ++i) {
            int topHeaderCenterX = startX + (i + 1) * cellSize + cellSize / 2;
            int topHeaderCenterY = startY + cellSize / 2;
            GL11.glPushMatrix();
            GL11.glTranslatef((float)topHeaderCenterX, (float)topHeaderCenterY, (float)0.0f);
            GL11.glScalef((float)iconScale, (float)iconScale, (float)1.0f);
            this.renderIcon(icons.get(i), 0, 0, IconRenderState.DEFAULT);
            GL11.glPopMatrix();
            int leftHeaderCenterX = startX + cellSize / 2;
            int leftHeaderCenterY = startY + (i + 1) * cellSize + cellSize / 2;
            GL11.glPushMatrix();
            GL11.glTranslatef((float)leftHeaderCenterX, (float)leftHeaderCenterY, (float)0.0f);
            GL11.glScalef((float)iconScale, (float)iconScale, (float)1.0f);
            this.renderIcon(icons.get(i), 0, 0, IconRenderState.DEFAULT);
            GL11.glPopMatrix();
        }
        int effectiveMouseX = (int)(((float)mouseX - ((float)centerX + this.panX)) / this.zoom + (float)centerX);
        int effectiveMouseY = (int)(((float)mouseY - ((float)centerY + this.panY)) / this.zoom + (float)centerY);
        boolean hoverInGrid = effectiveMouseX >= startX && effectiveMouseX < startX + gridWidth && effectiveMouseY >= startY && effectiveMouseY < startY + gridHeight;
        int hoveredRow = -1;
        int hoveredCol = -1;
        HashSet<Integer> highlightTop = new HashSet<Integer>();
        HashSet<Integer> highlightLeft = new HashSet<Integer>();
        List<String> tooltipToShow = null;
        if (hoverInGrid) {
            int colIndex = (effectiveMouseX - startX) / cellSize;
            int rowIndex = (effectiveMouseY - startY) / cellSize;
            if (rowIndex == 0 && colIndex >= 1) {
                highlightTop.add(colIndex - 1);
                tooltipToShow = this.getIconTooltip(icons.get(colIndex - 1));
                this.onIconHover(icons.get(colIndex - 1));
            } else if (colIndex == 0 && rowIndex >= 1) {
                highlightLeft.add(rowIndex - 1);
                tooltipToShow = this.getIconTooltip(icons.get(rowIndex - 1));
                this.onIconHover(icons.get(rowIndex - 1));
            } else if (rowIndex >= 1 && colIndex >= 1) {
                hoveredRow = rowIndex - 1;
                hoveredCol = colIndex - 1;
                highlightTop.add(hoveredCol);
                highlightLeft.add(hoveredRow);
                DiagramIcon leftIcon = icons.get(hoveredRow);
                DiagramIcon topIcon = icons.get(hoveredCol);
                DiagramConnection conn = this.getConnectionByIds(leftIcon.id, topIcon.id);
                if (conn != null) {
                    tooltipToShow = this.getConnectionTooltip(conn);
                    this.onConnectionHover(conn);
                }
            }
        }
        GL11.glPushAttrib((int)8192);
        GL11.glDisable((int)3553);
        GL11.glColor4f((float)0.0f, (float)0.0f, (float)0.0f, (float)0.5f);
        GL11.glBegin((int)7);
        for (int r = 1; r < rows; ++r) {
            for (int c = 1; c < cols; ++c) {
                boolean keepBright = false;
                if (!highlightTop.isEmpty() && highlightTop.contains(c - 1)) {
                    keepBright = true;
                }
                if (!highlightLeft.isEmpty() && highlightLeft.contains(r - 1)) {
                    keepBright = true;
                }
                if (hoveredRow != -1 && hoveredCol != -1 && (r - 1 == hoveredRow || c - 1 == hoveredCol)) {
                    keepBright = true;
                }
                if (keepBright) continue;
                int cellX = startX + c * cellSize;
                int cellY = startY + r * cellSize;
                GL11.glVertex2i((int)cellX, (int)cellY);
                GL11.glVertex2i((int)(cellX + cellSize), (int)cellY);
                GL11.glVertex2i((int)(cellX + cellSize), (int)(cellY + cellSize));
                GL11.glVertex2i((int)cellX, (int)(cellY + cellSize));
            }
        }
        GL11.glEnd();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.3f);
        GL11.glBegin((int)7);
        for (int i = 0; i < n; ++i) {
            int hx;
            if (highlightTop.contains(i)) {
                hx = startX + (i + 1) * cellSize;
                int hy = startY;
                GL11.glVertex2i((int)hx, (int)hy);
                GL11.glVertex2i((int)(hx + cellSize), (int)hy);
                GL11.glVertex2i((int)(hx + cellSize), (int)(hy + cellSize));
                GL11.glVertex2i((int)hx, (int)(hy + cellSize));
            }
            if (!highlightLeft.contains(i)) continue;
            hx = startX;
            int hy = startY + (i + 1) * cellSize;
            GL11.glVertex2i((int)hx, (int)hy);
            GL11.glVertex2i((int)(hx + cellSize), (int)hy);
            GL11.glVertex2i((int)(hx + cellSize), (int)(hy + cellSize));
            GL11.glVertex2i((int)hx, (int)(hy + cellSize));
        }
        if (hoveredRow != -1 && hoveredCol != -1) {
            int cellX = startX + (hoveredCol + 1) * cellSize;
            int cellY = startY + (hoveredRow + 1) * cellSize;
            GL11.glVertex2i((int)cellX, (int)cellY);
            GL11.glVertex2i((int)(cellX + cellSize), (int)cellY);
            GL11.glVertex2i((int)(cellX + cellSize), (int)(cellY + cellSize));
            GL11.glVertex2i((int)cellX, (int)(cellY + cellSize));
        }
        GL11.glEnd();
        GL11.glEnable((int)3553);
        GL11.glPopAttrib();
        GL11.glPopMatrix();
        GL11.glDisable((int)3089);
        if (tooltipToShow != null && !tooltipToShow.isEmpty()) {
            this.drawHoveringText(tooltipToShow, mouseX, mouseY, mc.field_71466_p);
        }
    }

    public void drawDiagram(int mouseX, int mouseY, boolean subGui) {
        DiagramConnection conn;
        List<String> tooltip;
        int slotY;
        int slotX;
        Object pos;
        boolean allowInput;
        boolean bl = allowInput = this.parent == null || !this.parent.hasSubGui();
        if (allowInput && this.isWithin(mouseX, mouseY) && !subGui) {
            this.handleMouseScroll(Mouse.getDWheel());
        }
        if (this.layout == EnumDiagramLayout.CHART) {
            this.drawChartDiagram(mouseX, mouseY, subGui);
            return;
        }
        Minecraft mc = Minecraft.func_71410_x();
        ScaledResolution sr = new ScaledResolution(mc, mc.field_71443_c, mc.field_71440_d);
        int factor = sr.func_78325_e();
        Map<Integer, Point> positions = this.calculatePositions();
        int centerX = this.x + this.width / 2;
        int centerY = this.y + this.height / 2;
        int effectiveMouseX = (int)(((float)mouseX - ((float)centerX + this.panX)) / this.zoom + (float)centerX);
        int effectiveMouseY = (int)(((float)mouseY - ((float)centerY + this.panY)) / this.zoom + (float)centerY);
        Integer hoveredIconId = null;
        int hoveredConnFrom = -1;
        int hoveredConnTo = -1;
        HashSet<Integer> selectedIconIds = new HashSet<Integer>();
        for (DiagramIcon icon : this.getIcons()) {
            if (!icon.enabled || (pos = positions.get(icon.id)) == null) continue;
            slotX = ((Point)pos).x - this.slotSize / 2;
            slotY = ((Point)pos).y - this.slotSize / 2;
            if (effectiveMouseX < slotX || effectiveMouseX >= slotX + this.slotSize || effectiveMouseY < slotY || effectiveMouseY >= slotY + this.slotSize) continue;
            hoveredIconId = icon.id;
            selectedIconIds.add(icon.id);
            this.onIconHover(icon);
            break;
        }
        if (hoveredIconId == null) {
            double threshold = 5.0;
            pos = this.getConnections().iterator();
            while (pos.hasNext()) {
                double dist;
                boolean useCustomCurveAngle;
                DiagramConnection conn2 = pos.next();
                DiagramIcon iconFrom = this.getIconById(conn2.idFrom);
                DiagramIcon iconTo = this.getIconById(conn2.idTo);
                if (iconFrom == null || iconTo == null || !iconFrom.enabled || !iconTo.enabled) continue;
                Point pFrom = positions.get(conn2.idFrom);
                Point pTo = positions.get(conn2.idTo);
                if (pFrom == null || pTo == null) continue;
                boolean separateTwoWay = this.getConnectionByIds(conn2.idTo, conn2.idFrom) != null && !this.allowTwoWay;
                boolean bl2 = useCustomCurveAngle = conn2.customAllowCurve != null ? conn2.customAllowCurve : this.curvedArrows;
                if (separateTwoWay) {
                    double len;
                    double dy;
                    int offsetAmount;
                    if (useCustomCurveAngle) {
                        offsetAmount = 4;
                        double dx = pTo.x - pFrom.x;
                        dy = pTo.y - pFrom.y;
                        len = Math.sqrt(dx * dx + dy * dy);
                        if (len == 0.0) {
                            len = 1.0;
                        }
                        double normX = -dy / len;
                        double normY = dx / len;
                        double sign = conn2.idFrom < conn2.idTo ? 1.0 : -1.0;
                        int effectiveCurveAngle = conn2.customCurveAngle != null ? conn2.customCurveAngle : this.curveAngle;
                        Point baseControl = this.computeControlPoint(pFrom.x, pFrom.y, pTo.x, pTo.y, effectiveCurveAngle);
                        Point offsetControl = new Point(baseControl.x + (int)(normX * (double)offsetAmount * sign), baseControl.y + (int)(normY * (double)offsetAmount * sign));
                        dist = this.distanceToBezier(pFrom, offsetControl, pTo, 200, effectiveMouseX, effectiveMouseY);
                    } else {
                        offsetAmount = 4;
                        double dx = pTo.x - pFrom.x;
                        dy = pTo.y - pFrom.y;
                        len = Math.sqrt(dx * dx + dy * dy);
                        if (len == 0.0) {
                            len = 1.0;
                        }
                        double offsetX = -dy / len * (double)offsetAmount;
                        double offsetY = dx / len * (double)offsetAmount;
                        if (conn2.idFrom >= conn2.idTo) {
                            offsetX = -offsetX;
                            offsetY = -offsetY;
                        }
                        int newX1 = pFrom.x + (int)offsetX;
                        int newY1 = pFrom.y + (int)offsetY;
                        int newX2 = pTo.x + (int)offsetX;
                        int newY2 = pTo.y + (int)offsetY;
                        dist = this.pointLineDistance(effectiveMouseX, effectiveMouseY, newX1, newY1, newX2, newY2);
                    }
                } else if (useCustomCurveAngle) {
                    int effectiveCurveAngle = conn2.customCurveAngle != null ? conn2.customCurveAngle : this.curveAngle;
                    Point cp1 = this.computeControlPoint(pFrom.x, pFrom.y, pTo.x, pTo.y, effectiveCurveAngle);
                    Point cp2 = this.computeControlPoint(pFrom.x, pFrom.y, pTo.x, pTo.y, -effectiveCurveAngle);
                    double d1 = this.getMinDistanceToIcon(cp1, conn2);
                    double d2 = this.getMinDistanceToIcon(cp2, conn2);
                    Point control = d1 >= d2 ? cp1 : cp2;
                    dist = this.distanceToBezier(pFrom, control, pTo, 200, effectiveMouseX, effectiveMouseY);
                } else {
                    dist = this.pointLineDistance(effectiveMouseX, effectiveMouseY, pFrom.x, pFrom.y, pTo.x, pTo.y);
                }
                if (!(dist < 5.0)) continue;
                hoveredConnFrom = conn2.idFrom;
                hoveredConnTo = conn2.idTo;
                selectedIconIds.add(conn2.idFrom);
                selectedIconIds.add(conn2.idTo);
                this.onConnectionHover(conn2);
            }
        }
        GL11.glEnable((int)3089);
        GL11.glScissor((int)(this.x * factor), (int)((sr.func_78328_b() - (this.y + this.height)) * factor), (int)(this.width * factor), (int)(this.height * factor));
        GuiDiagram.func_73734_a((int)this.x, (int)this.y, (int)(this.x + this.width), (int)(this.y + this.height), (int)-13421773);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)centerX + this.panX), (float)((float)centerY + this.panY), (float)0.0f);
        GL11.glScalef((float)this.zoom, (float)this.zoom, (float)1.0f);
        GL11.glTranslatef((float)(-centerX), (float)(-centerY), (float)0.0f);
        for (DiagramConnection conn3 : this.getConnections()) {
            DiagramIcon iconFrom = this.getIconById(conn3.idFrom);
            DiagramIcon iconTo = this.getIconById(conn3.idTo);
            if (iconFrom == null || iconTo == null || !iconFrom.enabled || !iconTo.enabled) continue;
            Point pFrom = positions.get(conn3.idFrom);
            Point pTo = positions.get(conn3.idTo);
            if (pFrom == null || pTo == null) continue;
            int ax = pFrom.x;
            int ay = pFrom.y;
            int bx = pTo.x;
            int by = pTo.y;
            boolean dim = hoveredIconId != null ? conn3.idFrom != hoveredIconId : !selectedIconIds.isEmpty() && (!selectedIconIds.contains(conn3.idFrom) || !selectedIconIds.contains(conn3.idTo));
            this.drawConnectionLine(ax, ay, bx, by, conn3, dim);
        }
        for (DiagramIcon icon : this.getIcons()) {
            if (!icon.enabled || (pos = positions.get(icon.id)) == null) continue;
            slotX = ((Point)pos).x - this.slotSize / 2;
            slotY = ((Point)pos).y - this.slotSize / 2;
            boolean highlighted = selectedIconIds.isEmpty() || selectedIconIds.contains(icon.id);
            this.drawIconBox(slotX, slotY, this.slotSize, highlighted);
            IconRenderState state = highlighted ? IconRenderState.HIGHLIGHTED : IconRenderState.NOT_HIGHLIGHTED;
            this.renderIcon(icon, ((Point)pos).x, ((Point)pos).y, state);
        }
        GL11.glPopMatrix();
        if (this.showArrowHeads) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float)((float)centerX + this.panX), (float)((float)centerY + this.panY), (float)0.0f);
            GL11.glScalef((float)this.zoom, (float)this.zoom, (float)1.0f);
            GL11.glTranslatef((float)(-centerX), (float)(-centerY), (float)0.0f);
            for (DiagramConnection conn3 : this.getConnections()) {
                boolean showArrow;
                boolean bl3 = showArrow = conn3.showArrowHead != null ? conn3.showArrowHead : this.showArrowHeads;
                if (!showArrow) continue;
                DiagramIcon iconFrom = this.getIconById(conn3.idFrom);
                DiagramIcon iconTo = this.getIconById(conn3.idTo);
                if (iconFrom == null || iconTo == null || !iconFrom.enabled || !iconTo.enabled) continue;
                Point pFrom = positions.get(conn3.idFrom);
                Point pTo = positions.get(conn3.idTo);
                if (pFrom == null || pTo == null) continue;
                int ax = pFrom.x;
                int ay = pFrom.y;
                int bx = pTo.x;
                int by = pTo.y;
                boolean dim = hoveredIconId != null ? conn3.idFrom != hoveredIconId : !selectedIconIds.isEmpty() && (!selectedIconIds.contains(conn3.idFrom) || !selectedIconIds.contains(conn3.idTo));
                this.drawArrowHead(ax, ay, bx, by, conn3, dim);
            }
            GL11.glPopMatrix();
        }
        GL11.glDisable((int)3089);
        if (hoveredIconId != null) {
            DiagramIcon icon = this.getIconById(hoveredIconId);
            tooltip = this.getIconTooltip(icon);
            if (tooltip != null && !tooltip.isEmpty()) {
                this.drawHoveringText(tooltip, mouseX, mouseY, mc.field_71466_p);
            }
        } else if (hoveredConnFrom != -1 && hoveredConnTo != -1 && (tooltip = this.getConnectionTooltip(conn = this.getConnectionByIds(hoveredConnFrom, hoveredConnTo))) != null && !tooltip.isEmpty()) {
            this.drawHoveringText(tooltip, mouseX, mouseY, mc.field_71466_p);
        }
    }

    protected void drawIconBox(int slotX, int slotY, int slotSize, boolean highlighted) {
        int bg = highlighted ? this.mixColors(this.iconBackgroundColor, -1, 0.2f) : this.iconBackgroundColor;
        GuiDiagram.func_73734_a((int)slotX, (int)slotY, (int)(slotX + slotSize), (int)(slotY + slotSize), (int)bg);
        for (int i = 0; i < this.iconBorderThickness; ++i) {
            this.func_73730_a(slotX + i, slotX + slotSize - i, slotY + i, this.iconBorderColor);
            this.func_73730_a(slotX + i, slotX + slotSize - i, slotY + slotSize - 1 - i, this.iconBorderColor);
            this.func_73728_b(slotX + i, slotY + i, slotY + slotSize - i, this.iconBorderColor);
            this.func_73728_b(slotX + slotSize - 1 - i, slotY + i, slotY + slotSize - i, this.iconBorderColor);
        }
    }

    protected int mixColors(int color1, int color2, float ratio) {
        int a1 = color1 >> 24 & 0xFF;
        int r1 = color1 >> 16 & 0xFF;
        int g1 = color1 >> 8 & 0xFF;
        int b1 = color1 & 0xFF;
        int a2 = color2 >> 24 & 0xFF;
        int r2 = color2 >> 16 & 0xFF;
        int g2 = color2 >> 8 & 0xFF;
        int b2 = color2 & 0xFF;
        int a = (int)((float)a1 + (float)(a2 - a1) * ratio);
        int r = (int)((float)r1 + (float)(r2 - r1) * ratio);
        int g = (int)((float)g1 + (float)(g2 - g1) * ratio);
        int b = (int)((float)b1 + (float)(b2 - b1) * ratio);
        return a << 24 | r << 16 | g << 8 | b;
    }

    protected DiagramIcon getIconById(int id) {
        for (DiagramIcon icon : this.getIcons()) {
            if (icon.id != id) continue;
            return icon;
        }
        return null;
    }

    protected DiagramConnection getConnectionByIds(int idFrom, int idTo) {
        for (DiagramConnection conn : this.getConnections()) {
            if (conn.idFrom != idFrom || conn.idTo != idTo) continue;
            return conn;
        }
        return null;
    }

    protected int getConnectionColor(DiagramConnection conn) {
        int b;
        int g;
        int r;
        if (conn.customColor != null) {
            return conn.customColor;
        }
        float value = Math.max(-1.0f, Math.min(1.0f, conn.percent));
        if (value >= 0.0f) {
            float t = value;
            r = (int)(144.0f + -144.0f * t);
            g = (int)(238.0f + -138.0f * t);
            b = (int)(144.0f + -144.0f * t);
        } else {
            float t = -value;
            r = (int)(255.0f + -116.0f * t);
            g = (int)(200.0f + -200.0f * t);
            b = 0;
        }
        return 0xFF000000 | r << 16 | g << 8 | b;
    }

    private double pointLineDistance(int px, int py, int x1, int y1, int x2, int y2) {
        double yy;
        double xx;
        double param;
        double A = px - x1;
        double B = py - y1;
        double C = x2 - x1;
        double D = y2 - y1;
        double dot = A * C + B * D;
        double lenSq = C * C + D * D;
        double d = param = lenSq != 0.0 ? dot / lenSq : -1.0;
        if (param < 0.0) {
            xx = x1;
            yy = y1;
        } else if (param > 1.0) {
            xx = x2;
            yy = y2;
        } else {
            xx = (double)x1 + param * C;
            yy = (double)y1 + param * D;
        }
        double dx = (double)px - xx;
        double dy = (double)py - yy;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (this.parent != null && this.parent.hasSubGui()) {
            return false;
        }
        if (mouseButton == 0) {
            for (DiagramIcon icon : this.getIcons()) {
                Point pos;
                if (!icon.enabled || !icon.pressable || (pos = this.calculatePositions().get(icon.id)) == null) continue;
                int centerX = this.x + this.width / 2;
                int centerY = this.y + this.height / 2;
                int effectiveX = (int)(((float)mouseX - ((float)centerX + this.panX)) / this.zoom + (float)centerX);
                int effectiveY = (int)(((float)mouseY - ((float)centerY + this.panY)) / this.zoom + (float)centerY);
                int slotX = pos.x - this.slotSize / 2;
                int slotY = pos.y - this.slotSize / 2;
                if (effectiveX < slotX || effectiveX >= slotX + this.slotSize || effectiveY < slotY || effectiveY >= slotY + this.slotSize) continue;
                this.onIconClick(icon);
                this.currentlyPressedIcon = icon;
                return true;
            }
            if (this.isWithin(mouseX, mouseY)) {
                this.dragging = true;
                this.lastDragX = mouseX;
                this.lastDragY = mouseY;
                return true;
            }
        }
        return false;
    }

    public void mouseClickMove(int mouseX, int mouseY, int mouseButton, long timeSinceLastClick) {
        if (this.parent != null && this.parent.hasSubGui()) {
            return;
        }
        if (this.dragging) {
            int dx = mouseX - this.lastDragX;
            int dy = mouseY - this.lastDragY;
            this.panX += (float)dx / this.zoom * 0.7f;
            this.panY += (float)dy / this.zoom * 0.7f;
            this.lastDragX = mouseX;
            this.lastDragY = mouseY;
        }
        if (this.currentlyPressedIcon != null) {
            this.onIconHeld(this.currentlyPressedIcon);
        }
    }

    public void mouseReleased(int mouseX, int mouseY, int state) {
        if (this.parent != null && this.parent.hasSubGui()) {
            return;
        }
        if (this.currentlyPressedIcon != null) {
            this.onIconRelease(this.currentlyPressedIcon);
            this.currentlyPressedIcon = null;
        }
        this.dragging = false;
    }

    public boolean isWithin(int mouseX, int mouseY) {
        if (this.parent.hasSubGui()) {
            return false;
        }
        return mouseX >= this.x && mouseX <= this.x + this.width && mouseY >= this.y && mouseY <= this.y + this.height;
    }

    public void handleMouseScroll(int scrollDelta) {
        this.zoom += (float)scrollDelta * 9.0E-4f;
        if (this.zoom < 0.2f) {
            this.zoom = 0.2f;
        }
        if (this.zoom > 3.0f) {
            this.zoom = 3.0f;
        }
    }

    protected void drawHoveringText(List<String> textLines, int mouseX, int mouseY, FontRenderer font) {
    }

    public void setShowArrowHeads(boolean showArrowHeads) {
        this.showArrowHeads = showArrowHeads;
    }

    public void setUseColorScaling(boolean useColorScaling) {
        this.useColorScaling = useColorScaling;
    }

    protected void onIconClick(DiagramIcon icon) {
    }

    protected void onIconHeld(DiagramIcon icon) {
    }

    protected void onIconRelease(DiagramIcon icon) {
    }

    protected void onIconHover(DiagramIcon icon) {
    }

    protected void onConnectionHover(DiagramConnection conn) {
    }

    public static enum IconRenderState {
        DEFAULT,
        HIGHLIGHTED,
        NOT_HIGHLIGHTED;

    }

    public static class DiagramConnection {
        public int idFrom;
        public int idTo;
        public float percent;
        public String hoverText;
        public Integer customCurveAngle = null;
        public Integer customColor = null;
        public Boolean customAllowCurve = null;
        public Boolean showArrowHead = null;

        public DiagramConnection(int idFrom, int idTo, float percent, String hoverText) {
            this.idFrom = idFrom;
            this.idTo = idTo;
            this.percent = percent;
            this.hoverText = hoverText;
        }
    }

    public static class DiagramIcon {
        public int id;
        public boolean enabled = true;
        public boolean pressable = false;
        public int index = 0;
        public int priority = 0;

        public DiagramIcon(int id) {
            this.id = id;
        }

        public DiagramIcon(int id, int index, int priority) {
            this.id = id;
            this.index = index;
            this.priority = priority;
        }
    }
}

