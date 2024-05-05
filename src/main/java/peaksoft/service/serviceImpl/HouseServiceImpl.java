package peaksoft.service.serviceImpl;

import peaksoft.dao.HouseDao;
import peaksoft.dao.daoImpl.HouseDaoImpl;
import peaksoft.entity.House;
import peaksoft.service.HouseService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class HouseServiceImpl implements HouseService {
    HouseDao houseDao = new HouseDaoImpl();

    @Override
    public String saveHouseWithAssignToOwner(House house, Long ownerId) {
        return houseDao.saveHouseWithAssignToOwner(house, ownerId);
    }

    @Override
    public String deleteHouse(Long houseId) {
        return houseDao.deleteHouse(houseId);
    }

    @Override
    public List<House> getAllHousesByRegion(String region) {
        return houseDao.getAllHousesByRegion(region);
    }

    @Override
    public List<House> getAllHouseByAgencyId(Long agencyId) {
        return houseDao.getAllHouseByAgencyId(agencyId);
    }

    @Override
    public List<House> getAllHousesByOwnerId(Long ownerId) {
        return houseDao.getAllHousesByOwnerId(ownerId);
    }

    @Override
    public List<House> getAllHousesBetweenDates(LocalDate checkIn1, LocalDate checkIn2) {
        return houseDao.getAllHousesBetweenDates(checkIn1, checkIn2);
    }

    @Override
    public Optional<House> getHouseById(Long houseId) {
        return houseDao.getHouseById(houseId);
    }

    @Override
    public String updateHouse(Long houseId, House newHouse) {
        return houseDao.updateHouse(houseId, newHouse);
    }
}
