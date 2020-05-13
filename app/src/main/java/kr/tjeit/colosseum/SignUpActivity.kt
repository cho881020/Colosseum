package kr.tjeit.colosseum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_sign_up.*
import kr.tjeit.colosseum.utils.ServerUtil
import org.json.JSONObject

class SignUpActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        emailCheckBtn.setOnClickListener {

            val inputEmail = emailEdt.text.toString()

            ServerUtil.getRequestEmailDuplCheck(mContext, inputEmail, object  : ServerUtil.JsonResponseHandler {
                override fun onResponse(json: JSONObject) {
                    Log.d("이메일중복응답", json.toString())

                    val code = json.getInt("code")

                    if (code == 200) {

                        runOnUiThread {
                            emailCheckResultTxt.setTextColor(resources.getColor(R.color.azul))
                            emailCheckResultTxt.setText(R.string.id_check_success_message)
                        }
                    }
                    else {
                        runOnUiThread {
                            emailCheckResultTxt.setTextColor(resources.getColor(R.color.grapefruit))
                            emailCheckResultTxt.setText(R.string.id_check_fail_message)
                        }

                    }

                }

            })

        }

    }

    override fun setValues() {

    }

}
