package ru.zatsoft.sitecj;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import ru.zatsoft.repository.RepositoryImp;
import ru.zatsoft.sitecj.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {
    private FragmentLoginBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
      }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String nameUser = getArguments().getString("name");
        binding.tvName.setText(nameUser);
        binding.btnLogin.setOnClickListener(v -> {
            String password = binding.etPassword.getText().toString();
            SharedPreferences sharedPref = getContext().getSharedPreferences("PREF", Context.MODE_PRIVATE);
            RepositoryImp newAuth = new RepositoryImp(nameUser, password);
            newAuth.authent(sharedPref.getString("IMEI"," "));
        });
    }
}
