/********************************************************************************
 * Copyright (c) 2022 EclipseSource and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0, or the MIT License which is
 * available at https://opensource.org/licenses/MIT.
 *
 * SPDX-License-Identifier: EPL-2.0 OR MIT
 ********************************************************************************/
package com.eclipsesource.uml.glsp.core.handler.operation.reconnect_edge;

import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.glsp.server.operations.ReconnectEdgeOperation;

public interface DiagramReconnectEdgeHandler<TElementType extends EObject> {
   Set<Class<? extends TElementType>> getElementTypes();

   void handleReconnect(ReconnectEdgeOperation operation);
}
