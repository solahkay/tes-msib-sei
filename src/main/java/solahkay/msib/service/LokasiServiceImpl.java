package solahkay.msib.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import solahkay.msib.dto.AddLokasiRequest;
import solahkay.msib.dto.LokasiResponse;
import solahkay.msib.dto.PagingResponse;
import solahkay.msib.dto.UpdateLokasiRequest;
import solahkay.msib.entity.Lokasi;
import solahkay.msib.entity.Proyek;
import solahkay.msib.helper.TimeHelper;
import solahkay.msib.repository.LokasiRepository;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class LokasiServiceImpl implements LokasiService {

    private final LokasiRepository lokasiRepository;

    private final ValidationService validationService;

    @Override
    public void addLocation(AddLokasiRequest request) {
        validationService.validate(request);
        if (lokasiRepository.existsByNamaLokasi(request.getNamaLokasi())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Lokasi sudah ada");
        }

        Lokasi lokasi = Lokasi.builder()
                .namaLokasi(request.getNamaLokasi())
                .negara(request.getNegara())
                .provinsi(request.getProvinsi())
                .kota(request.getKota())
                .createdAt(TimeHelper.getFormattedLocalDateTimeNow())
                .build();
        Set<Proyek> proyekSet = new HashSet<>();
        lokasi.setProyek(proyekSet);

        lokasiRepository.save(lokasi);
    }

    @Override
    public LokasiResponse getLocation(Integer id) {
        return null;
    }

    @Override
    public PagingResponse getAllLocations(int page, int size) {
        return null;
    }

    @Override
    public LokasiResponse updateLocation(UpdateLokasiRequest request) {
        return null;
    }

    @Override
    public void deleteLocation(Integer id) {

    }

}
