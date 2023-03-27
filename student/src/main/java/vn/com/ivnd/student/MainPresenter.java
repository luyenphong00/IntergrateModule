package vn.com.ivnd.student;

import android.annotation.SuppressLint;
import android.content.Context;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import vn.com.ivnd.student.database.AppDatabase;
import vn.com.ivnd.student.model.Student;

/**
 * Created by luyenphong on 10/13/2020.
 * 0358844343
 * luyenphong00@gmail.com
 */
public class MainPresenter implements MainContract.Presenter {
    private MainContract.View view;
    private AppDatabase appDatabase;

    public MainPresenter(MainContract.View view) {
        this.view = view;
        appDatabase = AppDatabase.getInMemoryDatabase((Context) view);
    }

    @Override
    public void insertStudent(Student student) {
        appDatabase.studentDao().insert(student);
        appDatabase.studentDao().findStudent();
        view.updateStudentSuccess("Thêm thành công");
    }

    @Override
    public void deleteStudent(int id) {
        appDatabase.studentDao().deleteStudentById(id);
        view.updateStudentSuccess("Xóa thành công");
    }

    @Override
    public void editStudent(Student student) {
        appDatabase.studentDao().insert(student);
        view.updateStudentSuccess("Sửa thành công sinh viên có id " + student.getIdStudent());
    }

    @SuppressLint("CheckResult")
    @Override
    public void retrieveListStudent() {
        appDatabase.studentDao().findStudent().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    view.retrieveListStudentSuccess(response);
                });
    }

    @Override
    public void deleteAll() {
        appDatabase.studentDao().deleteAll();
        view.updateStudentSuccess("Xóa thành công tất cả sinh viên");
    }
}
