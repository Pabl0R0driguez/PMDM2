package com.example.pablorodriguezpruebatema9;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ModeloCompartido extends ViewModel {
    private final MutableLiveData<String> texto = new MutableLiveData<>();

    // Método para actualizar el texto
    public void setTexto(String nuevoTexto) {
        texto.setValue(nuevoTexto);
    }

    // Método para obtener el texto observable
    public LiveData<String> getTexto() {
        return texto;
    }
}
