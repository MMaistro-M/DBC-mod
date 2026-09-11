/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.geckolib3.watchers;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractDirWatcher
extends Thread {
    private WatchService watcher;
    private final Map<WatchKey, Path> keys = new HashMap<WatchKey, Path>();
    private boolean recursive;

    private void register(Path dir) {
        try {
            WatchKey key = dir.register(this.watcher, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);
            this.keys.put(key, dir);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private void registerAll(Path start) {
        try {
            Files.walkFileTree(start, (FileVisitor<? super Path>)new SimpleFileVisitor<Path>(){

                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                    AbstractDirWatcher.this.register(dir);
                    return FileVisitResult.CONTINUE;
                }
            });
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public AbstractDirWatcher(Path dir, boolean recursive) {
        try {
            this.watcher = FileSystems.getDefault().newWatchService();
            this.recursive = recursive;
            if (recursive) {
                this.registerAll(dir);
            } else {
                this.register(dir);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    @Override
    public void run() {
        try {
            WatchKey key;
            while ((key = this.watcher.take()) != null) {
                this.processKey(key);
                key.reset();
                boolean valid = key.reset();
                if (valid) continue;
                this.keys.remove(key);
                if (!this.keys.isEmpty()) continue;
                break;
            }
        }
        catch (Exception ignored) {
            System.out.println("COLLAPSED");
        }
    }

    public void processKey(WatchKey key) {
        for (WatchEvent<?> event : key.pollEvents()) {
            String name = ((Path)event.context()).getFileName().toString();
            Path dir = this.keys.get(key);
            Path child = dir.resolve(name);
            if (this.recursive && event.kind() == StandardWatchEventKinds.ENTRY_CREATE && Files.isDirectory(child, LinkOption.NOFOLLOW_LINKS)) {
                this.registerAll(child);
            }
            if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
                this.processCreate(child.toFile());
            }
            if (event.kind() == StandardWatchEventKinds.ENTRY_DELETE) {
                this.processDelete(child.toFile());
            }
            if (event.kind() != StandardWatchEventKinds.ENTRY_MODIFY) continue;
            this.processModify(child.toFile());
        }
    }

    public abstract void processCreate(File var1);

    public abstract void processDelete(File var1);

    public abstract void processModify(File var1);
}

