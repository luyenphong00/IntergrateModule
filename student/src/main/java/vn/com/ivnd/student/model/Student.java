package vn.com.ivnd.student.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by luyenphong on 10/8/2020.
 * 0358844343
 * luyenphong00@gmail.com
 */

@Entity(tableName = "Student")
public class Student {
    @PrimaryKey(autoGenerate = true)
    private int idStudent;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "classRoom")
    private String classRoom;

    public Student( String name, String classRoom) {
        this.name = name;
        this.classRoom = classRoom;
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

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }
}
