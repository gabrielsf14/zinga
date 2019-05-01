package br.com.zinga.models

enum class MaterialType {
    PDF, VIDEO;

    fun formattedName() : String {
        return when(this) {
            PDF -> "PDF"
            VIDEO -> "Vídeo"
        }
    }
}