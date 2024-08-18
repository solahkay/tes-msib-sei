package solahkay.msib.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        Pageable pageable = PageRequest.of(page, size);
        Page<Proyek> projects = proyekRepository.findAll(pageable);
        List<ProyekResponse> projectResponses = projects.getContent().stream()
                .map(ProyekServiceImpl::toProyekResponse)
                .toList();

        return new PageImpl<>(projectResponses, pageable, projects.getTotalElements());
    }

    public static ProyekResponse toProyekResponse(Proyek proyek) {
        return ProyekResponse.builder()
                .id(proyek.getId())
                .namaProyek(proyek.getNamaProyek())
                .client(proyek.getClient())
                .tglMulai(TimeHelper.formatToString(proyek.getTglMulai()))
                .tglSelesai(TimeHelper.formatToString(proyek.getTglSelesai()))
                .pimpinanProyek(proyek.getPimpinanProyek())
                .keterangan(proyek.getKeterangan())
                .lokasi(proyek.getLokasi().stream()
                        .map(LokasiServiceImpl::toLokasiResponse)
                        .collect(Collectors.toSet()))
                .createdAt(TimeHelper.formatToString(proyek.getCreatedAt()))
                .build();
    }

    @Override
    public ProyekResponse updateProject(UpdateProyekRequest request) {
        validationService.validate(request);

        Proyek proyek = proyekRepository.findById(request.getIdProyek())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Proyek tidak ditemukan"));

        if (!(proyek.getNamaProyek().equalsIgnoreCase(request.getNamaProyek()))
                && proyekRepository.existsByNamaProyek(request.getNamaProyek())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Proyek sudah ada");
        }

        proyek.setNamaProyek(request.getNamaProyek());
        proyek.setClient(request.getClient());
        proyek.setTglMulai(request.getTglMulai());
        proyek.setTglSelesai(request.getTglSelesai());
        proyek.setPimpinanProyek(request.getPimpinanProyek());
        proyek.setKeterangan(request.getKeterangan());

        Set<Lokasi> locations = request.getLokasi().stream()
                .map(s -> lokasiRepository.findByNamaLokasi(s)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lokasi tidak ditemukan")))
                .collect(Collectors.toSet());
        proyek.setLokasi(locations);

        proyekRepository.save(proyek);

        return toProyekResponse(proyek);
    }

    @Override
    public void deleteProject(DeleteProyekRequest request) {

    }

}
