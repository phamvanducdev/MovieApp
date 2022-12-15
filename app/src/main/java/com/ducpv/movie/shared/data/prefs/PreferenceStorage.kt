package com.ducpv.movie.shared.data.prefs

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by ducpv on 09/12/2022.
 */
interface PreferenceStorage {
    fun registerOnPreferenceChangeListener(onChangeListener: SharedPreferences.OnSharedPreferenceChangeListener)
    var onboardingCompleted: Boolean
}

@Singleton
class SharedPreferenceStorage @Inject constructor(context: Context) : PreferenceStorage {

    companion object {
        const val PREFS_NAME = "movie"
        const val PREF_ONBOARDING = "pref_onboarding"
    }

    private val prefs: Lazy<SharedPreferences> = lazy {
        context.applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).apply {
            registerOnSharedPreferenceChangeListener(changeListener)
        }
    }

    private val changeListener = SharedPreferences.OnSharedPreferenceChangeListener { _, _ ->
        // TODO
    }

    override fun registerOnPreferenceChangeListener(
        onChangeListener: SharedPreferences.OnSharedPreferenceChangeListener
    ) {
        prefs.value.registerOnSharedPreferenceChangeListener(onChangeListener)
    }

    override var onboardingCompleted: Boolean
        get() = prefs.value.getBoolean(PREF_ONBOARDING, false)
        set(value) {
            prefs.value.edit { putBoolean(PREF_ONBOARDING, value) }
        }
}
