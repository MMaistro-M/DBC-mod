/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.controllers.data.action;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import noppes.npcs.api.handler.data.IAction;
import noppes.npcs.api.handler.data.IActionListener;
import noppes.npcs.api.handler.data.IActionQueue;
import noppes.npcs.controllers.data.action.ActionManager;
import noppes.npcs.controllers.data.action.ActionQueue;

public class ActionListener
implements IActionListener {
    private static final ActionManager manager = ActionManager.GLOBAL;
    private final Object object;
    private final Map<String, Hook> hooks = new ConcurrentHashMap<String, Hook>();

    public ActionListener(Object target) {
        this.object = target;
    }

    public void tick() {
        Iterator<Map.Entry<String, Hook>> it = this.hooks.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Hook> entry = it.next();
            Hook hook = entry.getValue();
            ActionQueue hookQueue = (ActionQueue)hook.queue;
            hookQueue.tick();
            if (!hookQueue.isDead()) continue;
            hookQueue.clear();
            if (ActionListener.manager.debug) {
                ActionListener.manager.LOGGER.log(String.format("Removing queue '%s'", hookQueue.getName()), manager);
            }
            it.remove();
        }
    }

    public Object getObject() {
        return this.object;
    }

    public Hook getOrCreateHook(String hookName) {
        return this.hooks.computeIfAbsent(hookName, x$0 -> new Hook((String)x$0));
    }

    public Hook getHook(String hookName) {
        return this.hooks.get(hookName);
    }

    public boolean fire(String hookName) {
        Hook hook = this.hooks.get(hookName);
        if (hook != null) {
            hook.fire();
            return true;
        }
        return false;
    }

    public IAction schedule(String hookName, IAction action) {
        return this.getOrCreateHook(hookName).schedule(action);
    }

    public class Hook {
        public final String name;
        public final IActionQueue queue;

        public Hook(String hookName) {
            this.name = hookName;
            String queueName = String.format("ActionListener#%s:%s", ActionListener.this.object, hookName);
            this.queue = manager.createQueue(queueName).stopWhenEmpty(true).killWhenEmpty(false);
        }

        public String getName() {
            return this.name;
        }

        public void fire() {
            this.queue.getManager().start();
            this.queue.start();
        }

        public IAction schedule(IAction action) {
            return this.queue.schedule(action);
        }
    }
}

