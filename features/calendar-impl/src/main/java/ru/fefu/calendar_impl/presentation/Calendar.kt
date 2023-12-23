package ru.fefu.calendar_impl.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.fefu.calendar_impl.domain.models.CalendarActions
import ru.fefu.calendar_impl.domain.models.CalendarUiModel
import ru.fefu.calendar_impl.R
import ru.fefu.calendar_impl.domain.models.BookingStatus
import ru.fefu.calendar_impl.domain.models.BookingStatusEntity
import ru.fefu.calendar_impl.domain.models.CalendarType
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
    modifier: Modifier = Modifier
) {
    Row {
        Text(
            text = "${
                data.startDate.date.month.getDisplayName(TextStyle.FULL, Locale(stringResource(R.string.ru_locale))).uppercase()
            } ${data.startDate.date.year}  ",
            modifier = modifier
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
fun CalendarScreen(
    modifier: Modifier = Modifier,
    viewModel: CalendarViewModel = hiltViewModel(),
    onBackNavigate: () -> Unit,
    onTaskNavigate: (String) -> Unit,
) {
    val type = viewModel.typesCalendar[viewModel.indexOfActiveType].type
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
                        viewModel.chooseTypeCalendar(0)
                        viewModel.updateUiModelData(type = CalendarActions.ALL)
                    })
        }

        Spacer(modifier = Modifier.height(10.dp))
        TypeOfCalendar(list = viewModel.typesCalendar.toList(), onClick = { index, type ->
            viewModel.chooseTypeCalendar(index)
            viewModel.updateUiModelData(type)
        })
        Header(
            data = viewModel.calendarState,
            onPrevClickListener = {
                viewModel.onPrevMonthClickListener(it,type)
            },
            onNextClickListener = {
                viewModel.onNextMonthClickListener(it,type)
            }
        )
        Content(
            data = viewModel.calendarState, onDateClickListener = { date ->
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
    onDismiss: () -> Unit,
    modifier:Modifier = Modifier
) {
    ModalBottomSheet(
        modifier = modifier.fillMaxHeight(0.3f),
        onDismissRequest = { onDismiss() },
        sheetState = state,
    ) {
        Text(
            modifier = modifier.fillMaxWidth(),
            text = title,
            textAlign = TextAlign.Center
        )
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = modifier.fillMaxWidth(0.5f),
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

@Composable
fun CalendarBooking(time: TimeRange, title: String, status: BookingStatus,modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
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
        Spacer(modifier = modifier.height(10.dp))
    }

}

@Composable
fun TypeOfCalendar(
    list: List<CalendarType>,
    onClick: (index: Int, type: CalendarActions) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 1 until list.size) {
            Box(modifier = modifier
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