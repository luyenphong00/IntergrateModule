package vn.com.ivnd.student;

import java.util.List;

import vn.com.ivnd.student.model.Cosmetics;

public interface MainContract {
    interface Model {
    }

    interface View {
        void retrieveListStudentSuccess(List<Cosmetics> listData);
        void updateStudentSuccess(String message);
    }

    interface Presenter {
        void insertStudent(Cosmetics student);
        void deleteStudent(int id);
        void editStudent(Cosmetics student);
        void retrieveListStudent();
        void deleteAll();
    }
}
