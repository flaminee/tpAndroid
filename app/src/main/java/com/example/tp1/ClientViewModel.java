package com.example.tp1;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class ClientViewModel extends BaseObservable {

    private Client model ;
    ClientViewModel(){
        this.model = new Client();
    }

    @Bindable
    public String getName () {
        return model.name;
    }
    public void setName ( String name ) {
        this.model.name = name ;
        // BR . name is automatically generated
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getPlace () {
        return model.place;
    }
    public void setPlace ( String place ) {
        this.model.place = place ;
        // BR . name is automatically generated
        notifyPropertyChanged(BR.place);
    }
}