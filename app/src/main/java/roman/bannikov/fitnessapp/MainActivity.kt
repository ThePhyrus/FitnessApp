package roman.bannikov.fitnessapp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import roman.bannikov.fitnessapp.fragments.DaysFragment
import roman.bannikov.fitnessapp.utils.FragmentManager


//todo Добавить "сек." или "раз" к отображению названия следующего упражнения
//todo Настроить tvWinner (шрифт и всякое такое)
//todo Повыносить изменение текста ActionBar из onViewCreated() в какую-нибудь функцию?
//FIXME Гифка следующего упражнения должна показываться во время расслабления и на экране подготовки
//todo Найти кучу однообразных gif-картинок и подписать все упражнения
//FIXME Берётся весь размер массива. Как убрать учёт разделителей (запятых)?

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.preferences = getSharedPreferences("main", MODE_PRIVATE)
        FragmentManager.setFragment(DaysFragment.newInstance(), this)
    }

    override fun onBackPressed() {
        if (FragmentManager.currentFrag is DaysFragment) {
            super.onBackPressed()
        } else {
            FragmentManager.setFragment(DaysFragment.newInstance(), this)
        }
    }
}