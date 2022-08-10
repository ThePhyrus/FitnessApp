package roman.bannikov.fitnessapp.fragments

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import roman.bannikov.fitnessapp.databinding.FragmentCountdownBinding
import roman.bannikov.fitnessapp.utils.COUNTDOWN_INTERVAL
import roman.bannikov.fitnessapp.utils.COUNTDOWN_TIME
import roman.bannikov.fitnessapp.utils.FragmentManager
import roman.bannikov.fitnessapp.utils.TimeUtils

class CountdownFragment : Fragment() {

    private var _binding: FragmentCountdownBinding? = null
    private val binding: FragmentCountdownBinding get() = _binding!!
    private lateinit var countdownTimer: CountDownTimer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCountdownBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pBarCountdown.max = COUNTDOWN_TIME.toInt()
        startCountdownTimer()
    }

    private fun startCountdownTimer() = with(binding) {
        countdownTimer = object : CountDownTimer(COUNTDOWN_TIME, COUNTDOWN_INTERVAL) {
            override fun onTick(millisUntilFinished: Long) {
                tvCountdownTimer.text = TimeUtils.getTime(millisUntilFinished)
                pBarCountdown.progress = millisUntilFinished.toInt()
            }

            override fun onFinish() {
                FragmentManager.setFragment(
                    ExerciseFragment.newInstance(),
                    activity as AppCompatActivity
                )
            }
        }.start()
    }


    companion object {
        @JvmStatic
        fun newInstance() = CountdownFragment()
    }

    override fun onDetach() {
        super.onDetach()
        countdownTimer.cancel()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}