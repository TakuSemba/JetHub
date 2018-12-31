package com.takusemba.jethub.di

import javax.inject.Scope

/**
 * Scope of activity.
 * Annotate this for activity scoped classes.
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScope
