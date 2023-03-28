package vn.com.ivnd.student;

import java.util.List;

import vn.com.ivnd.student.model.Student;

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
