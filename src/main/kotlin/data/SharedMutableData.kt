package data

interface SharedMutableData {
    fun increment()
    val numOperations: Int
}
