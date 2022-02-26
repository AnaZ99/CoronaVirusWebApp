package com.example.demo;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.bigquery.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

@Controller
public class events {

    private final NewServiceInterface newServiceInterface;

    public events(NewServiceInterface newServiceInterface) {
        this.newServiceInterface = newServiceInterface;
    }

    @GetMapping("/baza")
    public String vleciOdBaza(Model model)
    {
        ArrayList list=newServiceInterface.fi();
        if(list.size()==0)
        {
            model.addAttribute("nemanisto",1);
        }
        else
        {
            model.addAttribute("nemanisto",2);
        }
        model.addAttribute("nastani",newServiceInterface.fi());
        model.addAttribute("kokos","lala");
        System.out.println(newServiceInterface.fi().size());

        return "map.html";
    }


    @GetMapping({"/events",""})
    public String gett(Model model)
    {
        String query = "SELECT * \n" +
                " FROM `gdelt-bq.gdeltv2.ggg` where \n" +
                " (ContextualText like '%covid%' and ContextualText like '%coronavirus%')and GeoType>1\n" +
                "  and DATE(DateTime) >= \""+java.time.LocalDate.now().minusDays(2)+"\"  ";


        try {
            // Initialize client that will be used to send requests. This client only needs to be created
            // once, and can be reused for multiple requests.

            String jsonPath="src/main/java/com/example/demo/universal-chain-342203-af8ccc9a3556.json";
            GoogleCredentials credentials= null;
            try {
                credentials = GoogleCredentials.fromStream(new FileInputStream(jsonPath));
                System.out.println(credentials);
            } catch (IOException e) {
                e.printStackTrace();
            }

            BigQuery bigquery = BigQueryOptions.newBuilder()
                    .setCredentials(credentials)
                    .build().getService();


            QueryJobConfiguration queryConfig = QueryJobConfiguration.newBuilder(query).build();


            TableResult result = bigquery.query(queryConfig);

            ArrayList<CoronaEvents> list= new ArrayList<>();

            for (FieldValueList row : bigquery.query(queryConfig).iterateAll()) {
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


            if(list.size()==0)
            {
                model.addAttribute("nemanisto",1);
            }
            else
            {
                model.addAttribute("nemanisto",2);
            }
            model.addAttribute("nastani",list);




            System.out.println("Query ran successfully");
        } catch (BigQueryException | InterruptedException e) {
            System.out.println("Query did not run \n" + e.toString());
        }

        return "map.html";
    }


@GetMapping("/search")
    public String pokategorija(Model model, HttpServletRequest request) {

        String datum = request.getParameter("date");
        String []datumm=datum.split("/");
        String   finalDate= datumm[2] + "-" + datumm[0] + "-" + datumm[1];

    String lokacija = request.getParameter("search");

        String query = "SELECT * \n" +
                " FROM `gdelt-bq.gdeltv2.ggg` where \n" +
                " (ContextualText like '%covid%' and ContextualText like '%coronavirus%')and GeoType>1\n" +
                " and Location like '%" + lokacija + "' "+
                "  and DATE(DateTime) = \""+finalDate+"\"  ";


        System.out.println(query);

  //  and DATE(DateTime) = \""+d+"\"  ";
 //   "  and DATE(DateTime) = \"2021-10-01\"  ";

        try {
            // Initialize client that will be used to send requests. This client only needs to be created
            // once, and can be reused for multiple requests.

            String jsonPath="src/main/java/com/example/demo/universal-chain-342203-af8ccc9a3556.json";
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

            if(list.size()==0)
            {
                model.addAttribute("nemanisto",1);
            }
            else
            {
                model.addAttribute("nemanisto",2);
            }
            model.addAttribute("nastani",list);




            System.out.println("Query ran successfully");
        } catch (BigQueryException | InterruptedException e) {
            model.addAttribute("nemanisto",1);
            System.out.println("Query did not run \n" + e.toString());
        }

        return "map.html";


    }
}
