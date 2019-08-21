package gearsoftware.settings.base

class SettingsSeparator(override var name: String) : ISettingsItem {
    override var description: String? = null
    override var isEnabled: Boolean = true
    override var isVisible: Boolean = true
}