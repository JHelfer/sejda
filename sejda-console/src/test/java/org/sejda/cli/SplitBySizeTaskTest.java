/*
 * Created on Jul 1, 2011
 * Copyright 2011 by Eduard Weissmann (edi.weissmann@gmail.com).
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
package org.sejda.cli;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.sejda.model.parameter.SplitBySizeParameters;

/**
 * Tests for the SplitBySizeTask command line interface
 * 
 * @author Eduard Weissmann
 * 
 */
public class SplitBySizeTaskTest extends AbstractTaskTest {

    public SplitBySizeTaskTest() {
        super(TestableTask.SPLIT_BY_SIZE);
    }

    @Test
    public void size_Specified() {
        SplitBySizeParameters parameters = defaultCommandLine().with("-s", "1234567890123456789").invokeSejdaConsole();
        assertEquals(1234567890123456789L, parameters.getSizeToSplitAt());
    }

    @Test
    public void mandatoryParams() {
        defaultCommandLine().without("-s").assertConsoleOutputContains("Option is mandatory: --size");
    }
}
