package ru.zatsoft.sitecj;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ru.zatsoft.adapter.UserAdapter;
import ru.zatsoft.sitecj.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment implements UserAdapter.ItemClickListener {

    private FragmentSecondBinding binding;
    private UserAdapter radapter;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<String> users = null;
        if (getArguments() != null) {
            users = getArguments().getStringArrayList("listUsers");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1,
                users);
//        binding.listUsers.setAdapter(adapter);
        binding.buttonSecond.setOnClickListener(view1 -> NavHostFragment.findNavController(SecondFragment.this)
                .navigate(R.id.action_SecondFragment_to_FirstFragment));

        radapter = new UserAdapter(requireContext(), users);
        radapter.setClickListener(this);

        RecyclerView recyclerView = binding.recyclerView;

        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),1));
        recyclerView.setAdapter(radapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(requireContext(), "You clicked " + radapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }
}