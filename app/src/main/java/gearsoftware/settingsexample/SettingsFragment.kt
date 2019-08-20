package gearsoftware.settingsexample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import gearsoftware.settings.SettingsMainFragment
import gearsoftware.settings.base.*

class SettingsFragment : SettingsMainFragment() {

    companion object {
        const val TEXT_ITEM = "TEXT_ITEM"
        const val SEPARATOR = "SEPARATOR"
        const val CHECK_ITEM = "CHECK_ITEM"
        const val EDIT_TEXT = "EDIT_TEXT"
        const val PICK_OPTION = "PICK_OPTION"
    }

    private val pickOptionsArray = arrayOf("One", "Two", "Tree")
    private var activeOption = 0
    private var editItem = "This value"

    override fun getSettings(): Map<String, ISettingsItem> =
        mapOf(
            TEXT_ITEM to SettingsItem("Name", "Description"),
            SEPARATOR to SettingsSeparator("Separator"),
            CHECK_ITEM to SettingsCheckItem("Check", "Check item", false),
            EDIT_TEXT to SettingsItem("Edit text", editItem),
            PICK_OPTION to SettingsItem("Pick options", pickOptionsArray[activeOption])
        )

    override fun onItemClick(key: String, item: ISettingsItem) {
        when (key) {
            TEXT_ITEM -> Toast.makeText(context, item.name, Toast.LENGTH_SHORT).show()
            EDIT_TEXT -> super.showEditText(context!!, item.name, editItem, false) { s -> editItem = s }
            PICK_OPTION -> super.showPickOptions(context!!, "Pick options", arrayOf("One", "Two", "Three"), activeOption, false) { i ->
                activeOption = i
            }
        }
    }

    override fun onItemCheckedChange(key: String, item: SettingsCheckItem, isChecked: Boolean) {
        when (key) {
            CHECK_ITEM -> Toast.makeText(
                context,
                item.name + " checked: " + isChecked.toString(),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

}