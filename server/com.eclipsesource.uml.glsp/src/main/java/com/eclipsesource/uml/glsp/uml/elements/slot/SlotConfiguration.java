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
package com.eclipsesource.uml.glsp.uml.elements.slot;

import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.glsp.graph.DefaultTypes;
import org.eclipse.glsp.graph.GraphPackage;
import org.eclipse.glsp.server.types.ShapeTypeHint;
import org.eclipse.uml2.uml.Slot;

import com.eclipsesource.uml.glsp.uml.configuration.RepresentationNodeConfiguration;
import com.eclipsesource.uml.glsp.uml.utils.QualifiedUtil;
import com.eclipsesource.uml.modelserver.unotation.Representation;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public class SlotConfiguration extends RepresentationNodeConfiguration<Slot> {
   @Inject
   public SlotConfiguration(@Assisted final Representation representation) {
      super(representation);
   }

   @Override
   public String typeId() {
      return QualifiedUtil.representationTypeId(representation, DefaultTypes.COMPARTMENT,
         getElementType().getSimpleName());
   }

   public String definingFeatureTypeId() {
      return QualifiedUtil.representationTypeId(Representation.CLASS, DefaultTypes.LABEL,
         getElementType().getSimpleName() + "-defining-feature");
   }

   public enum Property {
      DEFINING_FEATURE,
      VALUES;
   }

   @Override
   public Map<String, EClass> getTypeMappings() { return Map.of(
      typeId(), GraphPackage.Literals.GCOMPARTMENT); }

   @Override
   public Set<String> getGraphContainableElements() { return Set.of(); }

   @Override
   public Set<ShapeTypeHint> getShapeTypeHints() {
      return Set.of(
         new ShapeTypeHint(typeId(), false, true, false, false, existingConfigurationTypeIds(Set.of())));
   }
}
