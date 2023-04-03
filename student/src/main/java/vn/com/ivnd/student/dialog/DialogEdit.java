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
import vn.com.ivnd.student.model.Cosmetics;

public class DialogEdit extends DialogFragment {

    private Cosmetics cosmetic;
    private itemOnClick listener;

    public DialogEdit(Cosmetics cosmetic, itemOnClick listener) {
        this.cosmetic = cosmetic;
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
        final EditText edtName, edtGiaTien, edtHSD;
        Button btnEdit, btnCancel;
        edtName = getView().findViewById(R.id.edt_name);
        edtGiaTien = getView().findViewById(R.id.edt_class);
        edtHSD = getView().findViewById(R.id.edtHSD);
        btnEdit = getView().findViewById(R.id.btn_edit);
        btnCancel = getView().findViewById(R.id.btn_cancel);
        edtName.setText(cosmetic.getTen());
        edtGiaTien.setText(cosmetic.getGiatien());
        edtHSD.setText(cosmetic.getHansudung());

        btnEdit.setOnClickListener(view1 -> {
            if (listener != null) {
                String name = edtName.getText().toString();
                String money = edtGiaTien.getText().toString();
                String hsd = edtHSD.getText().toString();
                if (!name.isEmpty()  && !money.isEmpty() && !hsd.isEmpty()){
                    Cosmetics studentUpdate = new Cosmetics(name, money, hsd);
                    studentUpdate.setIdCosmetics(this.cosmetic.getIdCosmetics());
                    listener.onClickEdit(studentUpdate);
                    dismiss();
                }else  {
                    Toast.makeText(getContext(), "Cần đủ thông tin mỹ phẩm", Toast.LENGTH_SHORT).show();
                }

            }
        });
        btnCancel.setOnClickListener(view12 -> dismiss());
    }

    public interface itemOnClick {
        void onClickEdit(Cosmetics student);
    }
}
