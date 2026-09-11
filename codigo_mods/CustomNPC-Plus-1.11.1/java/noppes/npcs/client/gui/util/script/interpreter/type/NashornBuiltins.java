/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.interpreter.type;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import noppes.npcs.client.gui.util.script.interpreter.type.ClassTypeInfo;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeInfo;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeResolver;
import noppes.npcs.client.gui.util.script.interpreter.type.synthetic.SyntheticMethod;
import noppes.npcs.client.gui.util.script.interpreter.type.synthetic.SyntheticParameter;
import noppes.npcs.client.gui.util.script.interpreter.type.synthetic.SyntheticType;
import noppes.npcs.client.gui.util.script.interpreter.type.synthetic.SyntheticTypeBuilder;

public class NashornBuiltins {
    private static NashornBuiltins instance;
    private final Map<String, SyntheticType> builtinTypes = new LinkedHashMap<String, SyntheticType>();
    private final Map<String, SyntheticMethod> globalFunctions = new LinkedHashMap<String, SyntheticMethod>();

    private NashornBuiltins() {
        this.initializeBuiltins();
    }

    public static NashornBuiltins getInstance() {
        if (instance == null) {
            instance = new NashornBuiltins();
        }
        return instance;
    }

    private void initializeBuiltins() {
        SyntheticType javaType = new SyntheticTypeBuilder("Java").documentation("Nashorn's Java interop object for accessing Java types and utilities.").addMethod("type").parameter("className", "String", "Fully-qualified Java class name").returns("java.lang.Class").returnsResolved(args -> {
            if (args != null && args.length > 0 && args[0] != null) {
                String className = args[0];
                if (className.startsWith("\"") && className.endsWith("\"")) {
                    className = className.substring(1, className.length() - 1);
                } else if (className.startsWith("'") && className.endsWith("'")) {
                    className = className.substring(1, className.length() - 1);
                }
                TypeInfo resolved = TypeResolver.getInstance().resolve(className);
                if (resolved != null && resolved.isResolved()) {
                    return new ClassTypeInfo(resolved);
                }
            }
            return null;
        }).documentation("Loads a Java class by its fully-qualified name.\n\n**Usage:**\n```javascript\nvar File = Java.type(\"java.io.File\");\nvar file = new File(\"path/to/file\");\n```\n\n@param className The fully-qualified Java class name\n@returns The Java class reference (use with 'new' to create instances)").done().addMethod("extend").parameter("type", "java.lang.Class", "Java class or interface to extend/implement").returns("java.lang.Class").documentation("Creates a subclass or implementation of a Java class or interface.\n\n**Usage:**\n```javascript\nvar MyRunnable = Java.extend(Java.type(\"java.lang.Runnable\"), {\n    run: function() {\n        print(\"Running!\");\n    }\n});\n```\n\n@param type The Java class or interface to extend\n@returns A new class that extends/implements the given type").done().addMethod("from").parameter("javaArray", "Object", "A Java array or Collection").returns("Object[]").documentation("Converts a Java array or Collection to a JavaScript array.\n\n**Usage:**\n```javascript\nvar jsList = Java.from(javaArrayList);\njsList.forEach(function(item) { print(item); });\n```\n\n@param javaArray A Java array or java.util.Collection\n@returns A JavaScript array containing the elements").done().addMethod("to").parameter("jsArray", "Object[]", "A JavaScript array").parameter("javaType", "Class", "The target Java array type").returns("Object").documentation("Converts a JavaScript array to a Java array of the specified type.\n\n**Usage:**\n```javascript\nvar jsArray = [1, 2, 3];\nvar intArray = Java.to(jsArray, \"int[]\");\n```\n\n@param jsArray A JavaScript array\n@param javaType The target Java array type (e.g., \"int[]\", \"java.lang.String[]\")\n@returns A Java array of the specified type").done().addMethod("super").parameter("object", "Object", "A Java object created via Java.extend()").returns("Object").documentation("Gets a reference to the super class for calling super methods.\n\n**Usage:**\n```javascript\nvar MyList = Java.extend(Java.type(\"java.util.ArrayList\"), {\n    add: function(e) {\n        print(\"Adding: \" + e);\n        return Java.super(this).add(e);\n    }\n});\n```\n\n@param object An extended Java object\n@returns A reference to call super methods").done().addMethod("synchronized").parameter("func", "java.util.function.Function", "The function to synchronize").parameter("lock", "Object", "The object to synchronize on").returns("java.util.function.Function").documentation("Wraps a function to execute synchronized on a given object.\n\n**Usage:**\n```javascript\nvar syncFunc = Java.synchronized(function() {\n    // Thread-safe code here\n}, lockObject);\n```\n\n@param func The function to synchronize\n@param lock The object to use as the monitor\n@returns A synchronized version of the function").done().build();
        this.builtinTypes.put("Java", javaType);
        SyntheticType printFunc = new SyntheticTypeBuilder("print").addMethod("print").parameter("message", "Object", "The message to print").returns("void").documentation("Prints a message to standard output.\n\n**Usage:**\n```javascript\nprint(\"Hello, world!\");\nprint(myObject);\n```").done().build();
        this.globalFunctions.put("print", printFunc.getMethod("print"));
        SyntheticType loadFunc = new SyntheticTypeBuilder("load").addMethod("load").parameter("script", "String", "Path or URL to the script").returns("Object").documentation("Loads and executes a script file or URL.\n\n**Usage:**\n```javascript\nload(\"./myScript.js\");\nload(\"http://example.com/script.js\");\n```\n\n@param script Path to a local script or URL\n@returns The result of the script execution").done().build();
        this.globalFunctions.put("load", loadFunc.getMethod("load"));
    }

