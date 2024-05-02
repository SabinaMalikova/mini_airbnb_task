package peaksoft.service;

import peaksoft.entity.Address;
import peaksoft.entity.Agency;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface AddressService {
    //RU
    // getAddressById
    Optional<Address> getAddressById(Long addressId);


    //get all address with agency
    Map<Address, Agency> getAllAddressWithAgency();


    //get quantity agencies by city
    int getCountAgenciesByCity(String city);


    //get all region with agencies Map<String, List<Agency>> groupByRegion
    Map<String, List<Agency>> getAllRegionWithAgency();


    //update address
    String updateAddress(Long oldAddressId, Address newAddress);
}
