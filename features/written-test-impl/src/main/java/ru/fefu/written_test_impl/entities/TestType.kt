package ru.fefu.written_test_impl.entities

import ru.fefu.written_test_impl.entities.testentities.WrittenTest
import ru.fefu.written_test_impl.entities.tests.TestFAB
import ru.fefu.written_test_impl.entities.tests.TestHADS
import ru.fefu.written_test_impl.entities.tests.TestHoehnYahr
import ru.fefu.written_test_impl.entities.tests.TestPDQ39
import ru.fefu.written_test_impl.entities.tests.TestPSQI
import ru.fefu.written_test_impl.entities.tests.TestSchwabEngland
import ru.fefu.written_test_impl.entities.tests.TestUPDRS1
import ru.fefu.written_test_impl.entities.tests.TestUPDRS2
import ru.fefu.written_test_impl.entities.tests.TestUPDRS3
import ru.fefu.written_test_impl.entities.tests.TestUPDRS4

internal enum class TestType(val test: WrittenTest) {
    UPDRS1(TestUPDRS1),
    UPDRS2(TestUPDRS2),
    UPDRS3(TestUPDRS3),
    UPDRS4(TestUPDRS4),
    PDQ39(TestPDQ39),
    HADS(TestHADS),
    SchwabEngland(TestSchwabEngland),
    HoehnYahr(TestHoehnYahr),
    FAB(TestFAB),
    PSQI(TestPSQI)
}