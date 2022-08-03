package roman.bannikov.fitnessapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import roman.bannikov.fitnessapp.R
import roman.bannikov.fitnessapp.adapters.DayModel
import roman.bannikov.fitnessapp.adapters.DaysAdapter
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRcView()
    }

    private fun initRcView() = with(binding) {
        val adapter = DaysAdapter()
        rcViewDays.layoutManager = LinearLayoutManager(activity as AppCompatActivity)
        rcViewDays.adapter = adapter
        adapter.submitList(fillDaysArray())
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