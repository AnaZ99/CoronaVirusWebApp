package com.example.demo.Service;


import com.example.demo.model.Apteka;

import java.util.List;

public interface AptekaService  {
     List<Apteka> findallPharmacy();
     public List<Apteka> findbyC(String id);
     public Apteka findbyId(Long id);
  public String convertCyrilic(String message);
  public String ToUpperCase(String s);


}
