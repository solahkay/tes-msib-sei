package solahkay.msib.service;

import org.springframework.data.domain.Page;
import solahkay.msib.dto.AddLokasiRequest;
import solahkay.msib.dto.DeleteLokasiRequest;
import solahkay.msib.dto.LokasiResponse;
import solahkay.msib.dto.UpdateLokasiRequest;

public interface LokasiService {

    void addLocation(AddLokasiRequest request);

    Page<LokasiResponse> getAllLocations(int page, int size);

    LokasiResponse updateLocation(UpdateLokasiRequest request);

    void deleteLocation(DeleteLokasiRequest request);

}
