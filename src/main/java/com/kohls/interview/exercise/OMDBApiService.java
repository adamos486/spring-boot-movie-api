package com.kohls.interview.exercise;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kohls.interview.exercise.models.OMDBMovie;
import com.kohls.interview.exercise.utils.HttpHelper;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Service
public class OMDBApiService {

    public OMDBMovie findMovieByTitle(String title) throws IOException {
        URL url = new URL("http://www.omdbapi.com/?i=tt3896198&apikey=7818bc6b&t=" + URLEncoder.encode(title, "UTF-8"));
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

//        Map<String, String> parameters = new HashMap<>();
//        parameters.put("i", "tt3896198");
//        parameters.put("apikey", "7818bc6b");
//        parameters.put("t", title);
//        connection.setDoOutput(true);
//        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
//        out.writeBytes(HttpHelper.getParamsString(parameters));
//        out.flush();
//        out.close();

        int status = connection.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        connection.disconnect();

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(content.toString(), OMDBMovie.class);
    }
}
