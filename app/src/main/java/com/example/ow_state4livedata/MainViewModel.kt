package com.example.ow_state4livedata
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/*
lo primero será definir un estado de tipo MutLivedata(textFielState)
creamos un metodo publico tambien para que cuando el TF cambie avise y modifique el estado


 */

class MainViewModel: ViewModel() {
    val textFieldState = MutableLiveData("")


    fun onTextField(newText:String){
        textFieldState.value = newText
        //actualiza el estado
    }

}