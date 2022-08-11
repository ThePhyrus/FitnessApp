package roman.bannikov.fitnessapp.utils

import android.app.AlertDialog
import android.content.Context
import roman.bannikov.fitnessapp.R

object DialogManager {
    fun showDialog(conte: Context, messageId: Int, listener: Listener) {
        val builder = AlertDialog.Builder(conte)
        var dialog: AlertDialog? = null
        builder.setTitle(R.string.alert)
        builder.setMessage(messageId)
        builder.setPositiveButton(R.string.reset) { _, _ ->
            listener.onClick()
            dialog?.dismiss()
        }
        builder.setNegativeButton(R.string.back) { _, _ ->
            dialog?.dismiss()
        }
        dialog = builder.create()
        dialog.show()
    }

    interface Listener {
        fun onClick()
    }

}