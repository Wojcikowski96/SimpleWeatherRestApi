package com.example.task.filter;

import com.example.task.clients.model.RequestFlowData;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;

@Component
public class CorrelationHeaderFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        final HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String currentCorrId = httpServletRequest.getHeader(RequestFlowData.correlationidkey);
        LOGGER.info("ngłówki requesta: " + httpServletRequest.getHeaderNames());

        if (!currentRequestIsAsyncDispatcher(httpServletRequest)) {
            if (currentCorrId == null) {
                currentCorrId = UUID.randomUUID().toString();
                LOGGER.info("No correlationId found in Header. Generated : " + currentCorrId);
            } else {
                LOGGER.info("Found correlationId in Header : " + currentCorrId);
            }

            RequestFlowData.setId(currentCorrId);
        }

        filterChain.doFilter(httpServletRequest, servletResponse);

    }
    private boolean currentRequestIsAsyncDispatcher(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getDispatcherType().equals(DispatcherType.ASYNC);}
}
