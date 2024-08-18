package solahkay.msib.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import solahkay.msib.dto.AddLokasiRequest;
import solahkay.msib.dto.LokasiResponse;
import solahkay.msib.dto.UpdateLokasiRequest;
import solahkay.msib.entity.Lokasi;
import solahkay.msib.entity.Proyek;
import solahkay.msib.helper.TimeHelper;
import solahkay.msib.repository.LokasiRepository;

import java.util.HashSet;
import java.util.List;
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

        Lokasi location = Lokasi.builder()
                .namaLokasi(request.getNamaLokasi())
                .negara(request.getNegara())
                .provinsi(request.getProvinsi())
                .kota(request.getKota())
                .createdAt(TimeHelper.getFormattedLocalDateTimeNow())
                .build();
        Set<Proyek> proyekSet = new HashSet<>();
        location.setProyek(proyekSet);

        lokasiRepository.save(location);
    }

    @Override
    public Page<LokasiResponse> getAllLocations(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Lokasi> locations = lokasiRepository.findAll(pageable);
        List<LokasiResponse> locationResponses = locations.getContent().stream()
                .map(LokasiServiceImpl::toLokasiResponse)
                .toList();

        return new PageImpl<>(locationResponses, pageable, locations.getTotalElements());
    }

    public static LokasiResponse toLokasiResponse(Lokasi lokasi) {
        return LokasiResponse.builder()
                .id(lokasi.getId())
                .namaLokasi(lokasi.getNamaLokasi())
                .negara(lokasi.getNegara())
                .provinsi(lokasi.getProvinsi())
                .kota(lokasi.getKota())
                .createdAt(TimeHelper.formatToString(lokasi.getCreatedAt()))
                .build();
    }

    @Override
    public LokasiResponse updateLocation(UpdateLokasiRequest request) {
        return null;
    }

    @Override
    public void deleteLocation(Integer id) {

    }

}
