/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /       
 */

package com.twilio.rest.reader.taskrouter.v1.workspace;

import com.twilio.rest.exception.ApiConnectionException;
import com.twilio.rest.exception.ApiException;
import com.twilio.rest.http.HttpMethod;
import com.twilio.rest.http.Request;
import com.twilio.rest.http.Response;
import com.twilio.rest.http.TwilioRestClient;
import com.twilio.rest.reader.Reader;
import com.twilio.rest.resource.Page;
import com.twilio.rest.resource.ResourceSet;
import com.twilio.rest.resource.RestException;
import com.twilio.rest.resource.taskrouter.v1.workspace.Activity;

public class ActivityReader extends Reader<Activity> {
    private final String workspaceSid;
    private String friendlyName;
    private String available;

    /**
     * Construct a new ActivityReader.
     * 
     * @param workspaceSid The workspace_sid
     */
    public ActivityReader(final String workspaceSid) {
        this.workspaceSid = workspaceSid;
    }

    /**
     * The friendly_name.
     * 
     * @param friendlyName The friendly_name
     * @return this
     */
    public ActivityReader byFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    /**
     * The available.
     * 
     * @param available The available
     * @return this
     */
    public ActivityReader byAvailable(final String available) {
        this.available = available;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the read.
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Activity ResourceSet
     */
    @Override
    public ResourceSet<Activity> execute(final TwilioRestClient client) {
        return new ResourceSet<>(this, client, firstPage());
    }

    /**
     * Make the request to the Twilio API to perform the read.
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Activity ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<Activity> firstPage(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            TwilioRestClient.Domains.TASKROUTER.toString(),
            "/v1/Workspaces/" + this.workspaceSid + "/Activities",
            client.getRegion()
        );
        
        addQueryParams(request);
        return pageForRequest(client, request);
    }

    /**
     * Retrieve the next page from the Twilio API.
     * 
     * @param page current page
     * @param client TwilioRestClient with which to make the request
     * @return Next Page
     */
    @Override
    public Page<Activity> nextPage(final Page<Activity> page, 
                                   final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getNextPageUrl(
                TwilioRestClient.Domains.TASKROUTER.toString(),
                client.getRegion()
            )
        );
        return pageForRequest(client, request);
    }

    /**
     * Generate a Page of Activity Resources for a given request.
     * 
     * @param client TwilioRestClient with which to make the request
     * @param request Request to generate a page for
     * @return Page for the Request
     */
    private Page<Activity> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("Activity read failed: Unable to connect to server");
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
        
        return Page.fromJson(
            "activities",
            response.getContent(),
            Activity.class,
            client.getObjectMapper()
        );
    }

    /**
     * Add the requested query string arguments to the Request.
     * 
     * @param request Request to add query string arguments to
     */
    private void addQueryParams(final Request request) {
        if (friendlyName != null) {
            request.addQueryParam("FriendlyName", friendlyName);
        }
        
        if (available != null) {
            request.addQueryParam("Available", available);
        }
        
        request.addQueryParam("PageSize", Integer.toString(getPageSize()));
    }
}