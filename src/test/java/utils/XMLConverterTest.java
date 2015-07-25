package utils;

import junit.framework.TestCase;
import model.Weather;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Testing XMLConverter
 */
public class XMLConverterTest extends TestCase {

    @Test
    /**
     *
     */
    public void testParseWeather() throws Exception {
        XMLConverter classUnderTest = new XMLConverter();
        Weather excpetedWeather = new Weather();
        excpetedWeather.setTime("Jul 30, 2014 - 08:00 AM EDT / 2014.07.30 1200 UTC");
        excpetedWeather.setWind("from the ENE (060 degrees) at 22 MPH (19 KT) (direction variable):0");
        excpetedWeather.setVisibility("greater than 7 mile(s):0");
        excpetedWeather.setTemperature(" 87 F (31 C)");
        excpetedWeather.setDewPoint(" 48 F (9 C)");
        excpetedWeather.setRelativeHumidity(" 25%");
        excpetedWeather.setPressure(" 29.85 in. Hg (1011 hPa)");
        String testData = readFile("src/test/resources/testXMLWeatherResponse.xml", Charset.forName("UTF-16"));
        Weather actualWeather = classUnderTest.parseWeather(testData);
        assertEquals(excpetedWeather.getTime(), actualWeather.getTime());

    }


    @Test
    /**
     *
     */
    public void testParseCities() throws Exception {
        XMLConverter classUnderTest = new XMLConverter();
        List<String> expectedCitiesList = new LinkedList<String>();
        expectedCitiesList.add("Boryspil");
        expectedCitiesList.add("Simferopol");
        expectedCitiesList.add("Kharkiv");
        expectedCitiesList.add("Kyiv");
        expectedCitiesList.add("L'Viv");
        expectedCitiesList.add("Odesa");
        Collections.sort(expectedCitiesList);
        String testData = readFile("src/test/resources/testXMLCitiesResponse.xml", Charset.forName("UTF-8"));
        List actualCitiesList = classUnderTest.parseCities(testData);
        assertEquals(expectedCitiesList, actualCitiesList);

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