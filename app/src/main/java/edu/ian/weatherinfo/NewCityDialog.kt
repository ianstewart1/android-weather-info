package edu.ian.weatherinfo

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.new_city_dialog.view.*

class NewCityDialog : DialogFragment() {

    interface CityHandler {
        fun cityAdded(city: String)
    }

    private lateinit var cityHandler: CityHandler

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is CityHandler) {
            cityHandler = context
        } else {
            throw RuntimeException(
                "The activity does not implement the CityHandlerInterface"
            )
        }
    }

    private lateinit var etCityName: EditText

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())

        builder.setTitle("New City")

        val rootView = requireActivity().layoutInflater.inflate(
            R.layout.new_city_dialog, null
        )
        etCityName = rootView.etCityName

        builder.setView(rootView)

        builder.setPositiveButton("OK") {
                _, _ -> // nothing
        }

        return builder.create()
    }

    override fun onResume() {
        super.onResume()

        val positiveButton = (dialog as AlertDialog).getButton(Dialog.BUTTON_POSITIVE)
        positiveButton.setOnClickListener {
            if (etCityName.text.isNotEmpty()) {
                handleCityAdd()
                (dialog as AlertDialog).dismiss()
            } else {
                etCityName.error = "City name field must not be empty"
            }
        }
    }

    private fun handleCityAdd() {
        cityHandler.cityAdded(etCityName.text.toString())
    }
}