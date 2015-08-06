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
package org.sejda.cli.transformer;

import org.sejda.cli.model.SplitBySizeTaskCliArguments;
import org.sejda.model.parameter.SplitBySizeParameters;

/**
 * {@link CommandCliArgumentsTransformer} for the SplitBySize task command line interface
 * 
 * @author Eduard Weissmann
 * 
 */
public class SplitBySizeCliArgumentsTransformer extends BaseCliArgumentsTransformer implements
        CommandCliArgumentsTransformer<SplitBySizeTaskCliArguments, SplitBySizeParameters> {

    /**
     * Transforms {@link SplitBySizeTaskCliArguments} to {@link SplitBySizeParameters}
     * 
     * @param taskCliArguments
     * @return populated task parameters
     */
    public SplitBySizeParameters toTaskParameters(SplitBySizeTaskCliArguments taskCliArguments) {
        SplitBySizeParameters parameters = new SplitBySizeParameters(taskCliArguments.getSize());

        populateAbstractParameters(parameters, taskCliArguments);
        populateSourceParameters(parameters, taskCliArguments);
        populateOutputTaskParameters(parameters, taskCliArguments);
        populateOutputPrefix(parameters, taskCliArguments);

        return parameters;
    }
}
