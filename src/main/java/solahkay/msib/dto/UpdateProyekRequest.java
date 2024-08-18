package solahkay.msib.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateProyekRequest {

    @NotNull
    private Integer idProyek;

    @NotBlank
    @Size(min = 1, max = 255)
    private String namaProyek;

    @NotBlank
    @Size(min = 1, max = 255)
    private String client;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime tglMulai;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime tglSelesai;

    @NotBlank
    @Size(min = 1, max = 255)
    private String pimpinanProyek;

    private String keterangan;

    @NotNull
    private Set<String> lokasi;

}
