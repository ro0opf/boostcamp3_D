package com.teamdonut.eatto.common;

import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.teamdonut.eatto.R;
import com.teamdonut.eatto.common.util.GlideApp;

import java.util.List;

public class Binding {
    @BindingAdapter("adapter")
    public static void setAdapter(RecyclerView recyclerView, RecyclerView.Adapter<?> adapter) {
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter({"imageUrl", "imageError"})
    public static void setImageFromUrl(ImageView view, String imageUrl, Drawable imageError) {
        GlideApp.with(view)
                .load(imageUrl)
                .error(imageError)
                .into(view);
    }

    @BindingAdapter({"roundedImageUrl", "roundedImageError"})
    public static void setRoundedImageFromUrl(ImageView view, String imageUrl, Drawable imageError) {
        GlideApp.with(view)
                .load(imageUrl)
                .transform(new RoundedCorners(10))
                .error(imageError)
                .into(view);
    }

    @BindingAdapter("items")
    public static void setItems(RecyclerView recyclerView, List<?> items) {
        BaseRecyclerViewAdapter adapter = (BaseRecyclerViewAdapter) recyclerView.getAdapter();

        if (adapter != null && items != null) {
            adapter.updateItems(items);
        }
    }

    @BindingAdapter({"highlightNumber", "mainText", "spannableFront"})
    public static void setSpannableText(TextView view, int number, String mainText, boolean isLocatedFront) {
        if (number >= 0) {
            String numberText = String.valueOf(number);
            SpannableStringBuilder sb = new SpannableStringBuilder();

            if (isLocatedFront) {
                sb.append(numberText);
                sb.append(mainText);

                sb.setSpan(new ForegroundColorSpan(ContextCompat.getColor(view.getContext(), R.color.colorHungryRed))
                        , 0, numberText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            } else {
                sb.append(mainText);
                sb.append(numberText);

                sb.setSpan(new ForegroundColorSpan(ContextCompat.getColor(view.getContext(), R.color.colorHungryRed))
                        , mainText.length(), mainText.length() + numberText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }

            view.setText(sb, TextView.BufferType.SPANNABLE);
        }
    }
}
