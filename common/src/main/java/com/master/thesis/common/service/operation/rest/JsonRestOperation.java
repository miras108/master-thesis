package com.master.thesis.common.service.operation.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

/**
 * Created by miras108 on 2016-06-17.
 */
public class JsonRestOperation implements RestOperation {

    public static final String REQUEST_METHOD = "GET";
    private String url;

    public String call(List<RequestParameter> requestParameters)
    {
        URL url = createUrlRequest(requestParameters);
        HttpURLConnection conn = openConnection(url);
        setRequestMethodOrCatchException(conn);
        conn.setRequestProperty("Accept", "application/json");
        checkResponseCode(conn);

        BufferedReader br = createBufferReader(conn);
        String response = getResponse(br);
        conn.disconnect();

        return response;
    }

    private String getResponse(BufferedReader br) {
        try {
            return br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private BufferedReader createBufferReader(HttpURLConnection conn) {
        try {
            return new BufferedReader(new InputStreamReader((conn.getInputStream())));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private HttpURLConnection openConnection(URL url) {
        try {
            return (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private URL createUrlRequest(List<RequestParameter> requestParameters) {
        try {
            return new URL(createRequest(requestParameters));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void setRequestMethodOrCatchException(HttpURLConnection conn) {
        try {
            conn.setRequestMethod(REQUEST_METHOD);
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
    }

    private void checkResponseCode(HttpURLConnection conn) {
        try {
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String createRequest(List<RequestParameter> requestParameters)
    {
        return url + "?" + joinParameters(requestParameters);
    }

    private String joinParameters(List<RequestParameter> requestParameters) {

        String joinedParameters = "";
        for(RequestParameter requestParameter : requestParameters)
        {
            joinedParameters += requestParameter.getParameter() + "=" + requestParameter.getParameterValue() + "&";
        }
        return joinedParameters.substring(0, joinedParameters.length()-1);
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
