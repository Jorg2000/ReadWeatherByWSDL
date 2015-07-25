package model.gmapsAPI;

/**
 * POJO class for converting FROM Json response from GoogleAPI
 */
import com.google.gson.annotations.Expose;

public class Term {

    @Expose
    private long offset;
    @Expose
    private String value;

    /**
     *
     * @return
     * The offset
     */
    public long getOffset() {
        return offset;
    }

    /**
     *
     * @param offset
     * The offset
     */
    public void setOffset(long offset) {
        this.offset = offset;
    }

    /**
     *
     * @return
     * The value
     */
    public String getValue() {
        return value;
    }

    /**
     *
     * @param value
     * The value
     */
    public void setValue(String value) {
        this.value = value;
    }

}