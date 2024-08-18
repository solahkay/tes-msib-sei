package solahkay.msib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import solahkay.msib.entity.Proyek;

public interface ProyekRepository extends JpaRepository<Proyek, Integer> {

    boolean existsByNamaProyek(String namaProyek);

}