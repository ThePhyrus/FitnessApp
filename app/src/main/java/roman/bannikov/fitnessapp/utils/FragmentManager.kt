package roman.bannikov.fitnessapp.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import roman.bannikov.fitnessapp.R

object FragmentManager { //todo сократить?
    fun setFragment(newFragment:Fragment, conte: AppCompatActivity){
        val transaction = conte.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentHolder, newFragment)
        transaction.commit()
    }
}