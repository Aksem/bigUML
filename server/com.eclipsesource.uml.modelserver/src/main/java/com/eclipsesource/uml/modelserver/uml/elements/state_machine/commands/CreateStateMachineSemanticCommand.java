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
package com.eclipsesource.uml.modelserver.uml.elements.state_machine.commands;

import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.UMLFactory;

import com.eclipsesource.uml.modelserver.shared.model.ModelContext;
import com.eclipsesource.uml.modelserver.shared.semantic.BaseCreateSemanticChildCommand;
import com.eclipsesource.uml.modelserver.uml.generator.ListNameGenerator;

public class CreateStateMachineSemanticCommand extends BaseCreateSemanticChildCommand<Model, StateMachine> {

   public CreateStateMachineSemanticCommand(final ModelContext context, final Model parent) {
      super(context, parent);
   }

   @Override
   protected StateMachine createSemanticElement(final Model parent) {
      var nameGenerator = new ListNameGenerator(StateMachine.class, parent.getPackagedElements());
      var stateMachine = UMLFactory.eINSTANCE.createStateMachine();

      stateMachine.setName(nameGenerator.newName());
      parent.getPackagedElements().add(stateMachine);

      return stateMachine;
   }

}
