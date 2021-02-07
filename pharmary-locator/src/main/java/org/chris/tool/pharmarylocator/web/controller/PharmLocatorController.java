package org.chris.tool.pharmarylocator.web.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.chris.tool.pharmarylocator.entity.QueryResult;
import org.chris.tool.pharmarylocator.entity.UserQueries;
import org.chris.tool.pharmarylocator.repository.UserQueryuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@RestController
@CrossOrigin(origins = "*")
public class PharmLocatorController {
    
    private static final String ENDPOINT = "https://restapi.amap.com/v3";
    
    private static final String KEY = "fb6e185fdaac2b250b24be031f6859d5";
    
    private static final String POI = "药房";
    
    private static final String POI_TYPE = "090601";
    
    private static final String RADIUS = "5000";
    
    @Autowired
    private UserQueryuRepository repository;
    
    private Random random = new Random();

    @RequestMapping(value="/search", method = GET)
    public List<QueryResult> search(@RequestParam String user, @RequestParam int type, @RequestParam String keyword) {
        
        // get location for input
        String requestUrl = ENDPOINT + "/geocode/geo?key=" + KEY + "&address=" + keyword;
        String json = getResponse(requestUrl);
        JsonParser parser = new JsonParser();
        JsonElement geoElement = parser.parse(json);
        String location = "";
        if (geoElement.isJsonObject()) {
            JsonArray geoCodes = geoElement.getAsJsonObject().getAsJsonArray("geocodes");
            if (geoCodes != null && geoCodes.size() > 0) {
                JsonElement geoCode = geoCodes.get(0);
                location = geoCode.getAsJsonObject().get("location").getAsString();
            }
        }
        
        // search pharmaries around for given location
        Map<String, Integer> userQueryStats = new HashMap<>();  // cache stats for current search
        Map<String, String> pharmNames = new HashMap<>();
        List<QueryResult> queryResults = new ArrayList<>();
        requestUrl = ENDPOINT + "/place/around?key=" + KEY + "&location=" + location + "&keywords=" + POI + "&types=" + POI_TYPE + "&radius=" + RADIUS;
        json = getResponse(requestUrl);
        JsonElement pharmariesElement = parser.parse(json);
        if (pharmariesElement.isJsonObject()) {
            JsonArray pois = pharmariesElement.getAsJsonObject().getAsJsonArray("pois");
            for (int i = 0; i < pois.size(); i++) {
                JsonObject poiObj = pois.get(i).getAsJsonObject();
                String poiId = poiObj.get("id").getAsString();
                String pharmName = poiObj.get("name").getAsString();
                String statKey = user + "-" + poiId;
                Integer count = userQueryStats.get(statKey);
                if (count == null) {
                    userQueryStats.put(statKey, 1);
                } else {
                    userQueryStats.put(statKey, count + 1);     // thread-safe caveat here, but fine for statistic purpose
                }
                pharmNames.put(poiId, pharmName);
                queryResults.add(new QueryResult(poiId, pharmName));
            }
        }
      
        // store statictics into H2 database
        for (String key : userQueryStats.keySet()) {
            String[] userPoiId = key.split("-");
            String username = userPoiId[0];
            String poiId = userPoiId[1];
            List<UserQueries> userQueries = repository.findByUserAndPoiId(username, poiId);
            if (userQueries == null || userQueries.size() == 0) {
                repository.save(new UserQueries(username, poiId, pharmNames.get(poiId), userQueryStats.get(key)));
            } else {
                UserQueries userQuery = userQueries.get(0);
                userQuery.setQueryCount(userQuery.getQueryCount() + userQueryStats.get(key) + random.nextInt(20));
                repository.save(userQuery);
            }
        }

        listQueries();
        return queryResults;
    }
    
    @RequestMapping(value="/stat", method = GET)
    public List<UserQueries> showStat() {
        List<UserQueries> queries = repository.showStat();
        for (UserQueries query : queries) {
            System.out.println(query);
        }
        return queries;
    }
    
    private void listQueries() {
        Iterable<UserQueries> queries = repository.findAll();
        for (UserQueries query : queries) {
            System.out.println(query);
        }
    }
    
    private String getResponse(String serverUrl) {
        StringBuffer result = new StringBuffer();
        try {
            URL url = new URL(serverUrl);
            URLConnection conn = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            String line;
            while((line = in.readLine()) != null){
                result.append(line);
            }
            in.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }
    

    public UserQueryuRepository getRepository() {
        return repository;
    }

    public void setRepository(UserQueryuRepository repository) {
        this.repository = repository;
    }
}
