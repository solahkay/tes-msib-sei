package solahkay.msib.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateLokasiRequest {

    @NotNull
    private Integer idLokasi;

    @NotBlank
    @Size(min = 1, max = 255)
    private String namaLokasi;

    @NotBlank
    @Size(min = 1, max = 255)
    private String negara;

    @NotBlank
    @Size(min = 1, max = 255)
    private String provinsi;

    @NotBlank
    @Size(min = 1, max = 255)
    private String kota;

}
