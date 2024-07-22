package com.ingsha.resource;


import com.ingsha.entity.IdParam;
import com.ingsha.entity.User;
import com.ingsha.service.UserService;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author Martin Yi
 * @Date 2024/4/1 21:12
 * @Version 1.0
 */
@Path("/sql")
public class SysController {

    @Inject
    UserService userService;

    @POST
    @Path("/insert")
    public Uni<String> insert(User user) {
        return userService.insert(user);
    }

    @POST
    @Path("/update")
    public Uni<String> update(User user) {
        return userService.update(user);
    }

    @POST
    @Path("/delete")
    public Uni<String> delete(IdParam idParam) {
        return userService.delete(idParam);
    }

    @POST
    @Path("/select")
    public Uni<User> select(IdParam idParam) {
        return userService.select(idParam);
    }


}
