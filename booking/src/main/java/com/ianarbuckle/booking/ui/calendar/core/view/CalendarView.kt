package com.ianarbuckle.booking.ui.calendar.core.view

import android.content.Context
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.ianarbuckle.booking.R
import com.ianarbuckle.core.extensions.getDrawableFromAttr
import com.ianarbuckle.core.extensions.parseDate
import com.squareup.timessquare.CalendarPickerView
import kotlinx.android.synthetic.main.bottom_sheet.view.*
import kotlinx.android.synthetic.main.calendar_view.view.*
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import java.util.*

/**
 * Created by Ian Arbuckle on 2019-09-02.
 *
 */
interface CalendarView {
    fun getView(): View
    fun calendarDateClickListener(clickListener: (String?) -> Unit)
    fun toolbarClickListener(clickListener: () -> Unit)
}

class CalendarViewImpl(context: Context) : CalendarView, ConstraintLayout(context) {

    private var dateClickListener: ((String?) -> Unit)? = null

    private val calendarPickerView: CalendarPickerView

    private var selectedDate: Date? = null

    init {
        inflate(context, R.layout.calendar_view, this)
        calendarPickerView = findViewById<View>(R.id.calendar) as CalendarPickerView
        initCalendar(context)
    }

    private fun initCalendar(context: Context) {
        val nextYear = Calendar.getInstance()
        nextYear.add(Calendar.YEAR, 1)
        val today = Date()
        calendarPickerView.init(today, nextYear.time)
                .inMode(CalendarPickerView.SelectionMode.SINGLE)
                .withSelectedDate(today)
        toolbar.setNavigationIcon(context.getDrawableFromAttr(R.attr.backArrowDrawable))
        calendarPickerView.setOnDateSelectedListener(object : CalendarPickerView.OnDateSelectedListener {
            override fun onDateSelected(date: Date?) {
                bottomSheet.visibility = View.VISIBLE
                selectedDate = date
            }

            override fun onDateUnselected(date: Date?) {

            }
        })
    }

    override fun getView(): View = this

    override fun calendarDateClickListener(clickListener: (String?) -> Unit) {
        button.setOnClickListener {
            dateClickListener = clickListener
            dateClickListener?.invoke(selectedDate?.parseDate())
        }
    }

    override fun toolbarClickListener(clickListener: () -> Unit) {
        toolbar.setOnClickListener {
            clickListener()
        }
    }

}