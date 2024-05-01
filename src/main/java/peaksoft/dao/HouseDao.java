package peaksoft.dao;

import peaksoft.entity.House;

import javax.swing.plaf.basic.BasicColorChooserUI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface HouseDao {
            //CRUD
    //save house with assign to owner
    String saveHouseWithAssignToOwner(House house, Long ownerId);


    //delete house with address, rent_info if rent_info is null, if rent_info is true and checkOut < date_now
    String deleteHouseWithAddressAndRentInfo(Long houseId);


    //get all house by region
    List<House> getAllHousesByRegion(String region);


    //get all house by agency id
    List<House>getAllHouseByAgencyId(Long agencyId);


    //get all by owner id
    List<House> getAllHousesByOwnerId(Long ownerId);


    //get all houses where checkIn date between 2 dates
    List<House>getAllHousesBetweenDates(LocalDate checkIn1, LocalDate checkIn2);


    //get house by id
    Optional<House> getHouseById(Long houseId);


    //update house
    String updateHouse(Long houseId,House newHouse);


}
