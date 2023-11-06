package com.example.calendar_impl.presentation

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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.calendar_impl.R
import com.example.calendar_impl.domain.models.CalendarUiModel
import com.example.calendar_impl.domain.models.CalendarType
import com.example.calendar_impl.domain.models.ActionModel
import com.example.calendar_impl.domain.models.AvailableTime
import com.example.calendar_impl.domain.models.BookingModel
import com.example.calendar_impl.domain.models.BookingStatus
import com.example.calendar_impl.domain.models.BookingStatusEntity
import com.example.calendar_impl.domain.models.CalendarActions
import com.example.calendar_impl.domain.models.EventType
import com.example.calendar_impl.domain.models.TimeRange
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
            onNextClickListener(data.endDate.date)
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
//    val dataSource = CalendarDataSource(viewModel.calendarRepository)
//    val type = viewModel.typesCalendar[viewModel.activeType.value].type
//    // we use mutableStateOf and remember inside composable function to schedules recomposition
//    var calendarUiModel by remember {
//        mutableStateOf(
//            dataSource.getData(
//                lastSelectedDate = dataSource.today,
//                typeCalendar = type
//            )
//        )
//    }
    val dataSource = viewModel.calendarDataSource
    val type = viewModel.typesCalendar[viewModel.activeType.intValue].type
    // we use mutableStateOf and remember inside composable function to schedules recomposition
    val calendarUiModel = viewModel.calendarUiModel.value
    val clickableEventElementIndex = viewModel.clickableEventElementIndex.value
    val clickableTimesElementIndex = viewModel.clickableTimesElementIndex.value
    val openBottomSheetPills = viewModel.openBottomSheetPills.value
    val openBottomSheetTimes = viewModel.openBottomSheetTimes.value
    val skipPartiallyExpanded by remember { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = skipPartiallyExpanded
    )
    val selectedDate = viewModel.selectedDate.value
//    var selectedDate by remember {
//        mutableStateOf(calendarUiModel.selectedDate.date)
//    }
//
//
//    var clickableEventElementIndex by remember { mutableStateOf<Int?>(null)}
//    var clickableTimesElementIndex by remember { mutableStateOf<Int?>(null)}
//    var openBottomSheetPills by rememberSaveable { mutableStateOf(false) }
//    var openBottomSheetTimes by rememberSaveable { mutableStateOf(false) }
//    var skipPartiallyExpanded by remember { mutableStateOf(false) }
//    var edgeToEdgeEnabled by remember { mutableStateOf(false) }
//    val bottomSheetState = rememberModalBottomSheetState(
//        skipPartiallyExpanded = skipPartiallyExpanded
//    )
    if (openBottomSheetPills && clickableEventElementIndex != null) {
        ModalComponent(
            title = "Вы принимали лекарства сегодня?",
            state = bottomSheetState,
            onApplyClick = {
                viewModel.openBottomSheetPills.value = false
                viewModel.calendarUiModel.value.selectedDate.listEvents.set(
                    clickableEventElementIndex,
                    (calendarUiModel.selectedDate.listEvents[clickableEventElementIndex] as ActionModel).copy(
                        isCompleted = true
                    )
                )
            },
            onRejectClick = {
                viewModel.openBottomSheetPills.value = false
            },
            onDismiss = {
                viewModel.openBottomSheetPills.value = false
            }
        )
    }
    if (openBottomSheetTimes && clickableTimesElementIndex!=null ) {
        val listAvailableTimes = viewModel.getAvailableTime(selectedDate)

        ModalComponent(
            title = "Вы действительно хотите записаться на это время?",
            state = bottomSheetState,
            onApplyClick = {
                viewModel.openBottomSheetTimes.value = false
                if(clickableTimesElementIndex != null){
                    viewModel.calendarUiModel.value.selectedDate.listEvents.add(
                        BookingModel(
                            title = listAvailableTimes[clickableTimesElementIndex].doctorsFullName,
                            status = BookingStatus(
                                type = BookingStatusEntity.CONFIRMATION,
                                comment = null
                            ),
                            time = listAvailableTimes[clickableTimesElementIndex].timeRange,
                            type = CalendarActions.CONSULTATION
                        )
                    )
                }

            },
            onRejectClick = {
                viewModel.openBottomSheetTimes.value = false
            },
            onDismiss = {
                viewModel.openBottomSheetTimes.value = false
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
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(start = 5.dp, end = 5.dp)
            .height(40.dp)) {
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
                        val finalStartDate = calendarUiModel.startDate.date
                        viewModel.calendarUiModel.value = dataSource.getData(
                            startDate = finalStartDate,
                            lastSelectedDate = viewModel.calendarUiModel.value.selectedDate.date,
                            typeCalendar = CalendarActions.ALL
                        )
                    })
        }

        Spacer(modifier = Modifier.height(10.dp))
        TypeOfCalendar(list = viewModel.typesCalendar.toList()) { index, type ->
            viewModel.chooseType(index)
            val finalStartDate = calendarUiModel.startDate.date
            viewModel.calendarUiModel.value = dataSource.getData(
                startDate = finalStartDate,
                lastSelectedDate = viewModel.calendarUiModel.value.selectedDate.date,
                typeCalendar = type
            )
        }
        Header(
            data = calendarUiModel,
            onPrevClickListener = { startDate ->
                // refresh the CalendarUiModel with new data
                // by get data with new Start Date (which is the startDate-1 from the visibleDates)
                val finalStartDate = startDate.minusMonths(1).withDayOfMonth(1)
                viewModel.calendarUiModel.value = dataSource.getData(
                    startDate = finalStartDate,
                    lastSelectedDate = viewModel.calendarUiModel.value.selectedDate.date,
                    typeCalendar = type
                )

            },
            onNextClickListener = { endDate ->
                // refresh the CalendarUiModel with new data
                // by get data with new Start Date (which is the endDate+2 from the visibleDates)
                val finalStartDate = endDate.plusMonths(1).withDayOfMonth(1)
                viewModel.calendarUiModel.value = dataSource.getData(
                    startDate = finalStartDate,
                    lastSelectedDate = viewModel.calendarUiModel.value.selectedDate.date,
                    typeCalendar = type
                )
            }
        )
        Content(
            data = calendarUiModel, onDateClickListener = { date ->
                // refresh the CalendarUiModel with new data
                // by changing only the selectedDate with the date selected by User
                viewModel.calendarUiModel.value = calendarUiModel.copy(
                    selectedDate = date,
                    visibleDates = calendarUiModel.visibleDates.map {
                        it.copy(
                            isSelected = it.date.isEqual(date.date)
                        )
                    }
                )
                viewModel.selectedDate.value = date.date
            },
            type = type,
            onClickPillsEvent = {
                viewModel.openBottomSheetPills.value = true
                viewModel.clickableEventElementIndex.value = it
            },
            onClickTimes ={
                viewModel.openBottomSheetTimes.value = true
                viewModel.clickableTimesElementIndex.value = it
            },
            getAvailableTime = {
                viewModel.getAvailableTime(it)
            },
            onTaskNavigate = { index , str ->
                viewModel.calendarUiModel.value.selectedDate.listEvents.set(index,
                    (calendarUiModel.selectedDate.listEvents[index] as ActionModel).copy(
                        isCompleted = false
                    )
                )
                onTaskNavigate(str)
            }
        )
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalComponent(state:SheetState,title:String,onApplyClick:()->Unit,onRejectClick:()->Unit,onDismiss:()->Unit){
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
                    Text(text = "Да")
                }
                Button(onClick = {
                    onRejectClick()
                }) {
                    Text(text = "Нет")
                }
            }
        }


    }
}

