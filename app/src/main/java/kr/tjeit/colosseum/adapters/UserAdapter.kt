package kr.tjeit.colosseum.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import kr.tjeit.colosseum.R
import kr.tjeit.colosseum.datas.User

class UserAdapter(val mContext: Context, val resId: Int, val mList:ArrayList<User>) : ArrayAdapter<User>(mContext, resId, mList) {

    val inf = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var tempRow = convertView

        if (tempRow == null) {
            tempRow = inf.inflate(R.layout.user_list_item, null)
        }

        val row = tempRow!!

        val userInfoTxt = row.findViewById<TextView>(R.id.userInfoTxt)

        val data = mList.get(position)

        userInfoTxt.text = "${data.nickName}(${data.email})"


        return row

    }

}