package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.gmapsAPI.Response;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Testing class for Response Converter
 */
public class ResponseConverterTest {

    @Test
    /**
     * This test checks work of ResponseConverter.getCountriesNames().
     * This test use Good data
     * 1) It loads sample of JSON returned by Google (for test saved in local txt file)
     * 2) Compare it with constant
     */
    public void testGetCountriesNamesGOODData() throws Exception {
        Response response;
        List expectedList = new LinkedList<String>();
        expectedList.add("Ukraine");
        expectedList.add("United Kingdom");
        expectedList.add("United States");
        expectedList.add("United Arab Emirates");

        String testData = readFile("src/test/resources/testResponseJson.txt", Charset.defaultCharset());
        Gson gson = new GsonBuilder().create();
        response = gson.fromJson(testData, Response.class);
        ResponseConverter classUnderTest = new ResponseConverter(response);
        assertEquals(classUnderTest.getCountriesNames(), expectedList);
    }

    @Test
    /**
     * Test for NULL in argument
     */
    public void testGetCountriesNamesNULLData() throws Exception {

        Response response;
        List testList = new LinkedList<String>();
        Gson gson = gson = new GsonBuilder().create();
        response = null;
        ResponseConverter classUnderTest = new ResponseConverter(response);
        assertEquals(classUnderTest.getCountriesNames(), testList);
    }

    /**
     * Converting file to String
     *
     * @param path     path to the file
     * @param encoding encoding used in chosen file
     * @return text in file
     * @throws IOException
     */
    private String readFile(String path, Charset encoding)
            throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
}