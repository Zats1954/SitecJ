package ru.zatsoft.sitecj;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ru.zatsoft.adapter.UserAdapter;
import ru.zatsoft.sitecj.databinding.FragmentSecondBinding;

import static ru.zatsoft.sitecj.MainActivity.users;

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

        binding.buttonSecond.setOnClickListener(view1 -> NavHostFragment.findNavController(SecondFragment.this)
                .navigate(R.id.action_SecondFragment_to_FirstFragment));
        radapter = new UserAdapter(requireContext(), users);
        radapter.setClickListener(this);

        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), 1));
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
        Bundle bundle = new Bundle();
        bundle.putString("name", radapter.getItem(position).getName());
        NavHostFragment.findNavController(SecondFragment.this)
                .navigate(R.id.action_SecondFragment_to_loginFragment, bundle);
    }
}