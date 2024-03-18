package id.ac.unpas.agenda.ui.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import id.ac.unpas.agenda.models.Register

@Composable
fun RegisterItem(item: Register) {
    Row {
        Text(modifier = Modifier.weight(3f), text = item.NISN)
        Text(modifier = Modifier.weight(3f), text = item.Nama)
        Text(modifier = Modifier.weight(3f), text = item.Email)
        Text(modifier = Modifier.weight(3f), text = item.Alamat)
    }
}