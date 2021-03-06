package com.cyut.motor.s176;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;
import com.cyut.motor.Adapter.BaseRecyclerAdapter;
import com.cyut.motor.Adapter.SmartViewHolder;
import com.cyut.motor.R;
import com.cyut.motor.s176.pickview.CharacterPickerView;
import com.cyut.motor.s176.pickview.OnOptionChangedListener;

import java.util.Arrays;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;


/**
 * Created by aunt on 2017/8/24.
 */

public class HelpFragment extends Fragment implements AdapterView.OnItemClickListener{
    BaseRecyclerAdapter baseRecyclerAdapter;
    private enum Item {
        台北市(""),
        桃園市(""),
        新竹縣(""),
        新竹市(""),
        苗栗縣(""),
        台中市(""),
        彰化縣(""),
        雲林縣(""),
        南投縣(""),
        嘉義縣(""),
        嘉義市(""),
        台南市(""),
        高雄市(""),
        屏東市(""),
        宜蘭市(""),
        花蓮市(""),
        台東市(""),
        澎湖縣(""),
        金門縣(""),
        媽祖("");
        public String name;
        Item(String name) {
            this.name = name;
        }
    }

    //台北市
    private String[] a ={"點選立即撥打電話"," 02-28370460,台北市士林區士東路250號1樓,良陽國際有限公司","02-2838-3610,台北市士林區中山北路六段14號,震達車業有限公司","02-28341376,台北市士林區文林路623號,泰成機車行","02-28341376 ,台北市士林區文林路679號,富偉機車行","02-2861-6117,台北市士林區光華路24號,新鎰昌車業行","02-2812-6365,台北市士林區延平北路5段220號1樓,力泰車業有限公司","02-28119912,台北市士林區延平北路5段41號1樓,日輝車業有限公司","02-2812-2405,台北市士林區延平北路五段127-129號,宏國車業有限公司","02-2831-3711,台北市士林區芝山岩至誠路1段2-6號,新鎰昌車業行","02-28127500,台北市士林區重慶北路4段244號1樓,益隆車業有限公司","02-8811-3919,台北市士林區重慶北路四段232號,福元機車有限公司","02-2832-8781,台北市士林區德行東路100號,坤鴻機車行","02-2553-1543,台北市大同區民權西路270-1號,健程機車行","02-25523055 ,台北市大同區西寧北路17之4號,忠億車業有限公司"};
    //桃園市
    private String[] b ={"點選立即撥打電話"," 03-350-0834 ,桃園市三民路三段563號,全營機車行","03-369-7557,桃園市上海路99號,合興機車行","03-3579616,桃園市大興路169號,大鑫機車行","03-334-6073,桃園市中山北路62號,任泰商行","03-3381739 ,桃園市中山路207號,通台輪業有限公司","03-3356566,桃園市中山路410號,進興輪業有限公司","03-360-2490,桃園市中山路916號,宏凱機車行","03-3615983,桃園市介壽路479號,松輪機車有限公司","03-3629643,桃園市介壽路53號,正漢機車行","03-3623609,桃園市民生路399號,富國機業有限公司"};
    //新竹縣
    private String[] c ={"點選立即撥打電話"," 03-522-1083 ,新竹縣北埔鄉水?子1號,全營機車行","03-555-2238,新竹縣竹北市三民路188號,新盛輪業","03-556-1104,新竹縣竹北市大義里45-16號,佳昌機車行","03-5552151,新竹縣竹北市中山路493號,雲集輪業有限公司","03-5552467 ,新竹縣竹北市中山路543號,茂泰輪業有限公司","03-5558932,新竹縣竹北市中正東路65號,弘達機車行","03-5512091,新竹縣竹北市中正東路72號,向陽車業行","03-555-3937,新竹縣竹北市中華路342號,達成機車行","03-555-2699,新竹縣竹北市中華路357號,力達機車行","03-5553075,新竹縣竹北市中華路47號,春信輪業行"};
    //新竹市
    private String[] d ={"點選立即撥打電話"," 03-522-1083 ,新竹市中山路1鄰265號,全營機車行","03-523-8700,新竹市中華路2段566號,聯合機車行","03-532-1648 ,新竹市中華路一段232號,銘祥機車行","03-530-1763,新竹市中華路五段327號,日鈴機車行","03-534-6566,新竹市北門里中正路241號,凱竣車業有限公司","03-522-6035 ,新竹市北門街115號,大葉機車行","03-5244485 ,新竹市北區中山路397號,凱利機車行","03-5335589,新竹市北區中正路369號,慶陽機車行","03-5363573,新竹市北區天府路2段33號,進福機車行","03-5252298 ,新竹市北區北大路451號,松泰車業有限公司"};
    //苗栗縣
    private String[] e ={"點選立即撥打電話","037-877605 ,苗栗縣三義鄉中正路106號,信益機車行","037-881-037,苗栗縣三義鄉鯉魚口18-1號,坤勝機車行","037-831-038,苗栗縣三灣鄉中正路198號,全輪機車行","037-322-657 ,苗栗縣中山北路247號,協泰機車行","037-222-339,苗栗縣公館鄉大同路92號,和泰機車行","037-225767,苗栗縣公館鄉通明街39號,德明機車行","037-474-700 ,苗栗縣竹南鎮山佳里光復路299號,大明輪業商行","037-461-862,苗栗縣竹南鎮中美里西門路17-5號,苗栗縣竹南鎮中美里西門路17-5號","037-581-746,苗栗縣竹南鎮公義路903號,建昌機車行","037-474-693,苗栗縣竹南鎮光復路316號,通益機車行"};
    //台中市
    private String[] f ={"點選立即撥打電話"," 03-522-1083 ,台中市大甲區三民路110號,全營機車行","04-26980057,台中市大肚區沙田路2段752號,志翔車業行","04-2493-2341,台中市大里區中興路1段292-5號,坤昇機車行","04-24951666,台中市大里區金城里塗城路622號,宏勝機車行","04-2393-3672,台中市太平區大源路22號,瑞福機車行","04-22433433,台中市北屯區北屯路286號,素巾晶機車行","04-22016838 ,台中市北區篤行路338號1樓,榮輪機車行","04-27022165,台中市西屯區西屯路三段33號,明風車業有限公司","04-2635-5065,台中市沙鹿區沙田路57-3號, 德豐機車行","04-2587-2987,台中市東勢區豐勢路158號,東立機車行","04-25622298,台中市神岡區中山路1155號,明興機車行"};
    //彰化縣
    private String[] g ={"點選立即撥打電話"," 04-879-0121 ,彰化縣二水鄉員集路三段654號,永川機車行","04-896-8963 ,彰化縣二林鎮二溪路一段182號,進財機車行","04-8962285,彰化縣二林鎮中正路81號,元彰機車行","04-890-3070,彰化縣二林鎮斗苑路一段1020號,輝光機車行","04-8961546,彰化縣二林鎮北平里二溪路一段93號,宏興機車行","04-824-8599,彰化縣永靖鄉永北村瑚璉路438號, 高典機車行","04-8239110,彰化縣永靖鄉永西村瑚璉路365號,進達車業有限公司","04-897-2182,彰化縣竹塘鄉竹林路1段228號,芳宜機車行","04-873-8551 彰化縣社頭鄉舊社村忠義路764號昇佳機車行","04-786-0638,彰化縣花壇鄉中山路一段398號,上合車業有限公司"};
    //雲林縣
    private String[] h ={"點選立即撥打電話","03-522-1083 ,雲林縣口湖鄉口湖路53-1號,全營機車行","05-7991289,雲林縣口湖鄉崙東村中山路3之39號,東易車業行","05-522-2109,雲林縣斗六市仁義路20號,宏茂機車行","05-557-2848 ,雲林縣斗六市石榴路107號,  祥輪機車行","05-5513550 ,雲林縣斗六市石榴路85-3號,石榴機車行","05-785-1849 ,雲林縣水林鄉宏仁路83-7號,信芳機車行"," 05-772-3903,雲林縣四湖鄉飛沙村大同路67-7號,正光機車行"," 05-5894380 ,雲林縣林內鄉林南村中正路157號,誠億機車行","05-632-9433,雲林縣虎尾鎮廉使里光復路868號,力山機車行"};
    //南投縣
    private String[] i ={"點選立即撥打電話","03-5221083,南投市中興路330-9號,全營機車行","04-92228398,南投市平和里民族路12號,順進機車行","04-92223398,南投市民族路51號,安成機車行","04-92230940,南投市彰南路2段453號,千聖機車行","04-92225825,南投市彰南路一段1026號,銓興機車行","04-92223127,南投市彰南路一段736號,美輪機車行","04-92223645,南投市漳和里彰南路二段323號,昌隆機車行","04-92291953,南投市福山里八卦路442號,寶山機車行","04-92335835,南投市營北里中正路232號,福裕機車行","04-92691150,南投縣中寮鄉永平村永安街102號,昇晉機車行","04-92692991,南投縣中寮鄉永安街162號,諭興機車行","04-92771087,南投縣水里鄉民權路200號,三盛機車有限公司","04-92772032,南投縣水里鄉頂崁村31-13號,頂昌機車行"};
    //嘉義縣
    private String[] j ={"點選立即撥打電話","03-5221083,嘉義縣大林鎮中正路428號,全營機車行","05-2653423,嘉義縣大林鎮中正路596號,德興機車行","05-1652147,嘉義縣大林鎮中興路153號,信榮機車行","05-2391968,嘉義縣中埔鄉中山路5段957號,鴻興車業行","05-2391215,嘉義縣中埔鄉中山路五段723號,阿財車業行","05-2531404,嘉義縣中埔鄉同仁村同仁161-2號,國利機車行","05-3711321,嘉義縣太保市後潭里後潭439~9號,昶旭車業有限公司","05-2373207,嘉義縣太保市麻寮里中山路二段151號,富發車行","05-2683327,嘉義縣水上鄉水上村中和路249號,吉成機車行","05-2892038,嘉義縣水上鄉忠和村檳榔樹角7-2號,龍菁機車行","05-2351465,嘉義縣水上鄉寬士村崎子頭172號,永新機車行"};
    //嘉義市
    private String[] k ={"點選立即撥打電話","03-5221083,嘉義市大雄路2段569號,全營機車行","05-2766094,嘉義市小雅路507號,全成機車行","05-2223028,嘉義市中山路400號,富達輪業有限公司","05-2249572,嘉義市仁愛路369號,捷輪機車行","05-2320779,嘉義市文化路560號,通民機車行","05-2334336,嘉義市北興街116巷7號,通興機車行","05-2323363,嘉義市北興街219號,達興機車行","05-2339779,嘉義市北鎮街71-7號,昇益機車行","05-2279496,嘉義市民生北路101號,勇士機車行","05-2225148,嘉義市民生北路231號,建福車業行","05-2350282,嘉義市西區民生南路570號,滿金機車行","05-2851482,嘉義市西區民生南路878號,德盛機車行"};
    //台南市
    private String[] l ={"點選立即撥打電話","03-5221083,台南市七股區大埕里297號,全營機車行","06-6892450,台南市下營區中山路1段127號,旺達機車行","06-6893156,台南市下營區中山路一段116號1樓,勝興機車行","06-5761324,台南市大內區大內村2-7號,省南機車行","06-2204337,台南市中西區中山路87號1樓,東昌機車有限公司","06-2229401,台南市中西區民權路三段19號,葡枝企業有限公司","06-2598025,台南市中西區協和里文賢路164號1樓,昇興機車行","06-2232552,台南市中西區府前路一段164號1樓,龍達車業行","06-2238908,台南市中西區武英街28號,黑人機車行","06-2220069,台南市中區開山路25號,志明","06-2792559,台南市仁德區中正路二段452號,新力機車行"};
    //高雄市
    private String[] m ={"點選立即撥打電話","03-5221083,高雄市三民區九如一路496號,全營機車行","07-3110705,高雄市三民區九如二路158號,晉立車業有限公司","07-3219599,高雄市三民區九如二路575號,明德車業有限公司","07-3132112,高雄市三民區九如三路2號,三順旺車業股份有限公司","07-3212935,高雄市三民區力行路39號,有泰車業行","07-3819017,高雄市三民區大裕路73號1F,晟大機車行","07-3816403,高雄市三民區大豐一路240號,冠誠機車行","07-3987737,高雄市三民區大豐二路362號,亨旺機車行","07-3810627,高雄市三民區正忠路329號,金麗安企業有限公司","07-3975650,高雄市三民區正興路164號,利泰車業有限公司"};
    //屏東市
    private String[] n ={"點選立即撥打電話","03-5221083,屏東市中山路61-7號,全營機車行","08-7373506,屏東市中正路166號,和成車業行","08-7331496,屏東市中正路215號,永祥車業行","08-7365728,屏東市中正路654-1號,振輪車業行","08-7364642,屏東市仁愛路77-15號,峰裕機車行","08-7365202,屏東市北勢里自由路462號1F,屏宏機車行","08-7237887,屏東市民生東路54-6號,佳昌機車行","08-7341568,屏東市民生路178號,盟昌機車有限公司","08-7224988,屏東市民生路37號,新光興機車行","08-7517119,屏東市永大路193號,正大機車行","08-7510738,屏東市永大路60號,新屏重型機車"};
    //宜蘭市
    private String[] o ={"點選立即撥打電話","03-5221083,宜蘭市中山路2段97號,全營機車行","03-9323302,宜蘭市中山路3段245號,南僑車業有限公司","03-9368166,宜蘭市中山路三段297號,良勝車業有限公司","03-9322739,宜蘭市中山路三段307號,善志企業有限公司","03-9322738,宜蘭市文昌路150號,新福車業","03-9324869,宜蘭市民權路二段219號,宏昌機車行","03-9383732,宜蘭市東港路23-8號,立成機車行","03-9388160,宜蘭市東港路32-35號,壯六機車行","03-9380322,宜蘭市校舍路31號,正旭機車行","03-9360211,宜蘭市泰山路155號,長興機車行","03-9367479,宜蘭市泰山路72-1號,宜慶機車業行"};
    //花蓮市
    private String[] p ={"點選立即撥打電話","03-5221083,花蓮市中山路646號,全營機車行","03-8347107,花蓮市中正路258之2號,俊達車業行","03-8326772,花蓮市中原路365號,信成機車行","03-8325555,花蓮市中華路326號,泰多車業","03-8530759,花蓮市中華路469~6號,三統車業行","03-8228606,花蓮市府前路370號,建德機車行","03-8354395,花蓮市南京街152號,慶昌機車行","03-8321980,花蓮市建國路168號1樓,逸陽車業股份有限公司"};
    //台東市
    private String[] q ={"點選立即撥打電話","03-5221083,台東市中正路260號1樓,全營機車行","08-9325423,台東市中華路1段768號,華楠機車行","08-9325202,台東市中華路3段220號,主全機車行","08-9333196,台東市中華路一段216號,翔泰車業行","08-9380511,台東市中興路6段756號1樓,東德車業行","08-9224349,台東市更生北路529號,品勝機車行","08-9222197,台東市更生北路58號1樓,特昇機車行","08-9325450,台東市更生路146號1樓,金昌機車行","08-9226728,台東市更生路593號,昶興車業商行","08-9515880,台東市建和里青海路3段380號,三聖機車行","08-9513658,台東市建興路8號,長興機車行","08-9332573,台東市傳廣路210號,宏星機車行"};
    //澎湖縣
    private String[] r ={"點選立即撥打電話","03-5221083,澎湖縣馬公市中華路119號,全營機車行","06-9273129,澎湖縣馬公市中華路49-1號,鈤立","06-9274321,澎湖縣馬公市光復里民福路91號,新保輪車行","06-9273962,澎湖縣馬公市陽明里中華路130號1樓,東泰車業行","06-9278126,澎湖縣馬公市陽明里中華路151號,光億車業行","06-9272061,澎湖縣馬公市澎湖新生路4號,保輪"};
    //金門縣
    private String[] s ={"點選立即撥打電話","03-5221083,金門縣金城鎮民族路2號,全營機車行"};
    //馬祖
    private String[] t ={"點選立即撥打電話","03-5221083,馬祖連江縣南竿鄉介壽村72號,全營機車行","08-3625657,馬祖連江縣南竿鄉復興村4號,鼎鑫機車行"};


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_help, null);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(baseRecyclerAdapter = new BaseRecyclerAdapter<Item>(Arrays.asList(Item.values()), R.layout.item_help_list,HelpFragment.this) {
            @Override
            protected void onBindViewHolder(SmartViewHolder holder, Item model, int position) {
                holder.text(R.id.name_tv,model.name());
            }
        });
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(getActivity());
        if(position == 0){
            builder.items(a);
        }else if(position == 1){
            builder.items(b);
        }else if(position == 2){
            builder.items(c);
        }else if(position == 3){
            builder.items(d);
        }else if(position == 4){
            builder.items(e);
        }else if(position == 5){
            builder.items(f);
        }else if(position == 6){
            builder.items(g);
        }else if(position == 7){
            builder.items(h);
        }else if(position == 8){
            builder.items(i);
        }else if(position == 9){
            builder.items(j);
        }else if(position == 10){
            builder.items(k);
        }else if(position == 11){
            builder.items(l);
        }else if(position == 12){
            builder.items(m);
        }else if(position == 13){
            builder.items(n);
        }else if(position == 14){
            builder.items(o);
        }else if(position == 15){
            builder.items(p);
        }else if(position == 16){
            builder.items(q);
        }else if(position == 17){
            builder.items(r);
        }else if(position == 18){
            builder.items(s);
        }else if(position == 19){
            builder.items(t);
        }
        builder.theme(Theme.LIGHT)
                .itemsGravity(GravityEnum.CENTER)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                        Log.e("name",text+"");
                        if(checkPermission()){
                            String phone_number = text.toString().split(",")[0].replaceAll("-","").replaceAll(" ","");
                            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone_number));
                            getActivity().startActivity(intent);
                        }
                    }
                }).show();
    }

    //打電話權限檢查
    private boolean checkPermission() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, 10);
            return false;
        } else {
            return true;
        }
    }

}