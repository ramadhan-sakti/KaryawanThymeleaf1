package com.example.KaryawanThymeleaf1.dao;

import com.example.KaryawanThymeleaf1.model.Karyawan;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


//Instantiate this class as a bean*, so we can inject it to other classes
//*Bean: the objects that form the backbone of your application and that are managed by the Spring IoC container
//*IoC = Inversion of Control, a process in which an object defines its dependencies without creating them

@Repository("fakeDao") //@Component also viable
public class FakeKaryawanDataAccessService implements KaryawanDao {

    private static List<Karyawan> DB = new ArrayList<>();

    @Override
    public int insertKaryawan(String id, Karyawan karyawan) {
        DB.add(new Karyawan(id, karyawan.getName(),karyawan.getTglMasuk(),
                karyawan.getNoHP(),karyawan.getReimbursement(), karyawan.getTglBuat(),
                karyawan.getTglUpdate()));
        return 1;
    }

    @Override
    public List<Karyawan> selectAllPeople() {
        return DB;
    }

    @Override
    public Optional<Karyawan> selectKaryawanById(String id) {
        return DB.stream().filter(karyawan -> karyawan.getId().equals(id)).findFirst();
    }

    @Override
    public int deleteKaryawanByID(String id) {
        Optional<Karyawan> karyawanMaybe = selectKaryawanById(id);
        if (karyawanMaybe.isEmpty()) {
            return 0;
        } else {
            DB.remove((karyawanMaybe.get()));
            return 1;
        }
    }

    @Override
    public int updateKaryawanByID(String id, Karyawan karyawanUpdate) {
        return selectKaryawanById(id).map(karyawan -> {
            int indexOfKaryawanToUpdate = DB.indexOf(karyawan);
            if (indexOfKaryawanToUpdate >= 0) {
                DB.set(indexOfKaryawanToUpdate, new Karyawan(id, karyawanUpdate.getName(),karyawanUpdate.getTglMasuk(),
                        karyawanUpdate.getNoHP(),karyawanUpdate.getReimbursement(), karyawanUpdate.getTglBuat(),
                        karyawanUpdate.getTglUpdate()));
                return 1;
              }
              return 0;
        }).orElse(0);
    }
}
