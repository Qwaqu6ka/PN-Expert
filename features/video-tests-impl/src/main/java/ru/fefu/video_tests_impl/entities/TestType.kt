package ru.fefu.video_tests_impl.entities

import androidx.annotation.StringRes
import ru.fefu.video_tests_impl.R

internal enum class TestType(@StringRes val titleRes: Int, @StringRes val instructionRes: Int) {
    FacialExpressiveness(R.string.facial_expressiveness, R.string.facial_expressiveness_instruction),
    FingersTapping(R.string.fingers_tapping, R.string.fingers_tapping_instruction),
    BrushMovements(R.string.brush_movements, R.string.brush_movements_instruction),
    BrushPronationSupination(R.string.brush_pronation_supination, R.string.brush_pronation_supination_instruction),
    FootTapping(R.string.foot_tapping, R.string.foot_tapping_instruction),
    LegsMobility(R.string.legs_mobility, R.string.legs_mobility_instruction),
    GettingUpFromChair(R.string.getting_up_from_chair, R.string.getting_up_from_chair_instruction),
    Gait(R.string.gait, R.string.gait_instruction),
    PosturalHandsTremor(R.string.postural_hands_tremor, R.string.postural_hands_tremor_instruction),
    KineticHandsTremor(R.string.kinetic_hands_tremor, R.string.kinetic_hands_tremor_instruction),
    AmplitudeOfRestingTremor(R.string.amplitude_of_resting_tremor, R.string.amplitude_of_resting_tremor_instruction)
}