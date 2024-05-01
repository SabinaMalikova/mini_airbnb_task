package peaksoft.dao;

import peaksoft.entity.Owner;

import java.util.List;
import java.util.Optional;

public interface OwnerDao {
             //CRUD
    //save owner if age >=18
    String saveOwner(Owner owner);


    //save owner with house if age >=18
    String saveOwnerWithHouse(Owner owner);


    //assign owner to agency
    String assignOwnerToAgency(Long ownerId, Long agencyId);


    //delete owner with house if rent_info is null, or rent_info is true and rent_info < date_now
    String deleteOwnerWithHouseAndRentInfo(Long ownerId);


    //get owner by agency id
    Optional<Owner> getOwnerByAgencyId(Long agencyId);


    //get all owners with name, age
    List<Owner> getAllOwners();


    //update owner
    String updateOwner(Long ownerId);


    //delete owner
    String deleteOwner(Long ownerId);


}
