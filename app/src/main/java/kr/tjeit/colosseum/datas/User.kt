package kr.tjeit.colosseum.datas

import org.json.JSONObject

class User {

    companion object {
        fun getUserFromJson(json:JSONObject) : User {
            val user = User()

            user.id = json.getInt("id")
            user.email = json.getString("email")
            user.nickName = json.getString("nick_name")

            return user
        }
    }

    var id = 0
    var nickName = ""
    var email = ""

}