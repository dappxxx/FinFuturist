package Models;

import java.time.LocalDate;

public class Article {
    private int id; // Tambahkan atribut id
    private String namaPengirim;
    private String fileGambar;
    private String fileArtikel;
    private String kategoriArtikel;
    private LocalDate tanggalKirim;
    private String filePathGambar;
    private String filePathArtikel;
    private long fileSizeGambar; // Tambahkan atribut ukuran file gambar
    private long fileSizeArtikel; // Tambahkan atribut ukuran file artikel

    // Constructor dengan id dan ukuran file
    public Article(int id, String namaPengirim, String fileGambar, String fileArtikel, String kategoriArtikel, LocalDate tanggalKirim, String filePathGambar, String filePathArtikel, long fileSizeGambar, long fileSizeArtikel) {
        this.id = id; // Set id di constructor
        this.namaPengirim = namaPengirim;
        this.fileGambar = fileGambar;
        this.fileArtikel = fileArtikel;
        this.kategoriArtikel = kategoriArtikel;
        this.tanggalKirim = tanggalKirim;
        this.filePathGambar = filePathGambar;
        this.filePathArtikel = filePathArtikel;
        this.fileSizeGambar = fileSizeGambar;
        this.fileSizeArtikel = fileSizeArtikel;
    }

    // Constructor tanpa id, jika diperlukan
    public Article(String namaPengirim, String fileGambar, String fileArtikel, String kategoriArtikel, LocalDate tanggalKirim, String filePathGambar, String filePathArtikel, long fileSizeGambar, long fileSizeArtikel) {
        this.namaPengirim = namaPengirim;
        this.fileGambar = fileGambar;
        this.fileArtikel = fileArtikel;
        this.kategoriArtikel = kategoriArtikel;
        this.tanggalKirim = tanggalKirim;
        this.filePathGambar = filePathGambar;
        this.filePathArtikel = filePathArtikel;
        this.fileSizeGambar = fileSizeGambar;
        this.fileSizeArtikel = fileSizeArtikel;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamaPengirim() {
        return namaPengirim;
    }

    public void setNamaPengirim(String namaPengirim) {
        this.namaPengirim = namaPengirim;
    }

    public String getFileGambar() {
        return fileGambar;
    }

    public void setFileGambar(String fileGambar) {
        this.fileGambar = fileGambar;
    }

    public String getFileArtikel() {
        return fileArtikel;
    }

    public void setFileArtikel(String fileArtikel) {
        this.fileArtikel = fileArtikel;
    }

    public String getKategoriArtikel() {
        return kategoriArtikel;
    }

    public void setKategoriArtikel(String kategoriArtikel) {
        this.kategoriArtikel = kategoriArtikel;
    }

    public LocalDate getTanggalKirim() {
        return tanggalKirim;
    }

    public void setTanggalKirim(LocalDate tanggalKirim) {
        this.tanggalKirim = tanggalKirim;
    }

    public String getFilePathGambar() {
        return filePathGambar;
    }

    public void setFilePathGambar(String filePathGambar) {
        this.filePathGambar = filePathGambar;
    }

    public String getFilePathArtikel() {
        return filePathArtikel;
    }

    public void setFilePathArtikel(String filePathArtikel) {
        this.filePathArtikel = filePathArtikel;
    }

    public long getFileSizeGambar() {
        return fileSizeGambar;
    }

    public void setFileSizeGambar(long fileSizeGambar) {
        this.fileSizeGambar = fileSizeGambar;
    }

    public long getFileSizeArtikel() {
        return fileSizeArtikel;
    }

    public void setFileSizeArtikel(long fileSizeArtikel) {
        this.fileSizeArtikel = fileSizeArtikel;
    }
}
