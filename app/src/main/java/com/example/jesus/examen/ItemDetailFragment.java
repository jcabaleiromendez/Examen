package com.example.jesus.examen;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.jesus.examen.dummy.DummyContent;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private DummyContent.DummyItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.content);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_item_detail, container, false);

        Button button = (Button) rootView.findViewById(R.id.button);//Recogemos el boton del fragment
        button.setOnClickListener(new View.OnClickListener() {//Enlazamos el onClickListener
            @Override
            public void onClick(View v) {
                //damos la accion al boton
                ItemListFragment frag = (ItemListFragment) getFragmentManager().findFragmentById(android.R.id.item_list);//Recogemos el fragment de la lista
                if (frag == null || !frag.isInLayout()) {//Si el fragment NO esta cargado en pantalla
                    Intent intentResultado = new Intent();//Declaramos el intent
                    intentResultado.putExtra("resultado", "Activity Cerrada");//Informacion del intent
                    getActivity().setResult(Activity.RESULT_OK, intentResultado);//Cargamos el resultado
                    getActivity().finish();//Cerramos la activity

                } else {//en caso contrario
                    ((TextView) rootView.findViewById(R.id.item_detail)).setText("");//ponemos el texto del frag item_detail en blanco
                }
            }
        });//Fin del onClick
        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.item_detail)).setText(mItem.details);
        }

        return rootView;
    }
}