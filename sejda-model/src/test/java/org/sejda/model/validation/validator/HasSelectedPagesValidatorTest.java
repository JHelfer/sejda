/*
 * Created on 02/ott/2011
 * Copyright 2011 by Andrea Vacondio (andrea.vacondio@gmail.com).
 * 
 * This file is part of the Sejda source code
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.sejda.model.validation.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.Test;
import org.sejda.model.parameter.ExtractPagesParameters;
import org.sejda.model.pdf.page.PageRange;
import org.sejda.model.pdf.page.PredefinedSetOfPages;

/**
 * @author Andrea Vacondio
 * 
 */
public class HasSelectedPagesValidatorTest {
    private HasSelectedPagesValidator victim = new HasSelectedPagesValidator();
    private ExtractPagesParameters params = mock(ExtractPagesParameters.class);
    private PageRange range = new PageRange(10);

    @Test
    public void testNull() {
        assertTrue(victim.isValid(null, null));
    }

    @Test
    public void testHasDefault() {
        when(params.getPredefinedSetOfPages()).thenReturn(PredefinedSetOfPages.EVEN_PAGES);
        assertTrue(victim.isValid(params, null));
    }

    @Test
    public void testHasTransitions() {
        when(params.getPredefinedSetOfPages()).thenReturn(null);
        when(params.getPageSelection()).thenReturn(Collections.singleton(range));
        assertTrue(victim.isValid(params, null));
    }

    @Test
    public void testHasBoth() {
        when(params.getPredefinedSetOfPages()).thenReturn(PredefinedSetOfPages.EVEN_PAGES);
        when(params.getPageSelection()).thenReturn(Collections.singleton(range));
        assertTrue(victim.isValid(params, null));
    }

    @Test
    public void testHasNone() {
        when(params.getPredefinedSetOfPages()).thenReturn(PredefinedSetOfPages.NONE);
        when(params.getPageSelection()).thenReturn(Collections.EMPTY_SET);
        assertFalse(victim.isValid(params, null));
    }
}
