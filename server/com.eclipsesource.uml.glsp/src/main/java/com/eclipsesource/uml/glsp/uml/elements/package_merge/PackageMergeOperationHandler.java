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
package com.eclipsesource.uml.glsp.uml.elements.package_merge;

import org.eclipse.uml2.uml.PackageMerge;

import com.eclipsesource.uml.glsp.uml.configuration.ElementConfigurationRegistry;
import com.eclipsesource.uml.glsp.uml.handler.element.EdgeOperationHandler;
import com.eclipsesource.uml.modelserver.shared.registry.RepresentationKey;
import com.eclipsesource.uml.modelserver.unotation.Representation;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public class PackageMergeOperationHandler
   extends EdgeOperationHandler<PackageMerge, org.eclipse.uml2.uml.Package, org.eclipse.uml2.uml.Package> {

   @Inject
   public PackageMergeOperationHandler(@Assisted final Representation representation,
      final ElementConfigurationRegistry registry) {
      super(representation, registry.accessTyped(representation, PackageMerge.class).typeId());
   }
}