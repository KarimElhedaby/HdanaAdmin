package com.example.abdallah.hadanadataentry.ui.addkid

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.abdallah.hadanadataentry.R
import com.example.abdallah.hadanadataentry.model.ClassModel
import com.example.abdallah.hadanadataentry.model.Kid
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.hamza.solutions.kolo.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_add_kid.*
import timber.log.Timber
import java.io.FileNotFoundException

class AddKidActivity : BaseActivity(), KidContract.View {

    lateinit var selectedImage: Bitmap
    var clss: MutableMap<String, ClassModel> = mutableMapOf()
    lateinit var storageReference: StorageReference
    lateinit var database: FirebaseDatabase
    var choosenClass: String = " "
    var imageUri: Uri = Uri.EMPTY


    override fun getActivityView(): Int = R.layout.activity_add_kid

    lateinit var presenter: KidContract.Presenter<AddKidActivity>
    override fun afterInflation(savedInstance: Bundle?) {
        presenter = KidPresenter()
        presenter.onAttach(this)
        storageReference = FirebaseStorage.getInstance().getReference("kidsImages");

//        var techers:MutableMap<String,String> = mutableMapOf(Pair("sdgsd","dsg"), Pair("fsdfs","sdfsd"))
        getClasses()

        addKidBTN.setOnClickListener {

            var kid = Kid(kidNameET.text.toString(), imageUri.toString(), 0, choosenClass)
            presenter.addKid(kid)

        }


        kidClassSpiner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {


                for (v in clss) {
                    if (v.value == parent?.getItemAtPosition(position)) {
                        choosenClass = v.key
                    }
                }

            }

        }



        kidIV.setOnClickListener {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                if (ContextCompat.checkSelfPermission(this@AddKidActivity, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(this@AddKidActivity, "Permission Denied", Toast.LENGTH_LONG).show()
                    ActivityCompat.requestPermissions(this@AddKidActivity, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1)

                } else {

                    getImageFromAlbum()
                }

            } else {

                getImageFromAlbum()
            }
        }
    }


    fun getClasses() {
        database = FirebaseDatabase.getInstance()
        val classes = database.getReference("classes")

        classes.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
                Toast.makeText(applicationContext, p0?.message.toString(), Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(p0: DataSnapshot?) {


                for (c in p0?.children!!) {
                    clss?.put(c.key.toString(), c.getValue(ClassModel::class.java)!!)
                }

                var spinerData = ArrayAdapter<ClassModel>(applicationContext, android.R.layout.simple_spinner_dropdown_item)
                for (c in clss) {
                    spinerData.add(c.value)
                }

                kidClassSpiner.adapter = spinerData

                Timber.d(clss.toString())

            }

        })

    }


    private fun getImageFromAlbum() {
        val photoPickerIntent = Intent(Intent.ACTION_PICK)
        photoPickerIntent.type = "image/*"
        startActivityForResult(photoPickerIntent, 200)

    }

    override fun onActivityResult(reqCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(reqCode, resultCode, data)


        if (resultCode == Activity.RESULT_OK) {
            try {
                imageUri = data!!.data
                val imageStream = contentResolver.openInputStream(imageUri!!)
                selectedImage = BitmapFactory.decodeStream(imageStream)

                kidIV.setImageBitmap(selectedImage)
                uploadeImage()
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
                Toast.makeText(this@AddKidActivity, "Something went wrong", Toast.LENGTH_LONG).show()
            }

        } else {
            Toast.makeText(this@AddKidActivity, "You haven't picked Image", Toast.LENGTH_LONG).show()
        }
    }

    private fun uploadeImage() {
        storageReference.putFile(imageUri).addOnSuccessListener({

            imageUri = it.downloadUrl!!


        }).addOnFailureListener({
            Toast.makeText(applicationContext, "" + it.message.toString(), Toast.LENGTH_LONG).show()
        })


    }

    override fun onKidAdded(msg: String) {
        Toast.makeText(applicationContext, "" + msg, Toast.LENGTH_LONG).show()
    }

}
