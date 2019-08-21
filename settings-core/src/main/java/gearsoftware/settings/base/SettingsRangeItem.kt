package gearsoftware.settings.base

/**
 * Range settings item
 */
@Suppress("unused", "MemberVisibilityCanBePrivate")
class SettingsRangeItem(override var name: String, override var description: String?) : ISettingsItem {
    override var isEnabled: Boolean = false

    override var isVisible: Boolean = false

    var min = 0
    var max = 0
    var value = 0

    constructor(name: String, description: String?, max: Int, value: Int) : this(name, description, 0, max, value)

    constructor(name: String, description: String?, min: Int, max: Int, value: Int) : this(name, description) {
        this.min = min
        this.max = max
        this.value = value
        if (value > max || value < min) {
            throw IllegalArgumentException("Value not in range between min and max")
        }

        if (max <= min) {
            throw IllegalArgumentException("Max <= min")
        }
    }

}