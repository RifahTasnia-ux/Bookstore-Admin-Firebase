package com.example.adminapp

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.adminapp.databinding.ActivityMainBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private var selectImage: Uri? = null
    val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageUpload.setOnClickListener{
            resultLauncher.launch("image/*")

        }
        binding.btnUpload.setOnClickListener{
            binding.btnUpload.visibility = View.GONE
            binding.progressbar.visibility = View.VISIBLE
            val storage = FirebaseStorage.getInstance().getReference("images/${selectImage!!.path}")
            storage.putFile(selectImage!!).addOnCompleteListener{
               storage.downloadUrl.addOnSuccessListener {uri ->
                   var map = HashMap<String, Any>()
                   map["pic"]=uri.toString()
                   addDataToFirebase(map["pic"].toString())
               }.addOnFailureListener {
                   binding.btnUpload.visibility = View.VISIBLE
                   binding.progressbar.visibility = View.GONE
               }

            }.addOnFailureListener{
                Toast.makeText(this,it.localizedMessage,Toast.LENGTH_SHORT).show()
                binding.btnUpload.visibility = View.VISIBLE
                binding.progressbar.visibility = View.GONE
            }
        }

    }

    private fun addDataToFirebase(imageUrl:String) {
        /*if(binding.imageUpload==null){
           Toast.makeText(applicationContext,"Please enter the Image of the Book.",Toast.LENGTH_SHORT).show()
            binding.progressbar.visibility = View.GONE
            binding.btnUpload.visibility = View.VISIBLE
        }*/
        if(binding.pName.text.isBlank()){
            Toast.makeText(applicationContext,"Please enter the Name of the Book.",Toast.LENGTH_SHORT).show()
            binding.pName.error = "Please enter the Name of the Book."
            binding.progressbar.visibility = View.GONE
            binding.btnUpload.visibility = View.VISIBLE
        }
        else if(binding.pPrice.text.isBlank()){
            Toast.makeText(applicationContext,"Please enter the Price of the Book.",Toast.LENGTH_SHORT).show()
            binding.pPrice.error = "Please enter the Price of the Book."
            binding.progressbar.visibility = View.GONE
            binding.btnUpload.visibility = View.VISIBLE
        }
        else if(binding.pQuantity.text.isBlank()){
            Toast.makeText(applicationContext,"Please enter the Quantity of the Book.",Toast.LENGTH_SHORT).show()
            binding.pQuantity.error = "Please enter the Quantity of the Book."
            binding.progressbar.visibility = View.GONE
            binding.btnUpload.visibility = View.VISIBLE
        }
        else{

            val checker: String = intent.getStringExtra("check").toString()
            val model = ProductModel(
                imageUrl,
                binding.pName.text.toString(),
                binding.pPrice.text.toString(),
                binding.pQuantity.text.toString(),
                true
            )

            if (checker == "1") {
                db.collection("Educational Books").document().set(model).addOnCompleteListener {
                    if (it.isSuccessful) {
                        binding.imageUpload.setImageURI(null)
                        binding.pName.text.clear()
                        binding.pPrice.text.clear()
                        binding.pQuantity.text.clear()

                    }
                    Toast.makeText(this, "Product uploaded successfully.", Toast.LENGTH_SHORT)
                        .show()
                    binding.btnUpload.visibility = View.VISIBLE
                    binding.progressbar.visibility = View.GONE
                }.addOnFailureListener {
                    Toast.makeText(this, it.localizedMessage, Toast.LENGTH_SHORT).show()
                }
            } else if (checker == "2") {
                db.collection("Fictions").document().set(model).addOnCompleteListener {
                    if (it.isSuccessful) {
                        binding.imageUpload.setImageURI(null)
                        binding.pName.text.clear()
                        binding.pPrice.text.clear()
                        binding.pQuantity.text.clear()

                    }
                    Toast.makeText(this, "Product uploaded successfully.", Toast.LENGTH_SHORT)
                        .show()
                    binding.btnUpload.visibility = View.VISIBLE
                    binding.progressbar.visibility = View.GONE

                }.addOnFailureListener {
                    Toast.makeText(this, it.localizedMessage, Toast.LENGTH_SHORT).show()
                }

            } else if (checker == "3") {
                db.collection("Children's Books").document().set(model).addOnCompleteListener {
                    if (it.isSuccessful) {
                        binding.imageUpload.setImageURI(null)
                        binding.pName.text.clear()
                        binding.pPrice.text.clear()
                        binding.pQuantity.text.clear()

                    }
                    Toast.makeText(this, "Product uploaded successfully.", Toast.LENGTH_SHORT)
                        .show()
                    binding.btnUpload.visibility = View.VISIBLE
                    binding.progressbar.visibility = View.GONE
                }.addOnFailureListener {
                    Toast.makeText(this, it.localizedMessage, Toast.LENGTH_SHORT).show()
                }

            } else if (checker == "4") {
                db.collection("Non Fiction").document().set(model).addOnCompleteListener {
                    if (it.isSuccessful) {
                        binding.imageUpload.setImageURI(null)
                        binding.pName.text.clear()
                        binding.pPrice.text.clear()
                        binding.pQuantity.text.clear()

                    }
                    Toast.makeText(this, "Product uploaded successfully.", Toast.LENGTH_SHORT)
                        .show()
                    binding.btnUpload.visibility = View.VISIBLE
                    binding.progressbar.visibility = View.GONE
                }.addOnFailureListener {
                    Toast.makeText(this, it.localizedMessage, Toast.LENGTH_SHORT).show()
                }

            }
        }

    }

    private var resultLauncher = registerForActivityResult(ActivityResultContracts.GetContent()){
        selectImage = it
        binding.imageUpload.setImageURI(selectImage)

    }
}