package vn.com.ivnd.student.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "Cosmetics")
public class Cosmetics {
    @PrimaryKey(autoGenerate = true)
    private int idCosmetics;
    @ColumnInfo(name = "ten")
    private String ten;
    @ColumnInfo(name = "giatien")
    private String giatien;
    @ColumnInfo(name = "hansudung")
    private String hansudung;


    public Cosmetics(String ten, String giatien, String hansudung) {
        this.ten = ten;
        this.giatien = giatien;
        this.hansudung = hansudung;
    }

    public int getIdCosmetics() {
        return idCosmetics;
    }

    public void setIdCosmetics(int idCosmetics) {
        this.idCosmetics = idCosmetics;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getGiatien() {
        return giatien;
    }

    public void setGiatien(String giatien) {
        this.giatien = giatien;
    }

    public String getHansudung() {
        return hansudung;
    }

    public void setHansudung(String hansudung) {
        this.hansudung = hansudung;
    }
}
