package solahkay.msib.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import solahkay.msib.dto.AddProyekRequest;
import solahkay.msib.dto.DeleteProyekRequest;
import solahkay.msib.dto.PagingResponse;
import solahkay.msib.dto.ProyekResponse;
import solahkay.msib.dto.UpdateProyekRequest;
import solahkay.msib.dto.WebResponse;
import solahkay.msib.service.ProyekService;

import java.util.List;

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

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<ProyekResponse>> getAll(@RequestParam(name = "page", defaultValue = "0") int page,
                                                    @RequestParam(name = "size", defaultValue = "10") int size) {
        Page<ProyekResponse> projects = proyekService.getAllProjects(page, size);
        return WebResponse.<List<ProyekResponse>>builder()
                .data(projects.getContent())
                .message(HttpStatus.OK.name())
                .paging(PagingResponse.builder()
                        .currentPage(projects.getNumber())
                        .totalPage(projects.getTotalPages())
                        .size(projects.getSize())
                        .build())
                .build();
    }

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<ProyekResponse> update(@RequestBody UpdateProyekRequest request) {
        ProyekResponse project = proyekService.updateProject(request);
        return WebResponse.<ProyekResponse>builder()
                .data(project)
                .message(HttpStatus.OK.name())
                .build();
    }

    @DeleteMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> delete(@RequestBody DeleteProyekRequest request) {
        proyekService.deleteProject(request);
        return WebResponse.<String>builder()
                .message(HttpStatus.OK.name())
                .build();
    }

}
