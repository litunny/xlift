package osinnowo.com.xlift.fragment;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.github.rahatarmanahmed.cpv.CircularProgressView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import osinnowo.com.xlift.R;
import osinnowo.com.xlift.adapter.RVAdapter;
import osinnowo.com.xlift.gps.GPSTracker;
import osinnowo.com.xlift.model.EtaEstimateEndpoint;
import osinnowo.com.xlift.model.Person;
import osinnowo.com.xlift.network.ApiClient;
import osinnowo.com.xlift.service.ETAService;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ETAFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ETAFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RelativeLayout mRoot;

    @BindView(R.id.rv) RecyclerView mRecyclerView;
    private ArrayList<Person> mList = new ArrayList<Person>();
    private RVAdapter MyRecyclerAdapter;
    private CircularProgressView progressView ;
    public ETAFragment() {
        // Required empty public constructor
    }

    public static ETAFragment newInstance(String param1, String param2) {
        ETAFragment fragment = new ETAFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
       // this.SetupRecyclerView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.SetupRecyclerView();
        View view =  inflater.inflate(R.layout.fragment_eta, container, false);

        mRoot = (RelativeLayout) view.findViewById(R.id.location_view);

        return view;

    }

    private void SetupRecyclerView () {
        //final LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        //layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        try{
            final LinearLayoutManager llm = new LinearLayoutManager(getContext());
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            mRecyclerView.setLayoutManager(llm);

            GPSTracker gpsTracker = new GPSTracker(getActivity().getApplicationContext());

            double Lat = gpsTracker.getLatitude();
            double Lng = gpsTracker.getLongitude();

            ETAService service = ApiClient.getClient().create(ETAService.class); //37.7833 , Double.toString(Lat)
            SharedPreferences settings = getContext().getSharedPreferences("OAUTH_STORE", 0);

            String header = "Bearer " + settings.getString("token", null);

            Call<EtaEstimateEndpoint> call = service.getData("37.7833", "-122.4167", header);

            call.enqueue(new Callback<EtaEstimateEndpoint>() {
                @Override
                public void onResponse(Response<EtaEstimateEndpoint> response, Retrofit retrofit) {
                    try{
                        EtaEstimateEndpoint Res = response.body();
                        progressView.setVisibility(View.INVISIBLE);
                        MyRecyclerAdapter = new RVAdapter(getContext(), Res.getEtaEstimates());
                        mRecyclerView.setAdapter(MyRecyclerAdapter);
                    } catch (Exception e) {
                        progressView.setVisibility(View.INVISIBLE);
                        Snackbar.make(mRoot, "The requested location is not inside a Lyft service area", Snackbar.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });
        }catch (Exception e) {

        }

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        this.progressView = (CircularProgressView) view.findViewById(R.id.progress_view);
        this.SetupRecyclerView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
