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
package com.eclipsesource.uml.glsp.uml.elements.model;

import com.eclipsesource.uml.glsp.uml.configuration.di.NodeConfigurationFactory;
import com.eclipsesource.uml.glsp.uml.elements.model.features.ModelLabelEditMapper;
import com.eclipsesource.uml.glsp.uml.elements.model.features.ModelPropertyMapper;
import com.eclipsesource.uml.glsp.uml.elements.model.gmodel.ModelNodeMapper;
import com.eclipsesource.uml.glsp.uml.features.label_edit.di.LabelEditMapperFactory;
import com.eclipsesource.uml.glsp.uml.features.property_palette.di.PropertyMapperFactory;
import com.eclipsesource.uml.glsp.uml.gmodel.di.GModelMapperFactory;
import com.eclipsesource.uml.glsp.uml.handler.di.NodeOperationHandlerFactory;
import com.eclipsesource.uml.modelserver.unotation.Representation;

public interface ModelFactory
   extends NodeConfigurationFactory, NodeOperationHandlerFactory, GModelMapperFactory, PropertyMapperFactory,
   LabelEditMapperFactory {
   @Override
   ModelConfiguration nodeConfiguration(Representation representation);

   @Override
   ModelNodeMapper gmodel(Representation representation);

   @Override
   ModelOperationHandler nodeOperationHandler(Representation representation);

   @Override
   ModelLabelEditMapper labelEditMapper(Representation representation);

   @Override
   ModelPropertyMapper elementPropertyMapper(Representation representation);
}
