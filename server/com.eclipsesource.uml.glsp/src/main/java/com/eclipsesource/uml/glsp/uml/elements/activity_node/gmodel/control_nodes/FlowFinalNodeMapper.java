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
package com.eclipsesource.uml.glsp.uml.elements.activity_node.gmodel.control_nodes;

import org.eclipse.glsp.graph.GNode;
import org.eclipse.glsp.graph.builder.impl.GNodeBuilder;
import org.eclipse.uml2.uml.FlowFinalNode;

import com.eclipsesource.uml.glsp.core.constants.CoreCSS;
import com.eclipsesource.uml.glsp.uml.elements.activity_node.ActivityNodeConfiguration;
import com.eclipsesource.uml.glsp.uml.gmodel.RepresentationGNodeMapper;
import com.eclipsesource.uml.glsp.uml.gmodel.element.NamedElementGBuilder;
import com.eclipsesource.uml.modelserver.unotation.Representation;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public class FlowFinalNodeMapper extends RepresentationGNodeMapper<FlowFinalNode, GNode>
   implements NamedElementGBuilder<FlowFinalNode> {

   @Inject
   public FlowFinalNodeMapper(@Assisted final Representation representation) {
      super(representation);
   }

   @Override
   public GNode map(final FlowFinalNode source) {

      var builder = new GNodeBuilder(configuration(ActivityNodeConfiguration.class).flowFinalNodeTypeId())
         .id(idGenerator.getOrCreateId(source))
         .addCssClass(CoreCSS.NODE);

      applyShapeNotation(source, builder);

      return builder.build();
   }

}
