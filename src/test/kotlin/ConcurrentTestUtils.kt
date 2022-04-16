import data.MutableDataOutputImpl
import data.NumOperations
import org.junit.Assume
import kotlin.system.measureTimeMillis
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

object ConcurrentTestUtils {

    const val TEST_TIMEOUT = 5000L
    private const val TEST_ASSERT_TIME = 1000L

    fun testClass(data: ConcurrentWork, canBeLong: Boolean, expectedToBeUnstable: Boolean, print: Boolean = true) {
        val time = measureTimeMillis { data.run() }

        if (expectedToBeUnstable) {
            assertNotEquals(NumOperations.NUM_TOTAL_OPERATIONS, data.sharedMutableData.numOperations)
        } else {
            assertEquals(NumOperations.NUM_TOTAL_OPERATIONS, data.sharedMutableData.numOperations)
        }

        if (print)
            MutableDataOutputImpl().print(
                data,
                NumOperations.NUM_TOTAL_OPERATIONS,
                time
            )

        if (!canBeLong) {
            assert(time < TEST_ASSERT_TIME) {
                "it took to long to execute ${time}"
            }
        } else {
            Assume.assumeTrue("too short! $time", time < TEST_ASSERT_TIME)
        }
    }
}