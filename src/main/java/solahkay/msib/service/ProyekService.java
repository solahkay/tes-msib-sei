package solahkay.msib.service;

import org.hibernate.sql.Update;
import org.springframework.data.domain.Page;
import solahkay.msib.dto.AddProyekRequest;
import solahkay.msib.dto.DeleteProyekRequest;
import solahkay.msib.dto.ProyekResponse;
import solahkay.msib.dto.UpdateProyekRequest;

public interface ProyekService {

    void addProject(AddProyekRequest request);

    Page<ProyekResponse> getAllProjects(int page, int size);

    ProyekResponse updateProject(UpdateProyekRequest request);

    void deleteProject(DeleteProyekRequest request);

}
