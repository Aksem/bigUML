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
package com.eclipsesource.uml.glsp.uml.configuration;

import java.util.Set;

import org.eclipse.emf.ecore.EObject;

public interface ElementConfiguration<TElement extends EObject> {
   Class<? extends TElement> getElementType();

   default Set<Class<? extends TElement>> getElementTypes() { return Set.of(getElementType()); }

   String typeId();

   default Set<String> allTypeIds() {
      return Set.of(typeId());
   }
}
