import coroutines.*
import org.junit.Assume.assumeThat
import org.junit.Assume.assumeTrue
import kotlin.system.measureTimeMillis
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class CoroutineDataTest {
    private val runner: CoroutineRunner = CoroutineRunnerImpl()

    @Test(timeout = TEST_TIMEOUT)
    fun testActor() {
        testClass(CoroutinedDataActor(runner), canBeLong = true, expectedToBeUnstable = false)
    }

    @Test(timeout = TEST_TIMEOUT)
    fun testChannel() {
        testClass(CoroutinedDataChannel(runner), canBeLong = true, expectedToBeUnstable = false)
    }

    @Test(timeout = TEST_TIMEOUT)
    fun testConfinement() {
        testClass(CoroutinedDataConfinement(runner), canBeLong = true, expectedToBeUnstable = false)
    }

    @Test(timeout = TEST_TIMEOUT)
    fun testMutex() {
        testClass(CoroutinedDataMutex(runner), canBeLong = true, expectedToBeUnstable = false)
    }

    @Test(timeout = TEST_TIMEOUT)
    fun testVolatile() {
        testClass(CoroutinedDataVolatile(runner), canBeLong = false, expectedToBeUnstable = true)
    }

    @Test(timeout = TEST_TIMEOUT)
    fun testBasic() {
        testClass(CoroutinedData(runner), canBeLong = false, expectedToBeUnstable = true)
    }

    companion object {

        const val TEST_TIMEOUT = 5000L
        private const val TEST_ASSERT_TIME = 1000L

        fun testClass(data: SharedMutableData, canBeLong: Boolean, expectedToBeUnstable: Boolean) {
            val time = measureTimeMillis { data.run() }

            if (expectedToBeUnstable) {
                assertNotEquals(CoroutineRunnerImpl.NUM_TOTAL_OPERATIONS, data.mutableState)
            } else {
                assertEquals(CoroutineRunnerImpl.NUM_TOTAL_OPERATIONS, data.mutableState)
            }

            if (!canBeLong) {
                assert(time < TEST_ASSERT_TIME) {
                    "it took to long to execute ${time}"
                }
            } else {
                assumeTrue("too short! ${time}" , time < TEST_ASSERT_TIME)
            }
        }
    }
}
