package roman.bannikov.fitnessapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import pl.droidsonroids.gif.GifDrawable
import roman.bannikov.fitnessapp.databinding.FragmentWinnerBinding
import roman.bannikov.fitnessapp.utils.FragmentManager

class WinnerFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = WinnerFragment()
    }

    private var _binding: FragmentWinnerBinding? = null
    private val binding: FragmentWinnerBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWinnerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivWinnerFragment.setImageDrawable(
            GifDrawable(
                (activity as AppCompatActivity).assets,
                "space_gif_300x300.gif"
            )
        )
        binding.btnIKnowIt.setOnClickListener {
            FragmentManager.setFragment(DaysFragment.newInstance(), activity as AppCompatActivity)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}