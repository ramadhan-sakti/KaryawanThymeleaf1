package com.example.KaryawanThymeleaf1.api;

import com.example.KaryawanThymeleaf1.service.KaryawanService;
import com.example.KaryawanThymeleaf1.model.Karyawan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RequestMapping("api/v1/karyawan")
@RestController
public class KaryawanController {

    private final KaryawanService karyawanService;

    @Autowired
    public KaryawanController(KaryawanService karyawanService) {
        this.karyawanService = karyawanService;
    }

    //Post Request
    @PostMapping
    //Request HTTP Body containing the person in JSON form
    public void addPerson(@Valid @NonNull @RequestBody Karyawan karyawan) {
        karyawanService.addKaryawan(karyawan);
    }

    //Get Request
    @GetMapping
    public List<Karyawan> getAllPeople() {
        return karyawanService.getAllPeople();
    }




//    @RequestMapping(value="/getById", method = {RequestMethod.GET})
    @GetMapping(path = "{id}")
    public Karyawan getKaryawanById(@PathVariable("id") String id, HttpServletResponse httpResponse) throws IOException {
        karyawanService.getKaryawanByID(id);
//        httpResponse.sendRedirect("/dashboard");
        return karyawanService.getKaryawanByID(id).orElse(null);
    }

//    @DeleteMapping(path = "{id}")
    @RequestMapping(value="/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteKaryawanById(String id, HttpServletResponse httpResponse) throws IOException {
        karyawanService.deleteKaryawan(id);
        httpResponse.sendRedirect("/dashboard");
        return null;
    }

    @PutMapping(path = "/update/{id}")
    public String updatePerson(@PathVariable("id") String id, @Valid @NonNull @RequestBody Karyawan karyawanToUpdate,
                             HttpServletResponse httpResponse) throws IOException {
        karyawanService.updateKaryawan(id, karyawanToUpdate);
//        httpResponse.sendRedirect("/dashboard");
        return null;
    }
}