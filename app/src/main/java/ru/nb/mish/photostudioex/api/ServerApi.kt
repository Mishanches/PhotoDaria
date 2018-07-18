package ru.nb.mish.photostudioex.api

import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url
import ru.nb.mish.photostudioex.model.PhotoCategory

interface ServerApi {
    @GET("{player_id}/")

    fun loadPhotoCategory(@Path("player_id")player_id:Int=1,
                          @Query("format") format: String="json"): Deferred<PhotoCategory>
    //@Path - подменяет в самом запросе инф
    // @Path("player_id")player_id:Int=1 - заменяем player_id на тот, который придет (по умолчанию стоит - 6 (6ой альбом))

}