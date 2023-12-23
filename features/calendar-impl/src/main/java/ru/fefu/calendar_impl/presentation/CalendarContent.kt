package ru.fefu.calendar_impl.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import ru.fefu.calendar_impl.R
import ru.fefu.calendar_impl.domain.models.AvailableTime
import ru.fefu.calendar_impl.domain.models.BookingModel
import ru.fefu.calendar_impl.domain.models.CalendarActions
import ru.fefu.calendar_impl.domain.models.CalendarUiModel
import ru.fefu.calendar_impl.domain.models.PillModel
import ru.fefu.calendar_impl.domain.models.TaskModel
import ru.fefu.calendar_impl.domain.models.TestModel
import ru.fefu.calendar_impl.domain.models.TimeRange
import ru.fefu.theme.PnExpertTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Content(
    data: CalendarUiModel,
    onDateClickListener: (CalendarUiModel.Date) -> Unit,
    type: CalendarActions,
    onClickPillsEvent: (Int) -> Unit,
    getAvailableTime: (LocalDate) -> List<AvailableTime>,
    onTaskNavigate: (Int, TestModel) -> Unit,
    onClickTimes: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {

    FlowRow() {
        repeat(data.visibleDates.size - 1) {
            ContentItem(
                data.visibleDates[it],
                onDateClickListener,
            )

        }
    }
    Spacer(modifier = modifier.height(10.dp))
    Text(
        text = "${
            data.selectedDate.date.dayOfWeek.getDisplayName(TextStyle.FULL, Locale("ru"))
                .uppercase()
        } ${data.selectedDate.date.dayOfMonth}"
    )
    Spacer(modifier = modifier.height(8.dp))
    Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        if (type == CalendarActions.CONSULTATION) {
            val listAvailableTimes = getAvailableTime(data.selectedDate.date)
            Text(text = stringResource(R.string.available_times))
            if (listAvailableTimes.isNotEmpty()) {
                Column() {
                    listAvailableTimes.forEachIndexed { index, it ->
                        CalendarTimes(index = index, it.timeRange, it.doctorsFullName, onClick = {
                            onClickTimes(it)
                        })
                    }
                }
            } else {
                Text(text = stringResource(R.string.havnt_available_times))
            }

        }
        if (data.selectedDate.listEvents.isNotEmpty()) {
            DisplayContentItems(
                data, type,
                onClickPillsEvent = {
                    onClickPillsEvent(it)

                },
                onTaskNavigate = {index,route ->
                    onTaskNavigate(index,route)
                },
            )
            if (type == CalendarActions.APPOINTMENT) {
                AppointmentButtons()
            }
        } else {

            Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text(text = stringResource(R.string.nothing_is_planned))
            }
            if (type == CalendarActions.APPOINTMENT) {
                AppointmentButtons()
            }
        }
    }
}

@Composable
fun ContentItem(
    date: CalendarUiModel.Date,
    onClickListener: (CalendarUiModel.Date) -> Unit,
    modifier:Modifier = Modifier

) {
    Card(
        modifier = modifier
            .padding(vertical = 4.dp, horizontal = 4.dp)
            .clickable {
                onClickListener(date)

            }
            .border(
                0.5.dp,
                PnExpertTheme.LocalPnExpertColors.current.mainAppColors.AppGreyDarkColor, RoundedCornerShape(25)
            ),
        colors = CardDefaults.cardColors(
            containerColor = if (date.isSelected) {
                PnExpertTheme.LocalPnExpertColors.current.mainAppColors.AppBlueColor
            } else {
                Color.White
            }
        ),
    ) {
        Column(
            modifier = modifier
                .width(40.dp)
                .padding(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = date.day, // day "Mon", "Tue"
                modifier = modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = modifier.height(4.dp))
            Text(
                text = date.date.dayOfMonth.toString(), // date "15", "16"
                modifier = modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.bodyMedium,
            )
            Spacer(modifier = modifier.height(if (date.listEvents.isNotEmpty()) 4.dp else 9.dp))

            if (date.listEvents.isNotEmpty()) {
                Box(
                    modifier = modifier
                        .align(Alignment.CenterHorizontally)
                        .size(5.dp)
                        .clip(CircleShape)
                        .background(Color(76, 175, 80, 255), CircleShape)
                ) {

                }
            }


        }
    }
}

@Composable
fun AppointmentButtons(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(containerColor = PnExpertTheme.LocalPnExpertColors.current.mainAppColors.AppBlueColor)
        ) {
            Text(text = stringResource(R.string.additional_tests_btn), fontSize = 11.sp)
        }
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(containerColor = PnExpertTheme.LocalPnExpertColors.current.mainAppColors.AppBlueColor)
        ) {
            Text(text = stringResource(R.string.MRI_btn), fontSize = 11.sp)
        }
    }
}

