package vn.com.ivnd.student;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import vn.com.ivnd.student.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity{

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.submit.setOnClickListener(view -> {
            String username = binding.edtUsername.getText().toString();
            String password = binding.edtPass.getText().toString();
            if (!username.isEmpty() && !password.isEmpty()){
                if (username.equals("admin") && password.equals("admin")){
                    startActivity(new Intent(LoginActivity.this, ModuleMainActivity.class));
                }else {
                    Toast.makeText(this, "Không đúng tài khoản admin", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(this, "Nhập đủ dữ liệu", Toast.LENGTH_SHORT).show();
            }
        });
    }

}