package peaksoft.service;

import peaksoft.MyException;
import peaksoft.entity.Agency;

import java.util.List;

public interface AgencyService {

    void saveAgency(Agency agency);
    List<Agency> getAllAgency();
    Agency getById(Long agencyId);
    void  updateAgencyById(Long agencyId,Agency newAgency) throws MyException;
    void  deleteAgencyById(Long agencyId);
    List<Agency> searchAgency(String word);
}
