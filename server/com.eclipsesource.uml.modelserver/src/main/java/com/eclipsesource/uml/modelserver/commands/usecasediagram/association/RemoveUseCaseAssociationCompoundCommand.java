/********************************************************************************
 * Copyright (c) 2021 EclipseSource and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0, or the MIT License which is
 * available at https://opensource.org/licenses/MIT.
 *
 * SPDX-License-Identifier: EPL-2.0 OR MIT
 ********************************************************************************/
package com.eclipsesource.uml.modelserver.commands.usecasediagram.association;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.edit.domain.EditingDomain;

public class RemoveUseCaseAssociationCompoundCommand extends CompoundCommand {

   public RemoveUseCaseAssociationCompoundCommand(final EditingDomain domain, final URI modelUri,
                                                  final String semanticUriFragment) {
      this.append(new RemoveUseCaseAssociationCommand(domain, modelUri, semanticUriFragment));
      this.append(new RemoveUseCaseAssociationEdgeCommand(domain, modelUri, semanticUriFragment));

      // TODO Make sure to remove also all other AssociationEnds (not only the ownedByAssociation Ends!
   }

}