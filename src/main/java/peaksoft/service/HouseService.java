package peaksoft.service;

import peaksoft.MyException;
import peaksoft.entity.House;

import java.util.List;

public interface HouseService {
    void saveHouse(Long agencyId,House house);
    List<House> getAllHouse(Long agencyId);
    House getById(Long houseId) throws MyException;
    void  updateHouseById(Long houseId,House newHouse);
    void  deleteHouseById(Long houseId);
    List<House> sortHouseByHouseType(String ascOrDesc);
    House searchHouse(String word);
}
