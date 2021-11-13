package com.example.mynote

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mynote.Adapter.SaveAdapter
import com.example.mynote.model.SaveModel
import com.example.mynote.model.SaveTextModel
import kotlinx.android.synthetic.main.activity_save.*
import kotlinx.android.synthetic.main.editext_layout.view.*
import kotlinx.android.synthetic.main.save_item_layout.*

class SaveActivity : AppCompatActivity() {

    companion object {
        private const val CAMERA_PERMISSION_CODE = 1
        private const val CAMERA_REQUEST_CODE = 2
    }

    val save_list = arrayListOf<SaveModel>(
        SaveModel("5.10.2021", null, "15:41"),
        SaveModel("5.10.2021",null, "15:41"),
        SaveModel("5.10.2021", null, "15:41"),
        SaveModel("5.10.2021", null, "15:41"),

    )
    var save_text_list = arrayListOf<SaveTextModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save)

        save_recycler.layoutManager = LinearLayoutManager(this)
        save_recycler.adapter = SaveAdapter(save_list, save_text_list)

        back_id.setOnClickListener {
            finish()
        }


        oppen_camera_btn.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
            ){
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(intent, CAMERA_REQUEST_CODE)
            }else{
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.CAMERA),
                    CAMERA_PERMISSION_CODE
                )
            }
        }
        //start local xotiradan rasm olish
        val getImage = registerForActivityResult(
            ActivityResultContracts.GetContent(),

            ActivityResultCallback {
                val image = it.compareTo(it)
                save_image_id.setImageURI(it)
                Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
            }
        )
        save_add_image_gallarey_id.setOnClickListener {
           getImage.launch("image/")

//            val dialog = AlertDialog.Builder(this)
//            dialog.setTitle("Fooo")
//                .setMessage("Bla bla bla")
//                .setPositiveButton("Ok", DialogInterface.OnClickListener {
//                        dialog, which ->
//                    Toast.makeText(this, "Ok", Toast.LENGTH_SHORT).show()
//                }).setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->
//                    Toast.makeText(this, "No", Toast.LENGTH_SHORT).show()
//                })
//            dialog.show()
        }
        //finsh local xotiradan rasm olish

        //start text add
    save_add_text_id.setOnClickListener {
        showDialogLayout()
    }
    }

    private fun showDialogLayout(){
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater.inflate(R.layout.editext_layout, null)
        val editext = inflater.ediText.toString()

        with(builder){
            setTitle("Hello")
            setPositiveButton("Ok", DialogInterface.OnClickListener { dialog, which ->
                Toast.makeText(this@SaveActivity, "Ok ${editext}", Toast.LENGTH_SHORT).show()
                if (editext.isNotEmpty()){
                    save_add_text_id.visibility = View.VISIBLE
                    save_text_list.add(SaveTextModel(editext))

                    which.and(666)
                }
            })

            setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->
                Toast.makeText(this@SaveActivity, "No", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            })
            setView(inflater)
            show()
            

        }
         fun saveActivityAddText(){

        }
    }

    //finsh text add
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_CODE){
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(intent, CAMERA_REQUEST_CODE)
            }else{
                Toast.makeText(this,
                    "Oops you just denied the permission for camera." +
                            " Don't worry you can allow it in the settings.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK){
            if (requestCode == CAMERA_REQUEST_CODE){
                val thumBanil: Bitmap = data!!.extras!!.get("data") as Bitmap

                save_list.set(0,SaveModel("08.10.2021", thumBanil, "00:3"))
                save_image_id.setImageBitmap(thumBanil)
                val addSave = MutableLiveData<SaveModel>()

            }
        }

    }

}