package gearsoftware.settingsexample

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import gearsoftware.settings.SettingsBaseActivity
import gearsoftware.settings.SettingsBaseActivity.Companion.SETTINGS_ACTIVITY_REQUEST_CODE

class MainActivity : AppCompatActivity() {


    private val settingsCloseReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            when (intent.action) {
                SettingsBaseActivity.SETTINGS_ACTIVITY_FINISHED -> Toast.makeText(
                    context,
                    "Settings closed intent",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startActivityForResult(
            Intent(this, SettingsActivity::class.java),
            SETTINGS_ACTIVITY_REQUEST_CODE
        )
        registerReceiver(
            settingsCloseReceiver,
            IntentFilter(SettingsBaseActivity.SETTINGS_ACTIVITY_FINISHED)
        )
    }


    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(settingsCloseReceiver)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            SETTINGS_ACTIVITY_REQUEST_CODE -> {
                when (resultCode) {
                    Activity.RESULT_OK -> {
                        Toast.makeText(this, "Settings closed", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}
