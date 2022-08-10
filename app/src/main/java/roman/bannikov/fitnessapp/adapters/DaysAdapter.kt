package roman.bannikov.fitnessapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import roman.bannikov.fitnessapp.R
import roman.bannikov.fitnessapp.databinding.DaysListItemBinding
import roman.bannikov.fitnessapp.listener.Listener

class DaysAdapter(var listener: Listener) : ListAdapter<DayModel, DaysAdapter.DayHolder>(Comparator()), Listener {

    class DayHolder(xmlDaysListItem: View) : RecyclerView.ViewHolder(xmlDaysListItem) {
        private val binding = DaysListItemBinding.bind(xmlDaysListItem)

        fun setData(day: DayModel, listener: Listener) = with(binding) {
            val dayNumber = root.context.getString(R.string.day) + " ${adapterPosition + 1}"
            tvDayNumber.text = dayNumber
            val exCounter = day.exercises.split(",").size.toString()//FIXME берётся весь размер массива(( Как убрать учёт разделителей (запятых)?
            tvExerciseCounter.text = exCounter
            itemView.setOnClickListener {
                listener.onClick(day.copy(dayNumber = adapterPosition + 1))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayHolder {
        val xmlView = LayoutInflater.from(parent.context)
            .inflate(R.layout.days_list_item, parent, false)
        return DayHolder(xmlView)
    }

    //TODO сделай тут гадость, чтобы понять, как эта хрень работает

    override fun onBindViewHolder(holder: DayHolder, position: Int) {
        holder.setData(getItem(position), listener)
    }

    class Comparator : DiffUtil.ItemCallback<DayModel>() {
        override fun areItemsTheSame(oldItem: DayModel, newItem: DayModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: DayModel, newItem: DayModel): Boolean {
            return oldItem == newItem
        }

    }

    override fun onClick(day: DayModel) {

    }

}