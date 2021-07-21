package com.android.assessment.ui.activities

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.android.assessment.R
import com.android.assessment.ui.viewmodels.DetailVenueViewModel
import com.android.assessment.util.Constants

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

                    if (it != null) {
                        tvVenueTitle.text = it.response.venue.name
                        tvVenueDescription.text =
                            it.response.venue.description ?: getString(R.string.no_description)
                        tvVenuePhoneNumber.text = it.response.venue.contact?.formattedPhone
                            ?: getString(R.string.no_contact_information)

                        tvVenueAddress.text =
                            it.response.venue.location?.address ?: getString(R.string.no_address)
                        tvVenueRating.text =
                            it.response.venue.rating ?: getString(R.string.no_rating)

                    }
                })
        }
    }

}
