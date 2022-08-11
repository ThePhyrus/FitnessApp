package roman.bannikov.fitnessapp.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import roman.bannikov.fitnessapp.R

object FragmentManager { //todo сократить?

    var currentFrag:Fragment? = null

    fun setFragment(newFragment:Fragment, conte: AppCompatActivity){
        val transaction = conte.supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.slide_out_right)
        transaction.replace(R.id.fragmentHolder, newFragment)
        transaction.commit()
        currentFrag = newFragment
    }
}