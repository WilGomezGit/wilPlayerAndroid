package com.wilplayer.android.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

/**
 * Receives BOOT_COMPLETED so the app can react after device reboot.
 * Currently no-op — extend here to restore the last media session,
 * reschedule notifications, or trigger WorkManager tasks if needed.
 */
class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            // Reserved for future use: restore media session, refresh notifications, etc.
        }
    }
}
