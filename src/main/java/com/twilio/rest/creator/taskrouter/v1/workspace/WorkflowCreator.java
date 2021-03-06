/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /       
 */

package com.twilio.rest.creator.taskrouter.v1.workspace;

import com.twilio.rest.converter.Promoter;
import com.twilio.rest.creator.Creator;
import com.twilio.rest.exception.ApiConnectionException;
import com.twilio.rest.exception.ApiException;
import com.twilio.rest.http.HttpMethod;
import com.twilio.rest.http.Request;
import com.twilio.rest.http.Response;
import com.twilio.rest.http.TwilioRestClient;
import com.twilio.rest.resource.RestException;
import com.twilio.rest.resource.taskrouter.v1.workspace.Workflow;

import java.net.URI;

public class WorkflowCreator extends Creator<Workflow> {
    private final String workspaceSid;
    private final String friendlyName;
    private final String configuration;
    private final URI assignmentCallbackUrl;
    private URI fallbackAssignmentCallbackUrl;
    private Integer taskReservationTimeout;

    /**
     * Construct a new WorkflowCreator.
     * 
     * @param workspaceSid The workspace_sid
     * @param friendlyName The friendly_name
     * @param configuration The configuration
     * @param assignmentCallbackUrl The assignment_callback_url
     */
    public WorkflowCreator(final String workspaceSid, 
                           final String friendlyName, 
                           final String configuration, 
                           final URI assignmentCallbackUrl) {
        this.workspaceSid = workspaceSid;
        this.friendlyName = friendlyName;
        this.configuration = configuration;
        this.assignmentCallbackUrl = assignmentCallbackUrl;
    }

    /**
     * The fallback_assignment_callback_url.
     * 
     * @param fallbackAssignmentCallbackUrl The fallback_assignment_callback_url
     * @return this
     */
    public WorkflowCreator setFallbackAssignmentCallbackUrl(final URI fallbackAssignmentCallbackUrl) {
        this.fallbackAssignmentCallbackUrl = fallbackAssignmentCallbackUrl;
        return this;
    }

    /**
     * The fallback_assignment_callback_url.
     * 
     * @param fallbackAssignmentCallbackUrl The fallback_assignment_callback_url
     * @return this
     */
    public WorkflowCreator setFallbackAssignmentCallbackUrl(final String fallbackAssignmentCallbackUrl) {
        return setFallbackAssignmentCallbackUrl(Promoter.uriFromString(fallbackAssignmentCallbackUrl));
    }

    /**
     * The task_reservation_timeout.
     * 
     * @param taskReservationTimeout The task_reservation_timeout
     * @return this
     */
    public WorkflowCreator setTaskReservationTimeout(final Integer taskReservationTimeout) {
        this.taskReservationTimeout = taskReservationTimeout;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the create.
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Created Workflow
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Workflow execute(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            TwilioRestClient.Domains.TASKROUTER.toString(),
            "/v1/Workspaces/" + this.workspaceSid + "/Workflows",
            client.getRegion()
        );
        
        addPostParams(request);
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("Workflow creation failed: Unable to connect to server");
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
        
        return Workflow.fromJson(response.getStream(), client.getObjectMapper());
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
        
        if (configuration != null) {
            request.addPostParam("Configuration", configuration);
        }
        
        if (assignmentCallbackUrl != null) {
            request.addPostParam("AssignmentCallbackUrl", assignmentCallbackUrl.toString());
        }
        
        if (fallbackAssignmentCallbackUrl != null) {
            request.addPostParam("FallbackAssignmentCallbackUrl", fallbackAssignmentCallbackUrl.toString());
        }
        
        if (taskReservationTimeout != null) {
            request.addPostParam("TaskReservationTimeout", taskReservationTimeout.toString());
        }
    }
}