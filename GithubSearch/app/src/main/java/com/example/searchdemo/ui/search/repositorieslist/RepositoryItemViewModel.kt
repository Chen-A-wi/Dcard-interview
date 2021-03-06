package com.example.searchdemo.ui.search.repositorieslist

import androidx.lifecycle.MutableLiveData
import com.example.searchdemo.common.ext.formatYMD
import com.example.searchdemo.common.ext.localDateTimeFromISO
import com.example.searchdemo.common.ext.orZero
import com.example.searchdemo.data.Item
import com.example.searchdemo.ui.base.BaseViewModel
import java.text.DecimalFormat
import kotlin.math.floor
import kotlin.math.log10
import kotlin.math.pow

class RepositoryItemViewModel(itemData: Item) : BaseViewModel() {
    val title = MutableLiveData<String>(itemData.full_name.orEmpty())
    val description = MutableLiveData<String>(itemData.description.orEmpty())
    val starCount = MutableLiveData<String>(getFormatNum(itemData.stargazers_count.orZero()))
    val language = MutableLiveData<String>(itemData.language.orEmpty())
    val upLoadDate = MutableLiveData<String>(getUpLoadDate(itemData.updated_at))

    private fun getUpLoadDate(updatedAt: String?): String {
        return if (updatedAt.isNullOrEmpty()) {
            ""
        } else {
            "Updated on ${updatedAt.localDateTimeFromISO.toLocalDate().formatYMD()}"
        }
    }

    private fun getFormatNum(num: Int): String {
        val array = arrayOf(' ', 'k', 'M', 'B', 'T', 'P', 'E')
        val value = floor(log10(num.toDouble())).toInt()
        val base = value / 3
        return if (value >= 3 && base < array.size) {
            DecimalFormat("#0.0").format(num / 10.0.pow((base * 3).toDouble())) + array[base]
        } else {
            DecimalFormat("#,##0").format(num)
        }
    }
}