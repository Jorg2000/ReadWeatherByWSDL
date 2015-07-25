package utils;

import model.Location;
import model.Weather;
import org.jdom2.Content;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.filter.Filters;
import org.jdom2.input.SAXBuilder;
import org.jdom2.util.IteratorIterable;

import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is utils containers for converting xml responses from WSDL weather API
 */
public class XMLConverter {

    /**
     * @param xml is XML string received from Weather API
     * @return Weather class represents current weather in chosen location
     */
    public Weather parseWeather(String xml) {
        SAXBuilder saxBuilder = new SAXBuilder();

        Weather weather = new Weather();
        try {
            Document doc = saxBuilder.build(new StringReader(xml));
            Element data = doc.getRootElement();
            weather.setLocation(createLocation(data.getChildText("Location")));
            weather.setTime(data.getChildText("Time"));
            weather.setWind(data.getChildText("Wind"));
            weather.setVisibility(data.getChildText("Visibility"));
            weather.setTemperature(data.getChildText("Temperature"));
            weather.setDewPoint(data.getChildText("DewPoint"));
            weather.setRelativeHumidity(data.getChildText("RelativeHumidity"));
            weather.setPressure(data.getChildText("Pressure"));

        } catch (JDOMException e) {

        } catch (IOException e) {

        }
        return weather;
    }

    /**
     * @param xml is XML string received from Weather API
     * @return list of the cities available for Weather API for chosen country
     */

    public List<String> parseCities(String xml) {
        SAXBuilder saxBuilder = new SAXBuilder();
        LinkedList<String> result = new LinkedList<String>();
        try {
            Document doc = saxBuilder.build(new StringReader(xml));


            Element cities = doc.getRootElement();
            IteratorIterable<Element> citiesDescendants = cities.getDescendants(Filters.element("City"));

            while (citiesDescendants.hasNext()) {
                Content currentContent = citiesDescendants.next();
                result.add(currentContent.getValue());
            }

        } catch (JDOMException e) {
            // handle JDOMException
        } catch (IOException e) {
            // handle IOException
        }
        java.util.Collections.sort(result);
        return result;
    }


    /**
     * This method used for two purposes: 1) Convert String parameter "Location" received from weather API
     * to special Location class; 2) Transform geographical coordinates from degree-minute format to
     * more usable decimal degree format (this format is using for Google Maps)
     *
     * @param loc is String Location from Weather class.
     * @return object of Location Class that contains name of the place, latitude and longitude in
     * decimal degrees
     */

    private Location createLocation(String loc) {
        //Extracting coordinates of the point in format XX-XXN(S) XX-XXE(W)
        Pattern p = Pattern.compile("[0-9]+-[0-9]+[NnSsWwEe]");
        Matcher m = p.matcher(loc);
        LinkedList<String> match = new LinkedList<String>();
        Location result = new Location();
        String lat = null;
        String lon = null;
        String place = null;

        if (loc != null) {
            while (m.find()) {
                match.add((m.group(0).replace('-', '.')));
            }
            /*Choosing mark (+ or -) before value. Everything above equator(N) is "+" below(S) is "-"
            On the left side of Greenwich (W) is "-" on the right side (E) is "+"*/
            if (match.size() == 2) {
                lat = match.get(0).endsWith("S") ?
                        "-" + match.get(0).substring(0, match.get(0).length() - 1) :
                        match.get(0).substring(0, match.get(0).length() - 1);
                lon = match.get(1).endsWith("W") ?
                        "-" + match.get(1).substring(0, match.get(1).length() - 1) :
                        match.get(1).substring(0, match.get(1).length() - 1);
                m.reset();
                if (m.find()) {
                    place = loc.substring(0, m.start());
                }
                //Converting degree from degree-minute-second to decimal degree
                BigDecimal bdLat = new BigDecimal(lat);
                BigDecimal bdLon = new BigDecimal(lon);
                BigDecimal bdLatReminder = bdLat.remainder(BigDecimal.ONE).multiply(new BigDecimal(100));
                BigDecimal bdLonReminder = bdLon.remainder(BigDecimal.ONE).multiply(new BigDecimal(100));
                BigDecimal bdLatDecDegrees = bdLatReminder.divide(new BigDecimal(60), 2, BigDecimal.ROUND_HALF_UP);
                BigDecimal bdLonDecDegrees = bdLonReminder.divide(new BigDecimal(60), 2, BigDecimal.ROUND_HALF_UP);
                BigDecimal bdLatInt = new BigDecimal(String.valueOf(bdLat.toBigInteger()));
                BigDecimal bdLonInt = new BigDecimal(String.valueOf(bdLon.toBigInteger()));
                BigDecimal bdLatResult = bdLatInt.add(bdLatDecDegrees);
                BigDecimal bdLonResult = bdLonInt.add(bdLonDecDegrees);
                lat = bdLatResult.toString();
                lon = bdLonResult.toString();

            } else {
                place = loc;
            }

            result.setPlace(place);
            result.setLatitude(lat);
            result.setLongitude(lon);
        }
        return result;
    }
}
