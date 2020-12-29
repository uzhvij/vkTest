package com.blogspot.uzhvij.vktest.requests;

import com.blogspot.uzhvij.vktest.models.VkUser;
import com.vk.api.sdk.requests.VKRequest;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class VkFriendsRequest extends VKRequest<List<VkUser>>{

    public VkFriendsRequest() {
        super("friends.get", null);
        addParam("fields", "photo_200");
    }

    @Override
    public List<VkUser> parse(@NotNull JSONObject r) throws Exception {
        JSONArray users = r.getJSONObject("response").getJSONArray("items");
        List<VkUser> result = new ArrayList<>();
        for (int i = 0; i < users.length(); i++) {
            result.add(VkUser.parse(users.getJSONObject(i)));
        }
        return result;
    }
}