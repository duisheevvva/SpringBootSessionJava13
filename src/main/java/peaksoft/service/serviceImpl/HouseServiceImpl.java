package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.MyException;
import peaksoft.entity.Agency;
import peaksoft.entity.House;
import peaksoft.repository.AgencyRepository;
import peaksoft.repository.HouseRepository;
import peaksoft.service.HouseService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class HouseServiceImpl implements HouseService {


    private final HouseRepository houseRepository;
    private final AgencyRepository agencyRepository;

    @Override
    public void saveHouse(Long agencyId, House house) {
        try {
            Agency agency = agencyRepository.findById(agencyId).orElseThrow(() ->
                    new MyException("Agency with id: " + agencyId + " is not found"));
            house.setAgency(agency);
            houseRepository.save(house);
        }catch (MyException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<House> getAllHouse(Long agencyId) {
        return houseRepository.getAllHouse(agencyId);
    }

    @Override
    public House getById(Long id) throws MyException {
        try {
            return houseRepository.findById(id).orElseThrow(()->new MyException("not found!"));
        }catch (MyException e){
            throw new MyException("error");
        }
    }

    @Override
    public void updateHouseById(Long id, House house) {
        try{
            House house1 = houseRepository.findById(id).orElseThrow(() -> new MyException("not found!"));
            house1.setHouseType(house.getHouseType());
            house1.setDescription(house.getDescription());
            house1.setCountry(house.getCountry());
            house1.setPrice(house.getPrice());
            house1.setRoom(house.getRoom());
            house1.setAddress(house.getAddress());
            houseRepository.save(house1);
        }catch (MyException e){
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void deleteHouseById(Long id) {
        try {
            houseRepository.delete(houseRepository.findById(id).orElseThrow(() -> new MyException("not found!")));
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<House> sortHouseByHouseType(String ascOrDesc) {
        return null;
    }

    @Override
    public House searchHouse(String word) {
        return houseRepository.searchHouse(word);
    }
}
