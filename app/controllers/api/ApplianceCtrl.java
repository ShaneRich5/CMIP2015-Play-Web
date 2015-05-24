package controllers.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Appliance;
import models.Type;
import play.Logger;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

/**
 * Created by Shane on 5/23/2015.
 */
public class ApplianceCtrl extends Controller {
    private static Logger.ALogger applianceLog = Logger.of(ApplianceCtrl.class);
    private static ObjectMapper mapper = new ObjectMapper();

    public static Result index() {
        ObjectNode result = mapper.createObjectNode();
        List<Appliance> applianceList = Appliance.find.all();

        if (applianceList.size() > 0) {
            ArrayNode applianceArrayNode = mapper.createArrayNode();
            for (Appliance appliance : applianceList) {
                applianceArrayNode.add(Json.toJson(appliance));
            }
        } else
            result.put("message", "No data");
        return ok(result);
    }


    public static Result show(String name) {
        ObjectNode result = mapper.createObjectNode();
        Appliance appliance = Appliance.find.where().eq("name", name).findUnique();

        if (appliance == null)
            result.put("message", "Appliance not found");
        else {
            ArrayNode typesArrayNode = mapper.createArrayNode();
            List<Type> types = Type.find.where().eq("appliance_id", appliance.id).findList();

            for (Type type: types)
                typesArrayNode.add(Json.toJson(type));

            result = (ObjectNode) Json.toJson(appliance);
            result.put("types", typesArrayNode);
        }
        return ok(result);
    }

    @BodyParser.Of(BodyParser.Json.class)
    public static Result store() {
        ObjectNode result = mapper.createObjectNode();
        result.put("ok", "hello");
        JsonNode json = request().body().asJson();
        return ok(result);
    }
}
