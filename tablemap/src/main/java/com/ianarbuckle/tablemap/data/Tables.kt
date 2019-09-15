package com.ianarbuckle.tablemap.data

/**
 * Created by Ian Arbuckle on 2019-07-20.
 *
 */
data class Tables(val restaurantId: String, val restaurantName: String, val row: MutableList<Row>)

data class Row(val column: MutableList<Column>)

data class Column(val table: Table)

data class Table(val tableNumber: String, val status: String, val characteristics: TableCharacteristics)

data class TableCharacteristics(val type: String, val seatCapacity: Int, val infantSeat: Boolean)