CREATE SEQUENCE IF NOT EXISTS seq1;
SELECT setval('seq1',100);

CREATE TABLE IF NOT EXISTS karyawan (
        id text NOT NULL PRIMARY KEY CHECK(id ~'^K-[0-9]+$') DEFAULT 'K-' || nextval('seq1'),
        nama VARCHAR(100) NOT NULL, tgl_masuk date NOT NULL,
        no_hp text NOT NULL, reimbursement NUMERIC NOT NULL,
        tgl_buat date NOT NULL, tgl_update date NOT NULL);


