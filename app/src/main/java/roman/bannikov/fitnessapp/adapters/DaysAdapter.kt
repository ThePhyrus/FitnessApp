package roman.bannikov.fitnessapp.adapters

import android.view.View
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import roman.bannikov.fitnessapp.R
import roman.bannikov.fitnessapp.databinding.DaysListItemBinding

class DaysAdapter : ListAdapter<DayModel, DaysAdapter.DayHolder> {

    class DayHolder(xmlDaysListItem: View) : RecyclerView.ViewHolder(xmlDaysListItem) {
        private val binding = DaysListItemBinding.bind(xmlDaysListItem)//FIXME не будет ли утечки?
        fun setData(day: DayModel) = with(binding) {
            val dayNumber = root.context.getString(R.string.day) + " ${adapterPosition + 1}"
            tvDayNumber.text = dayNumber
            val exCounter = day.exercises.split(",").size.toString()
            tvExerciseCounter.text = exCounter
        }
    }

}