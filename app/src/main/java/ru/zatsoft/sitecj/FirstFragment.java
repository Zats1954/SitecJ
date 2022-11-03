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
import androidx.room.Room;
import ru.zatsoft.db.AppDatabase;
import ru.zatsoft.entity.UserEntity;
import ru.zatsoft.sitecj.databinding.FragmentFirstBinding;
import static ru.zatsoft.dao.UserConvertor.toUserEntity;
import static ru.zatsoft.sitecj.MainActivity.users;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private SharedPreferences sharedPref;
    private String imei = " ";
    private ArrayList<String> nameUsers;
    public AppDatabase db;
    private List<UserEntity> userEntity;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        db = Room.databaseBuilder(requireContext(),
                        AppDatabase.class, "sitecDb.db")
                .build();

        sharedPref = getActivity().getSharedPreferences("PREF", Context.MODE_PRIVATE);
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imei = sharedPref.getString("IMEI", " ");
        binding.textView.setText(imei);

        ArrayList<String> listU = new ArrayList<>();
        userEntity = new ArrayList<>();

        users.forEach(user -> listU.add(user.getName()));
        users.forEach(user -> userEntity.add(toUserEntity(user)));

        Thread writedB = new UseDB();
        writedB.start();

        Bundle bundle = new Bundle();
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

    class UseDB extends Thread {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void run() {
            super.run();

            db.userDao().clearAll();
            db.userDao().insertAll(userEntity);

            List<UserEntity> users = db.userDao().getAll();
            users.forEach(user -> {
                        System.out.println("id " + user.id);
                        System.out.println("Должность " + user.name);
                        System.out.println("шифр " + user.uid);
                        System.out.println("------------------------------------");
                    }
            );
        }
    }
}

