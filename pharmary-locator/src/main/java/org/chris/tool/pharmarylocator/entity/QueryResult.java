package org.chris.tool.pharmarylocator.entity;

public class QueryResult {


    private String poiId;
    
    private String pharmName;
    

    public QueryResult(String poiId, String pharmName) {
        this.poiId = poiId;
        this.pharmName = pharmName;
    }


    public String getPoiId() {
        return poiId;
    }


    public void setPoiId(String poiId) {
        this.poiId = poiId;
    }


    public String getPharmName() {
        return pharmName;
    }


    public void setPharmName(String pharmName) {
        this.pharmName = pharmName;
    }
    
    
}
