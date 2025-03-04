package com.eclipsesource.uml.modelserver.uml.elements.package_merge.commands;

import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageMerge;

import com.eclipsesource.uml.modelserver.shared.model.ModelContext;
import com.eclipsesource.uml.modelserver.shared.semantic.BaseCreateSemanticRelationCommand;

public final class CreatePackageMergeSemanticCommand
   extends BaseCreateSemanticRelationCommand<PackageMerge, Package, Package> {

   public CreatePackageMergeSemanticCommand(final ModelContext context,
      final Package source, final Package target) {
      super(context, source, target);
   }

   @Override
   protected PackageMerge createSemanticElement(final Package source, final Package target) {
      return source.createPackageMerge(target);
   }

}
