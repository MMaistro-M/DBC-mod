/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.controllers.data.action;

import java.util.LinkedList;
import java.util.function.Consumer;
import noppes.npcs.api.handler.data.IActionQueue;
import noppes.npcs.controllers.data.action.Action;

public class ActionList {
    protected final LinkedList<Action> list = new LinkedList();
    protected final Action first;
    protected IActionQueue originalQueue;

    public ActionList(Action action) {
        this.first = action;
        this.list.addFirst(this.first);
        this.originalQueue = action.getQueue();
    }

    public Action after(Action current, Action aft) {
        int currentIndex = this.list.indexOf(current);
        if (currentIndex == -1) {
            this.list.addLast(aft);
        } else {
            this.list.add(currentIndex + 1, aft);
        }
        aft.unscheduledList = this;
        return aft;
    }

    public Action before(Action current, Action bef) {
        int currentIndex = this.list.indexOf(current);
        if (currentIndex == -1) {
            this.list.addLast(bef);
        } else {
            this.list.add(Math.max(0, currentIndex), bef);
        }
        bef.unscheduledList = this;
        return bef;
    }

    public ActionList scheduleAll(IActionQueue queue) {
        this.list.forEach((? super T act) -> act.schedule(queue));
        return this;
    }

    public ActionList forEach(Consumer<Action> task) {
        this.list.forEach(task);
        return this;
    }

    public ActionList kill() {
        this.list.clear();
        return this;
    }
}

