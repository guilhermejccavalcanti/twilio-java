/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /       
 */

package com.twilio.rest.reader.taskrouter.v1.workspace.task;

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
import com.twilio.rest.resource.taskrouter.v1.workspace.task.Reservation;

public class ReservationReader extends Reader<Reservation> {
    private final String workspaceSid;
    private final String taskSid;
    private String status;
    private String assignmentStatus;
    private String reservationStatus;

    /**
     * Construct a new ReservationReader.
     * 
     * @param workspaceSid The workspace_sid
     * @param taskSid The task_sid
     */
    public ReservationReader(final String workspaceSid, 
                             final String taskSid) {
        this.workspaceSid = workspaceSid;
        this.taskSid = taskSid;
    }

    /**
     * The status.
     * 
     * @param status The status
     * @return this
     */
    public ReservationReader byStatus(final String status) {
        this.status = status;
        return this;
    }

    /**
     * The assignment_status.
     * 
     * @param assignmentStatus The assignment_status
     * @return this
     */
    public ReservationReader byAssignmentStatus(final String assignmentStatus) {
        this.assignmentStatus = assignmentStatus;
        return this;
    }

    /**
     * The reservation_status.
     * 
     * @param reservationStatus The reservation_status
     * @return this
     */
    public ReservationReader byReservationStatus(final String reservationStatus) {
        this.reservationStatus = reservationStatus;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the read.
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Reservation ResourceSet
     */
    @Override
    public ResourceSet<Reservation> execute(final TwilioRestClient client) {
        return new ResourceSet<>(this, client, firstPage());
    }

    /**
     * Make the request to the Twilio API to perform the read.
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Reservation ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<Reservation> firstPage(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            TwilioRestClient.Domains.TASKROUTER.toString(),
            "/v1/Workspaces/" + this.workspaceSid + "/Tasks/" + this.taskSid + "/Reservations",
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
    public Page<Reservation> nextPage(final Page<Reservation> page, 
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
     * Generate a Page of Reservation Resources for a given request.
     * 
     * @param client TwilioRestClient with which to make the request
     * @param request Request to generate a page for
     * @return Page for the Request
     */
    private Page<Reservation> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("Reservation read failed: Unable to connect to server");
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
            "reservations",
            response.getContent(),
            Reservation.class,
            client.getObjectMapper()
        );
    }

    /**
     * Add the requested query string arguments to the Request.
     * 
     * @param request Request to add query string arguments to
     */
    private void addQueryParams(final Request request) {
        if (status != null) {
            request.addQueryParam("Status", status);
        }
        
        if (assignmentStatus != null) {
            request.addQueryParam("AssignmentStatus", assignmentStatus);
        }
        
        if (reservationStatus != null) {
            request.addQueryParam("ReservationStatus", reservationStatus);
        }
        
        request.addQueryParam("PageSize", Integer.toString(getPageSize()));
    }
}