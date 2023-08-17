package ru.fefu.written_test_impl.entities

import ru.fefu.written_test_impl.entities.testentities.WrittenTest
import ru.fefu.written_test_impl.entities.tests.TestPSQI

internal enum class TestType(val test: WrittenTest) {
    UPDRS1(TestPSQI),
    UPDRS2(TestPSQI),
    UPDRS3(TestPSQI),
    UPDRS4(TestPSQI),
    PDQ39(TestPSQI),
    HADS(TestPSQI),
    SchwabEngland(TestPSQI),
    HoehnYahr(TestPSQI),
    FAB(TestPSQI),
    PSQI(TestPSQI)
}