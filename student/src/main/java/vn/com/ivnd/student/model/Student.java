package vn.com.ivnd.student.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "Student")
public class Student {
    @PrimaryKey(autoGenerate = true)
    private int idStudent;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "classRoom")
    private String classRoom;
    @ColumnInfo(name = "ngaysinh")
    private String ngaysinh;
    @ColumnInfo(name = "quequan")
    private String quequan;
    @ColumnInfo(name = "cccd")
    private String cccd;

    public Student(String name, String classRoom, String ngaysinh, String quequan, String cccd) {
        this.name = name;
        this.classRoom = classRoom;
        this.ngaysinh = ngaysinh;
        this.quequan = quequan;
        this.cccd = cccd;
    }

    public String getCccd() {
        if (cccd == null) {
            cccd = "";
        }
        return cccd;
    }

    public String getNgaysinh() {
        if (ngaysinh == null) {
            ngaysinh = "";
        }
        return ngaysinh;
    }

    public String getQuequan() {
        if (quequan == null) {
            quequan = "";
        }
        return quequan;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public void setQuequan(String quequan) {
        this.quequan = quequan;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }
}
