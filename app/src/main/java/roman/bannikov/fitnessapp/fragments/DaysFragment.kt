package roman.bannikov.fitnessapp.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
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
import roman.bannikov.fitnessapp.utils.DialogManager
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
    private lateinit var adapter: DaysAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

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
            val title = getString(R.string.train_every_day)
            this.title = title
        }
        initRcView()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        return inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.miReset) {

            DialogManager.showDialog(activity as AppCompatActivity,
                R.string.reset_all_days_message, object : DialogManager.Listener {
                    override fun onClick() {
                        viewModel.preferences?.edit()?.clear()?.apply()
                        adapter.submitList(fillDaysArray())
                    }

                })
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initRcView() = with(binding) {
        adapter = DaysAdapter(this@DaysFragment)
        rcViewDays.layoutManager = LinearLayoutManager(activity as AppCompatActivity)
        rcViewDays.adapter = adapter
        adapter.submitList(fillDaysArray())
    }

    private fun fillDaysArray(): ArrayList<DayModel> {
        val tempArray = ArrayList<DayModel>()
        var daysLDoneCounter = 0
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
        binding.progressBar.max = tempArray.size
        tempArray.forEach {
            if (it.isDone) {
                daysLDoneCounter++
            }
        }
        updateTextViewDaysCounter(tempArray.size - daysLDoneCounter, tempArray.size)
        return tempArray
    }

    private fun updateTextViewDaysCounter(daysLeft: Int, daysQuantity: Int) = with(binding) {
        //FIXME если локализировать на Россию, логика будет сложнее (day/days и день/дня/дней)
        if (daysLeft == 1 || daysLeft == 21 || daysLeft == 31
            || daysLeft == 41 || daysLeft == 51 || daysLeft == 61
        ) {
            tvDaysLeftCounter.text = daysLeft.toString()
            tvDaysLeft.text = getString(R.string.day_left)
            progressBar.progress = daysQuantity - daysLeft
        } else {
            tvDaysLeftCounter.text = daysLeft.toString()
            tvDaysLeft.text = getString(R.string.days_left)
            progressBar.progress = daysQuantity - daysLeft
        }

    }

    private fun fillExerciseList(day: DayModel) {
        val tempList = ArrayList<ExerciseModel>()
        day.exercises.split(",").forEach {
            val exerciseLIst = resources.getStringArray(R.array.exercises)
            val exercise = exerciseLIst[it.toInt()]
            val exerciseArray = exercise.split("|")
            tempList.add(
                ExerciseModel(
                    exerciseArray[0],
                    exerciseArray[1],
                    exerciseArray[2],
                    false
                )
            )
        }
        viewModel.mutableListOfExercises.value = tempList

    }

    override fun onClick(day: DayModel) {
        if (!day.isDone) {
            viewModel.currentDay = day.dayNumber
            fillExerciseList(day)
            FragmentManager.setFragment(
                ExerciseListFragment.newInstance(),
                activity as AppCompatActivity
            )
        } else {
            DialogManager.showDialog(activity as AppCompatActivity,
                R.string.reset_day_message, object : DialogManager.Listener {
                    override fun onClick() {
                        viewModel.savePreferences(day.dayNumber.toString(), 0)
                        fillExerciseList(day)
                        FragmentManager.setFragment(
                            ExerciseListFragment.newInstance(),
                            activity as AppCompatActivity
                        )
                    }
                }
            )
        }
    }

    override fun onDetach() {
        super.onDetach()

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}