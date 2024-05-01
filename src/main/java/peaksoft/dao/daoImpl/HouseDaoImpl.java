package peaksoft.dao.daoImpl;

import peaksoft.dao.HouseDao;
import peaksoft.entity.House;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class HouseDaoImpl implements HouseDao {
    @Override
    public String saveHouseWithAssignToOwner(House house) {
        return null;
    }

    @Override
    public String deleteHouseWithAddressAndRentInfo(Long HouseId) {
        return null;
    }

    @Override
    public List<House> getAllHousesByRegion(String region) {
        return null;
    }

    @Override
    public List<House> getAllHouseByAgencyId(Long agencyId) {
        return null;
    }

    @Override
    public List<House> getAllHousesByOwnerId(Long ownerId) {
        return null;
    }

    @Override
    public List<House> getAllHousesBetweenDates(LocalDate checkIn1, LocalDate checkIn2) {
        return null;
    }

    @Override
    public Optional<House> getHouseById(Long houseId) {
        return Optional.empty();
    }

    @Override
    public String updateHouse(Long houseId) {
        return null;
    }

    @Override
    public String deleteHouse(Long houseId) {
        return null;
    }
}
