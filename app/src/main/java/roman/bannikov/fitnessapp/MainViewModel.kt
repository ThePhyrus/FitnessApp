package roman.bannikov.fitnessapp

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import roman.bannikov.fitnessapp.adapters.ExerciseModel

class MainViewModel : ViewModel() {
    val mutableListOfExercises = MutableLiveData<ArrayList<ExerciseModel>>()
    var preferences: SharedPreferences? = null
    var currentDay:Int = 0

    fun savePreferences(key: String, value: Int) {
        preferences?.edit()!!.putInt(key, value)?.apply()
    }

    fun getPreferences(key: String): Int {
        return preferences?.getInt(key, 0) ?: 0
    }
}