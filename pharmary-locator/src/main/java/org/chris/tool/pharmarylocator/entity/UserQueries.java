package org.chris.tool.pharmarylocator.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserQueries {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String user;

    private String poiId;

    private String pharmName;

    private int queryCount;

    protected UserQueries() {

    }

    public UserQueries(String user, String poiId, String pharmName, int queryCount) {
        this.user = user;
        this.poiId = poiId;
        this.pharmName = pharmName;
        this.queryCount = queryCount;
    }
    
    @Override
    public String toString() {
        return "UserQueries [user=" + user + ", poiId=" + poiId + ", pharmName=" + pharmName + ", queryCount=" + queryCount + "]";
    }

    public Long getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public String getPharmName() {
        return pharmName;
    }
    

    public String getPoiId() {
        return poiId;
    }

    public void setPoiId(String poiId) {
        this.poiId = poiId;
    }

    public int getQueryCount() {
        return queryCount;
    }

    public void setQueryCount(int queryCount) {
        this.queryCount = queryCount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPharmName(String pharmName) {
        this.pharmName = pharmName;
    }
}
