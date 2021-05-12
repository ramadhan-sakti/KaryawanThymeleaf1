package com.example.KaryawanThymeleaf1.dao;

import com.example.KaryawanThymeleaf1.model.Karyawan;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface KaryawanDao {

    int insertKaryawan(String id, Karyawan karyawan);

    default int insertKaryawan(Karyawan karyawan) {
        String id = "1"; //
        return insertKaryawan(id, karyawan);
    }

    //Retrieve person
    List<Karyawan> selectAllPeople();

    Optional<Karyawan> selectKaryawanById(String id);

    int deleteKaryawanByID(String id);

    int updateKaryawanByID(String id, Karyawan karyawan);
}
