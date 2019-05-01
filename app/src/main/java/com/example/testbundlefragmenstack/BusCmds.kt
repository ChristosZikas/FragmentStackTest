package com.example.testbundlefragmenstack

import android.support.v4.app.Fragment
import org.greenrobot.eventbus.EventBus
import java.util.*

fun getStack() = EventBus.getDefault().getStickyEvent(FragmentStack::class.java).stack

fun newFragment() = EventBus.getDefault().post(NewFragment)
object NewFragment

fun goBack() = EventBus.getDefault().post(GoBack)
object GoBack

data class FragmentStack(val stack: Stack<Fragment>)