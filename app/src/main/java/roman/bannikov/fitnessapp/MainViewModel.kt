package roman.bannikov.fitnessapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import roman.bannikov.fitnessapp.adapters.ExerciseModel

class MainViewModel : ViewModel() {
    val mutableListOfExercises = MutableLiveData<ArrayList<ExerciseModel>>()
}