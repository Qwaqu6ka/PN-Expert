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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import ru.fefu.calendar_impl.domain.models.TaskModel
import ru.fefu.calendar_impl.domain.models.AvailableTime
import ru.fefu.calendar_impl.domain.models.CalendarActions
import ru.fefu.calendar_impl.domain.models.CalendarUiModel
import ru.fefu.calendar_impl.R
import ru.fefu.calendar_impl.domain.models.BookingModel
import ru.fefu.calendar_impl.domain.models.BookingStatus
import ru.fefu.calendar_impl.domain.models.BookingStatusEntity
import ru.fefu.calendar_impl.domain.models.CalendarType
import ru.fefu.calendar_impl.domain.models.PillModel
import ru.fefu.calendar_impl.domain.models.TestModel
import ru.fefu.calendar_impl.domain.models.TimeRange
import ru.fefu.theme.PnExpertTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun Header(
    data: CalendarUiModel,
    onPrevClickListener: (LocalDate) -> Unit,
    onNextClickListener: (LocalDate) -> Unit,
) {
    Row {
        Text(
            text = "${
                data.startDate.date.month.getDisplayName(TextStyle.FULL, Locale("ru")).uppercase()
            } ${data.startDate.date.year}  ",
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        )
        IconButton(onClick = {
            onPrevClickListener(data.startDate.date)
        }) {
            Image(
                painter = painterResource(id = R.drawable.baseline_arrow_back_ios_new_24),
                contentDescription = null
            )
        }
        IconButton(onClick = {
            onNextClickListener(data.startDate.date)
        }) {
            Image(
                painter = painterResource(id = R.drawable.baseline_arrow_forward_ios_24),
                contentDescription = null
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarApp(
    modifier: Modifier,
    viewModel: CalendarViewModel = hiltViewModel(),
    onBackNavigate: () -> Unit,
    onTaskNavigate: (String) -> Unit,
) {
    val type = viewModel.typesCalendar[viewModel.activeType].type
    val skipPartiallyExpanded by remember { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = skipPartiallyExpanded
    )
    if (viewModel.openBottomSheetPills && viewModel.clickableEventElementIndex != null) {
        ModalComponent(
            title = stringResource(R.string.ask_pills),
            state = bottomSheetState,
            onApplyClick = {
                viewModel.onApplyBottomSheetPills()
            },
            onRejectClick = {
                viewModel.changeBottomSheetPillsState()
            },
            onDismiss = {
                viewModel.changeBottomSheetPillsState()
            }
        )
    }
    if (viewModel.openBottomSheetTimes && viewModel.clickableTimesElementIndex != null) {
        val listAvailableTimes = viewModel.getAvailableTime()
        ModalComponent(
            title = stringResource(R.string.ask_booking),
            state = bottomSheetState,
            onApplyClick = {
                viewModel.onApplyBottomSheetTimes(
                    listAvailableTimes[viewModel.clickableTimesElementIndex!!].doctorsFullName,
                    listAvailableTimes[viewModel.clickableTimesElementIndex!!].timeRange
                )
            },
            onRejectClick = {
                viewModel.changeBottomSheetTimesState()
            },
            onDismiss = {
                viewModel.changeBottomSheetTimesState()
            }
        )
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 20.dp, start = 12.dp, end = 12.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 5.dp, end = 5.dp)
                .height(40.dp)
        ) {
            IconButton(
                modifier = Modifier.align(Alignment.CenterStart),
                onClick = { onBackNavigate() }) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_arrow_back_ios_new_24),
                    contentDescription = null
                )
            }
            Text(
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = 15.sp,
                text = viewModel.typesCalendar[0].title,
                modifier = Modifier
                    .align(Alignment.Center)
                    .clickable {
                        viewModel.chooseType(0)
                        viewModel.updateUiModelData(type = CalendarActions.ALL)
                    })
        }

        Spacer(modifier = Modifier.height(10.dp))
        TypeOfCalendar(list = viewModel.typesCalendar.toList()) { index, type ->
            viewModel.chooseType(index)
            viewModel.updateUiModelData(type)
        }
        Header(
            data = viewModel.calendarUiModel,
            onPrevClickListener = {
                viewModel.onPrevMonthClickListener(it,type)
            },
            onNextClickListener = {
                viewModel.onNextMonthClickListener(it,type)
            }
        )
        Content(
            data = viewModel.calendarUiModel, onDateClickListener = { date ->
                viewModel.onDateClickListener(date)
            },
            type = type,
            onClickPillsEvent = {
                viewModel.onClickPillsEvent(it)
            },
            onClickTimes = {
                viewModel.onClickTimes(it)
            },
            getAvailableTime = {
                viewModel.getAvailableTime()
            },
            onTaskNavigate = {index,route->
                viewModel.navigateTask(index,route, onTaskNavigate = onTaskNavigate)

            }
        )
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalComponent(
    state: SheetState,
    title: String,
    onApplyClick: () -> Unit,
    onRejectClick: () -> Unit,
    onDismiss: () -> Unit
) {
    ModalBottomSheet(
        modifier = Modifier.fillMaxHeight(0.3f),
        onDismissRequest = { onDismiss() },
        sheetState = state,
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = title,
            textAlign = TextAlign.Center
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(0.5f),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = {
                    onApplyClick()
                }) {
                    Text(text = stringResource(R.string.apply))
                }
                Button(onClick = {
                    onRejectClick()
                }) {
                    Text(text = stringResource(R.string.reject))
                }
            }
        }


    }
}

