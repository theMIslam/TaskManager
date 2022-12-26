import android.content.Context
import android.content.Context.MODE_PRIVATE

class Pref(val context: Context) {

    private val pref = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE)

    //onboard
    fun setOnBoardingSeen(isSeen: Boolean) {
        pref.edit().putBoolean(ON_BOARDING_SEEN, isSeen).apply()
    }

    fun isOnBoardingSeen(): Boolean {
        return pref.getBoolean(ON_BOARDING_SEEN, false)
    }

    //image
    fun setImage(image: String) {
        pref.edit().putString(IMAGE_KEY, image).apply()
    }

    fun getImage(): String {
        return pref.getString(IMAGE_KEY, "").toString()
    }

    //age
    fun setAge(age: String) {
        pref.edit().putString(AGE_KEY, age).apply()
    }

    fun getAge(): String {
        return pref.getString(AGE_KEY, "").toString()
    }

    //gen
    fun setGen(gen: String) {
        pref.edit().putString(GEN_KEY, gen).apply()
    }

    fun getGen(): String {
        return pref.getString(GEN_KEY, "").toString()
    }

    //name
    fun setName(name: String) {
        pref.edit().putString(NAME_KEY, name).apply()
    }

    fun getName(): String {
        return pref.getString(NAME_KEY, "").toString()
    }

    companion object {
        private const val ON_BOARDING_SEEN = "is_seen"
        private const val PREF_NAME = "pref_task_manager"
        private const val IMAGE_KEY = "image_pref"
        private const val AGE_KEY = "age_pref"
        private const val GEN_KEY = "gen_pref"
        private const val NAME_KEY = "name_pref"
    }
}