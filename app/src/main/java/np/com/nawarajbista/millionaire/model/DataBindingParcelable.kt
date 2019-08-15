package np.com.nawarajbista.millionaire.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataBindingParcelable(val level: Int, val q: String?, val options: List<String>): Parcelable