/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.controllers.data.action;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Date;
import java.util.Deque;
import java.util.List;
import java.util.ListIterator;
import noppes.npcs.LogWriter;
import noppes.npcs.api.handler.data.IAction;
import noppes.npcs.api.handler.data.IActionQueue;
import noppes.npcs.config.ConfigDebug;
import noppes.npcs.controllers.data.action.ActionManager;
import noppes.npcs.controllers.data.action.ActionThread;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

public class ActionLogger {
    private final ActionManager manager;
    private final Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
    private TreeNode root;
    private TreeNode currentNode;
    private static final String ANSI_CYAN = "\u001b[96m";
    private static final String ANSI_GOLD = "\u001b[93m";
    private static final String ANSI_RESET = "\u001b[0m";

    public ActionLogger(ActionManager manager) {
        this.manager = manager;
    }

    public ActionLogger beginTick(ActionManager manager) {
        this.stack.clear();
        this.root = this.currentNode = new TreeNode(manager);
        for (IActionQueue queue : manager.getAllQueues()) {
            if (!queue.hasActiveTasks()) continue;
            TreeNode queueNode = this.root.addChild(queue);
            for (IAction action : queue.getQueue()) {
                queueNode.addChild(action);
            }
        }
        return this;
    }

    public ActionLogger push(Object component) {
        for (TreeNode child : this.currentNode.children) {
            if (!child.component.equals(component)) continue;
            this.currentNode = child;
            break;
        }
        this.stack.push(this.currentNode);
        return this;
    }

    public ActionLogger pop() {
        if (!this.stack.isEmpty()) {
            this.currentNode = this.stack.pop();
        }
        return this;
    }

    public ActionLogger finish(String finished, Object component) {
        this.log(finished, component);
        this.log(StringUtils.repeat("-", finished.length()), component, true);
        return this;
    }

    public ActionLogger log(String message, Object source) {
        return this.log(message, source, false);
    }

    public ActionLogger log(String message, Object source, boolean isLast) {
        StringBuilder prefix = new StringBuilder();
        ListIterator<TreeNode> it = new ArrayList<TreeNode>(this.stack).listIterator(this.stack.size());
        while (it.hasPrevious()) {
            TreeNode node = it.previous();
            if (it.hasPrevious()) {
                prefix.append("\u2502    ");
                continue;
            }
            prefix.append(isLast ? "\u2514\u2500\u2500\u2500\u2500 " : "\u251c\u2500\u2500\u2500\u2500 ");
        }
        String header = this.getLogHeader(source);
        this.println(prefix + header + message);
        return this;
    }

    public void error(String err, Throwable t) {
        if (this.manager.reportTo != null) {
            this.manager.reportTo.appendConsole(err + (t != null ? "\n" + ExceptionUtils.getStackTrace(t) : ""));
        }
        LogWriter.error(err);
        if (t != null) {
            t.printStackTrace();
        }
    }

    private String getLogHeader(Object obj) {
        String type = obj.getClass().getSimpleName();
        String name = "";
        if (obj instanceof ActionManager) {
            name = ((ActionManager)obj).getName();
        } else if (obj instanceof IActionQueue) {
            name = ((IActionQueue)obj).getName();
        } else if (obj instanceof IAction) {
            name = ((IAction)obj).getName();
        } else if (obj instanceof ActionThread) {
            name = ((ActionThread)obj).getAction().getName();
        }
        return String.format("[%s/'%s'] ", type, name);
    }

    public void println(String str) {
        if (ConfigDebug.LogActionManager) {
            System.out.printf("[%tT] %s[%s]:%s %s%s%n", new Date(), ANSI_CYAN, Thread.currentThread().getName(), ANSI_GOLD, str, ANSI_RESET);
        }
    }

    static class TreeNode {
        public final Object component;
        public final List<TreeNode> children = new ArrayList<TreeNode>();
        public TreeNode parent;

        public boolean isLastChild() {
            if (this.parent == null) {
                return true;
            }
            List<TreeNode> siblings = this.parent.children;
            return siblings.get(siblings.size() - 1) == this;
        }

        public TreeNode(Object component) {
            this.component = component;
        }

        public TreeNode addChild(Object component) {
            TreeNode child = new TreeNode(component);
            this.children.add(child);
            child.parent = this;
            return child;
        }
    }
}

