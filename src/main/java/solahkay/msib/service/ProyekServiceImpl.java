package solahkay.msib.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import solahkay.msib.dto.AddProyekRequest;
import solahkay.msib.dto.DeleteProyekRequest;
import solahkay.msib.dto.ProyekResponse;
import solahkay.msib.dto.UpdateProyekRequest;
import solahkay.msib.entity.Lokasi;
import solahkay.msib.entity.Proyek;
import solahkay.msib.helper.TimeHelper;
import solahkay.msib.repository.LokasiRepository;
import solahkay.msib.repository.ProyekRepository;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class ProyekServiceImpl implements ProyekService {

    private final ProyekRepository proyekRepository;

    private final LokasiRepository lokasiRepository;

    private final ValidationService validationService;

    @Override
    public void addProject(AddProyekRequest request) {
        validationService.validate(request);
        if (proyekRepository.existsByNamaProyek(request.getNamaProyek())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Proyek sudah ada");
        }

        Proyek proyek = Proyek.builder()
                .namaProyek(request.getNamaProyek())
                .client(request.getClient())
                .tglMulai(request.getTglMulai())
                .tglSelesai(request.getTglSelesai())
                .pimpinanProyek(request.getPimpinanProyek())
                .keterangan(request.getKeterangan())
                .createdAt(TimeHelper.getFormattedLocalDateTimeNow())
                .build();

        Set<Lokasi> locations = new HashSet<>();

        request.getLokasi().forEach(lokasi -> {
            Lokasi location = lokasiRepository.findByNamaLokasi(lokasi)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lokasi tidak ditemukan"));
            locations.add(location);
        });

        proyek.setLokasi(locations);
        proyekRepository.save(proyek);
    }

    @Override
    public Page<ProyekResponse> getAllProjects(int page, int size) {
        return null;
    }

    @Override
    public ProyekResponse updateProject(UpdateProyekRequest request) {
        return null;
    }

    @Override
    public void deleteProject(DeleteProyekRequest request) {

    }
}
