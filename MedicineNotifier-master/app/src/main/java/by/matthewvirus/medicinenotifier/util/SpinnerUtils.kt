package by.matthewvirus.medicinenotifier.util

import android.content.res.Resources
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import by.matthewvirus.medicinenotifier.R

class SpinnerUtils {

    companion object {

        // Variables to store delay time and user times per day choice
        var delayTimeInMillis = 0L
        var userTimesPerDayChoice = ""
        var userTimesPerDayChoiceInt = 0

        // Function to select an item from the spinner
        fun selectSpinnerItem(spinner: Spinner,
                              resources: Resources,
        ) : TimesPerDayInputForm {
            spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {

                override fun onNothingSelected(p0: AdapterView<*>?) { }

                override fun onItemSelected(parent: AdapterView<*>?,
                                            view: View?,
                                            position: Int,
                                            id: Long) {
                    // Set delay time based on the selected item
                    when(id) {
                        0L -> delayTimeInMillis = 24 * HOUR_IN_MILLIS
                        1L -> delayTimeInMillis = 6 * HOUR_IN_MILLIS
                        2L -> delayTimeInMillis = 4 * HOUR_IN_MILLIS
                        3L -> delayTimeInMillis = 3 * HOUR_IN_MILLIS
                    }

                    // Store the user's choice
                    val choice = resources.getStringArray(R.array.times_per_day)
                    userTimesPerDayChoice = choice[position]
                    userTimesPerDayChoiceInt = position
                }
            }

            // Return a TimesPerDayInputForm object with the selected values
            return TimesPerDayInputForm(
                delayTimeInMillis,
                userTimesPerDayChoice,
                userTimesPerDayChoiceInt
            )
        }
    }
}

// Data class to hold user's times per day choice and delay time
data class TimesPerDayInputForm (
    var delayTimeInMillis: Long = 0L,
    var userTimesPerDayChoice: String = "",
    var userTimesPerDayChoiceInt: Int = 0
)