package ru.fefu.written_test_impl.entities

import ru.fefu.written_test_impl.entities.testentities.WrittenTest
import ru.fefu.written_test_impl.entities.tests.TestFAB
import ru.fefu.written_test_impl.entities.tests.TestHADS
import ru.fefu.written_test_impl.entities.tests.TestHoehnYahr
import ru.fefu.written_test_impl.entities.tests.TestPDQ39
import ru.fefu.written_test_impl.entities.tests.TestPSQI
import ru.fefu.written_test_impl.entities.tests.TestSchwabEngland

enum class TestType(val test: WrittenTest) {
    UPDRS1(TestPSQI),
    UPDRS2(TestPSQI),
    UPDRS3(TestPSQI),
    UPDRS4(TestPSQI),
    PDQ39(TestPDQ39),
    HADS(TestHADS),
    SchwabEngland(TestSchwabEngland),
    HoehnYahr(TestHoehnYahr),
    FAB(TestFAB),
    PSQI(TestPSQI)
}