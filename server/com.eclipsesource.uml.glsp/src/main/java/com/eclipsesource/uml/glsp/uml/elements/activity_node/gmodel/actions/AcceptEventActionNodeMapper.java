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
package com.eclipsesource.uml.glsp.uml.elements.activity_node.gmodel.actions;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.glsp.graph.GCompartment;
import org.eclipse.glsp.graph.GModelElement;
import org.eclipse.glsp.graph.GNode;
import org.eclipse.glsp.graph.builder.impl.GNodeBuilder;
import org.eclipse.glsp.graph.util.GConstants;
import org.eclipse.uml2.uml.AcceptEventAction;

import com.eclipsesource.uml.glsp.core.constants.CoreCSS;
import com.eclipsesource.uml.glsp.uml.elements.activity_node.ActivityNodeConfiguration;
import com.eclipsesource.uml.glsp.uml.gmodel.RepresentationGNodeMapper;
import com.eclipsesource.uml.glsp.uml.gmodel.element.NamedElementGBuilder;
import com.eclipsesource.uml.modelserver.unotation.Representation;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public class AcceptEventActionNodeMapper extends RepresentationGNodeMapper<AcceptEventAction, GNode>
   implements NamedElementGBuilder<AcceptEventAction> {

   @Inject
   public AcceptEventActionNodeMapper(@Assisted final Representation representation) {
      super(representation);
   }

   @Override
   public GNode map(final AcceptEventAction source) {
      var builder = new GNodeBuilder(configuration(ActivityNodeConfiguration.class).acceptEventActionTypeId())
         .id(idGenerator.getOrCreateId(source))
         .layout(GConstants.Layout.VBOX)
         .addCssClass(CoreCSS.NODE)
         .addCssClass(CoreCSS.NODE_CONTAINER)
         .add(buildHeader(source));

      applyShapeNotation(source, builder);

      return builder.build();
   }

   @Override
   public List<GModelElement> mapSiblings(final AcceptEventAction source) {
      var siblings = new ArrayList<GModelElement>();
      siblings.addAll(mapHandler.handle(source.getOutgoings()));
      return siblings;
   }

   protected GCompartment buildHeader(final AcceptEventAction source) {
      var header = compartmentHeaderBuilder(source)
         .layout(GConstants.Layout.VBOX);

      header.add(nameBuilder(source).build());

      return header.build();
   }
}
