package com.cyut.motor.Structure;

/**
 * Created by b4791 on 2017/11/28.
 */

public class MaintainStructure {
    public String day;
    public String Oil_gearoil;
    public String Oil_gearoil_label;
    public int Oil_gearoil_price;
    public String Oil_gearoil_trip;
    public String Oil_gasoline;
    public String Oil_gasoline_label;
    public int Oil_gasoline_price;
    public String Oil_gasoline_trip;
    public String Oil_oil;
    public String Oil_oil_label;
    public int Oil_oil_price;
    public String Oil_oil_trip;
    public String Oil_oilrefined;
    public String Oil_oilrefined_label;
    public int Oil_oilrefined_price;
    public String Oil_oilrefined_trip;
    public String Supplies_belt;
    public String Supplies_belt_label;
    public int Supplies_belt_price;
    public String Supplies_belt_trip;
    public String Supplies_shell;
    public String Supplies_shell_label;
    public int Supplies_shell_price;
    public String Supplies_shell_trip;
    public String Supplies_drive;
    public String Supplies_drive_label;
    public int Supplies_drive_price;
    public String Supplies_drive_trip;
    public String Supplies_Plug;
    public String Supplies_Plug_label;
    public int Supplies_Plug_price;
    public String Supplies_Plug_trip;
    public String Supplies_tire;
    public String Supplies_tire_label;
    public int Supplies_tire_price;
    public String Supplies_tire_trip;
    public String Supplies_filter;
    public String Supplies_filter_label;
    public int Supplies_filter_price;
    public String Supplies_filter_trip;
    public String Supplies_brake;
    public String Supplies_brake_label;
    public int Supplies_brake_price;
    public String Supplies_brake_trip;
    public String Supplies_injection;
    public String Supplies_injection_label;
    public int Supplies_injection_price;
    public String Supplies_injection_trip;
    public String Supplies_light;
    public String Supplies_light_label;
    public int Supplies_light_price;
    public String Supplies_light_trip;
    public String Supplies_skin;
    public String Supplies_skin_label;
    public int Supplies_skin_price;
    public String Supplies_skin_trip;
    public String user_id;


    public MaintainStructure() {
        // Default constructor required for calls to DataSnapshot.getValue(MapStructure.class)
    }

    public MaintainStructure(String day, String Oil_gearoil_label, int Oil_gearoil_price,String Oil_gearoil_trip,String Oil_gearoil
            ,String Oil_gasoline_label, int Oil_gasoline_price,String Oil_gasoline_trip,String Oil_gasoline
            ,String Oil_oil_label, int Oil_oil_price,String Oil_oil_trip,String Oil_oil
            ,String Oil_oilrefined_label, int Oil_oilrefined_price,String Oil_oilrefined_trip,String Oil_oilrefined
            ,String Supplies_belt_label, int Supplies_belt_price,String Supplies_belt_trip,String Supplies_belt
            ,String Supplies_shell_label, int Supplies_shell_price,String Supplies_shell_trip,String Supplies_shell
            ,String Supplies_drive_label, int Supplies_drive_price,String Supplies_drive_trip,String Supplies_drive
            ,String Supplies_Plug_label, int Supplies_Plug_price,String Supplies_Plug_trip,String Supplies_Plug
            ,String Supplies_tire_label, int Supplies_tire_price,String Supplies_tire_trip,String Supplies_tire
            ,String Supplies_filter_label, int Supplies_filter_price,String Supplies_filter_trip,String Supplies_filter
            ,String Supplies_brake_label, int Supplies_brake_price,String Supplies_brake_trip,String Supplies_brake
            ,String Supplies_injection_label, int Supplies_injection_price,String Supplies_injection_trip,String Supplies_injection
            ,String Supplies_light_label, int Supplies_light_price,String Supplies_light_trip,String Supplies_light
            ,String Supplies_skin_label, int Supplies_skin_price,String Supplies_skin_trip,String Supplies_skin,String user_id
    ) {
        this.day = day;
        this.Oil_gearoil = Oil_gearoil;
        this.Oil_gearoil_label = Oil_gearoil_label;
        this.Oil_gearoil_price = Oil_gearoil_price;
        this.Oil_gearoil_trip= Oil_gearoil_trip;
        this.Oil_gasoline = Oil_gasoline;
        this.Oil_gasoline_label = Oil_gasoline_label;
        this.Oil_gasoline_price = Oil_gasoline_price;
        this.Oil_gasoline_trip= Oil_gasoline_trip;
        this.Oil_oil = Oil_oil;
        this.Oil_oil_label = Oil_oil_label;
        this.Oil_oil_price = Oil_oil_price;
        this.Oil_oil_trip= Oil_oil_trip;
        this.Oil_oilrefined = Oil_oilrefined;
        this.Oil_oilrefined_label = Oil_oilrefined_label;
        this.Oil_oilrefined_price = Oil_oilrefined_price;
        this.Oil_oilrefined_trip= Oil_oilrefined_trip;
        this.Supplies_belt = Supplies_belt;
        this.Supplies_belt_label = Supplies_belt_label;
        this.Supplies_belt_price = Supplies_belt_price;
        this.Supplies_belt_trip= Supplies_belt_trip;
        this.Supplies_shell = Supplies_shell;
        this.Supplies_shell_label = Supplies_shell_label;
        this.Supplies_shell_price = Supplies_shell_price;
        this.Supplies_shell_trip= Supplies_shell_trip;
        this.Supplies_drive = Supplies_drive;
        this.Supplies_drive_label = Supplies_drive_label;
        this.Supplies_drive_price = Supplies_drive_price;
        this.Supplies_drive_trip= Supplies_drive_trip;
        this.Supplies_Plug = Supplies_Plug;
        this.Supplies_Plug_label = Supplies_Plug_label;
        this.Supplies_Plug_price = Supplies_Plug_price;
        this.Supplies_Plug_trip= Supplies_Plug_trip;
        this.Supplies_tire = Supplies_tire;
        this.Supplies_tire_label = Supplies_tire_label;
        this.Supplies_tire_price = Supplies_tire_price;
        this.Supplies_tire_trip= Supplies_tire_trip;
        this.Supplies_filter = Supplies_filter;
        this.Supplies_filter_label = Supplies_filter_label;
        this.Supplies_filter_price = Supplies_filter_price;
        this.Supplies_filter_trip= Supplies_filter_trip;
        this.Supplies_brake = Supplies_brake;
        this.Supplies_brake_label = Supplies_brake_label;
        this.Supplies_brake_price = Supplies_brake_price;
        this.Supplies_brake_trip= Supplies_brake_trip;
        this.Supplies_injection = Supplies_injection;
        this.Supplies_injection_label = Supplies_injection_label;
        this.Supplies_injection_price = Supplies_injection_price;
        this.Supplies_injection_trip= Supplies_injection_trip;
        this.Supplies_light = Supplies_light;
        this.Supplies_light_label = Supplies_light_label;
        this.Supplies_light_price = Supplies_light_price;
        this.Supplies_light_trip= Supplies_light_trip;
        this.Supplies_skin = Supplies_skin;
        this.Supplies_skin_label = Supplies_skin_label;
        this.Supplies_skin_price = Supplies_skin_price;
        this.Supplies_skin_trip= Supplies_skin_trip;
        this.user_id= user_id;




    }
}
