/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.sync.v1.service.synclist;

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

public class SyncListItemReader extends Reader<SyncListItem> {
    private final String pathServiceSid;
    private final String pathListSid;
    private SyncListItem.QueryResultOrder order;
    private String from;
    private SyncListItem.QueryFromBoundType bounds;

    /**
     * Construct a new SyncListItemReader.
     * 
     * @param pathServiceSid The service_sid
     * @param pathListSid The list_sid
     */
    public SyncListItemReader(final String pathServiceSid, 
                              final String pathListSid) {
        this.pathServiceSid = pathServiceSid;
        this.pathListSid = pathListSid;
    }

    /**
     * The order.
     * 
     * @param order The order
     * @return this
     */
    public SyncListItemReader setOrder(final SyncListItem.QueryResultOrder order) {
        this.order = order;
        return this;
    }

    /**
     * The from.
     * 
     * @param from The from
     * @return this
     */
    public SyncListItemReader setFrom(final String from) {
        this.from = from;
        return this;
    }

    /**
     * The bounds.
     * 
     * @param bounds The bounds
     * @return this
     */
    public SyncListItemReader setBounds(final SyncListItem.QueryFromBoundType bounds) {
        this.bounds = bounds;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the read.
     * 
     * @param client TwilioRestClient with which to make the request
     * @return SyncListItem ResourceSet
     */
    @Override
    public ResourceSet<SyncListItem> read(final TwilioRestClient client) {
        return new ResourceSet<>(this, client, firstPage(client));
    }

    /**
     * Make the request to the Twilio API to perform the read.
     * 
     * @param client TwilioRestClient with which to make the request
     * @return SyncListItem ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<SyncListItem> firstPage(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            Domains.SYNC.toString(),
            "/v1/Services/" + this.pathServiceSid + "/Lists/" + this.pathListSid + "/Items",
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
     * @return SyncListItem ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<SyncListItem> getPage(final String targetUrl, final TwilioRestClient client) {
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
    public Page<SyncListItem> nextPage(final Page<SyncListItem> page, 
                                       final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getNextPageUrl(
                Domains.SYNC.toString(),
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
    public Page<SyncListItem> previousPage(final Page<SyncListItem> page, 
                                           final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getPreviousPageUrl(
                Domains.SYNC.toString(),
                client.getRegion()
            )
        );
        return pageForRequest(client, request);
    }

    /**
     * Generate a Page of SyncListItem Resources for a given request.
     * 
     * @param client TwilioRestClient with which to make the request
     * @param request Request to generate a page for
     * @return Page for the Request
     */
    private Page<SyncListItem> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("SyncListItem read failed: Unable to connect to server");
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
            "items",
            response.getContent(),
            SyncListItem.class,
            client.getObjectMapper()
        );
    }

    /**
     * Add the requested query string arguments to the Request.
     * 
     * @param request Request to add query string arguments to
     */
    private void addQueryParams(final Request request) {
        if (order != null) {
            request.addQueryParam("Order", order.toString());
        }

        if (from != null) {
            request.addQueryParam("From", from);
        }

        if (bounds != null) {
            request.addQueryParam("Bounds", bounds.toString());
        }

        if (getPageSize() != null) {
            request.addQueryParam("PageSize", Integer.toString(getPageSize()));
        }
    }
}