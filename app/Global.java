import models.Appliance;
import models.Type;
import play.Application;
import play.GlobalSettings;

/**
 * Created by Shane on 5/23/2015.
 */
public class Global extends GlobalSettings {

    public String applianceNames[] = {
            "fan", "computer", "air conditioner", "vacuum", "heater", "television", "printer", "bulb", "deep freezer", "washing machine"
    };

    public String[][] typeNames = {
            {"table", "ceiling"},
            {"laptop", "desktop"},
            {"room", "central"},
            {"upright", "handheld"},
            {"engine block", "portable", "waterbed", "stock tank"},
            {"projection", "CRT", "LED", "LCD", "plasma"},
            {"ink jet", "laser"},
            {"CFL", "fluorescent", "halogen", "holiday", "incandescent"},
            {"regular", "energy star"},
            {"top load", "front load"}
    };

    // watt's per hour
    double[][] typeWatts = {
            {17, 30},
            {45, 120},
            {1000, 2500},
            {350, 100},
            {975, 1500, 400, 100},
            {45, 27, 22, 20, 20},
            {60, 40},
            {300, 180, 600, 1500, 1200},
            {46, 42},
            {46, 18}
    };

    @Override
    public void onStart(Application application) {
        super.onStart(application);

        initializeAppliances();
    }

    private void initializeAppliances() {
        if (Appliance.find.all().size() > 0)
            return;

        for (int i = 0; i < applianceNames.length ; i++) {
            Appliance appliance = new Appliance();
            appliance.name = applianceNames[i];
            appliance.save();

            for (int j = 0; j < typeNames[i].length; j++) {
                Type type = new Type();
                type.name = typeNames[i][j];
                type.watts = typeWatts[i][j];
                type.appliance = appliance;
                type.save();
            }
        }
    }

    @Override
    public void onStop(Application application) {
        super.onStop(application);
    }
}