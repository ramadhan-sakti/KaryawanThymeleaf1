# KaryawanThymeleaf1
CRUD Karyawan dengan Springboot dan GitHub
CRUD with Springboot Thymeleaf.

Database postgreSQL bernama karyawandb, port 5432

######################################

CREATE SEQUENCE IF NOT EXISTS seq1; SELECT setval('seq1',100);

CREATE TABLE IF NOT EXISTS karyawan ( id text NOT NULL PRIMARY KEY CHECK(id ~'^K-[0-9]+$') DEFAULT 'K-' || nextval('seq1'), nama VARCHAR(100) NOT NULL, tgl_masuk date NOT NULL, no_hp text NOT NULL, reimbursement NUMERIC NOT NULL, tgl_buat date NOT NULL, tgl_update date NOT NULL);

INSERT INTO karyawan(nama,tgl_masuk,no_hp,reimbursement,tgl_buat,tgl_update) VALUES ('Johnny Paris', '2021-04-03', '082287676613', '4000000', '2021-05-02', '2021-05-03'), ('Zayn Zen', '2021-04-02', '082223456613', '5000000', '2021-05-02', '2021-05-03'), ('Jackie Chan', '2021-04-01', '082239876613', '4000000', '2021-05-02', '2021-05-03'), ('John Dory', '2021-04-03', '082222311613', '4000000', '2021-05-04', '2021-05-04'), ('Linux Torvalds', '2021-04-03', '082837276613', '6000000', '2021-05-03', '2021-05-03');

*Insert masih bermasalah :(
*Read dan delete sudah bisa :)
