package com.example.abdallah.hadanadataentry.ui.addkid

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.Toast
import com.example.abdallah.hadanadataentry.R
import com.example.abdallah.hadanadataentry.ui.model.Kid
import com.hamza.solutions.kolo.ui.base.BaseActivity
import com.theartofdev.edmodo.cropper.CropImage
import kotlinx.android.synthetic.main.activity_add_kid.*


class AddKidActivity : BaseActivity(), KidContract.View {

    override fun getActivityView(): Int = R.layout.activity_add_kid

    lateinit var presenter: KidContract.Presenter<AddKidActivity>
    override fun afterInflation(savedInstance: Bundle?) {
        presenter = KidPresenter()
        presenter.onAttach(this)

//        var techers:MutableMap<String,String> = mutableMapOf(Pair("sdgsd","dsg"), Pair("fsdfs","sdfsd"))

        var kid = Kid("Fa5ry", "img", 0, "Class A")
        presenter.addKid(kid)


        kidIV.setOnClickListener {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                if (ContextCompat.checkSelfPermission(this@AddKidActivity, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(this@AddKidActivity, "Permission Denied", Toast.LENGTH_LONG).show()
                    ActivityCompat.requestPermissions(this@AddKidActivity, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1)

                } else {

                    selectImageInAlbum()

                }

            } else {

                selectImageInAlbum()

            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {

                var mainImageURI = result?.uri
                kidIV.setImageURI(mainImageURI)

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                val error = result.error

            }
        }
    }

    fun selectImageInAlbum() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE)
        }
    }

    override fun onKidAdded(msg: String) {
        Toast.makeText(applicationContext, "" + msg, Toast.LENGTH_LONG).show()
    }


}
