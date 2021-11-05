package com.devapp.to_docompose.data.models


import androidx.compose.ui.graphics.Color
import com.devapp.to_docompose.ui.theme.*

enum class Priority(val color:Color) {
    HIGH(HighPriorityColor),
    MEDIUM(MediumPriorityColor),
    LOW(LowPriorityColor),
    NONE(NonePriorityColor)
}