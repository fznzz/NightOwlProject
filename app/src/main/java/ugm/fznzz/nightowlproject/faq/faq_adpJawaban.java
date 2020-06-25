package ugm.fznzz.nightowlproject.faq;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

import ugm.fznzz.nightowlproject.R;

public class faq_adpJawaban extends ExpandableRecyclerViewAdapter<faq_vhPertanyaan,faq_vhJawaban> {
    public faq_adpJawaban(List<? extends ExpandableGroup> groups) {
        super(groups);
    }

    @Override
    public faq_vhPertanyaan onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.faq_expandable_pertanyaan,parent,false);
        return new faq_vhPertanyaan(v);
    }

    @Override
    public faq_vhJawaban onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.faq_expandable_jawaban,parent,false);
        return new faq_vhJawaban(v);
    }

    @Override
    public void onBindChildViewHolder(faq_vhJawaban holder, int flatPosition, ExpandableGroup group, int childIndex) {
        final faq_jawaban jawaban = (faq_jawaban) group.getItems().get(childIndex);
        holder.bind(jawaban);
    }

    @Override
    public void onBindGroupViewHolder(faq_vhPertanyaan holder, int flatPosition, ExpandableGroup group) {
        final faq_pertanyaan pertanyaan = (faq_pertanyaan) group;
        holder.bind(pertanyaan);
    }
}
