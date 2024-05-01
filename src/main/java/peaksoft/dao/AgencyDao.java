package peaksoft.dao;

import peaksoft.entity.Agency;

import java.util.List;
import java.util.Optional;

public interface AgencyDao {
             //CRUD

    //save agency with address
    String saveAgency(Agency agency);


    //get all
    List<Agency> getAllAgency();


    //get agency by id
    Optional<Agency> getAgencyById();


    //update agency by id
    String updateAgency();


    //delete agency with address and rent_info
    String deleteAgencyWithAddressAndRentInfo();


}
