package gearsoftware.settings.base

import gearsoftware.settings.base.ISettingsItem
import java.util.*

/**
 * Settings item class
 */
open class SettingsItem() : Observable(), ISettingsItem {

    constructor(name: String) : this() {
        this.name = name
    }

    constructor(name: String, description: String?) : this(name) {
        this.description = description
    }

    final override var name: String = ""
        set(value) {
            if (field != value) {
                field = value
                setChanged()
                notifyObservers()
            }
        }


    final override var description: String? = null
        set(value) {
            if (field != value) {
                field = value
                setChanged()
            }
            notifyObservers()
        }

    override var isVisible: Boolean = true
        set(value) {
            if (field != value) {
                field = value
                setChanged()
            }
            notifyObservers()
        }

    override var isEnabled: Boolean = true
        set(value) {
            if (field != value) {
                field = value
                setChanged()
            }
            notifyObservers()
        }


}