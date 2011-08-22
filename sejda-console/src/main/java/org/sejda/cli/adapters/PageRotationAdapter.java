/*
 * Created on Jul 10, 2011
 * Copyright 2010 by Eduard Weissmann (edi.weissmann@gmail.com).
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 * http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License. 
 */
package org.sejda.cli.adapters;

import org.apache.commons.lang.StringUtils;
import org.sejda.core.manipulation.model.rotation.PageRotation;
import org.sejda.core.manipulation.model.rotation.Rotation;
import org.sejda.core.manipulation.model.rotation.RotationType;

/**
 * 
 * Adapter for {@link PageRotation}. Main role is to be a string-based constructor for the underlying model object
 * 
 * @author Eduard Weissmann
 * 
 */
public class PageRotationAdapter {
    /**
     * Tokens separator in the user input
     */
    private static final String SEPARATOR = ":";

    private final PageRotation pageRotation;

    public PageRotationAdapter(String input) {
        final String[] tokens = StringUtils.split(input, SEPARATOR);
        if (tokens.length < 2) {
            throw new IllegalArgumentException("Separator '" + SEPARATOR + "' missing");
        }

        final String pageToken = tokens[0];
        final String rotationToken = tokens[1];

        final Rotation rotation = valueOfSilently(Rotation.class, rotationToken);
        if (rotation == null) {
            throw new IllegalArgumentException("Unknown rotation: '" + rotationToken + "'");
        }

        RotationType rotationType = valueOfSilently(RotationType.class, pageToken);
        Integer pageNumber = null;

        if (rotationType == null) {
            rotationType = RotationType.SINGLE_PAGE;
            pageNumber = valueOfSilently(pageToken);
        }

        if (rotationType.isSinglePage() && pageNumber == null) {
            throw new IllegalArgumentException("Unknown pages definition: '" + pageToken + "'");
        }

        this.pageRotation = rotationType.isSinglePage() ? PageRotation.createSinglePageRotation(pageNumber, rotation)
                : PageRotation.createMultiplePagesRotation(rotation, rotationType);
    }

    public static Integer valueOfSilently(String input) {
        try {
            return Integer.valueOf(input);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static <T extends Enum<T>> T valueOfSilently(Class<T> enumClass, String name) {
        try {
            return T.valueOf(enumClass, name);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    /**
     * @return the pageRotation
     */
    public PageRotation getPageRotation() {
        return pageRotation;
    }
}