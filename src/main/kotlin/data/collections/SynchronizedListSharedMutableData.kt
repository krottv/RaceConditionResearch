package data.collections

import java.util.*
import kotlin.collections.ArrayList

class SynchronizedListSharedMutableData: BaseMutableListSharedData() {

    override val list = Collections.synchronizedList(ArrayList<String>())
}