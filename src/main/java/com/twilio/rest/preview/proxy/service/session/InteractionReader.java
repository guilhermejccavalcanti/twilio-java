/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.preview.proxy.service.session;

import com.twilio.base.Page;
import com.twilio.base.Reader;
import com.twilio.base.ResourceSet;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

public class InteractionReader extends Reader<Interaction> {
    private final String pathServiceSid;
    private final String pathSessionSid;
    private Interaction.ResourceStatus inboundParticipantStatus;
    private Interaction.ResourceStatus outboundParticipantStatus;

    /**
     * Construct a new InteractionReader.
     * 
     * @param pathServiceSid Service Sid.
     * @param pathSessionSid Session Sid.
     */
    public InteractionReader(final String pathServiceSid, 
                             final String pathSessionSid) {
        this.pathServiceSid = pathServiceSid;
        this.pathSessionSid = pathSessionSid;
    }

    /**
     * The Inbound Participant Status of this Interaction. One of `completed`,
     * `in-progress` or `failed`..
     * 
     * @param inboundParticipantStatus The Inbound Participant Status of this
     *                                 Interaction
     * @return this
     */
    public InteractionReader setInboundParticipantStatus(final Interaction.ResourceStatus inboundParticipantStatus) {
        this.inboundParticipantStatus = inboundParticipantStatus;
        return this;
    }

    /**
     * The Outbound Participant Status of this Interaction. One of `completed`,
     * `in-progress` or `failed`..
     * 
     * @param outboundParticipantStatus The Outbound Participant Status of this
     *                                  Interaction
     * @return this
     */
    public InteractionReader setOutboundParticipantStatus(final Interaction.ResourceStatus outboundParticipantStatus) {
        this.outboundParticipantStatus = outboundParticipantStatus;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the read.
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Interaction ResourceSet
     */
    @Override
    public ResourceSet<Interaction> read(final TwilioRestClient client) {
        return new ResourceSet<>(this, client, firstPage(client));
    }

    /**
     * Make the request to the Twilio API to perform the read.
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Interaction ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<Interaction> firstPage(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            Domains.PREVIEW.toString(),
            "/Proxy/Services/" + this.pathServiceSid + "/Sessions/" + this.pathSessionSid + "/Interactions",
            client.getRegion()
        );

        addQueryParams(request);
        return pageForRequest(client, request);
    }

    /**
     * Retrieve the target page from the Twilio API.
     * 
     * @param targetUrl API-generated URL for the requested results page
     * @param client TwilioRestClient with which to make the request
     * @return Interaction ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<Interaction> getPage(final String targetUrl, final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            targetUrl
        );

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
    public Page<Interaction> nextPage(final Page<Interaction> page, 
                                      final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getNextPageUrl(
                Domains.PREVIEW.toString(),
                client.getRegion()
            )
        );
        return pageForRequest(client, request);
    }

    /**
     * Retrieve the previous page from the Twilio API.
     * 
     * @param page current page
     * @param client TwilioRestClient with which to make the request
     * @return Previous Page
     */
    @Override
    public Page<Interaction> previousPage(final Page<Interaction> page, 
                                          final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getPreviousPageUrl(
                Domains.PREVIEW.toString(),
                client.getRegion()
            )
        );
        return pageForRequest(client, request);
    }

    /**
     * Generate a Page of Interaction Resources for a given request.
     * 
     * @param client TwilioRestClient with which to make the request
     * @param request Request to generate a page for
     * @return Page for the Request
     */
    private Page<Interaction> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Interaction read failed: Unable to connect to server");
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
            "interactions",
            response.getContent(),
            Interaction.class,
            client.getObjectMapper()
        );
    }

    /**
     * Add the requested query string arguments to the Request.
     * 
     * @param request Request to add query string arguments to
     */
    private void addQueryParams(final Request request) {
        if (inboundParticipantStatus != null) {
            request.addQueryParam("InboundParticipantStatus", inboundParticipantStatus.toString());
        }

        if (outboundParticipantStatus != null) {
            request.addQueryParam("OutboundParticipantStatus", outboundParticipantStatus.toString());
        }

        if (getPageSize() != null) {
            request.addQueryParam("PageSize", Integer.toString(getPageSize()));
        }
    }
}