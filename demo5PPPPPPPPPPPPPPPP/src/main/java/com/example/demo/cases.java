package com.example.demo;

import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Controller
public class cases {
    HttpURLConnection connection;
    @GetMapping("/cases")
    public String prikaz(Model model) {



        BufferedReader reader;
        String line;
        StringBuffer responseContet = new StringBuffer();

        try{
            URL url =new URL("https://api.covid19api.com/summary");
            connection=(HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            int status=connection.getResponseCode();
            if(status>299){
                reader=new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while((line=reader.readLine())!=null){
                    responseContet.append(line);
                }
                reader.close();
            }
            else{
                reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while((line=reader.readLine())!=null){
                    responseContet.append(line);
                    break;
                }
                reader.close();

            }
            parse(responseContet.toString());
        }catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }

return "cases.html";
    }


    public static  void parse (String responsebody) throws JSONException {
        JSONObject albums= new JSONObject(responsebody);
        JSONArray zemji= albums.getJSONArray("Countries");

        for(int i=0;i<zemji.length();i++)
        {
            JSONObject zemja=zemji.getJSONObject(i);

            if(zemja.getString("Country").equals("Albania"))
            {
                System.out.println(zemja.getString("Country"));
            }


        }

    }



}
