package com.example.KaryawanThymeleaf1.service;

import com.example.KaryawanThymeleaf1.dao.KaryawanDao;
import com.example.KaryawanThymeleaf1.model.Karyawan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

//Logic should be written here

@Service //@Component also viable
public class KaryawanService {

    private final KaryawanDao karyawanDao;

    @Autowired //inject dependencies (database can be changed just by setting qualifier)
    public KaryawanService(@Qualifier("postgres") KaryawanDao karyawanDao) {
        this.karyawanDao = karyawanDao;
    }

    public int addKaryawan(Karyawan karyawan) {
        return karyawanDao.insertKaryawan(karyawan);
    }

    public List<Karyawan> getAllPeople() {
        return karyawanDao.selectAllPeople();
    }

    public Optional<Karyawan> getKaryawanByID(String id) {
        System.out.println("ID = "+id);
        List<Karyawan> listKaryawan = karyawanDao.selectAllPeople();
        boolean exists = false;
        for(int i=0;i<listKaryawan.size();i++) {
            System.out.println(listKaryawan.get(i).getId());
            if (listKaryawan.get(i).getId().equals(id)) {
                exists = true;
            }
        }
        if(!exists) {
            throw new IllegalStateException(
                    "Karyawan dengan id "+id+" tidak ada");
        }
        return karyawanDao.selectKaryawanById(id);
    }

    public int deleteKaryawan(String id) {
        System.out.println("ID = "+id);
        List<Karyawan> listKaryawan = karyawanDao.selectAllPeople();
        boolean exists = false;
        for(int i=0;i<listKaryawan.size();i++) {
            System.out.println(listKaryawan.get(i).getId());
            if (listKaryawan.get(i).getId().equals(id)) {
                exists = true;
            }
        }
        if(!exists) {
            throw new IllegalStateException(
                    "Karyawan dengan id "+id+" tidak ada");
        }
        return karyawanDao.deleteKaryawanByID(id);
    }

    public int updateKaryawan(String id, Karyawan newKaryawan) {
        return karyawanDao.updateKaryawanByID(id, newKaryawan);
    }
}
