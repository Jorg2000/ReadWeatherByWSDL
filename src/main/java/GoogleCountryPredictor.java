import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import model.gmapsAPI.Response;
import utils.ResponseConverter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Properties;


/**
 * This servlet is using for predicting country list by first letters entered by user
 * It creates request for GoogleAPI, converts response for required format and returns it to
 * the frontend part
 */


@WebServlet(urlPatterns = {"/countrypredictor"}, name = "PredictCountryName")
public class GoogleCountryPredictor extends HttpServlet {
    private String apiKey;
    private Gson gson;

    @Override
    public void init() throws ServletException {
        //Creating instances for GSON and reading APIKey
        gson = new GsonBuilder().create();
        Properties properties = new Properties();
        ServletContext servletContext = super.getServletContext();
        System.out.println(servletContext.getResourcePaths("/resources"));

        try {
            InputStream resourceAsStream = servletContext.getResourceAsStream("/resources/app.conf");
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        apiKey = properties.getProperty("GooglePlacesWebServiceApiKey");
    }

    //
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Response response;
        List<String> countriesNames;

        String country = req.getParameter("country");
        String result = getLocations(country, apiKey);

        try {
            response = gson.fromJson(result, Response.class);
        } catch (JsonParseException e) {
            response = null;
        }

        //Transforming response and sending to frontend
        ResponseConverter responseConverter = new ResponseConverter(response);
        countriesNames = responseConverter.getCountriesNames();
        String gsonCountriesNames = gson.toJson(countriesNames, List.class);
        PrintWriter writer = resp.getWriter();
        writer.write(gsonCountriesNames);
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    /**
     * Transforming response from GoogleAPI to String
     *
     * @param country is first letters of the country name
     * @param apiKey  GoogleAPi key
     * @return String with JSON
     * @throws IOException
     */
    private String getLocations(String country, String apiKey) throws IOException {

        StringBuilder sbResult = new StringBuilder();
        String result = "";
        String query = null;
        query = buildQuery(country, apiKey);
        if (query != null) {
            InputStream is = new URL(query).openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            for (String s; (s = reader.readLine()) != null; ) {
                sbResult.append(s);
                sbResult.append("\n");
            }
            result = sbResult.toString();
        }
        return result;
    }

    /**
     * Creating query and checking arguments
     *
     * @param country is first letters of the country name
     * @param apiKey  GoogleAPi key
     * @return query for Google Api
     * @throws UnsupportedEncodingException
     */
    private String buildQuery(String country, String apiKey) throws UnsupportedEncodingException {
        String query = null;
        if (country != null) {
            if (!country.equals("")) {
                StringBuilder sbQuery = new StringBuilder();
                sbQuery.append("https://maps.googleapis.com/maps/api/place/autocomplete/json?");
                sbQuery.append("input=" + URLEncoder.encode(country, "UTF-8") + "&");
                sbQuery.append("types=(regions)&");
                sbQuery.append("key=" + apiKey);
                sbQuery.append("&language=en_UK");
                query = sbQuery.toString();
            }
        }
        return query;
    }
}
