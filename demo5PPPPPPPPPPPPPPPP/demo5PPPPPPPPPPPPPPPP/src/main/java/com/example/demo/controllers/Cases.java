package com.example.demo.controllers;

import com.example.demo.models.CoronaEvents;
import com.example.demo.models.Country;
import org.aspectj.bridge.AbortException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


@Controller
public class Cases {
    HttpURLConnection connection;
    private AbortException LoggerUtil;
    ArrayList<CoronaEvents> list=new ArrayList<>();
    ArrayList<Country> allCountries;

    @GetMapping("/cases")
    public String showDefault(Model model,HttpServletRequest request) throws IOException, JSONException {

        allCountries =new ArrayList<>();
        String line;
        StringBuffer responseContext = new StringBuffer();
        request.getSession().setAttribute("zemja","Macedonia");
        BufferedReader reader;
        try{
            URL url =new URL("https://api.covid19api.com/country/Macedonia" );
            connection=(HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            int status=connection.getResponseCode();
            if(status>299){
                reader=new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while((line=reader.readLine())!=null){
                    responseContext.append(line);
                }
                reader.close();
            }
            else{
                reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while((line=reader.readLine())!=null){
                    responseContext.append(line);
                    break;
                }
                reader.close();

            }

            JSONArray data= new JSONArray(responseContext.toString());
            JSONObject previousDay =null;
            for(int i=0;i<data.length();i++) {

                JSONObject day = data.getJSONObject(i);
                if(i>0)
                {
                    previousDay =data.getJSONObject(i-1);
                    String [] s= day.getString("Date").split("T");
                    allCountries.add(new Country(s[0], day.getInt("Confirmed")- previousDay.getInt("Confirmed"), day.getInt("Deaths")- previousDay.getInt("Deaths")));
                }else{

                //System.out.println(day.getString("Country") + " " + day.getInt("Confirmed") + " " +day.getInt("Deaths"));
                    String [] s= day.getString("Date").split("T");
                allCountries.add(new Country(s[0], day.getInt("Confirmed"), day.getInt("Deaths")));}
            }


        }catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }


        model.addAttribute("nastani", allCountries);
        return "cases.html";
    }


    @GetMapping("/cases/country")
    public String showUsingSearch(Model model, HttpServletRequest request) throws IOException, JSONException {

        String zemja = request.getParameter("search");
        request.getSession().setAttribute("zemja",zemja);

        allCountries =new ArrayList<>();
        String line;
        StringBuffer responseContext = new StringBuffer();
        BufferedReader reader;

        try{
            URL url =new URL("https://api.covid19api.com/country/"+zemja );
            connection=(HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            int status=connection.getResponseCode();
            if(status>299){
                reader=new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while((line=reader.readLine())!=null){
                    responseContext.append(line);
                }
                reader.close();
            }
            else{
                reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while((line=reader.readLine())!=null){
                    responseContext.append(line);
                    break;
                }
                reader.close();

            }

            JSONArray data= new JSONArray(responseContext.toString());
            JSONObject previousDay =null;
            for(int i=0;i<data.length();i++) {

                JSONObject day = data.getJSONObject(i);
                if(i>0)
                {
                    previousDay =data.getJSONObject(i-1);
                    String [] s= day.getString("Date").split("T");
                    Country d=new Country(s[0], day.getInt("Confirmed")- previousDay.getInt("Confirmed"), day.getInt("Deaths")- previousDay.getInt("Deaths"));
                    if(previousDay.getInt("Confirmed")<= day.getInt("Confirmed") && previousDay.getInt("Deaths")<= day.getInt("Deaths") ) {
                        allCountries.add(d);
                    }
                }else{

                    //System.out.println(day.getString("Country") + " " + day.getInt("Confirmed") + " " +day.getInt("Deaths"));
                    String [] s= day.getString("Date").split("T");
                    allCountries.add(new Country(s[0], day.getInt("Confirmed"), day.getInt("Deaths")));}
            }

        }catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }

        model.addAttribute("nastani", allCountries);
        return "cases.html";
    }

    @GetMapping("/global")
    public String visual(){
        return "global.html";
    }

}
