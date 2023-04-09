package vn.com.ivnd.student

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import vn.com.ivnd.student.StudentAdapter.onClickItem
import vn.com.ivnd.student.databinding.ActivityFilterBinding
import vn.com.ivnd.student.dialog.DialogEdit
import vn.com.ivnd.student.model.Student

class FilterStudentActivity : AppCompatActivity(), MainContract.View {

    private lateinit var binding: ActivityFilterBinding
    private var adapter: StudentAdapter? = null
    private val students = mutableListOf<Student>()
    private var presenter: MainPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@FilterStudentActivity, R.layout.activity_filter)
        supportActionBar?.hide()

        presenter = MainPresenter(this)
        presenter?.retrieveListStudent()
        adapter = StudentAdapter(students, this@FilterStudentActivity, object : onClickItem {
            override fun onClickEdit(student: Student) {
                val dialogEdit = DialogEdit(student) { student1: Student? -> presenter?.editStudent(student1) }
                dialogEdit.show(supportFragmentManager, dialogEdit.tag)
            }

            override fun onClickDelete(id: Int) {
                presenter?.deleteStudent(id)
            }
        })
        binding.rclStudent.adapter = adapter
        adapter?.setStudents(students)

        binding.searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {
                adapter?.setStudents(adapter?.searchStudent(editable.toString(), students))
            }
        })

        binding.include.back.setOnClickListener {
            finish()
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    override fun retrieveListStudentSuccess(listData: MutableList<Student>?) {
        students.clear()
        students.addAll(listData ?: mutableListOf())
        adapter?.notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateStudentSuccess(message: String?) {
        presenter?.retrieveListStudent()
        adapter?.notifyDataSetChanged()
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}