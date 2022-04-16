import data.SharedMutableDataOutput

interface ConcurrentWork {
    val sharedMutableData: SharedMutableDataOutput
    fun run()
}