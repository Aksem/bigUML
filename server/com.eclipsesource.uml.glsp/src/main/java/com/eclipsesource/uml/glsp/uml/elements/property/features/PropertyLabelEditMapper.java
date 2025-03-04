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
package com.eclipsesource.uml.glsp.uml.elements.property.features;

import java.util.Optional;

import org.eclipse.glsp.server.features.directediting.ApplyLabelEditOperation;
import org.eclipse.uml2.uml.Property;

import com.eclipsesource.uml.glsp.core.constants.CoreTypes;
import com.eclipsesource.uml.glsp.core.gmodel.suffix.NameLabelSuffix;
import com.eclipsesource.uml.glsp.core.handler.operation.update.UpdateOperation;
import com.eclipsesource.uml.glsp.uml.elements.property.PropertyConfiguration;
import com.eclipsesource.uml.glsp.uml.elements.property.PropertyOperationHandler;
import com.eclipsesource.uml.glsp.uml.elements.property.gmodel.suffix.PropertyMultiplicityLabelSuffix;
import com.eclipsesource.uml.glsp.uml.elements.property.gmodel.suffix.PropertyTypeLabelSuffix;
import com.eclipsesource.uml.glsp.uml.features.label_edit.RepresentationLabelEditMapper;
import com.eclipsesource.uml.glsp.uml.utils.MultiplicityUtil;
import com.eclipsesource.uml.modelserver.uml.elements.property.commands.UpdatePropertyArgument;
import com.eclipsesource.uml.modelserver.unotation.Representation;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public class PropertyLabelEditMapper extends RepresentationLabelEditMapper<Property> {

   @Inject
   public PropertyLabelEditMapper(@Assisted final Representation representation) {
      super(representation);
   }

   @Override
   public Optional<UpdateOperation> map(final ApplyLabelEditOperation operation) {
      var handler = getHandler(PropertyOperationHandler.class, operation);
      UpdateOperation update = null;

      if (matches(operation, CoreTypes.LABEL_NAME, NameLabelSuffix.SUFFIX)) {
         update = handler.withArgument(
            UpdatePropertyArgument.by()
               .name(operation.getText())
               .build());
      } else if (matches(operation, configuration(PropertyConfiguration.class).multiplicityTypeId(),
         PropertyMultiplicityLabelSuffix.SUFFIX)) {
         update = handler.withArgument(
            UpdatePropertyArgument.by()
               .upperBound(MultiplicityUtil.getUpper(operation.getText()))
               .lowerBound(MultiplicityUtil.getLower(operation.getText()))
               .build());
      } else if (matches(operation, configuration(PropertyConfiguration.class).typeTypeId(),
         PropertyTypeLabelSuffix.SUFFIX)) {
         update = handler.withArgument(
            UpdatePropertyArgument.by()
               .typeId(operation.getText())
               .build());
      }

      return withContext(update);
   }
}
