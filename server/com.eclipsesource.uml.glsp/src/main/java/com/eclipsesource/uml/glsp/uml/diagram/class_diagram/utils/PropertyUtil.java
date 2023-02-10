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
package com.eclipsesource.uml.glsp.uml.diagram.class_diagram.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.uml2.uml.Property;

public final class PropertyUtil {
   public static int getLower(final String multiplicityString) {
      return getBound(multiplicityString, 0);
   }

   public static int getUpper(final String multiplicityString) {
      return getBound(multiplicityString, 1);
   }

   private static int getBound(final String multiplicityString, final int index) {
      String result = multiplicityString.trim();
      Pattern pattern = Pattern.compile(multiplicityRegex());
      Matcher matcher = pattern.matcher(multiplicityString);
      if (matcher.find()) {
         result = multiplicityString.split(multiplicityRegex())[index];
      }
      return result.isEmpty() ? 1 : (result.equals("*") ? -1 : Integer.parseInt(result, 10));
   }

   public static String getMultiplicity(final Property property) {
      if (property.getLower() == property.getUpper()) {
         return String.format("%s", property.getUpper() == -1 ? "*" : property.getUpper());
      }
      return String.format("%s..%s", property.getLower(), property.getUpper() == -1 ? "*" : property.getUpper());
   }

   private static String multiplicityRegex() {
      return "\\.\\.";
   }

}