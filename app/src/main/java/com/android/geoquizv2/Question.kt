package com.android.geoquizv2

import androidx.annotation.StringRes

/*      Аннотация @StringRes (не является обязательной) помогает инспектору кода, встроенному в Android Studio (Lint), проверить во время компиляции,
        что вызовы конструктора предоставляют допустимый строковый идентификатор ресурса.
        Также, аннотация делает код более читабельным для других разработчиков.*/
data class Question (@StringRes val textResId: Int, val answer: Boolean)