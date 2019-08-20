package gearsoftware.settings.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView
import gearsoftware.settings.R
import kotlinx.android.synthetic.main.settings_item_layout.view.*
import java.util.*

/**
 * Settings adapter
 */
class SettingsAdapter(
        private val clickListener: ISettingsListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val NORMAL_VIEW = 1
        private const val CHECK_VIEW = 2
        private const val SEPARATOR_VIEW = 3
    }

    private val items: MutableMap<String, ISettingsItem> = mutableMapOf()

    init {
        setHasStableIds(true)
    }

    fun setItems(it: Map<String, ISettingsItem>) {
        items.clear()
        it.forEach {
            items[it.key] = it.value
        }
        notifyDataSetChanged()
    }

    override fun getItemCount() = items.size


    override fun getItemId(position: Int): Long =
            items.keys.elementAt(position).hashCode().toLong()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            when (viewType) {
                SEPARATOR_VIEW -> SeparatorItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.settings_separator_layout, parent, false))
                else ->
                    SettingsItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.settings_item_layout, parent, false))
            }


    override fun getItemViewType(position: Int): Int =
            when (items.values.elementAt(position)) {
                is SettingsCheckItem -> CHECK_VIEW
                is SettingsSeparator -> SEPARATOR_VIEW
                else -> NORMAL_VIEW
            }


    override fun onBindViewHolder(h: RecyclerView.ViewHolder, position: Int) {

        if (h is SeparatorItemViewHolder) {
            h.bind(items.values.elementAt(position))
            return
        }

        val holder = h as SettingsItemViewHolder
        val item = items.values.elementAt(position)

        holder.setValue(item.name, item.description)
        holder.isEnabled = item.isEnabled

        if (item is SettingsCheckItem) {
            holder.isChecked = item.isChecked
            holder.setOnCheckListener(CompoundButton.OnCheckedChangeListener { _, b ->
                item.isChecked = b
                clickListener.onItemCheckedChange(items.keys.elementAt(position), item, b)
            })
        } else {
            holder.setOnClickListener(View.OnClickListener {
                clickListener.onItemClick(items.keys.elementAt(position), item)
            })
        }

        (item as Observable).addObserver(holder)
        holder.setTextColor(R.color.settings_value_color)
    }

    inner class SeparatorItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name = itemView.settings_item_name

        fun bind(settingsItem: ISettingsItem) {
            name.text = settingsItem.name
        }
    }
}