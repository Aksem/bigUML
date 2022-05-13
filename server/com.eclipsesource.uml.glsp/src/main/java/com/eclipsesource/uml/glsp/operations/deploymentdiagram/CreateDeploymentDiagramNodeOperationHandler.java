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
package com.eclipsesource.uml.glsp.operations.deploymentdiagram;

import com.eclipsesource.uml.glsp.model.UmlModelState;
import com.eclipsesource.uml.glsp.modelserver.UmlModelServerAccess;
import com.eclipsesource.uml.glsp.util.UmlConfig.Types;
import com.eclipsesource.uml.modelserver.unotation.Shape;
import com.google.common.collect.Lists;
import org.eclipse.emfcloud.modelserver.glsp.operations.handlers.EMSBasicCreateOperationHandler;
import org.eclipse.glsp.graph.GPoint;
import org.eclipse.glsp.graph.util.GraphUtil;
import org.eclipse.glsp.server.operations.CreateNodeOperation;
import org.eclipse.glsp.server.operations.Operation;
import org.eclipse.glsp.server.types.GLSPServerException;
import org.eclipse.uml2.uml.Artifact;
import org.eclipse.uml2.uml.Node;
import org.eclipse.uml2.uml.PackageableElement;

import java.util.List;
import java.util.Optional;

import static org.eclipse.glsp.server.types.GLSPServerException.getOrThrow;

