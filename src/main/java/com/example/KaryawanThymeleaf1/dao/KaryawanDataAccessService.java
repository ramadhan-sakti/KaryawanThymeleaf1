package com.example.KaryawanThymeleaf1.dao;

import com.example.KaryawanThymeleaf1.model.Karyawan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.KaryawanThymeleaf1.JsonReader;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

//Instantiate this class as a bean*, so we can inject it to other classes
//*Bean: the objects that form the backbone of your application and that are managed by the Spring IoC container
//*IoC = Inversion of Control, a process in which an object defines its dependencies without creating them

@Repository("postgres")
public class KaryawanDataAccessService implements KaryawanDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public KaryawanDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertKaryawan(String id, Karyawan karyawan) {
        return 0;
    }

    @Override
    public int insertKaryawan(Karyawan karyawan) {
        return 0;
    }

    @Override
    public List<Karyawan> selectAllPeople() {
        String sql = "SELECT * FROM karyawan";
        List<Karyawan> people = jdbcTemplate.query(sql, (resultSet, i) -> {
            String id = resultSet.getString("id");
            String nama = resultSet.getString("nama");
            Date tglMasuk = resultSet.getDate("tgl_masuk");
            String noHP = resultSet.getString("no_hp");
            String reimbursement = resultSet.getString("reimbursement");
            Date tglBuat = resultSet.getDate("tgl_buat");
            Date tglUpdate = resultSet.getDate("tgl_update");
            return new Karyawan(id,nama, tglMasuk, noHP, reimbursement, tglBuat, tglUpdate);
        });
        return people;
    }

    @Override
    public Optional<Karyawan> selectKaryawanById(String id) {
        String sql = "SELECT * FROM karyawan WHERE id = ?";
        System.out.println("GET BY ID HERE ===================================================");
        System.out.println("ID= "+id);

        Karyawan karyawan = jdbcTemplate.queryForObject(sql, new Object[]{id}, (resultSet, i) -> {
            String karyawanId = resultSet.getString("id");
            String nama = resultSet.getString("nama");
            Date tglMasuk = resultSet.getDate("tgl_masuk");
            String noHP = resultSet.getString("no_hp");
            String reimbursement = resultSet.getString("reimbursement");
            Date tglBuat = resultSet.getDate("tgl_buat");
            Date tglUpdate = resultSet.getDate("tgl_update");
            return new Karyawan(karyawanId, nama, tglMasuk, noHP, reimbursement, tglBuat, tglUpdate);
        });
        System.out.println(karyawan.getName());
        System.out.println(karyawan.getId());
        return Optional.ofNullable(karyawan);
    }

    @Override
    public int deleteKaryawanByID(String id) {
        String sql = "DELETE FROM karyawan WHERE id = ?";
        Object[] args = new Object[] {id};
        System.out.println("DELETE HERE ===================================================");
        System.out.println("ID= "+id);
        System.out.println(jdbcTemplate.update(sql, args));
        return jdbcTemplate.update(sql, args);
    }

    @Override
    public int updateKaryawanByID(String id, Karyawan karyawan) {
        return 0;
    }
}
