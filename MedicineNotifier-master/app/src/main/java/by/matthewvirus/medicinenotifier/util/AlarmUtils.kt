package by.matthewvirus.medicinenotifier.util

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.os.Build
import android.widget.Spinner
import androidx.annotation.RequiresApi
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import by.matthewvirus.medicinenotifier.data.model.Medicine
import by.matthewvirus.medicinenotifier.receivers.AlarmReceiver
import by.matthewvirus.medicinenotifier.ui.aboutMedicine.AboutMedicineFragment

class AlarmUtils {

    companion object {

        private lateinit var alarmManager: AlarmManager
        private lateinit var pendingIntent: PendingIntent

        // Start an alarm to trigger a notification
        @RequiresApi(Build.VERSION_CODES.S)
        fun startAlarm(context: Context?,
                       medicine: Medicine,
                       notificationCode: Int,
                       spinner: Spinner,
                       resources: Resources
        ) {
            val intent = Intent(context, AlarmReceiver::class.java)
            val medicineName = medicine.medicineName
            intent.putExtra("id", medicineName)

            // Include additional data for medicines stored in containers
            if (medicine.isStoredInContainer) {
                intent.putExtra("cell", medicine.cellNumber)
            }

            // Set an inexact repeating alarm based on medicine settings
            alarmManager = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            pendingIntent = PendingIntent.getBroadcast(context, notificationCode, intent, PendingIntent.FLAG_IMMUTABLE)
            alarmManager.setInexactRepeating(
                AlarmManager.RTC_WAKEUP,
                medicine.medicineTakingFirstTime.time,
                SpinnerUtils.selectSpinnerItem(
                    spinner,
                    resources
                ).delayTimeInMillis,
                pendingIntent
            )
        }

        // Cancel a pending intent for an alarm
        fun cancelPendingIntent(context: Context?, index: Int?) {
            val intent = Intent(context, AboutMedicineFragment::class.java)
            alarmManager = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            pendingIntent = PendingIntent.getService(context, index!!, intent, PendingIntent.FLAG_IMMUTABLE)
            pendingIntent.cancel()
        }

        // Create a unique notification code based on the number of medicines
        fun createNotificationCode(medicinesLiveData: LiveData<List<Medicine>>,
                                   lifecycleOwner: LifecycleOwner) : Int {
            var notificationCode = 0
            medicinesLiveData.observe(
                lifecycleOwner
            ) { medicines ->
                medicines.let {
                    notificationCode = medicines.size
                }
            }
            return notificationCode
        }
    }
}