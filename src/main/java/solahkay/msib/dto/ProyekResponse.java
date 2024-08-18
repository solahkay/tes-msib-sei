package solahkay.msib.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProyekResponse {

    private Integer id;

    private String namaProyek;

    private String client;

    private String tglMulai;

    private String tglSelesai;

    private String pimpinanProyek;

    private String keterangan;

    private String createdAt;

    private Set<LokasiResponse> lokasi;

}
