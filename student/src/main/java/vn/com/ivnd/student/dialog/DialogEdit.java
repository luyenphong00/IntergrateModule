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
        final EditText edtName, edtClassRoom;
        Button btnEdit,btnCancel;
        edtName = getView().findViewById(R.id.edt_name);
        edtClassRoom = getView().findViewById(R.id.edt_class);
        btnEdit = getView().findViewById(R.id.btn_edit);
        btnCancel = getView().findViewById(R.id.btn_cancel);
        edtName.setText(student.getName());
        edtClassRoom.setText(student.getClassRoom());
        btnEdit.setOnClickListener(view1 -> {
            if (listener != null) {
                listener.onClickEdit(edtName.getText().toString()
                        , edtClassRoom.getText().toString());
                dismiss();
            }
        });
        btnCancel.setOnClickListener(view12 -> dismiss());
    }

    public interface itemOnClick {
        void onClickEdit(String name, String classRoom);
    }
}
