package kr.tjeit.colosseum

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kr.tjeit.colosseum.utils.ServerUtil
import org.json.JSONObject

class MainActivity : BaseActivity() {

    val REQ_FOR_PICK_IMAGE_FROM_GALLERY = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        changeProfileBtn.setOnClickListener {
            val myIntent = Intent(Intent.ACTION_PICK)
            myIntent.setType("image/*")
            myIntent.setType(MediaStore.Images.Media.CONTENT_TYPE)
            startActivityForResult(myIntent, REQ_FOR_PICK_IMAGE_FROM_GALLERY)
        }

    }

    override fun setValues() {

    }

    override fun onResume() {
        super.onResume()

        ServerUtil.getRequestMainInfo(mContext, object : ServerUtil.JsonResponseHandler {
            override fun onResponse(json: JSONObject) {
                Log.d("메인화면응답", json.toString())

                val code = json.getInt("code")

                if (code == 200) {
                    val data = json.getJSONObject("data")
                    val user = data.getJSONObject("user")
                    val topic = data.getJSONObject("topic")

                    runOnUiThread {
                        myNickNameTxt.text = user.getString("nick_name")
                        thisWeekBattleTopicTxt.text = topic.getString("title")
                    }

                }

            }

        })

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQ_FOR_PICK_IMAGE_FROM_GALLERY) {
            if (resultCode == Activity.RESULT_OK) {
                Glide.with(mContext).load(data?.data).into(myProfileImg)

            }
        }

    }

}
