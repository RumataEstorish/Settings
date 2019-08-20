package gearsoftware.settings

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import gearsoftware.settings.base.*
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.alert_dialog_input.view.*

/**
 * Main settings fragment
 */
abstract class SettingsMainFragment : SettingsListFragment() {

    companion object {
        const val ID = "MAIN_SETTINGS_FRAGMENT"
        const val SETTINGS_EXTRA = "SETTINGS_EXTRA"
        const val ACTION_SETTINGS_CHANGED = "gearsoftware.SETTINGS_CHANGED"
    }

    protected val compositeDisposable: CompositeDisposable = CompositeDisposable()

    abstract fun getSettings(): Map<String, ISettingsItem>

    abstract fun onItemClick(key: String, item: ISettingsItem)

    abstract fun onItemCheckedChange(key: String, item: SettingsCheckItem, isChecked: Boolean)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = createView(inflater, container)

        fillItems(view, getSettings(), object : ISettingsListener {
            override fun onItemClick(key: String, item: ISettingsItem) {
                this@SettingsMainFragment.onItemClick(key, item)
            }

            override fun onItemCheckedChange(key: String, item: SettingsCheckItem, isChecked: Boolean) {
                this@SettingsMainFragment.onItemCheckedChange(key, item, isChecked)
            }
        })

        return view
    }


    protected fun showPickOptions(
        context: Context,
        title: String,
        values: Array<String>,
        checkedPosition: Int,
        darkTheme: Boolean = false,
        ok: (Int) -> Unit
    ) {
        val ad = AlertDialog.Builder(
            context, if (darkTheme) {
                //R.style.AlertDialogOneUiStyle_Dark
                R.style.AlertDialogOneUiStyle_Lite
            } else {
                R.style.AlertDialogOneUiStyle_Lite
            }
        )
            .setSingleChoiceItems(
                values, checkedPosition
            ) { dialog, which ->
                dialog.dismiss()
                ok(which)
                adapter.setItems(getSettings())
            }
        ad.setTitle(title)

        val dlg = ad.create()
        dlg.window?.setGravity(Gravity.BOTTOM)
        dlg.show()
    }


    @SuppressLint("InflateParams")
    protected fun showEditText(
        context: Context,
        title: String,
        message: String,
        darkTheme: Boolean = false,
        ok: (String) -> Unit
    ) {
        val ad = AlertDialog.Builder(
            context, if (darkTheme) {
                //R.style.AlertDialogOneUiStyle_Dark
                R.style.AlertDialogOneUiStyle_Lite
            } else {
                R.style.AlertDialogOneUiStyle_Lite
            }
        )

        val view = LayoutInflater.from(context).inflate(
            if (darkTheme) {
                //R.layout.alert_dialog_input_dark
                R.layout.alert_dialog_input
            } else {
                R.layout.alert_dialog_input
            }, null
        )

        val input = view.input
        input.setText(message)
        input.setSelection(message.length)


        ad.setView(view)
        ad.setTitle(title)
        ad.setPositiveButton(android.R.string.ok) { _, _ ->
            ok(input.text.toString())
            adapter.setItems(getSettings())
        }
        ad.setNegativeButton(android.R.string.cancel) { _, _ -> }
        val dlg = ad.create()
        dlg.window?.setGravity(Gravity.BOTTOM)
        dlg.show()
    }

    override fun onPause() {
        super.onPause()
        compositeDisposable.clear()
    }
}