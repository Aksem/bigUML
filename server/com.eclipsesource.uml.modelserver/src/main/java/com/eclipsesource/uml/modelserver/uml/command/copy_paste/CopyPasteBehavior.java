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
package com.eclipsesource.uml.modelserver.uml.command.copy_paste;

import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;

import com.eclipsesource.uml.modelserver.shared.model.ModelContext;

public interface CopyPasteBehavior {
   boolean shouldSuspend(ModelContext context, UmlCopier copier, EObject original);

   boolean removeFromSuspension(ModelContext context, UmlCopier copier, EObject original);

   default List<Command> modifyReferences(final ModelContext context, final UmlCopier copier) {
      return List.of();
   }
}