@Preview()
@Composable
fun CalendarAppPreview() {
    CalendarApp(
        modifier = Modifier.padding(16.dp),
        onBackNavigate = {},
        onTaskNavigate = {}
    )
}

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
                Color(182, 182, 182, 255), RoundedCornerShape(25)
            ),
        colors = CardDefaults.cardColors(
            // background colors of the selected date
            // and the non-selected date are different
            containerColor = if (date.isSelected) {
                Color(104, 186, 250, 255)
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
    onTaskNavigate: (Int,String) -> Unit,
    onClickTimes:(Int)->Unit,
) {

    FlowRow() {
        repeat(data.visibleDates.size - 1) {
            ContentItem(
                data.visibleDates[it],
                onDateClickListener,
            )

        }
    }

//    LazyVerticalGrid(
//        modifier = Modifier.fillMaxHeight(0.4f),
//        columns = GridCells.Fixed(7),
//
//        ) {
//
//    }
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
            Text(text = "Доступные записи")
            if(listAvailableTimes.isNotEmpty()){
                Column() {
                    listAvailableTimes.forEachIndexed {index , it->
                        CalendarTimes(index = index, it.timeRange, it.doctorsFullName,onClick={
                            onClickTimes(it)
                        })
                    }
                }
            }else{
                Text(text = "Нет доступных записей")
            }

        }
        if (data.selectedDate.listEvents.isNotEmpty()) {
            DisplayContentItems(data, type, onClickPillsEvent = {
                onClickPillsEvent(it)

            },
                onTaskNavigate = {index ,str ->
                    onTaskNavigate(index,str)
                })
            if (type == CalendarActions.APPOINTMENT) {
                AppointmentButtons()
            }
        } else {

            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text(text = "Ничего не запланировано")
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
            colors = ButtonDefaults.buttonColors(containerColor = Color(104, 186, 250, 255))
        ) {
            Text(text = "Назначение врача на дополнительный анализы", fontSize = 11.sp)
        }
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(containerColor = Color(104, 186, 250, 255))
        ) {
            Text(text = "Записаться на МРТ", fontSize = 11.sp)
        }
    }
}

