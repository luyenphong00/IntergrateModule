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

public class DialogAdd extends DialogFragment {

    private Student student;
    private final itemOnClick listener;

    public DialogAdd(itemOnClick listener) {
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
        return inflater.inflate(R.layout.dialog_add, container);
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
        Button btnEdit,btnCancel;
        edtName = getView().findViewById(R.id.edt_name);
        edtClassRoom = getView().findViewById(R.id.edt_class);
        edtNS = getView().findViewById(R.id.ns);
        edtQQ = getView().findViewById(R.id.qq);
        edtCCCD = getView().findViewById(R.id.cccd);
        btnEdit = getView().findViewById(R.id.btn_edit);
        btnCancel = getView().findViewById(R.id.btn_cancel);
        btnEdit.setOnClickListener(view1 -> {
            if (listener != null) {
                String name = edtName.getText().toString();
                String classRoom = edtClassRoom.getText().toString();
                String ngaysinh = edtNS.getText().toString();
                String quequan = edtQQ.getText().toString();
                String cccd = edtCCCD.getText().toString();
                if (!name.isEmpty() && !classRoom.isEmpty() && !ngaysinh.isEmpty() && !quequan.isEmpty() && !cccd.isEmpty()){
                    student = new Student(name, classRoom, ngaysinh, quequan, cccd);
                    listener.onClickAdd(student);
                    dismiss();
                }else  {
                    Toast.makeText(requireContext(), "Nhập đủ dữ liệu", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnCancel.setOnClickListener(view12 -> dismiss());
    }

    public interface itemOnClick {
        void onClickAdd(Student student);
    }
}
