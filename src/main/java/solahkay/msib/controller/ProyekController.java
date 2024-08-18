package solahkay.msib.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import solahkay.msib.dto.AddProyekRequest;
import solahkay.msib.dto.WebResponse;
import solahkay.msib.service.ProyekService;

@RestController
@AllArgsConstructor
@RequestMapping("proyek")
public class ProyekController {

    private final ProyekService proyekService;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public WebResponse<String> add(@RequestBody AddProyekRequest request) {
        proyekService.addProject(request);

        return WebResponse.<String>builder()
                .message(HttpStatus.CREATED.name())
                .build();
    }

}
