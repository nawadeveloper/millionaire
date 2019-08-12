package np.com.nawarajbista.millionaire.viewmodel

import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@Parcelize
data class FinalResult(var username: String, var message: String, var win: Int): Parcelable