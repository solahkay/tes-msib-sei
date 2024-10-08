package solahkay.msib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import solahkay.msib.entity.Lokasi;

import java.util.Optional;

public interface LokasiRepository extends JpaRepository<Lokasi, Integer> {

    boolean existsByNamaLokasi(String namaLokasi);

    Optional<Lokasi> findByNamaLokasi(String namaLokasi);

}
