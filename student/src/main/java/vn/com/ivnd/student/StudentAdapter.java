package vn.com.ivnd.student;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

import vn.com.ivnd.student.model.Student;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentHolder> {

    private List<Student> students;
    private Context context;
    private onClickItem listener;

    public StudentAdapter(List<Student> students, Context context, onClickItem listener) {
        this.students = students;
        this.context = context;
        this.listener = listener;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
        notifyDataSetChanged();
    }

    public List<Student> searchStudent(String textQuery, List<Student> students) {
        List<Student> listAlbumSearch = new ArrayList<>();
        for (Student movies : students) {
            if (movies.getName().toLowerCase().contains(textQuery.toLowerCase())) {
                listAlbumSearch.add(movies);
            }
        }
        return listAlbumSearch;
    }

    @NonNull
    @Override
    public StudentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_student, parent, false);
        return new StudentHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentHolder holder, int position) {
        final Student student = students.get(position);
        holder.binDataStudent(student);
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClickEdit(student);
                }
            }
        });

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    students.remove(student);
                    listener.onClickDelete(student.getIdStudent());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return students == null ? 0 : students.size();
    }

    public class StudentHolder extends RecyclerView.ViewHolder {
        AppCompatTextView tvName, tvClass;
        AppCompatImageView imgEdit, imgDelete;

        public StudentHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_full_name);
            tvClass = itemView.findViewById(R.id.tv_classroom);
            imgEdit = itemView.findViewById(R.id.tv_edit);
            imgDelete = itemView.findViewById(R.id.img_delete);
        }

        public void binDataStudent(Student student) {
            tvName.setText(student.getName());
            tvClass.setText(student.getClassRoom() + "____" + student.getCccd());
        }
    }

    public interface onClickItem {
        void onClickEdit(Student student);

        void onClickDelete(int id);
    }
}
