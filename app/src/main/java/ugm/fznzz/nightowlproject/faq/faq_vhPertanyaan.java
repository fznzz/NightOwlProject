package ugm.fznzz.nightowlproject.faq;

import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import ugm.fznzz.nightowlproject.R;

import static android.view.animation.Animation.RELATIVE_TO_SELF;

public class faq_vhPertanyaan extends GroupViewHolder {
    private TextView textView;
    private ImageView imageView;

    public faq_vhPertanyaan(View itemView) {
        super(itemView);

        textView = itemView.findViewById(R.id.textView);
        imageView = itemView.findViewById(R.id.img_arrow);
    }

    public void bind(faq_pertanyaan pertanyaan){
        textView.setText(pertanyaan.getTitle());
    }

    @Override
    public void expand() {
        animateExpand();
    }

    @Override
    public void collapse() {
        animateCollapse();
    }

    private void animateExpand() {
        RotateAnimation rotate =
                new RotateAnimation(360, 180, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        imageView.setAnimation(rotate);
    }

    private void animateCollapse() {
        RotateAnimation rotate =
                new RotateAnimation(180, 360, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        imageView.setAnimation(rotate);
    }
}

