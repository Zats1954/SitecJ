package ru.zatsoft.viewmodel;


import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewmodel.ViewModelInitializer;
import ru.zatsoft.repository.MyRepository;

import static androidx.lifecycle.SavedStateHandleSupport.createSavedStateHandle;

public class MyViewModel extends ViewModel {

    private MyViewModel(
            MyRepository myRepository,
            SavedStateHandle savedStateHandle
    ) { /* Init ViewModel here */ }


    public static final ViewModelInitializer<MyViewModel> initializer = new ViewModelInitializer<>(
            MyViewModel.class,
            creationExtras -> {
//                MyApplication app = (MyApplication) creationExtras.get(APPLICATION_KEY);
//                assert app != null;
                SavedStateHandle savedStateHandle = createSavedStateHandle(creationExtras);

                return new MyViewModel(new MyRepository(), savedStateHandle);
            }
    );
}
