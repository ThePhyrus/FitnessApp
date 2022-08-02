package roman.bannikov.fitnessapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var countDownTimer: CountDownTimer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        countDownTimer = object : CountDownTimer(SPLASH_TIME, COUNTDOWN_INTERVAL) {
            override fun onTick(millisUntilFinished: Long) {
                //FIXME что с ним можно сделать?
            }

            override fun onFinish() {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            }

        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer.cancel()
    }
}