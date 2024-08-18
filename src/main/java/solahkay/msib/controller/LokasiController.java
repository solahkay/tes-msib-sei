package solahkay.msib.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import solahkay.msib.dto.AddLokasiRequest;
import solahkay.msib.service.LokasiService;

@RestController
@AllArgsConstructor
@RequestMapping("lokasi")
public class LokasiController {

    private final LokasiService lokasiService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void add(@RequestBody AddLokasiRequest request) {
        lokasiService.addLocation(request);
    }

}
