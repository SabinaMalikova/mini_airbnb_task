package peaksoft.dao.daoImpl;

import peaksoft.dao.OwnerDao;
import peaksoft.entity.Owner;

import java.util.List;
import java.util.Optional;

public class OwnerDaoImpl implements OwnerDao {
    @Override
    public String saveOwner(Owner owner) {
        return null;
    }

    @Override
    public String saveOwnerWithHouse(Owner owner) {
        return null;
    }

    @Override
    public String assignOwnerToAgency(Long ownerId, Long agencyId) {
        return null;
    }

    @Override
    public String deleteOwnerWithHouseAndRentInfo(Long ownerId) {
        return null;
    }

    @Override
    public Optional<Owner> getOwnerByAgencyId(Long agencyId) {
        return Optional.empty();
    }

    @Override
    public List<Owner> getAllOwners() {
        return null;
    }

    @Override
    public String updateOwner(Long ownerId) {
        return null;
    }

    @Override
    public String deleteOwner(Long ownerId) {
        return null;
    }
}
