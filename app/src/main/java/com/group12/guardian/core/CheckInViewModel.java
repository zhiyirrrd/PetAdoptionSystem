package com.group12.guardian.core;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class CheckInViewModel extends AndroidViewModel {
    private final CheckInManager manager;
    private final MutableLiveData<CheckInState> state = new MutableLiveData<>();

    public CheckInViewModel(Application application) {
        super(application);
        manager = new CheckInManager(application);
        manager.restoreOrComputeState();
        state.setValue(manager.getCurrentState());
    }

    public LiveData<CheckInState> getState() {
        return state;
    }

    public void onConfirmOk() {
        manager.confirmOk();
        state.setValue(manager.getCurrentState());
    }

    public void setInterval(int hours) {
        manager.setInterval(hours);
    }

    public void refreshState() {
        manager.restoreOrComputeState();
        state.setValue(manager.getCurrentState());
    }
}
