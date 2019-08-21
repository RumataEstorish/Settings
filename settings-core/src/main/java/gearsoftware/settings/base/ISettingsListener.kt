package gearsoftware.settings.base

/**
 * Settings action listener
 */
interface ISettingsListener {
    fun onItemClick(key: String, item: ISettingsItem)
    fun onItemCheckedChange(key: String, item: SettingsCheckItem, isChecked: Boolean)
}