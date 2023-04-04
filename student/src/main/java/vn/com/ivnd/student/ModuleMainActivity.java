package vn.com.ivnd.student;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import java.util.ArrayList;
import java.util.List;

import vn.com.ivnd.student.databinding.ActivityModuleMainBinding;
import vn.com.ivnd.student.dialog.DialogAdd;
import vn.com.ivnd.student.dialog.DialogEdit;
import vn.com.ivnd.student.model.Student;

public class ModuleMainActivity extends AppCompatActivity implements MainContract.View {

    private ActivityModuleMainBinding binding;
    private StudentAdapter adapter;
    private List<Student> students = new ArrayList<>();
    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_module_main);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        presenter = new MainPresenter(ModuleMainActivity.this);
        presenter.retrieveListStudent();
        adapter = new StudentAdapter(students, ModuleMainActivity.this, new StudentAdapter.onClickItem() {
            @Override
            public void onClickEdit(Student student) {
                DialogEdit dialogEdit = new DialogEdit(student, student1 -> presenter.editStudent(student1));
                dialogEdit.show(getSupportFragmentManager(), dialogEdit.getTag());
            }

            @Override
            public void onClickDelete(int id) {
                presenter.deleteStudent(id);
            }
        });
        binding.rclStudent.setAdapter(adapter);
        adapter.setStudents(students);

        binding.imgAddStudent.setOnClickListener(view -> {
            DialogAdd dialogEdit = new DialogAdd(student -> presenter.insertStudent(student));
            dialogEdit.show(getSupportFragmentManager(), dialogEdit.getTag());
        });

        binding.include.imgDelete.setOnClickListener(view -> presenter.deleteAll());

        binding.include.ivLogout.setOnClickListener(view -> {
            finish();
        });

        binding.include.search.setOnClickListener(view -> startActivity(new Intent(this, FilterStudentActivity.class)));
    }

    @Override
    public void retrieveListStudentSuccess(List<Student> listData) {
        students.clear();
        students.addAll(listData);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void updateStudentSuccess(String message) {
        presenter.retrieveListStudent();
        adapter.notifyDataSetChanged();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}