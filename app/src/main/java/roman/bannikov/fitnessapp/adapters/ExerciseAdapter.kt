package roman.bannikov.fitnessapp.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import pl.droidsonroids.gif.GifDrawable
import roman.bannikov.fitnessapp.R
import roman.bannikov.fitnessapp.databinding.ExerciseListItemBinding


class ExerciseAdapter() :
    ListAdapter<ExerciseModel, ExerciseAdapter.ExerciseHolder>(Comparator()) {

    class ExerciseHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ExerciseListItemBinding.bind(view)//FIXME не будет ли утечки?
        fun setData(exercise: ExerciseModel) = with(binding) {
            tvExerciseName.text = exercise.exerciseName
            tvRepeatQuantity.text = exercise.exerciseTime
            ivExercise.setImageDrawable(
                GifDrawable(
                    root.context.assets,
                    exercise.exerciseImage
                )
            ) //FIXME при вложенности в папке assets так не работает (как прописать путь ко вложенной папке?)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.exercise_list_item, parent, false)
        return ExerciseHolder(view)
    }

//TODO сделай тут гадость, чтобы понять, как эта хрень работает

    override fun onBindViewHolder(holder: ExerciseHolder, position: Int) {
        holder.setData(getItem(position))
    }

    class Comparator : DiffUtil.ItemCallback<ExerciseModel>() {
        override fun areItemsTheSame(oldItem: ExerciseModel, newItem: ExerciseModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ExerciseModel, newItem: ExerciseModel): Boolean {
            return oldItem == newItem
        }

    }

}
