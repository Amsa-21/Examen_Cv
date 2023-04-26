package sn.esmt.emploi.tools;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import sn.esmt.emploi.R;
import sn.esmt.emploi.httpConfig.CVsResponse;

public class MyAdapterCV extends BaseAdapter {
    private ArrayList<CVsResponse> cvs;
    private LayoutInflater myInflater;

    public MyAdapterCV(Context context, ArrayList<CVsResponse> cvs)
    {
        this.myInflater = LayoutInflater.from(context);
        this.cvs = cvs;
    }
    @Override
    public int getCount() {
        return this.cvs.size();
    }

    @Override
    public Object getItem(int i) {
        return this.cvs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public static class ViewHolder {
        TextView textemail;
        TextView textprenom;
        TextView textnom;
        TextView textspecialite;
        TextView textniveau;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        ViewHolder holder;

        if (view == null)
        {
            view = myInflater.inflate(R.layout.list_cv, null);
            holder = new ViewHolder();

            holder.textemail = (TextView) view.findViewById(R.id.txtemail);
            holder.textnom = (TextView) view.findViewById(R.id.txtnom);
            holder.textprenom = (TextView) view.findViewById(R.id.txtprenom);
            holder.textniveau = (TextView) view.findViewById(R.id.txtniveau);
            holder.textspecialite = (TextView) view.findViewById(R.id.txtspecialite);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.textemail.setText(cvs.get(i).getEmail());
        holder.textnom.setText(cvs.get(i).getNom());
        holder.textprenom.setText(cvs.get(i).getPrenom());
        holder.textniveau.setText(cvs.get(i).getNiveauEtude());
        holder.textspecialite.setText(cvs.get(i).getSpecialite());

        return view;
    }

}
