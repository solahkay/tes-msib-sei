package solahkay.msib.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateLokasiRequest {

    @Size(min = 1, max = 255)
    private String namaLokasi;

    @Size(min = 1, max = 255)
    private String negara;

    @Size(min = 1, max = 255)
    private String provinsi;

    @Size(min = 1, max = 255)
    private String kota;

}