//@Preview()
//@Composable
//fun CalendarAppPreview() {
//    CalendarApp(
//        modifier = Modifier.padding(16.dp),
//        onBackNavigate = {},
//        onTaskNavigate = {}
//    )
//}

@Composable
fun ContentItem(
    date: CalendarUiModel.Date,
    onClickListener: (CalendarUiModel.Date) -> Unit,

    ) {
    Card(
        modifier = Modifier
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
            modifier = Modifier
                .width(40.dp)
                .padding(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = date.day, // day "Mon", "Tue"
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = date.date.dayOfMonth.toString(), // date "15", "16"
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.bodyMedium,
            )
            Spacer(modifier = Modifier.height(if (date.listEvents.isNotEmpty()) 4.dp else 9.dp))

            if (date.listEvents.isNotEmpty()) {
                Box(
                    modifier = Modifier
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
) {

    FlowRow() {
        repeat(data.visibleDates.size - 1) {
            ContentItem(
                data.visibleDates[it],
                onDateClickListener,
            )

        }
    }
    Spacer(modifier = Modifier.height(10.dp))
    Text(
        text = "${
            data.selectedDate.date.dayOfWeek.getDisplayName(TextStyle.FULL, Locale("ru"))
                .uppercase()
        } ${data.selectedDate.date.dayOfMonth}"
    )
    Spacer(modifier = Modifier.height(8.dp))
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
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

            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text(text = stringResource(R.string.nothing_is_planned))
            }
            if (type == CalendarActions.APPOINTMENT) {
                AppointmentButtons()
            }
        }
    }
}

@Composable
fun AppointmentButtons() {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
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
                        }, onClickTaskEvent = {index,route->
                            onTaskNavigate(index,route)
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
                        }, onClickTaskEvent = {index,route->
                            onTaskNavigate(index,route)
                        })
                    }

                }
            }
        }

    }
}

@Composable
fun CalendarTimes(index: Int, time: TimeRange, title: String, onClick: (Int) -> Unit) {
    Row(
        modifier = Modifier
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
    Spacer(modifier = Modifier.height(10.dp))

}

@Composable
fun CalendarEvent(
    index: Int,
    item: Any,
    onClickPillsEvent: (Int) -> Unit,
    onClickTaskEvent: (Int,TestModel) -> Unit
) {
    if(item is TaskModel){
        TaskEvent(title = item.title, isCompleted = item.isCompleted) {
            onClickTaskEvent(index,item.route)
        }
    }else if(item is PillModel){
        PillEvent(
            index = index,
            title = item.title,
            dosage = item.dosage,
            isCompleted = item.isCompleted
        ) {
            onClickPillsEvent(it)
        }
    }
    Spacer(modifier = Modifier.height(10.dp))

}

@Composable
fun TaskEvent(
    title: String,
    isCompleted: Boolean,
    onClickTaskEvent: () -> Unit
) {
    Row(
        modifier = Modifier
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
    onClick: (Int) -> Unit
) {
    Row(
        modifier = Modifier
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
            modifier = Modifier.size(40.dp),
            painter = painterResource(id = R.drawable.baseline_local_hospital_24),
            contentDescription = null
        )

        Text(text = title, fontSize = 16.sp)
        Text(text = dosage, fontSize = 16.sp)

    }
}

@Composable
fun CalendarBooking(time: TimeRange, title: String, status: BookingStatus) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
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
                ),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${time.startTime.format(DateTimeFormatter.ofPattern("HH:mm"))} - ${
                    time.endTime.format(
                        DateTimeFormatter.ofPattern("HH:mm")
                    )
                }", fontSize = 13.sp
            )
            Text(text = title, fontSize = 13.sp)
            Image(
                painter = painterResource(
                    id =
                    if (status.type == BookingStatusEntity.CONFIRMATION) {
                        R.drawable.baseline_access_time_24
                    } else if (status.type == BookingStatusEntity.CONFIRMED) {
                        R.drawable.ic_completed_event
                    } else {
                        R.drawable.ic_notrelevant_event
                    }
                ), contentDescription = null
            )
        }
        if (status.type == BookingStatusEntity.REJECTED && status.comment != null) {
            Text(text = stringResource(R.string.comment) + "${status.comment}")
        }
        Spacer(modifier = Modifier.height(10.dp))
    }

}

@Composable
fun TypeOfCalendar(
    list: List<CalendarType>,
    onClick: (index: Int, type: CalendarActions) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 1 until list.size) {
            Box(modifier = Modifier
                .width(120.dp)
                .height(40.dp)
                .clip(RoundedCornerShape(25))
                .background(if (list[i].isSelected)  PnExpertTheme.LocalPnExpertColors.current.mainAppColors.AppBlueColor else Color.White)
                .border(
                    0.5.dp,
                    PnExpertTheme.LocalPnExpertColors.current.mainAppColors.AppGreyDarkColor, RoundedCornerShape(25)
                )
                .clickable {
                    onClick(i, list[i].type)
                }
                .padding(4.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = list[i].title,
                    fontSize = 12.sp
                )
            }
        }
    }
}