package sn.esmt.emploi.ws;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sn.esmt.emploi.dao.AppCVRepository;
import sn.esmt.emploi.entites.AppCVEntity;

import java.util.List;

@RestController
@RequestMapping(value = "/cv")
@AllArgsConstructor
public class AppCVController {
    private final AppCVRepository appCVRepository;

    @PostMapping(path = "/save")
    public AppCVEntity save(@RequestBody AppCVEntity appCVEntity) { return appCVRepository.save(appCVEntity); }
    @GetMapping(path = "/all")
    public List<AppCVEntity> getAll() {
        return appCVRepository.findAll();
    }
    @GetMapping(path = "/findId")
    public AppCVEntity findById(@RequestParam int id) {
        return appCVRepository.findById(id);
    }
    @PutMapping(path = "/update")
    public AppCVEntity update(@RequestBody AppCVEntity appCVEntity) { return appCVRepository.save(appCVEntity); }
    @DeleteMapping(path = "/delete")
    public void delete(@RequestParam int id) {
        appCVRepository.delete(findById(id));
    }
}
