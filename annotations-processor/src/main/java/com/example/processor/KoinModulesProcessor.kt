package com.example.processor

import com.example.annotations.KoinModules
import com.example.processor.KoinModulesProcessor.Companion.KAPT_KOTLIN_GENERATED_OPTION_NAME
import com.google.auto.service.AutoService
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asTypeName
import java.lang.reflect.Method
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.annotation.processing.SupportedOptions
import javax.annotation.processing.SupportedSourceVersion
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.ElementKind
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic

@AutoService(Processor::class)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedOptions(KAPT_KOTLIN_GENERATED_OPTION_NAME)
class KoinModulesProcessor : AbstractProcessor() {
    override fun process(p0: MutableSet<out TypeElement>?, p1: RoundEnvironment): Boolean {

        val generatedSourceRoot: String = processingEnv.options[KAPT_KOTLIN_GENERATED_OPTION_NAME].orEmpty()
        if (generatedSourceRoot.isEmpty()) {
            processingEnv.messager.printMessage(Diagnostic.Kind.ERROR, "Can't find the target directory for generated kotlin files")
            return false
        }


        val elements: MutableSet<out Element> = p1.getElementsAnnotatedWith(KoinModules::class.java)
        val fields = elements.filter { it.kind == ElementKind.FIELD }
//        val pacote = processingEnv.elementUtils.getPackageOf(fields.first()).toString()
        processingEnv.typeUtils.unboxedType(fields.first().asType())
        val first = fields.first().kind.declaringClass.fields.first()
//        processingEnv.typeUtils.getDeclaredType(fields.first().asType())


        return true
    }

    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        return mutableSetOf(KoinModules::class.java.name)
    }

    private fun processingAnnotated(element: Element) {
//        val className = element.simpleName.toString()
        val `package` = processingEnv.elementUtils.getPackageOf(element).toString()


        val fileName = "KoinModuleGenerated"
        val classBuilder = TypeSpec.Companion.classBuilder(fileName)



//        element.enclosedElements.forEach { enclosed ->
//           if(enclosed.kind == ElementKind.FIELD){
//               classBuilder.addProperty(
//                   PropertySpec.builder("modules", MutableList<String>::class, KModifier.PUBLIC)
//                       .build()
//               )
//           }
//
//        }
    }

    companion object {
        const val KAPT_KOTLIN_GENERATED_OPTION_NAME = "kapt.kotlin.generated"
    }
}