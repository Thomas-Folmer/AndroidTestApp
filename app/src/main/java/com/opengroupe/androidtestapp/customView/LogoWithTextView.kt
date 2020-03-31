package com.opengroupe.androidtestapp.customView

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import com.opengroupe.androidtestapp.R
import kotlinx.android.synthetic.main.layout_logo_with_text_view.view.*

/**
 * A custom view that consists of a Logo and a message
 * This custom view is used for showing "Welcome", "No Result", and "Error" message
 */
class LogoWithTextView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {


    init {
        val view =  LayoutInflater.from(context).inflate(R.layout.layout_logo_with_text_view, this)
        gravity = Gravity.CENTER
        orientation = VERTICAL
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.LogoWithTextViewAttrs)
        val backgroundId = typedArray.getResourceId(R.styleable.LogoWithTextViewAttrs_background, R.drawable.logo_welcome)
        val text = typedArray.getString(R.styleable.LogoWithTextViewAttrs_text)
        setBackground(backgroundId,view)
        setText(text!!,view)
    }

    fun setBackground(@DrawableRes resId : Int,view: View) {
        view.iv_logo.setBackgroundResource(resId)
    }

    fun setText(message : String,view: View) {
        view.tv_message.text = message
    }



}