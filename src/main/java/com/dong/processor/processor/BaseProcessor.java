package com.dong.processor.processor;

import com.sun.tools.javac.api.JavacTrees;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Types;
import com.sun.tools.javac.model.JavacElements;
import com.sun.tools.javac.model.JavacTypes;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.Names;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.tools.Diagnostic;

public abstract class BaseProcessor extends AbstractProcessor {

    /**
     * 用于在编译器打印消息的组件
     */
    Messager messager;

    Filer filer;

    JavacElements elementUtils;

    JavacTypes typeUtils;

    /**
     * 语法树
     */
    JavacTrees trees;

    /**
     * 用来构造语法树节点
     */
    TreeMaker treeMaker;

    /**
     * 用于创建标识符的对象
     */
    Names names;

    /**
     * 获取一些注解处理器执行处理逻辑时需要用到的一些关键对象
     *
     * @param processingEnv 处理环境
     */
    @Override
    public final synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        this.messager = processingEnv.getMessager();
        this.trees = JavacTrees.instance(processingEnv);
        Context context = ((JavacProcessingEnvironment) processingEnv).getContext();
        this.treeMaker = TreeMaker.instance(context);
        this.names = Names.instance(context);
        elementUtils = (JavacElements) processingEnv.getElementUtils();
        filer = processingEnv.getFiler();
        typeUtils = (JavacTypes) processingEnv.getTypeUtils();
    }


//    JCTree.JCExpression fromObjectType(String... names) {
//        Symbol.ClassSymbol typeElement = elementUtils.getTypeElement("java.util.List");
//        messager.printMessage(Diagnostic.Kind.WARNING, typeElement.type.stringValue());
//    }
}