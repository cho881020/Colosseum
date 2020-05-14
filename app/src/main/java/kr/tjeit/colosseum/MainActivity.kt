package kr.tjeit.colosseum

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : BaseActivity() {

    val REQ_FOR_PICK_IMG = 1000;
    val REQ_FOR_PICK_CROP = 1001;

    var photoURI:Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        testImg.setOnClickListener {
            val myIntent = Intent(Intent.ACTION_PICK)
            myIntent.setType("image/*")
            myIntent.setType(MediaStore.Images.Media.CONTENT_TYPE)
            startActivityForResult(myIntent, REQ_FOR_PICK_IMG)
        }

    }

    override fun setValues() {

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQ_FOR_PICK_IMG) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    if (data.data != null) {
                        val cropIntent = Intent("com.android.camera.action.CROP")
                        cropIntent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
                        cropIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                        cropIntent.setDataAndType(data.data!!, "image/*")

                        cropIntent.putExtra("scale",true)
                        cropIntent.putExtra("output", photoURI)
                        startActivityForResult(cropIntent, REQ_FOR_PICK_CROP)
                    }
                }
            }
        }
        else if (requestCode == REQ_FOR_PICK_CROP) {
            if (resultCode == Activity.RESULT_OK) {
                Glide.with(mContext).load(photoURI).into(testImg)
            }
        }
    }



}
