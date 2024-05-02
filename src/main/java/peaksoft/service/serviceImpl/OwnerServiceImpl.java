package peaksoft.service.serviceImpl;

import peaksoft.dao.OwnerDao;
import peaksoft.dao.daoImpl.OwnerDaoImpl;
import peaksoft.entity.House;
import peaksoft.entity.Owner;
import peaksoft.service.OwnerService;

import java.util.List;
import java.util.Optional;

public class OwnerServiceImpl implements OwnerService {
    OwnerDao ownerDao = new OwnerDaoImpl();

    @Override
    public String saveOwner(Owner owner) {
        return ownerDao.saveOwner(owner);
    }

    @Override
    public String saveOwnerWithHouse(Owner owner, House house) {
        return ownerDao.saveOwnerWithHouse(owner,house);
    }

    @Override
    public String assignOwnerToAgency(Long ownerId, Long agencyId) {
        return ownerDao.assignOwnerToAgency(ownerId,agencyId);
    }

    @Override
    public String deleteOwner(Long ownerId) {
        return ownerDao.deleteOwner(ownerId);
    }

    @Override
    public Optional<Owner> getOwnerByAgencyId(Long agencyId) {
        return ownerDao.getOwnerByAgencyId(agencyId);
    }

    @Override
    public List<Owner> getAllOwners() {
        return ownerDao.getAllOwners();
    }

    @Override
    public String updateOwner(Long ownerId, Owner newOwner) {
        return ownerDao.updateOwner(ownerId,newOwner);
    }
}
