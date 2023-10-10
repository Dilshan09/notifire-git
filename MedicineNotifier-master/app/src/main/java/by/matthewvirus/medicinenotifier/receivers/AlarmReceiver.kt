package by.matthewvirus.medicinenotifier.receivers

import android.app.Notification
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import by.matthewvirus.medicinenotifier.R
import by.matthewvirus.medicinenotifier.ui.activities.HomeActivity
import by.matthewvirus.medicinenotifier.util.*

class AlarmReceiver : BroadcastReceiver() {

    private val TAG = AlarmReceiver::class.java.simpleName

    val soundUri = Uri.parse("android.resource://raw/voice")

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onReceive(context: Context?, intent: Intent?) {


        // Create an intent to launch the HomeActivity when the notification is clicked
        val notificationIntent = Intent(context, HomeActivity::class.java)

        // Retrieve data from the intent extras
        val medicineName = intent?.extras?.getString("id")
        val medicineCellNumber = intent?.extras?.getInt("cell")

        // Set flags for the notification intent
        notificationIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

        // Create a pending intent for the notification
        val pendingIntent = PendingIntent.getActivity(context!!,0, notificationIntent, PendingIntent.FLAG_IMMUTABLE)

        // Create the notification based on the medicineCellNumber
        val notification = if (medicineCellNumber == 0) {
            createNotification(context, medicineName, pendingIntent)
        } else {
            createNotificationForContainer(context, medicineName, medicineCellNumber, pendingIntent)
        }

        // Log some debug information
        Log.d(TAG, "Medicine name: $medicineName")
        Log.d(TAG, "Medicine cell number: $medicineCellNumber")

        // Get the notification manager and show the notification
        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(NOTIFICATION_ID, notification)
    }

    //Simple Notification
    private fun createNotification(context: Context?,
                                   name: String?,
                                   intent: PendingIntent?
    ) : Notification {
        return NotificationCompat.Builder(context!!, CHANNEL_ID)
            .setSound(soundUri)
            .setContentTitle(context.getString(R.string.notification_title))
            .setContentText(context.getString(R.string.notification_message) + " " + name)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(intent)
            .build()
    }

    //Notification with additional stuff
    private fun createNotificationForContainer(context: Context,
                                               name: String?,
                                               number: Int?,
                                               intent: PendingIntent?
    ) : Notification {
        return NotificationCompat.Builder(context, CHANNEL_ID)
            .setSound(soundUri)
            .setContentTitle(context.getString(R.string.notification_title))
            .setContentText(context.getString(R.string.notification_message) + " " + name )
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(intent)
            .build()
    }

}