@Composable
fun DisplayContentItems(
    data: CalendarUiModel,
    type: CalendarActions,
    onClickPillsEvent: (Int) -> Unit,
    onTaskNavigate: (Int,String) -> Unit,
) {
    when (type) {
        CalendarActions.APPOINTMENT -> {
            Column() {
                data.selectedDate.listEvents.forEachIndexed() { index, it ->
                    val item = it as BookingModel
                    CalendarBooking(item.time, item.title,item.status)
                }

            }


        }

        CalendarActions.EVENT -> {
            Column() {
                data.selectedDate.listEvents.forEachIndexed() { index, it ->
                    if (it.type == CalendarActions.EVENT) {
                        val item = it as ActionModel
                        CalendarEvent(index = index,item = item, onClickPillsEvent = {
                            onClickPillsEvent(it)
                        }, onClickTaskEvent = {
                            onTaskNavigate(it,item.option)
                        })
                    }

                }
            }

        }

        CalendarActions.CONSULTATION -> {
//            val listAvailableTimes = getAvailableTime(data.selectedDate.date)
//            Text(text = "Доступные записи")
//            if(listAvailableTimes.isNotEmpty()){
//                Column() {
//                    listAvailableTimes.forEachIndexed() { index, it ->
//                        CalendarTimes(index = index ,time = it.timeRange, title = it.doctorsFullName){
//                            val item = BookingModel(
//                                status = BookingStatus(
//                                    type = BookingStatusEntity.CONFIRMED,
//                                    comment = null
//                                ),
//                                title = it.doctorsFullName,
//                                time = it.timeRange,
//                                type = CalendarActions.CONSULTATION
//                            )
//                            onClickAvailableTime(item)
//                        }
//                    }
//                }
//            }else{
//                Text(text = "Нет доступных записей")
//            }
            Text(text = "Текущие записи")
            Column() {
                data.selectedDate.listEvents.forEachIndexed() { index, it ->
                    val item = it as BookingModel
                    CalendarBooking(item.time, item.title,item.status)
                }
            }
        }

        CalendarActions.ALL -> {
            Column() {
                data.selectedDate.listEvents.forEachIndexed() { index, it ->
                    if (it.type == CalendarActions.CONSULTATION || it.type == CalendarActions.APPOINTMENT) {
                        val item = it as BookingModel
                        CalendarBooking(item.time, item.title,item.status)
                    } else {
                        val item = it as ActionModel
                        CalendarEvent(index = index,item = item, onClickPillsEvent = {
                            onClickPillsEvent(it)
                        }, onClickTaskEvent = {
                            item.option
                        })
                    }

                }
            }
        }

    }
}

@Composable
fun CalendarTimes(index:Int,time: TimeRange,title:String,onClick:(Int)->Unit){
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
                Color(182, 182, 182, 255), RoundedCornerShape(25)
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
fun CalendarEvent(index:Int ,item: ActionModel, onClickPillsEvent: (Int) -> Unit, onClickTaskEvent: (Int) -> Unit) {

    if (item.event == EventType.PILL) {

        PillEvent(index = index ,title = item.title, dosage = item.option,isCompleted = item.isCompleted) {
            onClickPillsEvent(it)
        }
    } else {
        TaskEvent(title = item.title, isCompleted = item.isCompleted) {
            onClickTaskEvent(index)
        }
    }
    Spacer(modifier = Modifier.height(10.dp))

}

@Composable
fun TaskEvent(title: String, isCompleted: Boolean, onClickTaskEvent: () -> Unit) {
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
                Color(182, 182, 182, 255), RoundedCornerShape(25)
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
fun PillEvent(index:Int,title: String, dosage: String,isCompleted: Boolean, onClick: (Int) -> Unit) {
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
                Color(182, 182, 182, 255), RoundedCornerShape(25)
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
fun CalendarBooking(time: TimeRange, title: String,status:BookingStatus) {
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
                    Color(182, 182, 182, 255), RoundedCornerShape(25)
                ),
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
            Image(painter = painterResource(id =
            if(status.type == BookingStatusEntity.CONFIRMATION){
                R.drawable.baseline_access_time_24
            }else if(status.type == BookingStatusEntity.CONFIRMED){
                R.drawable.ic_completed_event
            }else{
                R.drawable.ic_notrelevant_event
            }
            ) ,contentDescription = null)
        }
        if(status.type == BookingStatusEntity.REJECTED && status.comment != null){
            Text(text = "Комментарий: ${status.comment}")
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
                .background(if (list[i].isSelected) Color(104, 186, 250, 255) else Color.White)
                .border(
                    0.5.dp,
                    Color(182, 182, 182, 255), RoundedCornerShape(25)
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