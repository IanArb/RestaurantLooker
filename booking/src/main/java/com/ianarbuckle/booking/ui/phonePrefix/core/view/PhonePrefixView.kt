package com.ianarbuckle.booking.ui.phonePrefix.core.view

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ianarbuckle.booking.R
import com.ianarbuckle.booking.ui.phonePrefix.core.view.adapter.PhonePrefixAdapter
import com.ianarbuckle.core.extensions.getDrawableFromAttr
import com.ianarbuckle.models.Country
import kotlinx.android.synthetic.main.phone_prefix_view.view.*

/**
 * Created by Ian Arbuckle on 2019-09-03.
 *
 */
interface PhonePrefixView {
    fun getView(): View
    fun showCountries(countries: MutableList<Country>)
    fun toolbarBackClickListener(clickListener: () -> Unit)
    fun countryItemClickListener(clickListener: (Country) -> Unit)
}

class PhonePrefixViewImpl(context: Context) : PhonePrefixView, ConstraintLayout(context) {

    lateinit var adapter: PhonePrefixAdapter

    init {
        inflate(context, R.layout.phone_prefix_view, this)
        toolbar.setNavigationIcon(context.getDrawableFromAttr(R.attr.backArrowBlackDrawable))
    }

    override fun getView(): View = this

    override fun showCountries(countries: MutableList<Country>) {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.hasFixedSize()
        adapter = PhonePrefixAdapter(countries)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        filterInput.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(editable: Editable?) {

            }

            override fun beforeTextChanged(value: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(value: CharSequence, start: Int, before: Int, count: Int) {
                filterInput.setCompoundDrawablesWithIntrinsicBounds(0, 0, context.getDrawableFromAttr(R.attr.clearDrawable), 0)
                adapter.filterCountries(value.toString())
            }
        })
        onClearClicked()
    }

    private fun onClearClicked() {
        filterInput.setOnTouchListener { view, event ->
            if((event.action == MotionEvent.ACTION_UP && view.hasFocus()) &&
                    (event.rawX >= (filterInput.right) - filterInput.compoundDrawables[2].bounds.width())) {

                filterInput.setText("")
                filterInput.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
            }
            false
        }
    }

    override fun countryItemClickListener(clickListener: (Country) -> Unit) {
        adapter.clickListener = clickListener
    }

    override fun toolbarBackClickListener(clickListener: () -> Unit) {
        toolbar.setNavigationOnClickListener {
            clickListener()
        }
    }
}