
data class ClassModel(var name: String? = null, var teachers: MutableMap<String, String>? = null, var kids:MutableMap<String, String>?= null) {
    override fun toString(): String {
        return "$name"
    }
}