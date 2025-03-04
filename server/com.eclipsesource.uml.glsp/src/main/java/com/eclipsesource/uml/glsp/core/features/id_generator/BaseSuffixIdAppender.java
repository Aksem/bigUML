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
package com.eclipsesource.uml.glsp.core.features.id_generator;

public abstract class BaseSuffixIdAppender implements SuffixIdAppender {
   protected final String suffix;

   public BaseSuffixIdAppender(final String suffix) {
      this.suffix = suffix;
   }

   @Override
   public String suffix() {
      return this.suffix;
   }
}
