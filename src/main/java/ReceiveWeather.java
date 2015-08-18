import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Weather;
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

/**
 * This servlet using for obtaining weather in XML format from Weather API converting to JSON and
 * sending to frontend
 */
@WebServlet(urlPatterns = {"/getweather"}, name = "getWeatherForCity")
public class ReceiveWeather extends HttpServlet {
    GlobalWeather globalWeather;
    GlobalWeatherSoap globalWeatherSoap;
    XMLConverter converter;
    Gson gson;

    @Override
    public void init() throws ServletException {
        /*Initialising Weather API*/
        globalWeather = new GlobalWeather();
        globalWeatherSoap = globalWeather.getGlobalWeatherSoap();
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*Receding weather in XML, converting to JSON and sending to User*/
        String country = req.getParameter("country").trim();
        String city = req.getParameter("city").trim();
        String XMLWeather = globalWeatherSoap.getWeather(city, country);

        Weather weather = new XMLConverter().parseWeather(XMLWeather);
        /*adding status to Weather class. Used
        to prevent error with google maps widget on frontend.*/
        if (weather.getLocation() == null) {
            weather.setStatus("FAIL");
        } else {
            weather.setStatus("OK");
        }
        String gsonWeather = gson.toJson(weather, Weather.class);
        PrintWriter writer = resp.getWriter();
        writer.write(gsonWeather);
        writer.flush();
    }
}
