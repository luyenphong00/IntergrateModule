package vn.com.ivnd.student.dao;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Single;
import vn.com.ivnd.student.model.Cosmetics;


@Dao
public interface StudentDao {
    @Insert(onConflict = REPLACE)
    void insert(Cosmetics... items);

    @Query("Select * from Cosmetics")
    Single<List<Cosmetics>> findStudent();

    @Query("Delete from Cosmetics")
    void deleteAll();

    @Query("Delete from Cosmetics where Cosmetics.idCosmetics=:id")
    void deleteStudentById(int id);
}
