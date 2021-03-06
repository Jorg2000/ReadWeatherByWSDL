package model.gmapsAPI;
/**
 * POJO class for converting FROM Json response from GoogleAPI
 */


import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class Response {

    @Expose
    private List<Prediction> predictions = new ArrayList<Prediction>();
    @Expose
    private String status;

    /**
     *
     * @return
     * The predictions
     */
    public List<Prediction> getPredictions() {
        return predictions;
    }

    /**
     *
     * @param predictions
     * The predictions
     */
    public void setPredictions(List<Prediction> predictions) {
        this.predictions = predictions;
    }

    /**
     *
     * @return
     * The status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

}