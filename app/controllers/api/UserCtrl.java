package controllers.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.User;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

/**
 * Created by Shane on 5/23/2015.
 */
public class UserCtrl extends Controller {
    private static Logger.ALogger userLogger = Logger.of(UserCtrl.class);
    private static ObjectMapper mapper = new ObjectMapper();

    public static Result index() {
        ArrayNode result = mapper.createArrayNode();
        List<User> users = User.find.all();

        for (User user: users) {
            result.add(Json.toJson(user));
        }
        return ok(result);
    }
}
