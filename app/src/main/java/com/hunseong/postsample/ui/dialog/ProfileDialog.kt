package com.hunseong.postsample.ui.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Toast
import com.hunseong.postsample.databinding.DialogProfileBinding

class ProfileDialog(
    context: Context,
    private val okCallback: (String) -> Unit,
) : Dialog(context) {

    private lateinit var binding: DialogProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() = with(binding) {
        setCancelable(false)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        profileBtn.setOnClickListener {
            if (profileEt.text.isNullOrBlank()) {
                Toast.makeText(context, "이름을 입력하세요!", Toast.LENGTH_SHORT).show()
            } else {
                okCallback(profileEt.text.toString())
                dismiss()
            }
        }
    }
}