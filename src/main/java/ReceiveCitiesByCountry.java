import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.webservicex.GlobalWeather;
import net.webservicex.GlobalWeatherSoap;
import utils.XMLConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * This servlet obtain available cities for chosen county
 * and retiring to the user JSON with List of Strings contains cities names
 * Weather Api has limited number of cities for every country
 * moreover it doesn't know
 */

@WebServlet(urlPatterns = {"/cities"}, name = "ReceiveCitiesByCountry")
public class ReceiveCitiesByCountry extends HttpServlet {
    public static final String PATH = "/WEB-INF/jsp/citiesList.jsp";

    GlobalWeather globalWeather;
    GlobalWeatherSoap globalWeatherSoap;
    XMLConverter converter;
    Gson gson;

    @Override
    public void init() throws ServletException {
        /*Initialising Weather api classes*/
        globalWeather = new GlobalWeather();
        globalWeatherSoap = globalWeather.getGlobalWeatherSoap();
        converter = new XMLConverter();
        gson = new GsonBuilder().create();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*receiving XML data from WeatherAPI. converting it to JSON and sending to frontend*/
        String country = req.getParameter("country").trim();
        if (country != null) {
            if (country.length() != 0) {
                String citiesByCountry = globalWeatherSoap.getCitiesByCountry(country);
                List<String> citiesList = converter.parseCities(citiesByCountry);
                String gsonCitiesList = gson.toJson(citiesList, List.class);
                PrintWriter writer = resp.getWriter();
                writer.write(gsonCitiesList);
                writer.flush();
            }
        }
    }
}
