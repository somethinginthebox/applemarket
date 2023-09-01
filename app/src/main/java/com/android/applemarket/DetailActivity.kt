package com.android.applemarket

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.core.content.res.ResourcesCompat
import com.android.applemarket.databinding.ActivityDetailBinding
import com.google.android.material.snackbar.Snackbar
import java.text.DecimalFormat

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private var aLike = false


    private val item: MarketItem? by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(Constants.ITEM_OBJECT, MarketItem::class.java)
        } else{
        intent.getParcelableExtra<MarketItem>(Constants.ITEM_OBJECT)
    }
}

    private val itemPosition: Int by lazy {
        intent.getIntExtra(Constants.ITEM_INDEX, 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("check_1", "itemPosition = $itemPosition")
        item?.let { marketItem ->
            binding.detailFirstItem.setImageDrawable(
                ResourcesCompat.getDrawable(
                    resources,
                    marketItem.aIcon,
                    null
                )
            )
        }

        binding.detailSellerName.text = item?.aSeller
        binding.detailAddress.text = item?.aAddress
        binding.detailItemName.text = item?.aTitle
        binding.detailData.text = item?.aData
        binding.detailPrice.text = DecimalFormat("#,###").format(item?.aPrice) + "원"

        aLike = item?.aLike == true

        binding.detailLike.setImageResource(
            if (aLike) {
                R.drawable.icon_heart_f
            } else {
                R.drawable.icon_heart_b
            }
        )
        binding.detailBack.setOnClickListener {
            exit()
        }

        binding.detailLinearView.setOnClickListener {
            if (!aLike) {
                binding.detailLike.setImageResource(R.drawable.icon_heart_f)
                Snackbar.make(binding.constraintLayout3, "관심목록에 추가되었습니다", Snackbar.LENGTH_SHORT)
                    .show()
                aLike = true
            } else {
                binding.detailLike.setImageResource(R.drawable.icon_heart_b)
                aLike = false
            }
        }
    }

    fun exit() {
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("itemIndex", itemPosition)
            putExtra("aLike", aLike)
        }

        setResult(RESULT_OK, intent)
        if (!isFinishing) finish()
    }

    override fun onBackPressed() {
        exit()
    }
}




