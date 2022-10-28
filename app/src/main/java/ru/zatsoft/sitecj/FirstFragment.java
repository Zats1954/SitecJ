package ru.zatsoft.sitecj;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import ru.zatsoft.sitecj.databinding.FragmentFirstBinding;
import static ru.zatsoft.sitecj.MainActivity.users;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private SharedPreferences sharedPref;
    private String imei = " ";
    private ArrayList<String> nameUsers ;


    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
//        nameUsers = new ArrayList(MainActivity.listUsers.getUsers().getListUsers());
        sharedPref = getActivity().getSharedPreferences("PREF", Context.MODE_PRIVATE);
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imei = sharedPref.getString("IMEI"," ");
        binding.textView.setText(imei);

        Integer amount =  10 ;
        Bundle bundle = new Bundle();
        bundle.putInt("amount", amount);

       ArrayList<String> listU =  new ArrayList<>();
       users.forEach(user -> listU.add(user.getName()));

        bundle.putStringArrayList("listUsers", listU);
        binding.buttonFirst.setOnClickListener(view1 -> NavHostFragment.findNavController(FirstFragment.this)
                .navigate(R.id.action_FirstFragment_to_SecondFragment, bundle));
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        sharedPref = context.getSharedPreferences("PREF", Context.MODE_PRIVATE);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}