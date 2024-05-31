package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.MyException;
import peaksoft.entity.Agency;
import peaksoft.repository.AgencyRepository;
import peaksoft.service.AgencyService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AgencyServiceImpl implements AgencyService {

    private final AgencyRepository agencyRepository;
    @Override
    public void saveAgency(Agency agency) {
        agencyRepository.save(agency);
    }

    @Override
    public List<Agency> getAllAgency() {
        return agencyRepository.findAll();
    }

    @Override
    public Agency getById(Long agencyId) {
        try {
            return agencyRepository.findById(agencyId).orElseThrow(()->new MyException("Agency not found!"));
        }catch (MyException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void updateAgencyById(Long agencyId, Agency newAgency) throws MyException {
        Agency agency = agencyRepository.findById(agencyId).orElseThrow(() -> new MyException("Agency not found!"));
        agency.setName(newAgency.getName());
        agency.setEmail(newAgency.getEmail());
        agency.setImageLink(newAgency.getImageLink());
        agency.setPhoneNumber(newAgency.getPhoneNumber());
        agency.setCountry(newAgency.getCountry());
        agencyRepository.save(agency);
    }

    @Override
    public void deleteAgencyById(Long agencyId) {
       agencyRepository.deleteById(agencyId);
    }

    @Override
    public List<Agency> searchAgency(String word) {
        return null;
    }
}
