package roman.bannikov.fitnessapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import roman.bannikov.fitnessapp.R
import roman.bannikov.fitnessapp.adapters.DayModel
import roman.bannikov.fitnessapp.databinding.FragmentDaysBinding

class DaysFragment : Fragment() {

    private var _binding: FragmentDaysBinding? = null
    private val binding: FragmentDaysBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDaysBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun fillDaysArray(): ArrayList<DayModel> {
        val tempArray = ArrayList<DayModel>()
        resources.getStringArray(R.array.day_sets_of_exercises).forEach { setOfExercises ->
            tempArray.add(DayModel(setOfExercises, false))
        }
        return tempArray
    }

    companion object {
        @JvmStatic
        fun newInstance() = DaysFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}