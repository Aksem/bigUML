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
package com.eclipsesource.uml.modelserver.commands.activitydiagram.datanode;

import java.util.function.Supplier;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityParameterNode;
import org.eclipse.uml2.uml.UMLFactory;

import com.eclipsesource.uml.modelserver.commands.commons.semantic.UmlSemanticElementCommand;
import com.eclipsesource.uml.modelserver.commands.util.UmlSemanticCommandUtil;

public class AddParameterCommand extends UmlSemanticElementCommand implements Supplier<ActivityParameterNode> {

   private final Activity activity;
   private ActivityParameterNode param;

   public AddParameterCommand(final EditingDomain domain, final URI modelUri, final String actionUri) {
      super(domain, modelUri);
      activity = UmlSemanticCommandUtil.getElement(umlModel, actionUri, Activity.class);
   }

   @Override
   protected void doExecute() {
      param = UMLFactory.eINSTANCE.createActivityParameterNode();
      long count = activity.getOwnedNodes().stream().filter(ActivityParameterNode.class::isInstance).count();
      param.setName("NewParameter" + count);
      activity.getOwnedNodes().add(param);
   }

   @Override
   public ActivityParameterNode get() {
      return param;
   }

}