package solahkay.msib.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import solahkay.msib.dto.AddLokasiRequest;
import solahkay.msib.dto.LokasiResponse;
import solahkay.msib.dto.PagingResponse;
import solahkay.msib.dto.WebResponse;
import solahkay.msib.service.LokasiService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("lokasi")
public class LokasiController {

    private final LokasiService lokasiService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public WebResponse<String> add(@RequestBody AddLokasiRequest request) {
        lokasiService.addLocation(request);
        return WebResponse.<String>builder()
                .message(HttpStatus.CREATED.name())
                .build();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<List<LokasiResponse>> getAll(@RequestParam(name = "page", defaultValue = "0") int page,
                                                    @RequestParam(name = "size", defaultValue = "10") int size) {
        Page<LokasiResponse> locations = lokasiService.getAllLocations(page, size);
        return WebResponse.<List<LokasiResponse>>builder()
                .data(locations.getContent())
                .message(HttpStatus.OK.name())
                .paging(PagingResponse.builder()
                        .currentPage(locations.getNumber())
                        .totalPage(locations.getTotalPages())
                        .size(locations.getSize())
                        .build())
                .build();
    }

}
