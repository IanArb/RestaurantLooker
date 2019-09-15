package com.ianarbuckle.tablemap.ui.core.view

import android.content.Context
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.ianarbuckle.tablemap.data.Row

/**
 * Created by Ian Arbuckle on 2019-07-20.
 *
 */
interface TablesMapView {
    fun getView(): View
    fun showTableRow(row: MutableList<Row>)
}

class TablesMapViewImpl(context: Context) : TablesMapView, ConstraintLayout(context) {

    override fun getView(): View = this

    override fun showTableRow(row: MutableList<Row>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}