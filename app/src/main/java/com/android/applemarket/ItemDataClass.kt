package com.android.applemarket

import android.os.Parcelable
import androidx.versionedparcelable.VersionedParcelable
import androidx.versionedparcelable.VersionedParcelize
import kotlinx.parcelize.Parcelize


//사진,이름,데이터내용,셀러이름,가격,주소,좋아요수,채팅수
//리소스 아이디가 int 값이기 때문에 aIcon:Int 임.

@Parcelize
data class MarketItem (
    val aIcon:Int,
    val aTitle:String,
    val aData:String,
    val aSeller:String,
    val aPrice:Int,
    val aAddress:String,
    var aLikeCount:Int,
    val aChatting:Int,
    var aLike: Boolean,
    ) : Parcelable

