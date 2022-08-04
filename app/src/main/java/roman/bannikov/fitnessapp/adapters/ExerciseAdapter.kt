package roman.bannikov.fitnessapp.adapters
/*

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import roman.bannikov.fitnessapp.R
import roman.bannikov.fitnessapp.databinding.FragmentExerciseListBinding
import roman.bannikov.fitnessapp.listener.Listener

class ExerciseAdapter(var listener: Listener) : ListAdapter<ExerciseModel, ExerciseAdapter.ExerciseHolder>(Comparator()){

    class ExerciseHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = FragmentExerciseListBinding.bind(view)//FIXME не будет ли утечки?
*/
/*        fun setData(exercise: ExerciseModel) = with(binding) {
            tvDayNumber.text = dayNumber
            val exCounter = exercise.exercises.split(",").size.toString()//FIXME берётся весь размер массива(( Как убрать учёт разделителей (запятых)?
            tvExerciseCounter.text = exCounter
            itemView.setOnClickListener {
                listener.onClick(exercise)
            }
      *//*
  }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseHolder {
        val xmlView = LayoutInflater.from(parent.context)
            .inflate(R.layout.days_list_item, parent, false)
        return ExerciseHolder(xmlView)
    }

    //TODO сделай тут гадость, чтобы понять, как эта хрень работает

    override fun onBindViewHolder(holder: ExerciseHolder, position: Int) {
        holder.setData(getItem(position))
    }

    class Comparator : DiffUtil.ItemCallback<DayModel>() {
        override fun areItemsTheSame(oldItem: DayModel, newItem: DayModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: DayModel, newItem: DayModel): Boolean {
            return oldItem == newItem
        }

    }

}*/
