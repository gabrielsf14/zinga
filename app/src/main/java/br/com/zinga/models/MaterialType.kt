package br.com.zinga.models

enum class MaterialType {
    PDF, VIDEO, SLIDES, OTHER;

    fun formattedName() : String {
        return when(this) {
            PDF -> "PDF"
            VIDEO -> "Vídeo"
            SLIDES -> "Slides"
            OTHER -> ""
        }
    }
}