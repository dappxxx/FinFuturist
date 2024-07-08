package Models;

import java.time.LocalDate;

public class UploadArticle {
    private String namaPengirim;
    private String fileGambar;
    private String fileArtikel;
    private String kategoriArtikel;
    private LocalDate tanggalKirim;
    private String filePathGambar; // New field

    public UploadArticle(String namaPengirim, String fileGambar, String fileArtikel, String kategoriArtikel, LocalDate tanggalKirim, String filePathGambar) {
        this.namaPengirim = namaPengirim;
        this.fileGambar = fileGambar;
        this.fileArtikel = fileArtikel;
        this.kategoriArtikel = kategoriArtikel;
        this.tanggalKirim = tanggalKirim;
        this.filePathGambar = filePathGambar; // Set in constructor
    }

    // Getters and setters
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

    
}
