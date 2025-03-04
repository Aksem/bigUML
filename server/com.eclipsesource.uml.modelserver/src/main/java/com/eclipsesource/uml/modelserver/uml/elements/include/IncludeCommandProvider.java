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
package com.eclipsesource.uml.modelserver.uml.elements.include;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.uml2.uml.Include;
import org.eclipse.uml2.uml.UseCase;

import com.eclipsesource.uml.modelserver.shared.codec.ContributionDecoder;
import com.eclipsesource.uml.modelserver.shared.model.ModelContext;
import com.eclipsesource.uml.modelserver.shared.notation.commands.AddEdgeNotationCommand;
import com.eclipsesource.uml.modelserver.uml.command.provider.element.EdgeCommandProvider;
import com.eclipsesource.uml.modelserver.uml.elements.include.commands.CreateIncludeSemanticCommand;
import com.eclipsesource.uml.modelserver.uml.elements.include.commands.UpdateIncludeArgument;
import com.eclipsesource.uml.modelserver.uml.elements.include.commands.UpdateIncludeSemanticCommand;

public class IncludeCommandProvider extends EdgeCommandProvider<Include, UseCase, UseCase> {
   @Override
   protected Collection<Command> createModifications(final ModelContext context, final UseCase source,
      final UseCase target) {
      var semantic = new CreateIncludeSemanticCommand(context, source,
         target);
      var notation = new AddEdgeNotationCommand(context, semantic::getSemanticElement);
      return List.of(semantic, notation);
   }

   @Override
   protected Collection<Command> updateModifications(final ModelContext context, final Include element) {
      var decoder = new ContributionDecoder(context);
      var update = decoder.embedJson(UpdateIncludeArgument.class);
      return List.of(new UpdateIncludeSemanticCommand(context, element, update));
   }
}
