package ru.nb.mish.photostudioex.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties


@JsonIgnoreProperties(ignoreUnknown = true) // игнорируем ненужные параметры с сервера
class PhotoCategory() {

    lateinit var images: Array<String>


}