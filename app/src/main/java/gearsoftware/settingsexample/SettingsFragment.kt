package gearsoftware.settingsexample

import android.widget.Toast
import gearsoftware.settings.SettingsBaseFragment
import gearsoftware.settings.base.*

class SettingsFragment : SettingsBaseFragment() {

    companion object {
        const val TEXT_ITEM = "TEXT_ITEM"
        const val SEPARATOR = "SEPARATOR"
        const val CHECK_ITEM = "CHECK_ITEM"
        const val EDIT_TEXT = "EDIT_TEXT"
        const val PICK_OPTION = "PICK_OPTION"
        const val MULTI_PICK_OPTION = "MULTI_PICK_OPTION"
    }

    private val pickMultiOptionsArray = arrayOf("Four", "Five", "Six")
    private val pickOptionsArray = arrayOf("One", "Two", "Tree")
    private var activeOption = 0
    private var activeOptions: BooleanArray = booleanArrayOf(false, true, false)
    private var editItem = "This value"

    override fun getSettings(): Map<String, ISettingsItem> =
        mapOf(
            TEXT_ITEM to SettingsItem("Name", "Description"),
            SEPARATOR to SettingsSeparator("Separator"),
            CHECK_ITEM to SettingsCheckItem("Check", "Check item", false),
            EDIT_TEXT to SettingsItem("Edit text", editItem),
            PICK_OPTION to SettingsItem("Pick options", pickOptionsArray[activeOption]),
            MULTI_PICK_OPTION to SettingsItem("Multi pick option", pickMultiOptionsArray
                .filterIndexed { index, _ -> activeOptions[index] }
                .joinToString())
        )

    override fun onItemClick(key: String, item: ISettingsItem) {
        when (key) {
            TEXT_ITEM -> Toast.makeText(context, item.name, Toast.LENGTH_SHORT).show()
            EDIT_TEXT -> super.showEditText(context!!, item.name, editItem, false) { s ->
                editItem = s
            }
            PICK_OPTION -> super.showPickOptions(
                context!!,
                "Pick options",
                pickOptionsArray,
                activeOption,
                false
            ) { i ->
                activeOption = i
            }
            MULTI_PICK_OPTION -> showMultiPickOptions(
                context!!,
                "Multi pick options",
                pickMultiOptionsArray,
                activeOptions,
                false
            ) { a ->
                activeOptions = a
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