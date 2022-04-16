import data.SharedMutableData

interface ConcurrentWork {
    val sharedMutableData: SharedMutableData
    fun run()
}