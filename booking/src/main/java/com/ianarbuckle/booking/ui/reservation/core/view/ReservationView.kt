package com.ianarbuckle.booking.ui.reservation.core.view

import android.app.TimePickerDialog
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.ianarbuckle.booking.R
import com.ianarbuckle.core.extensions.getDrawableFromAttr
import kotlinx.android.synthetic.main.reservation_view.view.*

/**
 * Created by Ian Arbuckle on 2019-08-31.
 *
 */
interface ReservationView {
    fun getView(): View
    fun showRestaurantName(name: String?)
    fun getEmailValue(): String
    fun getPhoneNumberValue(): String
    fun getFullNameValue(): String
    fun showEmailFieldError()
    fun showPhoneNumberFieldError()
    fun showFullNameFieldError()
    fun onContinueButtonClick(clickListener: () -> Unit)
    fun getDietaryRequirementsValue(): Boolean
    fun getBookingDatesValue(): String
    fun getArrivalTimeValue(): String
    fun getPrefixValue(): String
    fun showBookingDateFieldError()
    fun showArrivalTimeFieldError()
    fun emailFieldTextWatcher()
    fun phoneFieldTextWatcher()
    fun fullnameTextWatcher()
    fun bookingDateTextWatcher()
    fun arrivalTimeDateTextWatcher()
    fun showErrorMessage()
    fun showLoading()
    fun onArrivalTimeClickListener(clickListener: () -> Unit)
    fun showDateTimePicker()
    fun onBookingDateClickListener(clickListener: () -> Unit)
    fun onToolbarBackClickListener(clickListener: () -> Unit)
    fun onPrefixClickListener(clickListener: () -> Unit)
    fun setEmailValue(value: String?): Unit?
    fun setPhoneNumberValue(value: String?): Unit?
    fun setFullNameValue(value: String?): Unit?
    fun setDietaryRequirementValue(value: Boolean)
    fun setBookingDatesValue(value: String?): Unit?
    fun setArrivalTimeValue(value: String?): Unit?
    fun setPrefixValue(value: String?): Unit?
    fun hideLoading()
}

class ReservationViewImpl(context: Context) : ReservationView, ConstraintLayout(context) {

    init {
        inflate(context, R.layout.reservation_view, this)
        toolbar.setNavigationIcon(context.getDrawableFromAttr(R.attr.backArrowDrawable))
    }

    override fun getView(): View = this

    override fun onToolbarBackClickListener(clickListener: () -> Unit) {
        toolbar.setNavigationOnClickListener {
            clickListener()
        }
    }

    override fun showRestaurantName(name: String?) {
        toolbar.title = context.getString(R.string.toolbar_name_format, name)
    }

    override fun getEmailValue(): String = emailTextInputLayout.editText?.text.toString()

    override fun setEmailValue(value: String?) = emailTextInputLayout.editText?.setText(value)

    override fun getPhoneNumberValue(): String = phoneNumberTextInputLayout.editText?.text.toString()

    override fun setPhoneNumberValue(value: String?) = phoneNumberTextInputLayout.editText?.setText(value)

    override fun getFullNameValue(): String = fullnameTextInputLayout.editText?.text.toString()

    override fun setFullNameValue(value: String?) = fullnameTextInputLayout.editText?.setText(value)

    override fun getDietaryRequirementsValue(): Boolean = checkbox.isChecked

    override fun setDietaryRequirementValue(value: Boolean) {
        checkbox.isChecked = value
    }

    override fun getBookingDatesValue(): String = bookingArrivalTimeTextInputLayout.editText?.text.toString()

    override fun setBookingDatesValue(value: String?) = bookingDatesTextInputLayout.editText?.setText(value)

    override fun getArrivalTimeValue(): String = bookingArrivalTimeTextInputLayout.editText?.text.toString()

    override fun setArrivalTimeValue(value: String?) = bookingArrivalTimeTextInputLayout.editText?.setText(value)

