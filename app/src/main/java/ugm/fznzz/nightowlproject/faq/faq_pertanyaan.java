package ugm.fznzz.nightowlproject.faq;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class faq_pertanyaan extends ExpandableGroup<faq_jawaban> {
    public faq_pertanyaan(String title, List<faq_jawaban> items) {
        super(title, items);
    }
}
