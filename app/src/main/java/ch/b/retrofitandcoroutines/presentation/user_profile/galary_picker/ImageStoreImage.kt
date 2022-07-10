package ch.b.retrofitandcoroutines.presentation.user_profile.galary_picker

import android.net.Uri
import android.os.Parcel
import android.os.Parcelable
import java.util.*

open class MediaStoreImage(
    private val id: Long,
    private val name: String?,
    private val dateAdded: Date,
    private val uri: Uri?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString(),
        parcel.readValue(Date::class.java.classLoader) as Date,
        parcel.readParcelable(Uri::class.java.classLoader) as? Uri
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(name)
        parcel.writeValue(dateAdded)
        parcel.writeParcelable(uri, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MediaStoreImage> {
        override fun createFromParcel(parcel: Parcel): MediaStoreImage {
            return MediaStoreImage(parcel)
        }

        override fun newArray(size: Int): Array<MediaStoreImage?> {
            return arrayOfNulls(size)
        }
    }

    open fun mapId() : Long{
        return id
    }

    open fun mapUri() : Uri {
        return uri!!
    }


}