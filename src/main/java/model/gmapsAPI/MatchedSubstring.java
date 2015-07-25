package model.gmapsAPI;
/**
 * POJO class for converting FROM Json response from GoogleAPI
 */


import com.google.gson.annotations.Expose;

public class MatchedSubstring {

    @Expose
    private long length;
    @Expose
    private long offset;

    /**
     *
     * @return
     * The length
     */
    public long getLength() {
        return length;
    }

    /**
     *
     * @param length
     * The length
     */
    public void setLength(long length) {
        this.length = length;
    }

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

}