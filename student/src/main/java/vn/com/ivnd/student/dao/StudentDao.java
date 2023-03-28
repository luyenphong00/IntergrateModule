package vn.com.ivnd.student.dao;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Single;
import vn.com.ivnd.student.model.Student;


@Dao
public interface StudentDao {
    @Insert(onConflict = REPLACE)
    void insert(Student... items);

    @Query("Select * from Student")
    Single<List<Student>> findStudent();

    @Query("Delete from Student")
    void deleteAll();

    @Query("Delete from Student where Student.idStudent=:id")
    void deleteStudentById(int id);
}
