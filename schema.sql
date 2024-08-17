CREATE TABLE proyek
(
    id              SERIAL PRIMARY KEY,
    nama_proyek     VARCHAR(255) NOT NULL,
    client          VARCHAR(255) NOT NULL,
    tgl_mulai       TIMESTAMP,
    tgl_selesai     TIMESTAMP,
    pimpinan_proyek VARCHAR(255) NOT NULL,
    keterangan      TEXT,
    created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE lokasi
(
    id          SERIAL PRIMARY KEY,
    nama_lokasi VARCHAR(255) NOT NULL,
    negara      VARCHAR(255) NOT NULL,
    provinsi    VARCHAR(255) NOT NULL,
    kota        VARCHAR(255) NOT NULL,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE proyek_lokasi
(
    proyek_id INTEGER REFERENCES proyek (id) ON DELETE CASCADE,
    lokasi_id INTEGER REFERENCES lokasi (id) ON DELETE CASCADE,
    PRIMARY KEY (proyek_id, lokasi_id)
);
