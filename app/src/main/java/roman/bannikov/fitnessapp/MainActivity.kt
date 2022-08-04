package roman.bannikov.fitnessapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import roman.bannikov.fitnessapp.fragments.DaysFragment
import roman.bannikov.fitnessapp.utils.FragmentManager


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FragmentManager.setFragment(DaysFragment.newInstance(), this)
    }

    override fun onBackPressed() {
        if (FragmentManager.currentFrag is DaysFragment) {
            super.onBackPressed()
        } else {
            FragmentManager.setFragment(DaysFragment.newInstance(), this)
        }
    }
}