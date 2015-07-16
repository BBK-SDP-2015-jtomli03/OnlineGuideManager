package tests;

import com.bond.sky.MyResource;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class MyResourceTest {

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Test
    public void testReadFile() throws IOException {
        final File tempFile = tempFolder.newFile("tempFile.txt");
        FileUtils.writeStringToFile(tempFile, "<?xml version=“1.0” encoding=“UTF-8”?>\n" +
                "\n" +
                "<movie_data>\n" +
                "    <title>movie data</title>\n" +
                "    <movie id=“1”>\n" +
                "        <sean_channel>\n" +
                "            <name>Dr No</name>\n" +
                "            <start_time>9.00am</start_time>\n" +
                "            <end_time>11.00am</end_time>\n" +
                "        </sean_channel>\n" +
                "    </movie> </movie_data>");
        MyResource resource = new MyResource();
        String result = resource.acceptFile(tempFile);
        Assert.assertEquals("File Upload Successful!", result);
    }

    //<sean_channel> start-tag missing from xml, ie not properly formatted therefore expect the SaxParser to throw exception
    //ERROR FAILS -> NEED TO WRITE CODE TO HANDLE CORRECTLY
    @Test(expected = ParserConfigurationException.class)
    public void testExceptionIfStartTagMissing() throws IOException {
        final File tempFile = tempFolder.newFile("tempFile.txt");
        FileUtils.writeStringToFile(tempFile, "<?xml version=“1.0” encoding=“UTF-8”?>\n" +
                "\n" +
                "<movie_data>\n" +
                "    <title>movie data</title>\n" +
                "    <movie id=“1”>\n" +
                "        \n" +
                "            <name>Dr No</name>\n" +
                "            <start_time>9.00am</start_time>\n" +
                "            <end_time>11.00am</end_time>\n" +
                "        </sean_channel>\n" +
                "    </movie> </movie_data>");
        MyResource resource = new MyResource();
        resource.acceptFile(tempFile);
    }

    //</end_time> end-tag missing from xml, ie not properly formatted therefore expect the SaxParser to throw exception
    //exception caught in MyResource.parseFile(File file) -> need to handle it gracefully
    @Test (expected = ParserConfigurationException.class)
    public void testExceptionIfEndTagMissing() throws IOException {
        final File tempFile = tempFolder.newFile("tempFile.txt");
        FileUtils.writeStringToFile(tempFile, "<?xml version=“1.0” encoding=“UTF-8”?>\n" +
                "\n" +
                "<movie_data>\n" +
                "    <title>movie data</title>\n" +
                "    <movie id=“1”>\n" +
                "        <sean_channel>\n" +
                "            <name>Dr No</name>\n" +
                "            <start_time>9.00am</start_time>\n" +
                "            <end_time>11.00am\n" +
                "        </sean_channel>\n" +
                "    </movie> </movie_data>");
        MyResource resource = new MyResource();
        resource.acceptFile(tempFile);
    }
}
