package com.enterprise.java.week2;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author paulawaite
 * @version 1.0 9/16/15.
 */
public class FindAndReplaceTest extends TestCase {

    @ClassRule
    public static TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Before
    public void setUp() throws IOException {
        temporaryFolder.create();
    }

    @Test
    public void testInstantiateFindAndReplace() {
        FindAndReplace findAndReplace = new FindAndReplace(null, null, null);
        assertNotNull(findAndReplace);
    }

    @Test
    public void testCreatePrintWriter() throws IOException {
        File output = temporaryFolder.newFile("testOutput.txt");
        FindAndReplace findAndReplace = new FindAndReplace(null, output.getAbsolutePath(), null);
        PrintWriter printWriter = findAndReplace.createOutputFile();
        assertNotNull(printWriter);
    }
    @Ignore
    @Test
    public void testCreatePrintWriterWithoutPermissions() throws IOException {
        File temp = temporaryFolder.newFolder("tempNoPermissions");
        File output = new File(temp, "testOutput.txt");
        temp.setReadOnly();
        FindAndReplace findAndReplace = new FindAndReplace(null, output.getAbsolutePath(), null);
        PrintWriter printWriter = findAndReplace.createOutputFile();
        assertNull(printWriter);
    }

    @Test
    public void testCreateMapOfFindAndReplaceValues() throws IOException {
        File findReplaceValues = temporaryFolder.newFile("findReplace.txt");
        PrintWriter writer = new PrintWriter(findReplaceValues);
        writer.println("ABC:abc,DEF:def");
        writer.println("key:value");
        writer.close();
        FindAndReplace findAndReplace = new FindAndReplace(null, null, findReplaceValues.getAbsolutePath());
        findAndReplace.createMapOfFindReplaceValues();
        assertEquals(3, findAndReplace.findReplaceValues.size());

    }


}