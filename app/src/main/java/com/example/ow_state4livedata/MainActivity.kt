package com.example.ow_state4livedata

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.example.ow_state4livedata.ui.theme.OWState4LiveDataTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OWState4LiveDataTheme(){
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}


/* PASO1: creamos un TF y dos capas superiores
En el TF usaremos State Hoisting, de modo que como parametros de la funcion del TF
le meteremos el value (name:String) y onValueChange(onTextFieldChange:(String) -> Unit)
Ya tenemos listo el componente hijo. Vamos al padre.

PASO2: lo primero será crear el estado (val nameState)que lo incializamos a vacío
Ahora vamos a MainLayout() y en los paréntesis comenzamos a meter lo que parametrizamos en el propio elemento (capa inferior)
name será nameState.value, y entre las llaves meteremos el valor de onValueChange

Tras hacer lo de la variable observeAsLiveData
tendremos que actualizar la lambda definida en MainScreen (newName-> nameState.value = newName)
a
 */


@Composable
fun MainScreen(//en un principio vacio, despues le paso el VM
    viewModel: MainViewModel= MainViewModel()) {
    //val nameState= remember { mutableStateOf("") } anteriormente asi
    val nameState = viewModel.textFieldState.observeAsState("")
    //implementar esto en el gradle del modulo implementation "androidx.compose.runtime:runtime-livedata:1.3.3"
    MainLayout( // Al prncipio solo habia esto sin parametros.
        name = nameState.value,
    ){ //aquí meteremos la funcionalidad del onValueChange(onTFChange)
        // newName-> nameState.value = newName
        //NewName es una variable que me invento para definir como le llamare a la modificacion
        //Antes de ObserveAsLiveData así. Ahora:
            newName-> viewModel.onTextField(newName) //lamamos al metodo para que actualice el estado



    }

}


@Composable
fun MainLayout(
    name: String,
    onTextFieldChange:(String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        TextField(value = name,
            onValueChange = onTextFieldChange)
        Text(text = name) //el texto que expulsará en tiempo real lo que varie en el TF
    }
}