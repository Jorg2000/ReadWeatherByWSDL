import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;


import static org.junit.Assert.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;



/**
    Full test for GoogleAPI country prediction.
    Unfortunately API response is "location sensitive", so it ignored to avoid failures
 */
public class TestGoogleCountryPredictor extends Mockito {
    @Ignore
    @Test
    public void testCountryPredictionGood() throws Exception {

        GoogleCountryPredictor classUnderTest = new GoogleCountryPredictor();

        /*Creating mocks for servlet*/
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        ServletContext servletContext  = mock(ServletContext.class);
        ServletConfig servletConfig = mock(ServletConfig.class);
        when(servletConfig.getServletContext()).thenReturn(servletContext);

        /*Simulate reading from the config file*/
        InputStream is = new FileInputStream("src/main/webapp/resources/app.conf");
        when(servletContext.getResourceAsStream("/resources/app.conf")).thenReturn(is);
        when(request.getParameter("country")).thenReturn("U");

        /*Creating mock writer for doGet method*/
        StringWriter sw = new StringWriter();
        PrintWriter writer = new PrintWriter(sw);
        when(response.getWriter()).thenReturn(writer);

        /*Testing*/
        classUnderTest.init(servletConfig);
        classUnderTest.doGet(request, response);

        /*Showing results*/
        String resultString = "[\"Ukraine\",\"United Kingdom\",\"United States\",\"United Arab Emirates\"]";

        assertEquals(sw.toString(), resultString);
        sw.flush();
    }
    @Ignore
    @Test
    public void testCountryPredictionWrongCountry() throws Exception {

        GoogleCountryPredictor classUnderTest = new GoogleCountryPredictor();

        /*Creating mocks for servlet*/
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        ServletContext servletContext  = mock(ServletContext.class);
        ServletConfig servletConfig = mock(ServletConfig.class);
        when(servletConfig.getServletContext()).thenReturn(servletContext);

        /*Simulate reading from the config file*/
        InputStream is = new FileInputStream("src/main/webapp/resources/app.conf");
        when(servletContext.getResourceAsStream("/resources/app.conf")).thenReturn(is);
        when(request.getParameter("country")).thenReturn("");

        /*Creating mock writer for doGet method*/
        StringWriter sw = new StringWriter();
        PrintWriter writer = new PrintWriter(sw);
        when(response.getWriter()).thenReturn(writer);

        /*Testing*/
        classUnderTest.init(servletConfig);
        classUnderTest.doGet(request, response);

        /*Showing results*/
        String resultString = "[]";

        assertEquals(sw.toString(), resultString);
        sw.flush();
    }

    @Ignore
    @Test
    public void testCountryPredictionNULLCountry() throws Exception {

        GoogleCountryPredictor classUnderTest = new GoogleCountryPredictor();

        /*Creating mocks for servlet*/
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        ServletContext servletContext  = mock(ServletContext.class);
        ServletConfig servletConfig = mock(ServletConfig.class);
        when(servletConfig.getServletContext()).thenReturn(servletContext);

        /*Simulate reading from the config file*/
        InputStream is = new FileInputStream("src/main/webapp/resources/app.conf");
        when(servletContext.getResourceAsStream("/resources/app.conf")).thenReturn(is);
        when(request.getParameter("country")).thenReturn(null);

        /*Creating mock writer for doGet method*/
        StringWriter sw = new StringWriter();
        PrintWriter writer = new PrintWriter(sw);
        when(response.getWriter()).thenReturn(writer);

        /*Testing*/
        classUnderTest.init(servletConfig);
        classUnderTest.doGet(request, response);

        /*Showing results*/
        String resultString = "[]";

        assertEquals(sw.toString(), resultString);
        sw.flush();
    }
}
