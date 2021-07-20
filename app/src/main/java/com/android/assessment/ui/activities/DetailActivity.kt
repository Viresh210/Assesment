package com.android.assessment.ui.activities

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.android.assessment.ui.viewmodels.DetailVenueViewModel
import com.android.assessment.R
import com.android.assessment.util.Constants
import com.android.assessment.util.Constants.ERROR_MESSAGE_API

class DetailActivity : AppCompatActivity() {

    private val viewModel by viewModels<DetailVenueViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val tvVenueTitle = findViewById<TextView>(R.id.venue_title)
        val tvVenueDescription = findViewById<TextView>(R.id.venue_description)
        val tvVenuePhoneNumber = findViewById<TextView>(R.id.venue_phoneNumber)
        val tvVenueAddress = findViewById<TextView>(R.id.venue_address)
        val tvVenueRating = findViewById<TextView>(R.id.venue_rating)
        val bundle: Bundle? = intent.extras
        val venueID = bundle!!.getString("VenueID")
        venueID?.let { it ->

            viewModel.getDetailData(
                venueID = it,
                date = Constants.DATE
            )

            viewModel.data
                .observe(this, Observer {

                    it.let {
                        if (it != null) {
                            println(it.response.venue)
                            tvVenueTitle.text = it.response.venue.name
                            if (it.response.venue.description != null) {
                                tvVenueDescription.text = it.response.venue.description
                            } else {
                                tvVenueDescription.text = Constants.NO_DESCRIPTION
                            }

                            if (it.response.venue.contact.formattedPhone != null) {
                                tvVenuePhoneNumber.text = it.response.venue.contact.formattedPhone
                            } else {
                                tvVenuePhoneNumber.text = Constants.NO_CONTACT_INFORMATION
                            }

                            if (it.response.venue.location.address != null) {
                                tvVenueAddress.text = it.response.venue.location.address
                            } else {
                                tvVenueAddress.text = Constants.NO_ADDRESS
                            }

                            if (it.response.venue.rating != null) {
                                tvVenueRating.text = it.response.venue.rating
                            } else {
                                tvVenueRating.text = Constants.NO_RATING
                            }

                        }else{
                            Toast.makeText(this, ERROR_MESSAGE_API, Toast.LENGTH_LONG).show()
                            tvVenueDescription.text = Constants.NO_DESCRIPTION
                            tvVenuePhoneNumber.text = Constants.NO_CONTACT_INFORMATION
                            tvVenueAddress.text = Constants.NO_ADDRESS
                            tvVenueRating.text = Constants.NO_RATING
                        }
                    }
                })
        }
    }

}
