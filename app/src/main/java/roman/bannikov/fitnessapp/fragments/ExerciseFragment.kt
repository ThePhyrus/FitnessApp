package roman.bannikov.fitnessapp.fragments

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import pl.droidsonroids.gif.GifDrawable
import roman.bannikov.fitnessapp.MainViewModel
import roman.bannikov.fitnessapp.adapters.ExerciseAdapter
import roman.bannikov.fitnessapp.adapters.ExerciseModel
import roman.bannikov.fitnessapp.databinding.FragmentExerciseBinding
import roman.bannikov.fitnessapp.databinding.FragmentExerciseListBinding
import roman.bannikov.fitnessapp.utils.COUNTDOWN_INTERVAL
import roman.bannikov.fitnessapp.utils.COUNTDOWN_TIME
import roman.bannikov.fitnessapp.utils.FragmentManager
import roman.bannikov.fitnessapp.utils.TimeUtils

class ExerciseFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = ExerciseFragment()
    }

    private var _binding: FragmentExerciseBinding? = null
    private val binding: FragmentExerciseBinding get() = _binding!!
    private var exerciseCounter: Int = 0
    private var exerciseTimer: CountDownTimer? = null
    private var exerciseList: ArrayList<ExerciseModel>? = null
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExerciseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.mutableListOfExercises.observe(viewLifecycleOwner) {
            exerciseList = it
            nextExercise()
        }
        binding.btnNextExercise.setOnClickListener {
            exerciseTimer?.cancel()
            nextExercise()
        }
    }

    private fun nextExercise() {
        if (exerciseCounter < exerciseList?.size!!) {
            val exercise = exerciseList?.get(exerciseCounter++) ?: return
            specifyExerciseType(exercise)
            showExercise(exercise)
        } else {
            Toast.makeText(activity, "Done", Toast.LENGTH_LONG).show()
        }
    }

    private fun showExercise(exercise: ExerciseModel) = with(binding) {
        ivCurrentExercise.setImageDrawable(GifDrawable(root.context.assets, exercise.exerciseImage))
        tvExerciseName.text = exercise.exerciseName
    }

    private fun specifyExerciseType(exercise: ExerciseModel) {
        if (exercise.exerciseTime.startsWith("x")) {
            binding.tvExerciseTime.text = exercise.exerciseTime
        } else {
            startExerciseTimer(exercise)
        }
    }

    private fun startExerciseTimer(exercise: ExerciseModel) = with(binding) {
        progressBarExercise.max = exercise.exerciseTime.toInt() * 1000
        exerciseTimer?.cancel()
        exerciseTimer =
            object : CountDownTimer(exercise.exerciseTime.toLong() * 1000, COUNTDOWN_INTERVAL) {
                override fun onTick(millisUntilFinished: Long) {
                    tvExerciseTime.text = TimeUtils.getTime(millisUntilFinished)
                    progressBarExercise.progress = millisUntilFinished.toInt()
                }

                override fun onFinish() {
                    nextExercise()
                }
            }.start()
    }

    override fun onDetach() {
        super.onDetach()
        exerciseTimer?.cancel()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}