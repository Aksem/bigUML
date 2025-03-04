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
package com.eclipsesource.uml.modelserver.uml.elements.pin.commands;

import org.eclipse.uml2.uml.InputPin;
import org.eclipse.uml2.uml.OpaqueAction;

import com.eclipsesource.uml.modelserver.shared.model.ModelContext;
import com.eclipsesource.uml.modelserver.shared.semantic.BaseCreateSemanticChildCommand;

public class CreateInputPinSemanticCommand
   extends BaseCreateSemanticChildCommand<OpaqueAction, InputPin> {
   public CreateInputPinSemanticCommand(final ModelContext context, final OpaqueAction parent) {
      super(context, parent);
   }

   @Override
   protected InputPin createSemanticElement(final OpaqueAction parent) {
      return parent.createInputValue(null, null);
   }
}
