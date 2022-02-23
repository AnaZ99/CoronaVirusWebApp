package com.example.demo;

import com.example.demo.Model.CoronaEvents;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.bigquery.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;

import javax.sound.midi.SysexMessage;

@Controller
public class bigquerry {
    @GetMapping("/show")
    public String prikazNaFormaZaDodavanje(Model model)
    {
        String query = "SELECT * \n" +
                " FROM `gdelt-bq.gdeltv2.ggg` where \n" +
                " (ContextualText like '%covid%' and ContextualText like '%coronavirus%')and GeoType>1\n" +
                " and Location like '%India' "+
                "  and DATE(DateTime) = \"2021-10-01\"  ";


        try {
            // Initialize client that will be used to send requests. This client only needs to be created
            // once, and can be reused for multiple requests.

            String jsonPath="/Users/universal-chain-342203-af8ccc9a3556.json";
            GoogleCredentials credentials= null;
            try {
                credentials = GoogleCredentials.fromStream(new FileInputStream(jsonPath));
            } catch (IOException e) {
                e.printStackTrace();
            }

            BigQuery bigquery = BigQueryOptions.newBuilder()
                    .setCredentials(credentials)
                    .build().getService();

            // Create the query job.
            QueryJobConfiguration queryConfig = QueryJobConfiguration.newBuilder(query).build();

            // Execute the query.
            TableResult result = bigquery.query(queryConfig);

            ArrayList<CoronaEvents> list= new ArrayList<>();
          //  FieldValueList row = result.getValues().iterator().next();
            for (FieldValueList row : bigquery.query(queryConfig).iterateAll()) {
                //System.out.println(row.get(1).getValue().toString());
                String url=row.get(1).getValue().toString();

                String title="";
                if(row.get(2).getValue()==null)
                {
                    title="";
                }
                else
                {
                    title=row.get(2).getValue().toString();
                }
                String image="";
                if(row.get(3).getValue()==null)
                {
                    image="";
                }
                else
                {
                    image=row.get(3).getValue().toString();
                }

                String location=row.get(7).getValue().toString();
                String lat= row.get(8).getValue().toString();
                String lon= row.get(9).getValue().toString();
                String countryCode=row.get(10).getValue().toString();
                String contextualText=row.get(14).getValue().toString();

                list.add(new CoronaEvents(url,title,image,location,lat,lon,countryCode,contextualText));


            }
            model.addAttribute("nastani",list);




            System.out.println("Query ran successfully");
        } catch (BigQueryException | InterruptedException e) {
            System.out.println("Query did not run \n" + e.toString());
        }

        return "MAPA.html";
    }
}

