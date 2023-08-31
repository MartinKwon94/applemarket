package com.example.applemarket

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.example.applemarket.databinding.ActivityDetailBinding
import com.google.android.material.snackbar.Snackbar
import java.text.DecimalFormat

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    private var isLike = false

    private val item: SaleItem? by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(Constants.ITEM_OBJECT, SaleItem::class.java)
        } else {
            intent.getParcelableExtra<SaleItem>(Constants.ITEM_OBJECT)
        }
    }

    private val itemPosition: Int by lazy {
        intent.getIntExtra(Constants.ITEM_INDEX, 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("asd", "itemPosition = $itemPosition")

        binding.imageView10.setImageDrawable(item?.let {
            ResourcesCompat.getDrawable(
                resources,
                it.Image,
                null
            )
        })

        binding.textView9.text = item?.SellerName
        binding.textView11.text = item?.Address
        binding.textView14.text = item?.ItemTitle
        binding.textView15.text = item?.ItemDetail
        binding.textView7.text = DecimalFormat("#,###").format(item?.Price) + "원"

        isLike = item?.isLike == true

        binding.imageView8.setImageResource(
            if (isLike) {
                R.drawable.heart__1_
            } else {
                R.drawable.heart
            }
        )

        binding.imageView11.setOnClickListener {
            exit()
        }

        binding.imageView8.setOnClickListener(R.drawable.heart__1_)
        Snackbar.make(binding.underInfo, "관심 목록에 추가되었습니다.", Snackbar.LENGTH_SHORT).show()
        isLike = false
    }


    fun exit() {
        val Intent(this, MainActivity::class.java).apply{
            putExtra("itemIndex", ItemPosition)
            putExtra("isLike", isLike)
        }
        setResult(RESULT_OK, intent)
        if (!isFinishing) finish()
    }

    override fun onBackPressed() {
        exit()
    }
}