    public SyntheticType getBuiltinType(String name) {
        return this.builtinTypes.get(name);
    }

    public boolean isBuiltinType(String name) {
        return this.builtinTypes.containsKey(name);
    }

    public SyntheticMethod getGlobalFunction(String name) {
        return this.globalFunctions.get(name);
    }

    public boolean isGlobalFunction(String name) {
        return this.globalFunctions.containsKey(name);
    }

    public Set<String> getBuiltinTypeNames() {
        return Collections.unmodifiableSet(this.builtinTypes.keySet());
    }

    public Set<String> getGlobalFunctionNames() {
        return Collections.unmodifiableSet(this.globalFunctions.keySet());
    }

    public Collection<SyntheticType> getAllBuiltinTypes() {
        return Collections.unmodifiableCollection(this.builtinTypes.values());
    }

    public Map<String, SyntheticType> getAllGlobalFunctions() {
        LinkedHashMap<String, SyntheticType> result = new LinkedHashMap<String, SyntheticType>();
        for (Map.Entry<String, SyntheticMethod> entry : this.globalFunctions.entrySet()) {
            String name = entry.getKey();
            SyntheticMethod method = entry.getValue();
            SyntheticTypeBuilder builder = new SyntheticTypeBuilder(name);
            builder.documentation("Global " + name + " function");
            SyntheticTypeBuilder.MethodBuilder methodBuilder = builder.addMethod(name);
            for (SyntheticParameter param : method.parameters) {
                methodBuilder.parameter(param.name, param.typeName, param.documentation);
            }
            methodBuilder.returns(method.returnType);
            if (method.documentation != null && !method.documentation.isEmpty()) {
                methodBuilder.documentation(method.documentation);
            }
            methodBuilder.done();
            result.put(name, builder.build());
        }
        return result;
    }

    public TypeInfo resolveJavaType(String className) {
        SyntheticType javaBuiltin = this.builtinTypes.get("Java");
        if (javaBuiltin != null) {
            return javaBuiltin.resolveMethodReturnType("type", new String[]{className});
        }
        return null;
    }
}

