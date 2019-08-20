package gearsoftware.settingsexample

import gearsoftware.settings.SettingsCustomActivity

class SettingsActivity : SettingsCustomActivity<SettingsFragment>() {
    override fun getFragment(): SettingsFragment =
        SettingsFragment()
}