package com.example.demo;




import com.example.demo.Models.Drzava;
import org.aspectj.bridge.AbortException;
import org.json.simple.parser.JSONParser;
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
import java.util.Iterator;

@Controller
public class cases {
    HttpURLConnection connection;
    private AbortException LoggerUtil;
    ArrayList<CoronaEvents> list=new ArrayList<>();
    ArrayList<Drzava> listica;

    @GetMapping("/cases")
    public String prikaz(Model model,HttpServletRequest request) throws IOException, JSONException {



listica=new ArrayList<>();
        String line;
        StringBuffer responseContet = new StringBuffer();
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
            ///////////////////////////////////////////////////////////////////////////////////////


            JSONArray albums= new JSONArray(responseContet.toString());
            JSONObject event2 =null;
            for(int i=0;i<albums.length();i++) {

                JSONObject event = albums.getJSONObject(i);
                if(i>0)
                {
                    event2=albums.getJSONObject(i-1);
                    String [] s=event.getString("Date").split("T");
                    listica.add(new Drzava(s[0],event.getInt("Confirmed")-event2.getInt("Confirmed"),event.getInt("Deaths")-event2.getInt("Deaths")));
                }else{

                //System.out.println(event.getString("Country") + " " + event.getInt("Confirmed") + " " +event.getInt("Deaths"));
                    String [] s=event.getString("Date").split("T");
                listica.add(new Drzava(s[0],event.getInt("Confirmed"),event.getInt("Deaths")));}
            }



/////////////////////////////////////////////////////////////////////////////////


        }catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }


        model.addAttribute("nastani",listica);
        return "cases.html";
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////




    @GetMapping("/cases/country")
    public String prikazPosebno(Model model, HttpServletRequest request) throws IOException, JSONException {

        String zemja = request.getParameter("search");
        request.getSession().setAttribute("zemja",zemja);

        listica=new ArrayList<>();
        String line;
        StringBuffer responseContet = new StringBuffer();
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
            ///////////////////////////////////////////////////////////////////////////////////////


            JSONArray albums= new JSONArray(responseContet.toString());
            JSONObject event2 =null;
            for(int i=0;i<albums.length();i++) {

                JSONObject event = albums.getJSONObject(i);
                if(i>0)
                {
                    event2=albums.getJSONObject(i-1);
                    String [] s=event.getString("Date").split("T");
                    Drzava d=new Drzava(s[0],event.getInt("Confirmed")-event2.getInt("Confirmed"),event.getInt("Deaths")-event2.getInt("Deaths"));
                    if(event2.getInt("Confirmed")<=event.getInt("Confirmed") &&event2.getInt("Deaths")<=event.getInt("Deaths") ) {
                        listica.add(d);
                    }
                }else{

                    //System.out.println(event.getString("Country") + " " + event.getInt("Confirmed") + " " +event.getInt("Deaths"));
                    String [] s=event.getString("Date").split("T");
                    listica.add(new Drzava(s[0],event.getInt("Confirmed"),event.getInt("Deaths")));}
            }



/////////////////////////////////////////////////////////////////////////////////


        }catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }


        model.addAttribute("nastani",listica);
        return "cases.html";
    }















}
