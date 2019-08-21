package gearsoftware.settings.base

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.CompoundButton
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.SwitchCompat
import gearsoftware.settings.R
import java.util.*

/**
 * Base settings viewManager holder
 */
@Suppress("unused", "MemberVisibilityCanBePrivate")
open class SettingsItemViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView), Observer {

    private val itemName: AppCompatTextView = itemView.findViewById(R.id.settings_item_name)
    private val itemDescription: AppCompatTextView = itemView.findViewById(R.id.settings_item_description)
    private val itemLayout: RelativeLayout = itemView.findViewById(R.id.settings_item_parent)
    private val itemImage: AppCompatImageView = itemView.findViewById(R.id.settings_item_image)
    private val itemCheck: SwitchCompat = itemView.findViewById(R.id.settings_item_checkbox)

    init {
        itemImage.visibility = View.GONE
        itemCheck.visibility = View.GONE
    }

    override fun update(item: Observable?, arg: Any?) {
        if (item is SettingsCheckItem) {
            isChecked = item.isChecked
        }
        if (item is SettingsItem) {
            isEnabled = item.isEnabled
            isVisible = item.isVisible
            setValue(item.name, item.description)
        }
    }


    var isChecked: Boolean
        get() = itemCheck.isChecked
        set(value) {
            itemCheck.isChecked = value
        }

    var isEnabled: Boolean
        get() = itemCheck.isEnabled
        set(value) {
            itemCheck.isEnabled = value
            itemLayout.isEnabled = value
        }

    var isVisible: Boolean
        get() = itemLayout.visibility == View.VISIBLE
        set(value) {
            if (value) {
                itemLayout.visibility = View.VISIBLE
            } else {
                itemLayout.visibility = View.GONE
            }
        }

    fun setImage(drawable: Drawable) {
        itemImage.setImageDrawable(drawable)
        itemImage.visibility = View.VISIBLE
    }

    fun setOnClickListener(listener: View.OnClickListener) {
        itemLayout.setOnClickListener(listener)
    }

    fun setTextColor(color: Int) = itemDescription.setTextColor(color)

    fun setOnCheckListener(checkedChangeListener: CompoundButton.OnCheckedChangeListener) {
        setOnClickListener(View.OnClickListener {
            itemCheck.isChecked = !itemCheck.isChecked
        })

        itemCheck.setOnCheckedChangeListener(checkedChangeListener)
        itemCheck.visibility = View.VISIBLE
    }

    private fun setCenterInParent(center: Boolean) {
        val layoutParams: RelativeLayout.LayoutParams = this.itemName.layoutParams as RelativeLayout.LayoutParams
        if (center) {
            layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE)
        } else {
            layoutParams.removeRule(RelativeLayout.CENTER_IN_PARENT)
        }
        this.itemName.layoutParams = layoutParams
    }


    fun setValue(name: String, description: String?) {
        this.itemName.text = name
        if (description == null || description.isEmpty()) {
            this.itemDescription.visibility = View.INVISIBLE
            setCenterInParent(true)
        } else {
            this.itemDescription.visibility = View.VISIBLE
            setCenterInParent(false)
        }
        this.itemDescription.text = description
    }
}