package gearsoftware.settings.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import gearsoftware.settings.R

/**
 * Settings list abstract fragment
 */
abstract class SettingsListFragment : androidx.fragment.app.Fragment() {

    protected lateinit var adapter: SettingsAdapter
    private lateinit var recyclerView: androidx.recyclerview.widget.RecyclerView

    fun createView(inflater: LayoutInflater, container: ViewGroup?): View {
        val view = inflater.inflate(R.layout.settings_main_layout, container, false)
        recyclerView = view.findViewById(R.id.settings_list)
        return view
    }

    fun fillItems(view: View, settings: Map<String, ISettingsItem>, settingsListener: ISettingsListener) {
        adapter = SettingsAdapter(settingsListener)
        adapter.setItems(settings)
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(view.context)
        recyclerView.addItemDecoration(MarginDividerDecoration(view.context, androidx.recyclerview.widget.LinearLayoutManager.VERTICAL, 0))
        recyclerView.adapter = adapter
    }

}