@Composable
fun DisplayContentItems(
    data: CalendarUiModel,
    type: CalendarActions,
    onClickPillsEvent: (Int) -> Unit,
    onTaskNavigate: (Int,TestModel) -> Unit
) {
    when (type) {
        CalendarActions.APPOINTMENT -> {
            Column() {
                data.selectedDate.listEvents.forEachIndexed() { index, it ->
                    val item = it as BookingModel
                    CalendarBooking(item.time, item.title, item.status)
                }

            }
        }

        CalendarActions.EVENT -> {
            Column() {
                data.selectedDate.listEvents.forEachIndexed() { index, it ->
                    if (it.type == CalendarActions.EVENT) {
                        CalendarEvent(index = index, item = it, onClickPillsEvent = {
                            onClickPillsEvent(it)
                        }, onClickTaskEvent = {indexTask,route->
                            onTaskNavigate(indexTask,route)
                        })
                    }

                }
            }

        }

        CalendarActions.CONSULTATION -> {
            Text(text = stringResource(R.string.current_booking))
            Column() {
                data.selectedDate.listEvents.forEachIndexed() { index, it ->
                    val item = it as BookingModel
                    CalendarBooking(item.time, item.title, item.status)
                }
            }
        }

        CalendarActions.ALL -> {
            Column() {
                data.selectedDate.listEvents.forEachIndexed() { index, it ->
                    if (it.type == CalendarActions.CONSULTATION || it.type == CalendarActions.APPOINTMENT) {
                        val item = it as BookingModel
                        CalendarBooking(item.time, item.title, item.status)
                    } else {
                        CalendarEvent(index = index, item = it, onClickPillsEvent = {
                            onClickPillsEvent(it)
                        }, onClickTaskEvent = {indexTask,route->
                            onTaskNavigate(indexTask,route)
                        })
                    }

                }
            }
        }

    }
}

@Composable
fun CalendarTimes(index: Int, time: TimeRange, title: String, onClick: (Int) -> Unit, modifier:Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .clip(RoundedCornerShape(25))
            .background(
                Color.White
            )
            .padding(3.dp)
            .border(
                0.5.dp,
                PnExpertTheme.LocalPnExpertColors.current.mainAppColors.AppGreyDarkColor, RoundedCornerShape(25)
            )
            .clickable {
                onClick(index)
            },
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "${time.startTime.format(DateTimeFormatter.ofPattern("HH:mm"))} - ${
                time.endTime.format(
                    DateTimeFormatter.ofPattern("HH:mm")
                )
            } ", fontSize = 13.sp
        )
        Text(text = title, fontSize = 13.sp)
    }
    Spacer(modifier = modifier.height(10.dp))

}

@Composable
fun CalendarEvent(
    index: Int,
    item: Any,
    onClickPillsEvent: (Int) -> Unit,
    onClickTaskEvent: (Int,TestModel) -> Unit,
    modifier: Modifier = Modifier
) {
    if(item is TaskModel){
        TaskEvent(title = item.title, isCompleted = item.isCompleted , onClickTaskEvent = {
            onClickTaskEvent(index,item.route)
        })
    }else if(item is PillModel){
        PillEvent(
            index = index,
            title = item.title,
            dosage = item.dosage,
            isCompleted = item.isCompleted,
            onClick = {
                onClickPillsEvent(it)
            }
        )
    }
    Spacer(modifier = modifier.height(10.dp))

}

@Composable
fun TaskEvent(
    title: String,
    isCompleted: Boolean,
    onClickTaskEvent: () -> Unit,
    modifier:Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .clip(RoundedCornerShape(25))
            .shadow(15.dp, RoundedCornerShape(25))
            .background(
                if (isCompleted) Color(202, 255, 187, 255) else
                    Color(255, 233, 195, 255)
            )
            .border(
                0.5.dp,
                PnExpertTheme.LocalPnExpertColors.current.mainAppColors.AppGreyDarkColor, RoundedCornerShape(25)
            )
            .padding(3.dp)
            .clickable {
                onClickTaskEvent()
            }
            .zIndex(2f),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title, fontSize = 16.sp)
    }
}

@Composable
fun PillEvent(
    index: Int,
    title: String,
    dosage: String,
    isCompleted: Boolean,
    onClick: (Int) -> Unit,
    modifier:Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .clip(RoundedCornerShape(25))
            .background(
                if (isCompleted) Color(202, 255, 187, 255) else
                    Color(255, 233, 195, 255)
            )
            .border(
                0.5.dp,
                PnExpertTheme.LocalPnExpertColors.current.mainAppColors.AppGreyDarkColor, RoundedCornerShape(25)
            )
            .padding(3.dp)
            .clickable {
                if (!isCompleted) {
                    onClick(index)
                }

            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Image(
            modifier = modifier.size(40.dp),
            painter = painterResource(id = R.drawable.baseline_local_hospital_24),
            contentDescription = null
        )

        Text(text = title, fontSize = 16.sp)
        Text(text = dosage, fontSize = 16.sp)

    }
}