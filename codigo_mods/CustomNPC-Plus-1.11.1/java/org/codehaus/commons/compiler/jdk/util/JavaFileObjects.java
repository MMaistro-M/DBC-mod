/*
 * Decompiled with CFR 0.152.
 */
package org.codehaus.commons.compiler.jdk.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.NestingKind;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import org.codehaus.commons.compiler.io.Readers;
import org.codehaus.commons.compiler.util.resource.Resource;
import org.codehaus.commons.compiler.util.resource.ResourceCreator;
import org.codehaus.commons.nullanalysis.Nullable;

public final class JavaFileObjects {
    private JavaFileObjects() {
    }

    public static JavaFileObject fromResource(Resource resource, String className, JavaFileObject.Kind kind, Charset charset) {
        return new ResourceJavaFileObject(resource, className, kind, charset);
    }

    public static JavaFileObject fromUrl(final URL url, final String name, final JavaFileObject.Kind kind) {
        URI subresourceUri;
        try {
            subresourceUri = url.toURI();
        }
        catch (URISyntaxException use) {
            throw new AssertionError((Object)use);
        }
        JavaFileObject result = new JavaFileObject(){

            @Override
            public URI toUri() {
                return subresourceUri;
            }

            @Override
            public String getName() {
                return name;
            }

            @Override
            public InputStream openInputStream() throws IOException {
                return url.openStream();
            }

            @Override
            public JavaFileObject.Kind getKind() {
                return kind;
            }

            @Override
            public OutputStream openOutputStream() {
                throw new UnsupportedOperationException();
            }

            @Override
            public Reader openReader(boolean ignoreEncodingErrors) {
                throw new UnsupportedOperationException();
            }

            @Override
            public CharSequence getCharContent(boolean ignoreEncodingErrors) {
                throw new UnsupportedOperationException();
            }

            @Override
            public Writer openWriter() {
                throw new UnsupportedOperationException();
            }

            @Override
            public long getLastModified() {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean delete() {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean isNameCompatible(String simpleName, JavaFileObject.Kind kind2) {
                throw new UnsupportedOperationException();
            }

            @Override
            public NestingKind getNestingKind() {
                throw new UnsupportedOperationException();
            }

            @Override
            public Modifier getAccessLevel() {
                throw new UnsupportedOperationException();
            }

            public String toString() {
                return name + " from " + this.getClass().getSimpleName();
            }
        };
        return result;
    }

    public static ByteArrayJavaFileObject inMemory(final String className, final JavaFileObject.Kind kind2, final Charset charset) {
        class MyJavaFileObject
        extends SimpleJavaFileObject
        implements ByteArrayJavaFileObject {
            private final ByteArrayOutputStream buffer;

            MyJavaFileObject() {
                super(URI.create("bytearray:///" + string.replace('.', '/') + kind.extension), kind);
                this.buffer = new ByteArrayOutputStream();
            }

            @Override
            public InputStream openInputStream() throws IOException {
                return new ByteArrayInputStream(this.toByteArray());
            }

            @Override
            public OutputStream openOutputStream() throws IOException {
                return this.buffer;
            }

            @Override
            public Reader openReader(boolean ignoreEncodingErrors) throws IOException {
                return new InputStreamReader(this.openInputStream(), charset);
            }

            @Override
            public Writer openWriter() throws IOException {
                return new OutputStreamWriter(this.openOutputStream(), charset);
            }

            @Override
            public byte[] toByteArray() {
                return this.buffer.toByteArray();
            }
        }
        return new MyJavaFileObject();
    }

    public static JavaFileObject fromResourceCreator(final ResourceCreator resourceCreator, final String resourceName, JavaFileObject.Kind kind, final Charset charset) {
        return new SimpleJavaFileObject(URI.create("bytearray:///" + resourceName), kind){

            @Override
            public OutputStream openOutputStream() throws IOException {
                return resourceCreator.createResource(resourceName);
            }

            @Override
            public Writer openWriter() throws IOException {
                return new OutputStreamWriter(this.openOutputStream(), charset);
            }
        };
    }

    public static interface ByteArrayJavaFileObject
    extends JavaFileObject {
        public byte[] toByteArray();
    }

    public static final class ResourceJavaFileObject
    extends SimpleJavaFileObject {
        private final Resource resource;
        private final Charset charset;
        private final String name;

        private ResourceJavaFileObject(Resource resource, String className, JavaFileObject.Kind kind, Charset charset) {
            super(URI.create("bytearray:///" + className.replace('.', '/') + kind.extension), kind);
            this.resource = resource;
            this.charset = charset;
            this.name = "/" + className.replace('.', '/') + kind.extension;
        }

        @Override
        public boolean isNameCompatible(@Nullable String simpleName, @Nullable JavaFileObject.Kind kind) {
            return !"module-info".equals(simpleName);
        }

        @Override
        public String getName() {
            return this.name;
        }

        @Override
        public InputStream openInputStream() throws IOException {
            return this.resource.open();
        }

        @Override
        public Reader openReader(boolean ignoreEncodingErrors) throws IOException {
            return new InputStreamReader(this.resource.open(), this.charset);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
            try (Reader r = this.openReader(true);){
                String string = Readers.readAll(r);
                return string;
            }
        }

        @Override
        public long getLastModified() {
            return this.resource.lastModified();
        }

        public String getResourceFileName() {
            return this.resource.getFileName();
        }
    }
}

