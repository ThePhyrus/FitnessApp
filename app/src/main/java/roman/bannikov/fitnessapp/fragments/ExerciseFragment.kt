package roman.bannikov.fitnessapp.fragments

import android.os.Bundle
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
import roman.bannikov.fitnessapp.utils.FragmentManager

class ExerciseFragment : Fragment() {

    private var _binding: FragmentExerciseBinding? = null
    private val binding: FragmentExerciseBinding get() = _binding!!
    private var exerciseCounter: Int = 0
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
        binding.btnNextExercise.setOnClickListener {  }
    }

    private fun nextExercise() {
        if (exerciseCounter < exerciseList?.size!!) {
            val exercise = exerciseList?.get(exerciseCounter++)
            showExercise(exercise)
        } else {
            Toast.makeText(activity, "Done", Toast.LENGTH_LONG).show()
        }
    }

    private fun showExercise(exercise: ExerciseModel?) = with(binding) {
        if (exercise == null) return@with
        ivCurrentExercise.setImageDrawable(GifDrawable(root.context.assets, exercise.exerciseImage))
        tvExerciseName.text = exercise.exerciseName
    }

    companion object {
        @JvmStatic
        fun newInstance() = ExerciseFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}