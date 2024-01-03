package ru.fefu.written_test_impl.domain

import ru.fefu.written_test_impl.presentation.entities.WrittenTest
import ru.fefu.written_test_impl.domain.tests.TestFAB
import ru.fefu.written_test_impl.domain.tests.TestHADS
import ru.fefu.written_test_impl.domain.tests.TestHoehnYahr
import ru.fefu.written_test_impl.domain.tests.TestPDQ39
import ru.fefu.written_test_impl.domain.tests.TestPSQI
import ru.fefu.written_test_impl.domain.tests.TestSchwabEngland
import ru.fefu.written_test_impl.domain.tests.TestUPDRS1
import ru.fefu.written_test_impl.domain.tests.TestUPDRS2
import ru.fefu.written_test_impl.domain.tests.TestUPDRS3
import ru.fefu.written_test_impl.domain.tests.TestUPDRS4

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