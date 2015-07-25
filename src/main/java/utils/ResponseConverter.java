package utils;


import model.gmapsAPI.Prediction;
import model.gmapsAPI.Response;

import java.util.LinkedList;
import java.util.List;

/**
 * This class is container of utils for transforming Google prediction API response to required way
 */

public class ResponseConverter {
    private Response response;

    /**
     * @param response is Response object obtained by converting JSON response from GoogleAPI
     */
    public ResponseConverter(Response response) {
        this.response = response;
    }

    /**
     * This method runs through the list of location represented in Response object
     * and choose elements marked as "country" in Predictions.types field
     *
     * @return countries in form of the list of Predictions objects
     */
    public List<Prediction> getCountries() {
        LinkedList<Prediction> countries = new LinkedList<Prediction>();
        if (response != null) {
            if (response.getStatus().equals("OK")) {
                for (Prediction p : response.getPredictions()) {
                    if (p.getTypes().contains("country")) {
                        countries.add(p);
                    }
                }
                return countries;
            } else
                return new LinkedList<Prediction>();
        }
        return new LinkedList<Prediction>();
    }

    /**
     * This method runs through the list of location represented in Response object
     * and choose elements marked as "country" in Predictions.types field
     *
     * @return the list of countries names.
     */
    public List<String> getCountriesNames() {
        LinkedList<String> countries = new LinkedList<String>();
        if (response != null) {
            if (response.getStatus().equals("OK")) {
                for (Prediction p : response.getPredictions()) {
                    if (p.getTypes().contains("country")) {
                        countries.add(p.getDescription());
                    }
                }
                return countries;
            } else
                return new LinkedList<String>();
        }
        return new LinkedList<String>();
    }
}
