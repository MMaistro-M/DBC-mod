/*
 * Decompiled with CFR 0.152.
 */
package io.github.somehussar.janinoloader.annotations;

import io.github.somehussar.janinoloader.annotations.PermissableScriptHandler;
import java.io.IOException;
import java.io.Writer;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.MirroredTypeException;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

@SupportedAnnotationTypes(value={"io.github.somehussar.janinoloader.annotations.PermissableScriptHandler"})
@SupportedSourceVersion(value=SourceVersion.RELEASE_8)
public class PermissableProcessor
extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (TypeElement typeElement : annotations) {
            for (Element element : roundEnv.getElementsAnnotatedWith(typeElement)) {
                if (element.getKind() != ElementKind.CLASS) continue;
                TypeElement classElement = (TypeElement)element;
                for (ExecutableElement method : ElementFilter.methodsIn(classElement.getEnclosedElements())) {
                    if (!method.getSimpleName().toString().equals("prepareScriptToUnload")) continue;
                    this.processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "Method 'prepareScriptToUnload' is reserved and should not be implemented here", method);
                    return true;
                }
            }
        }
        for (Element element : roundEnv.getElementsAnnotatedWith(PermissableScriptHandler.class)) {
            if (!(element instanceof TypeElement)) continue;
            this.generatePermissibleWrapper((TypeElement)element);
        }
        return true;
    }

    private void generatePermissibleWrapper(TypeElement original) {
        try {
            String interfaceName;
            String pkg;
            block20: {
                pkg = this.processingEnv.getElementUtils().getPackageOf(original).getQualifiedName().toString();
                PermissableScriptHandler annotation = original.getAnnotation(PermissableScriptHandler.class);
                interfaceName = null;
                if (annotation != null) {
                    try {
                        Class<?> iface = annotation.implementedInterface();
                        if (iface != Void.class) {
                            interfaceName = iface.getCanonicalName();
                        }
                    }
                    catch (MirroredTypeException mte) {
                        TypeMirror tm = mte.getTypeMirror();
                        if (tm.toString().equals("java.lang.Void")) break block20;
                        interfaceName = mte.toString();
                    }
                }
            }
            String originalName = original.getSimpleName().toString();
            String wrapperName = "Permissible" + originalName;
            StringBuilder src = new StringBuilder();
            src.append("package ").append(pkg).append(";\n\n");
            src.append("import org.codehaus.commons.compiler.Sandbox;\n");
            src.append("import io.github.somehussar.janinoloader.api.script.IScriptClassBody;\n");
            src.append("public final class ").append(wrapperName);
            if (interfaceName != null) {
                src.append(" implements ").append(interfaceName);
            }
            src.append(" {\n\n");
            src.append("    private final Sandbox sandbox;\n");
            src.append("    private final IScriptClassBody<").append(originalName).append("> scriptBody;\n\n");
            src.append("    public ").append(wrapperName).append("(Sandbox sandbox, ").append("IScriptClassBody<").append(originalName).append("> scriptBody) {\n").append("       this.sandbox = sandbox;\n").append("       this.scriptBody = scriptBody;\n").append("   }\n\n");
            for (ExecutableElement method : ElementFilter.methodsIn(original.getEnclosedElements())) {
                if (method.getModifiers().contains((Object)Modifier.PRIVATE) || method.getModifiers().contains((Object)Modifier.STATIC)) continue;
                this.generateMethod(method, src);
            }
            src.append("}\n");
            JavaFileObject file = this.processingEnv.getFiler().createSourceFile(pkg + "." + wrapperName, original);
            try (Writer writer = file.openWriter();){
                writer.write(src.toString());
            }
        }
        catch (IOException e) {
            this.processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "Failed generating permissible wrapper: " + e.getMessage());
        }
    }

    private void generateMethod(ExecutableElement method, StringBuilder src) {
        String returnType = method.getReturnType().toString();
        boolean returns = method.getReturnType().getKind() != TypeKind.VOID;
        src.append("    public ").append(returnType).append(" ").append(method.getSimpleName()).append("(");
        StringBuilder argList = new StringBuilder();
        boolean first = true;
        for (VariableElement variableElement : method.getParameters()) {
            if (!first) {
                src.append(", ");
            }
            src.append(variableElement.asType().toString()).append(" ").append(variableElement.getSimpleName());
            if (!first) {
                argList.append(", ");
            }
            argList.append(variableElement.getSimpleName());
            first = false;
        }
        src.append(") {\n");
        if (returns) {
            src.append("        return sandbox.confine(").append("(java.security.PrivilegedAction<").append(this.boxIfPrimitive(method.getReturnType())).append(">) () -> scriptBody.get().").append(method.getSimpleName()).append("(").append((CharSequence)argList).append(")").append(");\n");
        } else {
            src.append("        sandbox.confine(").append("(java.security.PrivilegedAction<Void>) () -> { ").append("scriptBody.get().").append(method.getSimpleName()).append("(").append((CharSequence)argList).append("); return null; }").append(");\n");
        }
        src.append("    }\n\n");
    }

    private String boxIfPrimitive(TypeMirror type) {
        switch (type.getKind()) {
            case INT: {
                return "Integer";
            }
            case LONG: {
                return "Long";
            }
            case BOOLEAN: {
                return "Boolean";
            }
            case BYTE: {
                return "Byte";
            }
            case SHORT: {
                return "Short";
            }
            case CHAR: {
                return "Character";
            }
            case FLOAT: {
                return "Float";
            }
            case DOUBLE: {
                return "Double";
            }
            case VOID: {
                return "Void";
            }
        }
        return type.toString();
    }
}

