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
package com.eclipsesource.uml.modelserver.shared.semantic;

import java.util.function.Consumer;

import com.eclipsesource.uml.modelserver.shared.model.ModelContext;

public class CallbackSemanticCommand extends BaseSemanticElementCommand {
   Consumer<ModelContext> callback;

   public CallbackSemanticCommand(final ModelContext context, final Consumer<ModelContext> callback) {
      super(context);
      this.callback = callback;
   }

   @Override
   protected void doExecute() {
      callback.accept(context);
   }

}
