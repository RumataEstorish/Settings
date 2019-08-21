package gearsoftware.settings

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

/**
 * Settings activity
 */
abstract class SettingsBaseActivity<T : SettingsBaseFragment> : AppCompatActivity() {

    protected abstract fun getFragment(): T

    companion object {
        const val SETTINGS_ACTIVITY_REQUEST_CODE = 11000
        const val SETTINGS_ACTIVITY_FINISHED = "gearsoftware.settings.SETTINGS_ACTIVITY_FINISHED"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (savedInstanceState == null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.settings_frame, getFragment(), SettingsBaseFragment.ID)
            transaction.commit()
        }
    }

    override fun onBackPressed() {
        sendBroadcast(Intent(SETTINGS_ACTIVITY_FINISHED))
        setResult(Activity.RESULT_OK)
        super.onBackPressed()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}