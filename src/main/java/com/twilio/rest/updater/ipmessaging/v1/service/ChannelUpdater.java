/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /       
 */

package com.twilio.rest.updater.ipmessaging.v1.service;

import com.twilio.rest.converter.Converter;
import com.twilio.rest.exception.ApiConnectionException;
import com.twilio.rest.exception.ApiException;
import com.twilio.rest.http.HttpMethod;
import com.twilio.rest.http.Request;
import com.twilio.rest.http.Response;
import com.twilio.rest.http.TwilioRestClient;
import com.twilio.rest.resource.RestException;
import com.twilio.rest.resource.ipmessaging.v1.service.Channel;
import com.twilio.rest.updater.Updater;

import java.util.Map;

public class ChannelUpdater extends Updater<Channel> {
    private final String serviceSid;
    private final String sid;
    private String friendlyName;
    private String uniqueName;
    private Map<String, Object> attributes;
    private Channel.ChannelType type;

    /**
     * Construct a new ChannelUpdater.
     * 
     * @param serviceSid The service_sid
     * @param sid The sid
     */
    public ChannelUpdater(final String serviceSid, 
                          final String sid) {
        this.serviceSid = serviceSid;
        this.sid = sid;
    }

    /**
     * The friendly_name.
     * 
     * @param friendlyName The friendly_name
     * @return this
     */
    public ChannelUpdater setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    /**
     * The unique_name.
     * 
     * @param uniqueName The unique_name
     * @return this
     */
    public ChannelUpdater setUniqueName(final String uniqueName) {
        this.uniqueName = uniqueName;
        return this;
    }

    /**
     * The attributes.
     * 
     * @param attributes The attributes
     * @return this
     */
    public ChannelUpdater setAttributes(final Map<String, Object> attributes) {
        this.attributes = attributes;
        return this;
    }

    /**
     * The type.
     * 
     * @param type The type
     * @return this
     */
    public ChannelUpdater setType(final Channel.ChannelType type) {
        this.type = type;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the update.
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Updated Channel
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Channel execute(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            TwilioRestClient.Domains.IPMESSAGING.toString(),
            "/v1/Services/" + this.serviceSid + "/Channels/" + this.sid + "",
            client.getRegion()
        );
        
        addPostParams(request);
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("Channel update failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.apply(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
        
            throw new ApiException(
                restException.getMessage(),
                restException.getCode(),
                restException.getMoreInfo(),
                restException.getStatus(),
                null
            );
        }
        
        return Channel.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     * 
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
        }
        
        if (uniqueName != null) {
            request.addPostParam("UniqueName", uniqueName);
        }
        
        if (attributes != null) {
            request.addPostParam("Attributes", Converter.mapToJson(attributes));
        }
        
        if (type != null) {
            request.addPostParam("Type", type.toString());
        }
    }
}