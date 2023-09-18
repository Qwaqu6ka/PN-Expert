package ru.fefu.video_tests_impl.entities

import androidx.annotation.StringRes
import ru.fefu.video_tests_impl.R

internal enum class TestType(@StringRes val title: Int) {
    FacialExpressiveness(R.string.facial_expressiveness),
    FingersTapping(R.string.fingers_tapping),
    BrushMovements(R.string.brush_movements),
    BrushPronationSupination(R.string.brush_pronation_supination),
    FootTapping(R.string.foot_tapping),
    LegsMobility(R.string.legs_mobility),
    GettingUpFromChair(R.string.getting_up_from_chair),
    Gait(R.string.gait),
    PosturalHandsTremor(R.string.postural_hands_tremor),
    KineticHandsTremor(R.string.kinetic_hands_tremor),
    AmplitudeOfRestingTremor(R.string.amplitude_of_resting_tremor)
}