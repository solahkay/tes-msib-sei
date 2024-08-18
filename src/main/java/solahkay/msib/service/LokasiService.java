package solahkay.msib.service;

import solahkay.msib.dto.AddLokasiRequest;
import solahkay.msib.dto.LokasiResponse;
import solahkay.msib.dto.PagingResponse;
import solahkay.msib.dto.UpdateLokasiRequest;

public interface LokasiService {

    void addLocation(AddLokasiRequest request);

    LokasiResponse getLocation(Integer id);

    PagingResponse getAllLocations(int page, int size);

    LokasiResponse updateLocation(UpdateLokasiRequest request);

    void deleteLocation(Integer id);

}
