package data

class VolatileIntMutableData: IntSharedMutableData() {

    @Volatile
    override var i  = 0
}