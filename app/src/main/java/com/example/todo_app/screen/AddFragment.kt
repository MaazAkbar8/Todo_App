@file:Suppress("DEPRECATION")

package com.example.todo_app.screen

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.todo_app.DataClass.TodoData
import com.example.todo_app.R
import com.example.todo_app.databinding.FragmentAddBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import java.io.IOException

class AddFragment : Fragment() {

    private lateinit var btnAdd:TextView
    private val REQUEST_TAKE_PHOTO = 0
    private val REQUEST_SELECT_IMAGE_IN_ALBUM = 1
    private val REQUEST_IMAGE_CAPTURE = 0
    private val PICK_IMAGE_REQUEST = 1
    private var filePath: Uri? = null
    private var firebaseStorage: FirebaseStorage?=null
    private var storageReference: StorageReference?=null

    private lateinit var binding: FragmentAddBinding
    //private var storage: FirebaseStorage = FirebaseStorage.getInstance()
    private var storageRef= Firebase.storage

    private val currentUser = FirebaseAuth.getInstance().currentUser
    private lateinit var uri: Uri

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_add, container, false)



        firebaseStorage = FirebaseStorage.getInstance()
        storageReference = FirebaseStorage.getInstance().reference



//*************************************************************************************************************************
       // if clicke plus go to gallery
        binding.tvplus.setOnClickListener {
            chooseimg()
        }
    //**************************************************************************************************************
      // if clcick upload save image firebase
 binding.uploadimg.setOnClickListener {
     uploadImage()
 }


        return binding.root
    }
    //*****************************************************************************************************************
//  if user clcik pluse go to gallery mean choosing image fuction
    private fun chooseimg() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // if clciked show imgae button to image display in imGEVIEW
        binding.showimage.setOnClickListener {
            if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
                if (data == null || data.data == null) {
                    return@setOnClickListener
                }
                filePath = data.data// !!
                try {
                    val contentResolver = requireContext().contentResolver
                    val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filePath)
                    binding.iv.setImageBitmap(bitmap)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }

    // therse upload fuction if userclicked upload button to image save databse image saved storage and url save realtime databse

    private fun uploadImage() {

            if(filePath !=null){
                val timestamp = System.currentTimeMillis().toString()
                val imageName = "image_$timestamp"
                val imageRef = storageReference?.child("storageImg/${imageName}.jpg")//stored in storage
          // Set a unique image name

        val uploadTask = imageRef?.putFile(filePath!!) // imageUri is the Uri of the image file

        uploadTask?.continueWithTask { task ->
            if (!task.isSuccessful) {
                task.exception?.let {
                    throw it
                }
            }
            imageRef.downloadUrl
        }?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val downloadUri = task.result
                // Now you have the download URL of the uploaded image
                saveImageUrlToDatabase(downloadUri.toString())
            } else {
                // Handle failure
            }
        }

    }
}

    private fun saveImageUrlToDatabase(image: String) {

        val databaseRef = FirebaseDatabase.getInstance().reference
        val newImageKey = databaseRef.child("image").push().key // Generate a new key user childroot

        val imageItem = TodoData(image)
        newImageKey?.let {
            databaseRef.child("userimage").child(it).setValue(imageItem)// image2 save realtime datbase rootname
                .addOnSuccessListener {
                    // Image URL stored in the database successfully
                    Toast.makeText(requireContext(), "saved imageurl DB", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    // Handle failure
                    Toast.makeText(requireContext(), "not saved", Toast.LENGTH_SHORT).show()
                }
        }
    }

    }

    //***************************************************************************************************





















