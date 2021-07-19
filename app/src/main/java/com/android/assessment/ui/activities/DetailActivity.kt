package com.android.assessment.ui.activities

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.android.assessment.ui.viewmodels.DetailVenueViewModel
import com.android.assessment.R

class DetailActivity : AppCompatActivity() {

    private val viewModel by viewModels<DetailVenueViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val tvVenueTitle = findViewById<TextView>(R.id.venue_title)
        val tvVenueDescription = findViewById<TextView>(R.id.venue_description)
        val tvVenuePhoneNumber = findViewById<TextView>(R.id.venue_phoneNumber)
        val tvVenueAdress = findViewById<TextView>(R.id.venue_adress)
        val bundle: Bundle? = intent.extras
        val venueID = bundle!!.getString("VenueID")
        venueID?.let { it ->

            viewModel.getDetailData(
                venueID = it,
                date = "20210718" // date format is: YYYYMMDD
            )

            viewModel.data
                .observe(this, Observer {

                    it.let {
                        if (it != null) {
                            tvVenueTitle.text = it.response.venue.name
                            tvVenueDescription.text = it.response.venue.description
                           // tvVenuePhoneNumber.text = it.response.venue.contact.formattedPhone
                          //  tvVenueAdress.text = it.response.venue.location.address

                        }
                    }
                })
        }
    }

}