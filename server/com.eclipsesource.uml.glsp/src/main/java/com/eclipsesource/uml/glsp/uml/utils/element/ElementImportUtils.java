/********************************************************************************
 * Copyright (c) 2023 EclipseSource and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0, or the MIT License which is
 * available at https://opensource.org/licenses/MIT.
 *
 * SPDX-License-Identifier: EPL-2.0 OR MIT
 ********************************************************************************/
package com.eclipsesource.uml.glsp.uml.utils.element;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.glsp.server.emf.EMFIdGenerator;
import org.eclipse.uml2.uml.ElementImport;
import org.eclipse.uml2.uml.PackageableElement;

import com.eclipsesource.uml.glsp.features.property_palette.model.ElementReferencePropertyItem;

public class ElementImportUtils {

   public static List<ElementReferencePropertyItem.Reference> asReferenceFromElementImports(
      final List<ElementImport> packageImports,
      final EMFIdGenerator idGenerator) {
      var references = packageImports.stream()
         .map(v -> {
            var label = String.format("<Element Import> %s", v.getImportedElement().getName());
            return new ElementReferencePropertyItem.Reference.Builder(idGenerator.getOrCreateId(v), label).name(
               v.getImportedElement().getName()).build();
         })
         .collect(Collectors.toList());

      return references;
   }

   public static List<ElementReferencePropertyItem.Reference> asReferenceFromPackageableElement(
      final List<PackageableElement> packages,
      final EMFIdGenerator idGenerator) {
      var references = packages.stream()
         .map(v -> {
            var label = String.format("<Packageable Element> %s", v.getName());
            return new ElementReferencePropertyItem.Reference.Builder(idGenerator.getOrCreateId(v), label)
               .name(v.getName()).build();
         })
         .collect(Collectors.toList());

      return references;
   }
}
