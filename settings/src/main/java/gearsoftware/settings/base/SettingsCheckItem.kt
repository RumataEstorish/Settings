package gearsoftware.settings.base

/**
 * Settings item with checkbox
 */
@Suppress("unused")
class SettingsCheckItem() : SettingsItem() {
    var isChecked: Boolean = false
        set(value) {
            if (field != value) {
                field = value
                setChanged()
            }
            notifyObservers()
        }

    constructor(name: String) : this() {
        this.name = name
    }

    constructor(name: String, description: String?) : this(name) {
        this.description = description
    }

    constructor(name: String, description: String, isChecked: Boolean) : this(name) {
        this.description = description
        this.isChecked = isChecked
    }
}