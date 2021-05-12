package com.example.KaryawanThymeleaf1;

import com.example.KaryawanThymeleaf1.api.KaryawanController;
import com.example.KaryawanThymeleaf1.dao.KaryawanDataAccessService;
import com.example.KaryawanThymeleaf1.model.Karyawan;
import com.example.KaryawanThymeleaf1.service.KaryawanService;
import com.example.KaryawanThymeleaf1.JsonReader;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ThymeleafController {

    /**
     * Handle the root (/) endpoint and return a start page
     * @param model
     * @return
     */
    @GetMapping("/hello")
    public String printHello(Model model) {
        model.addAttribute("message", "Hello World");
        return "index";
    }

//    @GetMapping("/getAllKaryawan")
//    public String printKaryawan(Model model) throws IOException {
//        List<Karyawan> karyawanList = new ArrayList<Karyawan>();
//        JsonReader jsonReader = new JsonReader();
//        //JSONObject jsonObject = jsonReader.readJsonFromUrl("http://localhost:8080/api/v1/karyawan");
//        JSONArray jsonArray = jsonReader.readJsonArrFromUrl("http://localhost:8080/api/v1/karyawan");
//        JSONObject kar1 = jsonArray.getJSONObject(0);
//        JSONObject kar2 = jsonArray.getJSONObject(1);
//        JSONObject kar3 = jsonArray.getJSONObject(2);
//        String result = jsonArray.toString();
//        String namaKar1 = kar1.getString("name");
//        //JSONArray jsonArray = jsonObject.toJSONArray(jsonObject.names());
//        //model.addAttribute("message", new Karyawan("K-001","Hoha hahi", new Date(2020-02-12),"090909212", "20000",new Date(2020-02-12),new Date(2020-02-12) ));
//        model.addAttribute("message", namaKar1);
//        return "index";
//    }

    @GetMapping("/style")
    public String style() {
        return "cssjs-demo";
    }

    @GetMapping("/dashboard")
    public String getKaryawanDashboard(Model model) throws IOException, ParseException {
        List<Karyawan> listKaryawan = new ArrayList<Karyawan>();
        JsonReader jsonReader = new JsonReader();
        JSONArray jsonArray = jsonReader.readJsonArrFromUrl("http://localhost:8080/api/v1/karyawan");
        String result = jsonArray.toString();

        for(int i=0;i<jsonArray.length();i ++) {
            JSONObject jsonObjectKaryawan = jsonArray.getJSONObject(i);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date tglMasuk = sdf.parse(jsonObjectKaryawan.getString("tglMasuk"));
            Date tglBuat = sdf.parse(jsonObjectKaryawan.getString("tglBuat"));
            Date tglUpdate = sdf.parse(jsonObjectKaryawan.getString("tglUpdate"));
            listKaryawan.add(new Karyawan(jsonObjectKaryawan.getString("id"), jsonObjectKaryawan.getString("name"),
                    tglMasuk, jsonObjectKaryawan.getString("noHP"),
                    jsonObjectKaryawan.getString("reimbursement"), tglBuat,
                    tglUpdate));
        }

        model.addAttribute("listKaryawan", listKaryawan);
        return "dashboard";
    }

    @GetMapping("/dashboard/getById/")
    public String getKaryawanDashboardById(Model model,String id) throws IOException, ParseException {
        //id = "K-121";
        List<Karyawan> listKaryawan = new ArrayList<Karyawan>();
        JsonReader jsonReader = new JsonReader();
        JSONObject jsonObjectKaryawan = jsonReader.readJsonFromUrl("http://localhost:8080/api/v1/karyawan/"+id);
        String result = jsonObjectKaryawan.toString();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date tglMasuk = sdf.parse(jsonObjectKaryawan.getString("tglMasuk"));
        Date tglBuat = sdf.parse(jsonObjectKaryawan.getString("tglBuat"));
        Date tglUpdate = sdf.parse(jsonObjectKaryawan.getString("tglUpdate"));

        Karyawan karyawanResult = new  Karyawan(jsonObjectKaryawan.getString("id"), jsonObjectKaryawan.getString("name"),
                tglMasuk, jsonObjectKaryawan.getString("noHP"),
                jsonObjectKaryawan.getString("reimbursement"), tglBuat,
                tglUpdate);

        listKaryawan.add(karyawanResult);

        model.addAttribute("message", result);
        model.addAttribute("listKaryawan", listKaryawan);
        return "dashboard";
    }

    @GetMapping("/tambahKaryawan")
    public String tambahKaryawan() {
        return "tambah-karyawan";
    }
}
