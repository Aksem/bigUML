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
package com.eclipsesource.uml.glsp.uml.elements.final_state;

import com.eclipsesource.uml.glsp.uml.configuration.di.NodeConfigurationFactory;
import com.eclipsesource.uml.glsp.uml.elements.final_state.gmodel.FinalStateNodeMapper;
import com.eclipsesource.uml.glsp.uml.gmodel.di.GModelMapperFactory;
import com.eclipsesource.uml.glsp.uml.handler.di.NodeOperationHandlerFactory;
import com.eclipsesource.uml.modelserver.unotation.Representation;

public interface FinalStateFactory
   extends NodeConfigurationFactory, NodeOperationHandlerFactory, GModelMapperFactory {
   @Override
   FinalStateConfiguration nodeConfiguration(Representation representation);

   @Override
   FinalStateNodeMapper gmodel(Representation representation);

   @Override
   FinalStateOperationHandler nodeOperationHandler(Representation representation);
}
