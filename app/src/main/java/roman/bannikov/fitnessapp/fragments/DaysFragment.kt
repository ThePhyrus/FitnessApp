package roman.bannikov.fitnessapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import roman.bannikov.fitnessapp.MainViewModel
import roman.bannikov.fitnessapp.R
import roman.bannikov.fitnessapp.adapters.DayModel
import roman.bannikov.fitnessapp.adapters.DaysAdapter
import roman.bannikov.fitnessapp.adapters.ExerciseModel
import roman.bannikov.fitnessapp.databinding.FragmentDaysBinding
import roman.bannikov.fitnessapp.listener.Listener
import roman.bannikov.fitnessapp.utils.FragmentManager

class DaysFragment : Fragment(), Listener {

    companion object {
        @JvmStatic
        fun newInstance() = DaysFragment()
    }

    private var _binding: FragmentDaysBinding? = null
    private val binding: FragmentDaysBinding get() = _binding!!
    private var actionBar: ActionBar? = null
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDaysBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.currentDay = 0
        actionBar = (activity as AppCompatActivity).supportActionBar?.apply {
            val title = getString(R.string.train_evry_day)
            this.title = title
        }
        initRcView()
    }

    private fun initRcView() = with(binding) {
        val adapter = DaysAdapter(this@DaysFragment)
        rcViewDays.layoutManager = LinearLayoutManager(activity as AppCompatActivity)
        rcViewDays.adapter = adapter
        adapter.submitList(fillDaysArray())
    }

    private fun fillDaysArray(): ArrayList<DayModel> {
        val tempArray = ArrayList<DayModel>()
        resources.getStringArray(R.array.day_sets_of_exercises).forEach { setOfExercises ->
            viewModel.currentDay++
            val exerciseCounter = setOfExercises.split(",").size
            tempArray.add(
                DayModel(
                    setOfExercises,
                    0,
                    viewModel.getFinishedExerciseCount() == exerciseCounter
                )
            )
        }
        return tempArray
    }

    private fun fillExerciseList(day: DayModel) {
        val tempList = ArrayList<ExerciseModel>()
        day.exercises.split(",").forEach {
            val exerciseLIst = resources.getStringArray(R.array.exercises)
            val exercise = exerciseLIst[it.toInt()]
            val exerciseArray = exercise.split("|")
            tempList.add(ExerciseModel(exerciseArray[0], exerciseArray[1], exerciseArray[2], false))
        }
        viewModel.mutableListOfExercises.value = tempList

    }

    override fun onClick(day: DayModel) {
        viewModel.currentDay = day.dayNumber
        fillExerciseList(day)
        FragmentManager.setFragment(
            ExerciseListFragment.newInstance(),
            activity as AppCompatActivity
        )
    }

    override fun onDetach() {
        super.onDetach()

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}