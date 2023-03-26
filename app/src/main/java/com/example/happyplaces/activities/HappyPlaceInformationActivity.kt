package com.example.happyplaces.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.happyplaces.databinding.ActivityHappyPlaceInformationBinding
import com.example.happyplaces.models.HappyPlaceModel

class HappyPlaceInformationActivity : AppCompatActivity() {

    private var binding: ActivityHappyPlaceInformationBinding? = null
    private var happyPlaceDetailModel: HappyPlaceModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHappyPlaceInformationBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        if (intent.hasExtra(MainActivity.EXTRA_PLACE_DETAILS)){
            happyPlaceDetailModel = intent.getParcelableExtra(
                MainActivity.EXTRA_PLACE_DETAILS) as HappyPlaceModel?
        }

        if (happyPlaceDetailModel != null){
            setSupportActionBar(binding?.toolBarHappyPlacesInformation)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.title = happyPlaceDetailModel?.title
            binding?.toolBarHappyPlacesInformation?.setNavigationOnClickListener {
                onBackPressed()
            }

            binding?.ivPlaceImage?.setImageURI(Uri.parse(happyPlaceDetailModel?.image))
            binding?.tvDescription?.text = happyPlaceDetailModel?.description
            binding?.tvLocation?.text = happyPlaceDetailModel?.location


        }

        binding?.btnViewOnMap?.setOnClickListener {
            val intent = Intent(this, MapActivity::class.java)
            intent.putExtra(MainActivity.EXTRA_PLACE_DETAILS, happyPlaceDetailModel)
            startActivity(intent)
        }

    }



    override fun onDestroy() {
        super.onDestroy()

        binding = null
    }
}