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

import org.sejda.core.notification.context.GlobalNotificationContext;
import org.sejda.core.service.TaskExecutionService;
import org.sejda.model.notification.EventListener;
import org.sejda.model.parameter.base.TaskParameters;

/**
 * Default implementation of {@link TaskExecutionAdapter}
 * 
 * @author Eduard Weissmann
 * 
 */
public class DefaultTaskExecutionAdapter implements TaskExecutionAdapter {

    private final TaskExecutionService taskExecutionService;

    public DefaultTaskExecutionAdapter(TaskExecutionService taskExecutionService) {
        this.taskExecutionService = taskExecutionService;
        registerListeners();
    }

    private void registerListeners() {
        doRegisterProcessListener();
        doRegisterTaskFailureListener();
    }

    private void doRegisterProcessListener() {
        LoggingPercentageOfWorkDoneChangeEventListener listener = new LoggingPercentageOfWorkDoneChangeEventListener();
        addEnsuringOnlyOne(listener);
    }

    private void doRegisterTaskFailureListener() {
        DefaultTaskExecutionFailedEventListener listener = new DefaultTaskExecutionFailedEventListener();
        addEnsuringOnlyOne(listener);
    }

    /**
     * @param listener
     */
    private void addEnsuringOnlyOne(EventListener<?> listener) {
        GlobalNotificationContext.getContext().removeListener(listener);
        GlobalNotificationContext.getContext().addListener(listener);
    }

    TaskExecutionService getTaskExecutionService() {
        return taskExecutionService;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.sejda.cli.TaskExecutionAdapter#executeCommand(org.sejda.core.manipulation.model.parameter.TaskParameters)
     */
    public void execute(TaskParameters taskParameters) {
        getTaskExecutionService().execute(taskParameters);
    }
}
