package peaksoft.dao;

import peaksoft.entity.Address;
import peaksoft.entity.Agency;

import java.util.List;
import java.util.Map;

public interface AddressDao {
               //RU
    //get all address with agency
    List<Address> getAll();
    //get quantity agencies by city

    //get all region with agencies Map<String, List<Agency>> groupByRegion
    //    Map<String, List<Agency>> getAll
    //update address

}
