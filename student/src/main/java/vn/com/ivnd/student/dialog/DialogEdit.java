package vn.com.ivnd.student.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;


import vn.com.ivnd.student.R;
import vn.com.ivnd.student.model.Student;

/**
 * Created by luyenphong on 10/9/2020.
 * 0358844343
 * luyenphong00@gmail.com
 */
public class DialogEdit extends DialogFragment {

    private Student student;
    private itemOnClick listener;

    public DialogEdit(Student student, itemOnClick listener) {
        this.student = student;
        this.listener = listener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setCancelable(false);
        return inflater.inflate(R.layout.dialog_edit, container);
    }

    @io.reactivex.annotations.NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // the content
        final RelativeLayout root = new RelativeLayout(getActivity());
        root.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        // creating the fullscreen dialog
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(root);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        return dialog;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final EditText edtName, edtClassRoom, edtNS, edtQQ, edtCCCD;
        Button btnEdit, btnCancel;
        edtName = getView().findViewById(R.id.edt_name);
        edtClassRoom = getView().findViewById(R.id.edt_class);
        edtNS = getView().findViewById(R.id.ns);
        edtQQ = getView().findViewById(R.id.qq);
        edtCCCD = getView().findViewById(R.id.cccd);
        btnEdit = getView().findViewById(R.id.btn_edit);
        btnCancel = getView().findViewById(R.id.btn_cancel);
        edtName.setText(student.getName());
        edtClassRoom.setText(student.getClassRoom());
        edtNS.setText(student.getNgaysinh());
        edtQQ.setText(student.getQuequan());
        edtCCCD.setText(student.getCccd());
        btnEdit.setOnClickListener(view1 -> {
            if (listener != null) {
                if (!edtCCCD.getText().toString().isEmpty()){
                    Student studentUpdate = new Student(edtName.getText().toString(),
                            edtClassRoom.getText().toString(),
                            edtNS.getText().toString(),
                            edtQQ.getText().toString(),
                            edtCCCD.getText().toString());
                    studentUpdate.setIdStudent(this.student.getIdStudent());
                    listener.onClickEdit(studentUpdate);
                    dismiss();
                }else  {
                    Toast.makeText(getContext(), "Căn cước công dân không được để trống", Toast.LENGTH_SHORT).show();
                }

            }
        });
        btnCancel.setOnClickListener(view12 -> dismiss());
    }

    public interface itemOnClick {
        void onClickEdit(Student student);
    }
}
