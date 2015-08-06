/*
 * Created on 19/ott/2011
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

import java.io.File;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Andrea Vacondio
 * 
 */
public class ExistingFileValidatorTest {
    private File mockFile;
    private ExistingFileValidator victim = new ExistingFileValidator();

    @Before
    public void setUp() {
        mockFile = mock(File.class);
        when(mockFile.exists()).thenReturn(Boolean.TRUE);
    }

    @Test
    public void testNull() {
        assertTrue(victim.isValid(null, null));
    }

    @Test
    public void testPositive() {
        assertTrue(victim.isValid(mockFile, null));
    }

    @Test
    public void testNegative() {
        when(mockFile.exists()).thenReturn(Boolean.FALSE);
        assertFalse(victim.isValid(mockFile, null));
    }
}
