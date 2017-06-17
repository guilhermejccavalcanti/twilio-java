/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.api.v2010.account;

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

public class ShortCodeReader extends Reader<ShortCode> {
    private String pathAccountSid;
    private String friendlyName;
    private String shortCode;

    /**
     * Construct a new ShortCodeReader.
     */
    public ShortCodeReader() {
    }

    /**
     * Construct a new ShortCodeReader.
     * 
     * @param pathAccountSid The account_sid
     */
    public ShortCodeReader(final String pathAccountSid) {
        this.pathAccountSid = pathAccountSid;
    }

    /**
     * Only show the ShortCode resources with friendly names that exactly match this
     * name.
     * 
     * @param friendlyName Filter by friendly name
     * @return this
     */
    public ShortCodeReader setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    /**
     * Only show the ShortCode resources that match this pattern. You can specify
     * partial numbers and use '*' as a wildcard for any digit.
     * 
     * @param shortCode Filter by ShortCode
     * @return this
     */
    public ShortCodeReader setShortCode(final String shortCode) {
        this.shortCode = shortCode;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the read.
     * 
     * @param client TwilioRestClient with which to make the request
     * @return ShortCode ResourceSet
     */
    @Override
    public ResourceSet<ShortCode> read(final TwilioRestClient client) {
        return new ResourceSet<>(this, client, firstPage(client));
    }

    /**
     * Make the request to the Twilio API to perform the read.
     * 
     * @param client TwilioRestClient with which to make the request
     * @return ShortCode ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<ShortCode> firstPage(final TwilioRestClient client) {
        this.pathAccountSid = this.pathAccountSid == null ? client.getAccountSid() : this.pathAccountSid;
        Request request = new Request(
            HttpMethod.GET,
            Domains.API.toString(),
            "/2010-04-01/Accounts/" + this.pathAccountSid + "/SMS/ShortCodes.json",
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
     * @return ShortCode ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<ShortCode> getPage(final String targetUrl, final TwilioRestClient client) {
        this.pathAccountSid = this.pathAccountSid == null ? client.getAccountSid() : this.pathAccountSid;
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
    public Page<ShortCode> nextPage(final Page<ShortCode> page, 
                                    final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getNextPageUrl(
                Domains.API.toString(),
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
    public Page<ShortCode> previousPage(final Page<ShortCode> page, 
                                        final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getPreviousPageUrl(
                Domains.API.toString(),
                client.getRegion()
            )
        );
        return pageForRequest(client, request);
    }

    /**
     * Generate a Page of ShortCode Resources for a given request.
     * 
     * @param client TwilioRestClient with which to make the request
     * @param request Request to generate a page for
     * @return Page for the Request
     */
    private Page<ShortCode> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("ShortCode read failed: Unable to connect to server");
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
            "short_codes",
            response.getContent(),
            ShortCode.class,
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

        if (shortCode != null) {
            request.addQueryParam("ShortCode", shortCode);
        }

        if (getPageSize() != null) {
            request.addQueryParam("PageSize", Integer.toString(getPageSize()));
        }
    }
}