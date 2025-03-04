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
package com.eclipsesource.uml.glsp.uml.elements.parameter;

import com.eclipsesource.uml.glsp.uml.configuration.di.NodeConfigurationFactory;
import com.eclipsesource.uml.glsp.uml.elements.parameter.features.ParameterPropertyMapper;
import com.eclipsesource.uml.glsp.uml.features.property_palette.di.PropertyMapperFactory;
import com.eclipsesource.uml.glsp.uml.handler.di.NodeOperationHandlerFactory;
import com.eclipsesource.uml.modelserver.unotation.Representation;

public interface ParameterFactory
   extends NodeConfigurationFactory, NodeOperationHandlerFactory, PropertyMapperFactory {
   @Override
   ParameterConfiguration nodeConfiguration(Representation representation);

   @Override
   ParameterOperationHandler nodeOperationHandler(Representation representation);

   @Override
   ParameterPropertyMapper elementPropertyMapper(Representation representation);
}
