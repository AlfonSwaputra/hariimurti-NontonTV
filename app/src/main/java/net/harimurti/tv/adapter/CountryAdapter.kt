package net.harimurti.tv.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import net.harimurti.tv.BR
import net.harimurti.tv.R
import net.harimurti.tv.databinding.ItemCountryBinding
import net.harimurti.tv.model.ProxySource

interface CountryClickListener {
    fun onCountryClicked(countryCode: String,position: Int)
}
interface OnCountryClickedListener {
    fun onCountryClicked(countryCode: String?)
}

class CountryAdapter(private val country: ArrayList<ProxySource.Country>):
    RecyclerView.Adapter<CountryAdapter.ViewHolder>(), CountryClickListener {
    lateinit var context: Context
    private lateinit var mCallback: OnCountryClickedListener
    private var lastClick = -1

    class ViewHolder(var itemBinding: ItemCountryBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(obj: Any?) {
            itemBinding.setVariable(BR.country, obj)
            itemBinding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val binding: ItemCountryBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context), R.layout.item_country, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val country = country[position]
        viewHolder.bind(country)
        viewHolder.itemBinding.clickListener = this
        viewHolder.itemBinding.position = position
        viewHolder.itemBinding.country.setBackgroundColor(
            ContextCompat.getColor(context,if(lastClick == position) R.color.btn_focused else R.color.btn_default))
    }

    override fun getItemCount(): Int {
        return country.size
    }

    fun clear() {
        val size = itemCount
        country.clear()
        notifyItemRangeRemoved(0, size)
    }

    override fun onCountryClicked(countryCode: String,position: Int) {
        mCallback.onCountryClicked(countryCode)
        notifyItemChanged(lastClick)
        lastClick = position
        notifyItemChanged(position)
    }

    fun setOnShareClickedListener(mCallback: OnCountryClickedListener) {
        this.mCallback = mCallback
    }
}