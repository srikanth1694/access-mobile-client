/*
 *  This file is part of the IOTA Access distribution
 *  (https://github.com/iotaledger/access)
 *
 *  Copyright (c) 2020 IOTA Stiftung.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.iota.access.api.model;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.iota.access.models.RegisterUserModel;
import org.json.JSONException;
import org.json.JSONObject;

public class CommunicationMessage {

    public final static String CMD_ELEMENT = "cmd";
    public final static String USER_ID_ELEMENT = "user_id";
    public final static String RESOLVE = "resolve";
    public final static String GET_POLICY_LIST = "get_policy_list";
    public final static String SET_DATA_SET = "set_data_set";
    public final static String POLICY_ID_ELEMENT = "policy_id";
    public final static String ENABLE_POLICY = "enable_policy";
    public final static String GET_DATA_SET = "get_data_set";
    public final static String USERNAME = "username";
    public final static String GET_AUTH_USER_ID = "get_auth_user_id";
    public final static String GET_USER = "get_user";
    public final static String REGISTER_USER = "register_user";
    public final static String USER = "user";
    public final static String GET_ALL_USERS = "get_all_users";
    public final static String CLEAR_ALL_USERS = "clear_all_users";

    public static String makePolicyListRequest(String userId) {
        JSONObject json = new JSONObject();
        try {
            json.put(CMD_ELEMENT, GET_POLICY_LIST);
            json.put(USER_ID_ELEMENT, userId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json.toString();
    }

    public static String makeResolvePolicyRequest(String policyId) {
        JSONObject json = new JSONObject();
        try {
            json.put(CMD_ELEMENT, RESOLVE);
            json.put(POLICY_ID_ELEMENT, policyId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json.toString();
    }

    public static String makeEnablePolicyRequest(String policyId) {
        JSONObject json = new JSONObject();
        try {
            json.put(CMD_ELEMENT, ENABLE_POLICY);
            json.put(POLICY_ID_ELEMENT, policyId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json.toString();
    }

    public static String makeGetAuthenteqUserIdRequest(@NonNull String username) {
        JSONObject json = new JSONObject();
        try {
            json.put(CMD_ELEMENT, GET_AUTH_USER_ID);
            json.put(USERNAME, username);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json.toString();
    }

    public static String makeRegisterRequest(@NonNull RegisterUserModel registerUserModel, Gson gson) {
        JsonObject json = new JsonObject();
        JsonElement obj = gson.toJsonTree(registerUserModel);
        json.addProperty(CMD_ELEMENT, REGISTER_USER);
        json.add(USER, obj);
        return json.toString();
    }

    public static String makeClearAllUsersRequest() {
        JsonObject json = new JsonObject();
        json.addProperty(CMD_ELEMENT, CLEAR_ALL_USERS);
        return json.toString();
    }

    public static String getCmdFromMessage(String message) {
        try {
            JSONObject jsonObject = new JSONObject(message);
            if (jsonObject.has(CommunicationMessage.CMD_ELEMENT)) {
                return jsonObject.getString(CommunicationMessage.CMD_ELEMENT);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
