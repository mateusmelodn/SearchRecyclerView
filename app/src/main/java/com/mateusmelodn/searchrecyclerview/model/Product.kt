package com.mateusmelodn.searchrecyclerview.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Product(
    var id: String,
    var name: String,
    var value: String
    ): Parcelable