/********************************************************************************
 * Copyright (c) 2022 EclipseSource and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0, or the MIT License which is
 * available at https://opensource.org/licenses/MIT.
 *
 * SPDX-License-Identifier: EPL-2.0 OR MIT
 ********************************************************************************/
package com.eclipsesource.uml.glsp.uml.elements.enumeration_literal.gmodel;

import org.eclipse.glsp.graph.GCompartment;
import org.eclipse.glsp.graph.builder.impl.GCompartmentBuilder;
import org.eclipse.glsp.graph.builder.impl.GLayoutOptions;
import org.eclipse.glsp.graph.util.GConstants;
import org.eclipse.uml2.uml.EnumerationLiteral;

import com.eclipsesource.uml.glsp.uml.gmodel.RepresentationGModelMapper;
import com.eclipsesource.uml.glsp.uml.gmodel.element.NamedElementGBuilder;
import com.eclipsesource.uml.modelserver.unotation.Representation;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public final class EnumerationLiteralCompartmentMapper
   extends RepresentationGModelMapper<EnumerationLiteral, GCompartment>
   implements NamedElementGBuilder<EnumerationLiteral> {

   @Inject
   public EnumerationLiteralCompartmentMapper(@Assisted final Representation representation) {
      super(representation);
   }

   @Override
   public GCompartment map(final EnumerationLiteral source) {
      var builder = new GCompartmentBuilder(configuration().typeId())
         .id(idGenerator.getOrCreateId(source))
         .layout(GConstants.Layout.HBOX)
         .layoutOptions(new GLayoutOptions()
            .hGap(3)
            .resizeContainer(true))
         .add(iconFromCssPropertyBuilder(source, "--uml-enumeration-literal-icon").build())
         .add(nameBuilder(source).build());

      return builder.build();
   }
}
