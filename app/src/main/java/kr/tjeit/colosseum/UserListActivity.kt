package kr.tjeit.colosseum

import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_user_list.*
import kr.tjeit.colosseum.adapters.UserAdapter
import kr.tjeit.colosseum.datas.User
import kr.tjeit.colosseum.utils.ServerUtil
import org.json.JSONObject

class UserListActivity : BaseActivity() {

    lateinit var mAdapter : UserAdapter
    val userArrayList = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

        mAdapter = UserAdapter(mContext, R.layout.user_list_item, userArrayList)
        userListView.adapter = mAdapter

        ServerUtil.getRequestUserList(mContext, object : ServerUtil.JsonResponseHandler {
            override fun onResponse(json: JSONObject) {

                Log.d("사용자목록", json.toString())

                val data = json.getJSONObject("data")
                val users = data.getJSONArray("users")
                for (i in 0..users.length()-1) {
                    val userJson = users.getJSONObject(i)

                    userArrayList.add(User.getUserFromJson(userJson))
                }
                runOnUiThread {
                    mAdapter.notifyDataSetChanged()
                }


            }

        })

    }

}
