/*
 * Created on 17 set 2015
 * Copyright 2015 by Andrea Vacondio (andrea.vacondio@gmail.com).
 * This file is part of Sejda.
 *
 * Sejda is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Sejda is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Sejda.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.sejda.impl.sambox.component;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.sejda.core.support.io.IOUtils;
import org.sejda.model.exception.TaskIOException;
import org.sejda.model.exception.TaskWrongPasswordException;
import org.sejda.model.input.PdfFileSource;
import org.sejda.model.input.PdfStreamSource;

import java.io.*;

/**
 * @author Andrea Vacondio
 *
 */
public class DefaultPdfSourceOpenerTest {

    @Test
    public void openDocument() throws TaskIOException {
        assertNotNull(new DefaultPdfSourceOpener().open(PdfStreamSource.newInstanceNoPassword(
                getClass().getClassLoader().getResourceAsStream("pdf/test_file.pdf"), "my source")));
    }

    @Test
    public void openDocumentWithPasswordAES128() throws TaskIOException {
        assertNotNull(new DefaultPdfSourceOpener().open(PdfStreamSource.newInstanceWithPassword(
                getClass().getClassLoader().getResourceAsStream("pdf/encrypted_AES128_user_pwd.pdf"), "my source",
                "test")));
    }

    @Test
    public void openDocumentWithPasswordAES256() throws TaskIOException {
        assertNotNull(new DefaultPdfSourceOpener().open(PdfStreamSource.newInstanceWithPassword(
                getClass().getClassLoader().getResourceAsStream("pdf/encrypted_AES256_user_pwd.pdf"), "my source",
                "test")));
    }

    @Test
    public void openDocumentWrongPassword() throws TaskIOException, IOException {
        try {
            File tmp = IOUtils.createTemporaryBufferWithName("dumbo.pdf");
            OutputStream out = new FileOutputStream(tmp);
            InputStream in = getClass().getClassLoader().getResourceAsStream("pdf/encrypted_AES128_user_pwd.pdf");

            try {
                org.apache.commons.io.IOUtils.copy(in, out);
            } finally {
                org.apache.commons.io.IOUtils.closeQuietly(out);
                org.apache.commons.io.IOUtils.closeQuietly(in);
            }

            new DefaultPdfSourceOpener().open(PdfFileSource.newInstanceWithPassword(tmp, "my source"));
            fail("Exception expected");
        } catch (TaskWrongPasswordException e) {
            assertEquals("Unable to open 'dumbo.pdf' due to a wrong password.", e.getMessage());
        }
    }

    @Test(expected = TaskIOException.class)
    public void openDocumentError() throws TaskIOException {
        new DefaultPdfSourceOpener().open(PdfStreamSource.newInstanceNoPassword(
                getClass().getClassLoader().getResourceAsStream("pdf/not_a_pdf.pdf"), "my source"));

    }
}
