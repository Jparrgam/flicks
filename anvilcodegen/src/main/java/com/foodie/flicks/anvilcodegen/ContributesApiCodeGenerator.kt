package com.foodie.flicks.anvilcodegen

import com.foodie.flicks.daggerscopes.ContributesApi
import com.google.auto.service.AutoService
import com.squareup.anvil.annotations.ContributesTo
import com.squareup.anvil.annotations.ExperimentalAnvilApi
import com.squareup.anvil.compiler.api.AnvilContext
import com.squareup.anvil.compiler.api.CodeGenerator
import com.squareup.anvil.compiler.api.GeneratedFile
import com.squareup.anvil.compiler.api.createGeneratedFile
import com.squareup.anvil.compiler.internal.buildFile
import com.squareup.anvil.compiler.internal.fqName
import com.squareup.anvil.compiler.internal.reference.ClassReference
import com.squareup.anvil.compiler.internal.reference.asClassName
import com.squareup.anvil.compiler.internal.reference.classAndInnerClassReferences
import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asClassName
import dagger.Module
import dagger.Provides
import dagger.Reusable
import org.jetbrains.kotlin.psi.KtFile
import retrofit2.Retrofit
import java.io.File

@OptIn(ExperimentalAnvilApi::class)
private val contributesApiFqName = ContributesApi::class.fqName

@OptIn(ExperimentalAnvilApi::class)
@Suppress("unused")
@AutoService(CodeGenerator::class)
class ContributesApiCodeGenerator : CodeGenerator {

    override fun isApplicable(context: AnvilContext) = true

    override fun generateCode(
        codeGenDir: File,
        module: org.jetbrains.kotlin.descriptors.ModuleDescriptor,
        projectFiles: Collection<KtFile>
    ): Collection<GeneratedFile> {
        return projectFiles.classAndInnerClassReferences(module)
            .filter { it.isAnnotatedWith(contributesApiFqName) }
            .map { generateModule(it, codeGenDir) }
            .toList()
    }

    private fun generateModule(
        apiClass: ClassReference,
        codeGenDir: File
    ): GeneratedFile {
        val generatedPackage = apiClass.packageFqName.toString()
        //FeedListService_Module
        val moduleClassName = "${apiClass.shortName}_Module"

        val scope = apiClass.annotations
            .single { it.fqName == contributesApiFqName }
            .scope()
            .asClassName()

        val content = FileSpec.buildFile(generatedPackage, moduleClassName) {
            addType(
                TypeSpec.objectBuilder(moduleClassName)
                    .addAnnotation(Module::class)
                    .addAnnotation(
                        AnnotationSpec.builder(ContributesTo::class).addMember("%T::class", scope)
                            .build()
                    )
                    //provideFeedListService()
                    .addFunction(
                        FunSpec.builder("provide${apiClass.shortName}")
                            .addParameter("retrofit", Retrofit::class.asClassName())
                            //FeedListService::class
                            .returns(apiClass.asClassName())
                            .addAnnotation(Provides::class)
                            .addAnnotation(Reusable::class)
                            .addCode(
                                "return retrofit.create(%T::class.java)",
                                apiClass.asClassName()
                            )
                            .build(),
                    )
                    .build(),
            )
        }
        return createGeneratedFile(codeGenDir, generatedPackage, moduleClassName, content)
    }
}