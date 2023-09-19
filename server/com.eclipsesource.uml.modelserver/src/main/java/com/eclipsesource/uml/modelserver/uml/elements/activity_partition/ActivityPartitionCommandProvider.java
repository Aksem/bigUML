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
package com.eclipsesource.uml.modelserver.uml.elements.activity_partition;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.glsp.graph.GPoint;
import org.eclipse.glsp.graph.util.GraphUtil;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityPartition;
import org.eclipse.uml2.uml.Element;

import com.eclipsesource.uml.modelserver.shared.codec.ContributionDecoder;
import com.eclipsesource.uml.modelserver.shared.model.ModelContext;
import com.eclipsesource.uml.modelserver.shared.notation.commands.AddShapeNotationCommand;
import com.eclipsesource.uml.modelserver.shared.semantic.BaseCreateSemanticChildCommand;
import com.eclipsesource.uml.modelserver.uml.command.provider.element.NodeCommandProvider;
import com.eclipsesource.uml.modelserver.uml.elements.activity_partition.commands.CreateActivityPartitionSemanticCommand;
import com.eclipsesource.uml.modelserver.uml.elements.activity_partition.commands.CreateNestedActivityPartitionSemanticCommand;
import com.eclipsesource.uml.modelserver.uml.elements.activity_partition.commands.UpdateActivityPartitionArgument;
import com.eclipsesource.uml.modelserver.uml.elements.activity_partition.commands.UpdateActivityPartitionSemanticCommand;

public class ActivityPartitionCommandProvider extends NodeCommandProvider<ActivityPartition, Element> {

   @Override
   protected Collection<Command> createModifications(final ModelContext context, final Element parent,
      final GPoint position) {
      BaseCreateSemanticChildCommand<?, ?> semantic = null;

      if (parent instanceof Activity) {
         semantic = new CreateActivityPartitionSemanticCommand(context, (Activity) parent);
      } else if (parent instanceof ActivityPartition) {
         semantic = new CreateNestedActivityPartitionSemanticCommand(context, (ActivityPartition) parent);
      } else {
         throw new IllegalArgumentException(String.format("Parent %s can not be handled", parent.getClass().getName()));
      }

      var notation = new AddShapeNotationCommand(
         context, semantic::getSemanticElement, position, GraphUtil.dimension(160, 50));
      return List.of(semantic, notation);
   }

   @Override
   protected Collection<Command> updateModifications(final ModelContext context, final ActivityPartition element) {
      var decoder = new ContributionDecoder(context);
      var update = decoder.embedJson(UpdateActivityPartitionArgument.class);
      return List.of(new UpdateActivityPartitionSemanticCommand(context, element, update));
   }
}