    override fun getPrefixValue(): String = phonePrefixTextInputLayout.editText?.text.toString()

    override fun setPrefixValue(value: String?) = phonePrefixTextInputLayout.editText?.setText(value)

    override fun showEmailFieldError() {
        emailTextInputLayout.isErrorEnabled = true
        emailTextInputLayout.error = "Please enter valid email address"
    }

    override fun showPhoneNumberFieldError() {
        phoneNumberTextInputLayout.isErrorEnabled = true
        phoneNumberTextInputLayout.error = "Please fill in phone number"
    }

    override fun showFullNameFieldError() {
        fullnameTextInputLayout.isErrorEnabled = true
        fullnameTextInputLayout.error = "Please fill in fullname"
    }

    override fun showBookingDateFieldError() {
        bookingDatesTextInputLayout.isErrorEnabled = true
        bookingDatesTextInputLayout.error = "Please fill in booking date"
    }

    override fun showArrivalTimeFieldError() {
        bookingArrivalTimeTextInputLayout.isErrorEnabled = true
        bookingArrivalTimeTextInputLayout.error = "Please fill in arrival time"
    }

    override fun emailFieldTextWatcher() {
        emailTextInputLayout.editText?.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if(s.isNotEmpty()) {
                    emailTextInputLayout.isErrorEnabled = false
                }
            }
        })
    }

    override fun phoneFieldTextWatcher() {
        phoneNumberTextInputLayout.editText?.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if(s.isNotEmpty()) {
                    phoneNumberTextInputLayout.isErrorEnabled = false
                }
            }
        })
    }

    override fun fullnameTextWatcher() {
        fullnameTextInputLayout.editText?.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if(s.isNotEmpty()) {
                    fullnameTextInputLayout.isErrorEnabled = false
                }
            }
        })
    }

    override fun bookingDateTextWatcher() {
        bookingDatesTextInputLayout.editText?.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
               if(s.isNotEmpty()) {
                   bookingDatesTextInputLayout.isErrorEnabled = false
               }
            }
        })
    }

    override fun arrivalTimeDateTextWatcher() {
        bookingArrivalTimeTextInputLayout.editText?.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if(s.isNotEmpty()) {
                    bookingArrivalTimeTextInputLayout.isErrorEnabled = false
                }
            }
        })
    }

    override fun onContinueButtonClick(clickListener: () -> Unit) {
        submitButton.setOnClickListener {
            clickListener()
        }
    }

    override fun onArrivalTimeClickListener(clickListener: () -> Unit) {
        bookingArrivalTimeTextInputLayout.setOnClickListener {
            clickListener()
        }
        bookingArrivalTimeTextInputLayout.editText?.setOnClickListener {
            clickListener()
        }
    }

    override fun onBookingDateClickListener(clickListener: () -> Unit) {
        bookingDatesTextInputLayout.setOnClickListener {
            clickListener()
        }
        bookingDatesTextInputLayout.editText?.setOnClickListener {
            clickListener()
        }
    }

    override fun showDateTimePicker() {
        val timePickerDialog = TimePickerDialog(context, TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
            bookingArrivalTimeTextInputLayout.editText?.setText(context.getString(R.string.arrival_date_time_format, hourOfDay, minute))
            bookingArrivalTimeTextInputLayout.editText?.clearFocus()

        }, 0, 0, true)
        timePickerDialog.show()
    }

    override fun showLoading() {
        bookingsLoadingView.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        bookingsLoadingView.visibility = View.GONE
    }

    override fun showErrorMessage() {
        bookingsErrorState.visibility = View.VISIBLE
    }

    override fun onPrefixClickListener(clickListener: () -> Unit) {
        phonePrefixTextInputLayout.editText?.setOnClickListener {
            clickListener()
        }
        phonePrefixTextInputLayout.setOnClickListener {
            clickListener()
        }
    }
}