package ugm.fznzz.nightowlproject.faq;

import android.view.View;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

import ugm.fznzz.nightowlproject.R;

public class faq_vhJawaban extends ChildViewHolder {
    private TextView textView;

    public faq_vhJawaban(View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.textView);
    }

    public void bind(faq_jawaban jawaban){
        textView.setText(jawaban.name);
    }
}
