/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package org.spongepowered.libraries.com.google.common.util.concurrent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import org.spongepowered.libraries.com.google.common.annotations.GwtCompatible;
import org.spongepowered.libraries.com.google.common.base.Optional;
import org.spongepowered.libraries.com.google.common.base.Preconditions;
import org.spongepowered.libraries.com.google.common.collect.ImmutableCollection;
import org.spongepowered.libraries.com.google.common.collect.ImmutableList;
import org.spongepowered.libraries.com.google.common.collect.Lists;
import org.spongepowered.libraries.com.google.common.util.concurrent.AggregateFuture;
import org.spongepowered.libraries.com.google.common.util.concurrent.ListenableFuture;

@GwtCompatible(emulated=true)
abstract class CollectionFuture<V, C>
extends AggregateFuture<V, C> {
    CollectionFuture() {
    }

    static final class ListFuture<V>
    extends CollectionFuture<V, List<V>> {
        ListFuture(ImmutableCollection<? extends ListenableFuture<? extends V>> futures, boolean allMustSucceed) {
            this.init(new ListFutureRunningState(futures, allMustSucceed));
        }

        private final class ListFutureRunningState
        extends CollectionFutureRunningState {
            ListFutureRunningState(ImmutableCollection<? extends ListenableFuture<? extends V>> futures, boolean allMustSucceed) {
                super(futures, allMustSucceed);
            }

            public List<V> combine(List<Optional<V>> values) {
                ArrayList<Object> result = Lists.newArrayListWithCapacity(values.size());
                for (Optional element : values) {
                    result.add(element != null ? (Object)element.orNull() : null);
                }
                return Collections.unmodifiableList(result);
            }
        }
    }

    abstract class CollectionFutureRunningState
    extends AggregateFuture.RunningState {
        private List<Optional<V>> values;

        CollectionFutureRunningState(ImmutableCollection<? extends ListenableFuture<? extends V>> futures, boolean allMustSucceed) {
            super(futures, allMustSucceed, true);
            this.values = futures.isEmpty() ? ImmutableList.of() : Lists.newArrayListWithCapacity(futures.size());
            for (int i = 0; i < futures.size(); ++i) {
                this.values.add(null);
            }
        }

        final void collectOneValue(boolean allMustSucceed, int index, @Nullable V returnValue) {
            List localValues = this.values;
            if (localValues != null) {
                localValues.set(index, Optional.fromNullable(returnValue));
            } else {
                Preconditions.checkState(allMustSucceed || CollectionFuture.this.isCancelled(), "Future was done before all dependencies completed");
            }
        }

        @Override
        final void handleAllCompleted() {
            List localValues = this.values;
            if (localValues != null) {
                CollectionFuture.this.set(this.combine(localValues));
            } else {
                Preconditions.checkState(CollectionFuture.this.isDone());
            }
        }

        @Override
        void releaseResourcesAfterFailure() {
            super.releaseResourcesAfterFailure();
            this.values = null;
        }

        abstract C combine(List<Optional<V>> var1);
    }
}

