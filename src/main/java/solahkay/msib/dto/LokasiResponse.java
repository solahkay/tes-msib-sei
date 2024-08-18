package solahkay.msib.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LokasiResponse {

    private Integer id;

    private String namaLokasi;

    private String negara;

    private String provinsi;

    private String kota;

    private String createdAt;

}

