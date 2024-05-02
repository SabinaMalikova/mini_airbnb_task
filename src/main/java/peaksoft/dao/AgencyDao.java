package peaksoft.dao;

import peaksoft.entity.Address;
import peaksoft.entity.Agency;

import java.util.List;
import java.util.Optional;

public interface AgencyDao {
             //CRUD

    //save agency with address
    String saveAgency(Agency agency, Address address);


    //get all
    List<Agency> getAllAgency();


    //get agency by id
    Optional<Agency> getAgencyById(Long agencyId);


    //update agency by id
    String updateAgency(Long oldAgencyId, Agency newAgency);


    //delete agency with address and rent_info
    String deleteAgency(Long agencyId);


}
