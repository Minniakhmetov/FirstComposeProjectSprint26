package com.example.firstcomposeproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(stringResource(R.string.app_name))
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Color.Blue,
                            titleContentColor = Color.White
                        )
                    )
                }
            ) { paddingValues ->
                Box(
                    modifier = Modifier.padding(paddingValues)
                ) {
                    ContactDetails(
                        contact = Contact(
                            name = "Евгений",
                            surname = "Андреевич",
                            familyName = "Лукашин",
                            phone = "+7 495 495 95 95",
                            address = "г. Москва, 3-я улица Строителей, д. 25, кв. 12",
                            email = "ELukashin@practicum.ru",
                            isFavorite = true,
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun ContactDetails(contact: Contact) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.padding(vertical = 16.dp),
        ) {
            if (contact.imageRes != null) {
                Photo()
            } else {
                RoundInitials(getInitials(contact.name, contact.familyName))
            }
        }

        Row {
            Text(
                text = "${contact.name} ",
                fontWeight = FontWeight.Bold
            )
            Text(
                text = contact.surname.orEmpty(),
                fontWeight = FontWeight.Bold
            )
        }

        Row(
            modifier = Modifier.padding(bottom = 40.dp)
        ) {
            Text(
                text = contact.familyName,
                fontSize = 18.sp
            )
            if (contact.isFavorite) Image(
                modifier = Modifier
                    .padding(start = 4.dp)
                    .align(Alignment.CenterVertically),
                painter = painterResource(id = android.R.drawable.star_big_on),
                contentDescription = null
            )

        }

        InfoRow(R.string.phone, contact.phone)

        InfoRow(R.string.address, contact.address)

        if (contact.email != null) {
            InfoRow(R.string.email, contact.email)
        }
    }
}


@Composable
fun Photo() {
    Image(
        painterResource(R.drawable.img_kuzyakin),
        modifier = Modifier
            .size(200.dp, 80.dp),
        contentDescription = null,
    )
}

@Composable
fun RoundInitials(initials: String) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.circle),
            contentDescription = null,
            tint = Color.LightGray
        )
        Text(
            text = initials,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun InfoRow(parameter: Int, value: String) {
    Row(
        modifier = Modifier.padding(bottom = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(0.5f),
            text = "${stringResource(parameter)}:  ",
            textAlign = TextAlign.End,
            style = TextStyle(
                fontStyle = FontStyle.Italic
            )
        )
        Text(
            modifier = Modifier.weight(0.5f),
            text = value
        )
    }
}

private fun getInitials(name: String, familyName: String): String {
    return "${name.take(1)}${familyName.take(1)}"
}

@Preview(name = "portrait", showSystemUi = true)
@Composable
fun ContactColumnPreview() {
    ContactDetails(
        contact = Contact(
            name = "Евгений",
            surname = "Андреевич",
            familyName = "Лукашин",
            phone = "+7 495 495 95 95",
            address = "г. Москва, 3-я улица Строителей, д. 25, кв. 12",
            email = "ELukashin@practicum.ru",
            isFavorite = true,
        )
    )
}

@Preview(name = "portrait", showSystemUi = true)
@Composable
fun ContactColumnPreviewWithPhoto() {
    ContactDetails(
        contact = Contact(
            name = "Василий",
            familyName = "Кузякин",
            phone = "...",
            address = "Ивановская область, дер. Крутово, д. 4",
            imageRes = R.drawable.img_kuzyakin,
        )
    )
}
