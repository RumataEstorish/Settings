package gearsoftware.settings.base

/**
 * General settings interface
 */
interface ISettingsItem {
    var name: String
    var description: String?
    var isEnabled: Boolean
    var isVisible: Boolean
}