package roman.bannikov.fitnessapp.listener

import roman.bannikov.fitnessapp.adapters.DayModel

interface Listener {
   fun onClick(day: DayModel)
}