package vn.com.ivnd.student;

import java.util.List;

import vn.com.ivnd.student.model.Student;

/**
 * Created by luyenphong on 10/13/2020.
 * 0358844343
 * luyenphong00@gmail.com
 */
public interface MainContract {
    interface Model {
    }

    interface View {
        void retrieveListStudentSuccess(List<Student> listData);
        void updateStudentSuccess(String message);
    }

    interface Presenter {
        void insertStudent(Student student);
        void deleteStudent(int id);
        void editStudent(Student student);
        void retrieveListStudent();
        void deleteAll();
    }
}
