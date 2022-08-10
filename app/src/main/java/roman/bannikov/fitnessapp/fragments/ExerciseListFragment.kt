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
import roman.bannikov.fitnessapp.adapters.ExerciseAdapter
import roman.bannikov.fitnessapp.databinding.FragmentExerciseListBinding
import roman.bannikov.fitnessapp.utils.FragmentManager

class ExerciseListFragment : Fragment() {

    private var _binding: FragmentExerciseListBinding? = null
    private val binding: FragmentExerciseListBinding get() = _binding!!
    private var actionBar: ActionBar? = null
    private lateinit var adapter: ExerciseAdapter
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExerciseListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        actionBar = (activity as AppCompatActivity).supportActionBar?.apply {
            val title = getString(R.string.list_of_exercises)
            this.title = title
        }
        init()
        //FIXME отличие от l-15 6:08 ---------------------------------- ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓
        viewModel.mutableListOfExercises.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun init() = with(binding) {
        adapter = ExerciseAdapter()
        rcViewExercises.layoutManager = LinearLayoutManager(activity)
        rcViewExercises.adapter = adapter
        btnStart.setOnClickListener {
            FragmentManager.setFragment(
                CountdownFragment.newInstance(),
                activity as AppCompatActivity
            )
        }
    }


    companion object {
        @JvmStatic
        fun newInstance() = ExerciseListFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}