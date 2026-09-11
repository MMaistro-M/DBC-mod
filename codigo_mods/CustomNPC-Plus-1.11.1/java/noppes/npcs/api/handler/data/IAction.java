/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.handler.data;

import java.util.function.Consumer;
import java.util.function.Function;
import noppes.npcs.api.handler.IActionManager;
import noppes.npcs.api.handler.data.IActionQueue;
import noppes.npcs.api.handler.data.actions.IConditionalAction;

public interface IAction {
    public IActionQueue getQueue();

    public IAction setQueue(IActionQueue var1);

    public IAction setTask(Consumer<IAction> var1);

    public IActionManager getManager();

    public boolean isScheduled();

    public int getCount();

    public IAction onStart(Consumer<IAction> var1);

    public IAction onDone(Consumer<IAction> var1);

    public int getDuration();

    public String getName();

    public int getMaxDuration();

    public IAction setMaxDuration(int var1);

    public int getMaxCount();

    public IAction times(int var1);

    public IAction once();

    public void markDone();

    public boolean isDone();

    public void kill();

    public Object getData(String var1);

    public IAction setData(String var1, Object var2);

    public IAction removeData(String var1);

    public IAction copyDataTo(IAction var1);

    public String printData();

    public boolean hasData(String var1);

    public int getUpdateEvery();

    public IAction updateEvery(int var1);

    public IAction everyTick();

    public IAction everySecond();

    public int getStartAfterTicks();

    public IAction pauseFor(int var1);

    public IAction pauseFor(long var1);

    public void pause();

    public void pauseUntil(Function<IAction, Boolean> var1);

    public void resume();

    public boolean isPaused();

    public String getIdentifier();

    public IAction threadify();

    public IAction start();

    public IAction getNext();

    public IAction getPrevious();

    public IAction after(IAction var1);

    public void after(IAction ... var1);

    public void after(Consumer<IAction> ... var1);

    public IAction after(String var1, int var2, int var3, Consumer<IAction> var4);

    public IAction after(String var1, int var2, Consumer<IAction> var3);

    public IAction after(int var1, Consumer<IAction> var2);

    public IAction after(String var1, Consumer<IAction> var2);

    public IAction after(Consumer<IAction> var1);

    public IAction before(IAction var1);

    public IAction before(String var1, int var2, int var3, Consumer<IAction> var4);

    public IAction before(String var1, int var2, Consumer<IAction> var3);

    public IAction before(int var1, Consumer<IAction> var2);

    public IAction before(String var1, Consumer<IAction> var2);

    public IAction before(Consumer<IAction> var1);

    public IConditionalAction conditional(IConditionalAction var1);

    public void conditional(IConditionalAction ... var1);

    public IConditionalAction conditional(Function<IAction, Boolean> var1, Consumer<IAction> var2);

    public IConditionalAction conditional(String var1, Function<IAction, Boolean> var2, Consumer<IAction> var3);

    public IConditionalAction conditional(Function<IAction, Boolean> var1, Consumer<IAction> var2, Function<IAction, Boolean> var3);

    public IConditionalAction conditional(String var1, Function<IAction, Boolean> var2, Consumer<IAction> var3, Function<IAction, Boolean> var4);

    public IConditionalAction conditional(Function<IAction, Boolean> var1, Consumer<IAction> var2, Function<IAction, Boolean> var3, Consumer<IAction> var4);

    public IConditionalAction conditional(String var1, Function<IAction, Boolean> var2, Consumer<IAction> var3, Function<IAction, Boolean> var4, Consumer<IAction> var5);

    public IAction parallel(IAction var1);

    public void parallel(IAction ... var1);

    public IAction parallel(Consumer<IAction> var1);

    public void parallel(Consumer<IAction> ... var1);

    public IAction parallel(int var1, Consumer<IAction> var2);

    public IAction parallel(String var1, Consumer<IAction> var2);

    public IAction parallel(String var1, int var2, Consumer<IAction> var3);

    public IAction parallel(String var1, int var2, int var3, Consumer<IAction> var4);
}