public class CreateDeploymentDiagramNodeOperationHandler
   extends EMSBasicCreateOperationHandler<CreateNodeOperation, UmlModelServerAccess> {

   public CreateDeploymentDiagramNodeOperationHandler() {
      super(handledElementTypeIds);
   }

   private static List<String> handledElementTypeIds = Lists.newArrayList(Types.DEPLOYMENT_NODE, Types.ARTIFACT,
      Types.EXECUTION_ENVIRONMENT, Types.DEVICE, Types.DEPLOYMENT_SPECIFICATION);

   @Override
   public boolean handles(final Operation execAction) {
      if (execAction instanceof CreateNodeOperation) {
         CreateNodeOperation action = (CreateNodeOperation) execAction;
         return handledElementTypeIds.contains(action.getElementTypeId());
      }
      return false;
   }

   protected UmlModelState getUmlModelState() {
      return (UmlModelState) getEMSModelState();
   }

   @Override
   public void executeOperation(final CreateNodeOperation operation, final UmlModelServerAccess modelAccess) {

      UmlModelState modelState = getUmlModelState();

      switch (operation.getElementTypeId()) {
         case Types.DEPLOYMENT_NODE: {
            String containerId = operation.getContainerId();

            PackageableElement container = getOrThrow(modelState.getIndex().getSemantic(containerId),
               PackageableElement.class, "No valid container with id " + operation.getContainerId() + " found");

            Optional<GPoint> location = getNodePosition(modelState, container,
               operation.getLocation().orElse(GraphUtil.point(0, 0)));

            modelAccess.addNode(UmlModelState.getModelState(modelState), location, container).thenAccept(response -> {
               if (!response.body()) {
                  throw new GLSPServerException("Could not execute create operation on new Node node");
               }
            });
            break;
         }
         case Types.ARTIFACT: {
            String containerId = operation.getContainerId();

            PackageableElement container = getOrThrow(modelState.getIndex().getSemantic(containerId),
               PackageableElement.class, "No valid container with id " + operation.getContainerId() + " found");

            Optional<GPoint> location = getArtifactPosition(modelState, container,
               operation.getLocation().orElse(GraphUtil.point(0, 0)));

            modelAccess.addArtifact(modelState, location, container)
               .thenAccept(response -> {
                  if (!response.body()) {
                     throw new GLSPServerException("Could not execute create operation on new Artifact node");
                  }
               });
            break;
         }
         case Types.EXECUTION_ENVIRONMENT: {
            String containerId = operation.getContainerId();

            PackageableElement container = getOrThrow(modelState.getIndex().getSemantic(containerId),
               PackageableElement.class, "No valid container with id " + operation.getContainerId() + " found");

            Optional<GPoint> location = getExecutionEnvironmentPosition(modelState, container,
               operation.getLocation().orElse(GraphUtil.point(0, 0)));

            modelAccess
               .addExecutionEnvironment(modelState, location, container)
               .thenAccept(response -> {
                  if (!response.body()) {
                     throw new GLSPServerException(
                        "Could not execute create operation on new ExecutionEnvironment node");
                  }
               });
            break;
         }
         case Types.DEVICE: {
            String containerId = operation.getContainerId();

            PackageableElement container = getOrThrow(modelState.getIndex().getSemantic(containerId),
               PackageableElement.class, "No valid container with id " + operation.getContainerId() + " found");

            Optional<GPoint> location = getDevicePosition(modelState, container,
               operation.getLocation().orElse(GraphUtil.point(0, 0)));

            modelAccess.addDevice(modelState, location, container)
               .thenAccept(response -> {
                  if (!response.body()) {
                     throw new GLSPServerException("Could not execute create operation on new Device node");
                  }
               });
            break;
         }
         case Types.DEPLOYMENT_SPECIFICATION: {
            String containerId = operation.getContainerId();

            PackageableElement container = getOrThrow(modelState.getIndex().getSemantic(containerId),
               PackageableElement.class, "No valid container with id " + operation.getContainerId() + " found");

            Optional<GPoint> location = getDeploymentSpecificationPosition(modelState, container,
               operation.getLocation().orElse(GraphUtil.point(0, 0)));

            modelAccess.addDeploymentSpecification(modelState, location, container).thenAccept(response -> {
               if (!response.body()) {
                  throw new GLSPServerException(
                     "Could not execute create operation on new DeploymentSpecification node");
               }
            });
            break;
         }
      }
   }

   private Optional<GPoint> getDeploymentSpecificationPosition(final UmlModelState modelState,
      final PackageableElement container, final GPoint position) {

      if (container instanceof Artifact || container instanceof Node) {
         Shape containerShape = modelState.getIndex().getNotation(container, Shape.class).get();
         double x = position.getX();
         double y = position.getY();

         x = Math.max(0, x - containerShape.getPosition().getX());
         y = Math.max(0, y - containerShape.getPosition().getY() - 43);

         return Optional.of(GraphUtil.point(x - 16, y - 8));
      }
      return Optional.of(position);
   }

   private Optional<GPoint> getNodePosition(final UmlModelState modelState, final PackageableElement container,
      final GPoint position) {

      if (container instanceof Node) {
         Shape containerShape = modelState.getIndex().getNotation(container, Shape.class).get();
         double x = position.getX();
         double y = position.getY();

         x = Math.max(0, x - containerShape.getPosition().getX());
         y = Math.max(0, y - containerShape.getPosition().getY() - 43);

         return Optional.of(GraphUtil.point(x - 16, y - 8));
      }
      return Optional.of(position);
   }

   private Optional<GPoint> getArtifactPosition(final UmlModelState modelState, final PackageableElement container,
      final GPoint position) {

      if (container instanceof Node) {
         Shape containerShape = modelState.getIndex().getNotation(container, Shape.class).get();
         double x = position.getX();
         double y = position.getY();

         x = Math.max(0, x - containerShape.getPosition().getX());
         y = Math.max(0, y - containerShape.getPosition().getY() - 43);

         return Optional.of(GraphUtil.point(x - 16, y - 8));
      }
      return Optional.of(position);
   }

   private Optional<GPoint> getDevicePosition(final UmlModelState modelState, final PackageableElement container,
      final GPoint position) {

      if (container instanceof Node) {
         Shape containerShape = modelState.getIndex().getNotation(container, Shape.class).get();
         double x = position.getX();
         double y = position.getY();

         x = Math.max(0, x - containerShape.getPosition().getX());
         y = Math.max(0, y - containerShape.getPosition().getY() - 43);

         return Optional.of(GraphUtil.point(x - 16, y - 8));
      }
      return Optional.of(position);
   }

   private Optional<GPoint> getExecutionEnvironmentPosition(final UmlModelState modelState,
      final PackageableElement container,
      final GPoint position) {

      if (container instanceof Node) {
         Shape containerShape = modelState.getIndex().getNotation(container, Shape.class).get();
         double x = position.getX();
         double y = position.getY();

         x = Math.max(0, x - containerShape.getPosition().getX());
         y = Math.max(0, y - containerShape.getPosition().getY() - 43);

         return Optional.of(GraphUtil.point(x - 16, y - 8));
      }
      return Optional.of(position);
   }

   @Override
   public String getLabel() { return "Create uml classifier"; }

}