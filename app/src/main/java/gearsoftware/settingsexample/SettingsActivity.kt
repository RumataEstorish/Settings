package gearsoftware.settingsexample

import gearsoftware.settings.SettingsBaseActivity

class SettingsActivity : SettingsBaseActivity<SettingsFragment>() {
    override fun getFragment(): SettingsFragment =
        SettingsFragment()
}