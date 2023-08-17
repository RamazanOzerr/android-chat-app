package com.example.basicchatapp.Fragments.ChatsFragment.Stories;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class StoriesViewModel extends ViewModel {

    private MutableLiveData<List<StoriesModel>> liveData;

    public StoriesViewModel(){
        StoriesRepository repository = new StoriesRepository();
        liveData = repository.getStories();
    }

    public MutableLiveData<List<StoriesModel>> getLiveData(){
        return liveData;
    }
}
