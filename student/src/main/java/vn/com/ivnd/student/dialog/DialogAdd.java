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

public class DialogAdd extends DialogFragment {

    private Cosmetics cosmetic;
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
        final EditText tenMypam, giatien, hansudung;
        Button btnEdit,btnCancel;
        tenMypam = getView().findViewById(R.id.edt_name);
        giatien = getView().findViewById(R.id.edt_class);
        hansudung = getView().findViewById(R.id.edtHSD);
        btnEdit = getView().findViewById(R.id.btn_edit);
        btnCancel = getView().findViewById(R.id.btn_cancel);
        btnEdit.setOnClickListener(view1 -> {
            if (listener != null) {
                String name = tenMypam.getText().toString();
                String money = giatien.getText().toString();
                String hsd = hansudung.getText().toString();
                if (!name.isEmpty()  && !money.isEmpty() && !hsd.isEmpty()){
                    cosmetic = new Cosmetics(name, money, hsd);
                    listener.onClickAdd(cosmetic);
                    dismiss();
                }else  {
                    Toast.makeText(requireContext(), "Thiếu thông tin mỹ phẩm", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnCancel.setOnClickListener(view12 -> dismiss());
    }

    public interface itemOnClick {
        void onClickAdd(Cosmetics student);
    }
}
