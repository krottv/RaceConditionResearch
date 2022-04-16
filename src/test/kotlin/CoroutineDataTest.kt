import ConcurrentTestUtils.TEST_TIMEOUT
import ConcurrentTestUtils.testClass
import coroutines.*
import data.IntSharedMutableData
import data.VolatileIntMutableData
import org.junit.Assume.assumeTrue
import kotlin.system.measureTimeMillis
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class CoroutineDataTest {
    private val runner: CoroutineRunner = CoroutineRunnerImpl()

    @Test(timeout = TEST_TIMEOUT)
    fun testActor() {
        testClass(CoroutinedDataActor(runner, IntSharedMutableData()), canBeLong = true, expectedToBeUnstable = false)
    }

    @Test(timeout = TEST_TIMEOUT)
    fun testChannel() {
        testClass(CoroutinedDataChannel(runner, IntSharedMutableData()), canBeLong = true, expectedToBeUnstable = false)
    }

    @Test(timeout = TEST_TIMEOUT)
    fun testConfinement() {
        testClass(CoroutinedDataConfinement(runner, IntSharedMutableData()), canBeLong = true, expectedToBeUnstable = false)
    }

    @Test(timeout = TEST_TIMEOUT)
    fun testMutex() {
        testClass(CoroutinedDataMutex(runner, IntSharedMutableData()), canBeLong = true, expectedToBeUnstable = false)
    }

    @Test(timeout = TEST_TIMEOUT)
    fun testVolatile() {
        testClass(CoroutinedData(runner, VolatileIntMutableData()), canBeLong = false, expectedToBeUnstable = true)
    }

    @Test(timeout = TEST_TIMEOUT)
    fun testBasic() {
        testClass(CoroutinedData(runner, IntSharedMutableData()), canBeLong = false, expectedToBeUnstable = true)
    }
}
